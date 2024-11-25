package puppy.code;

import java.util.ArrayList;

import com.badlogic.gdx.math.MathUtils;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class BlockBreakerGame extends ApplicationAdapter {
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private ShapeRenderer shape;
	private PingBall ball;
	private Paddle pad;
	protected ArrayList<BlockAbstract> blocks = new ArrayList<>();
	private int vidas;
	private int puntaje;
	private int ronda;
	private BlockHP test;
	private BlockDoublePoints test2;
	private BlockFactory factory;
   
		@Override
		public void create () {	
			camera = new OrthographicCamera();
		    camera.setToOrtho(false, 800, 480);
		    batch = new SpriteBatch();
		    font = new BitmapFont();
		    font.getData().setScale(3, 2);
		    ronda = 1;
		    crearBloques();
			
		    shape = new ShapeRenderer();
		    pad = Paddle.getPaddle(Gdx.graphics.getWidth()/2-50,40,100,10);
		    ball = PingBall.getPingBall(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10, 5, 7, true);
		    vidas = 3;
		    puntaje = 0;    
		}
		public void crearBloques() {
			blocks.clear();
			int filas = 3;
			if (ronda == 1 || ronda == 2)
			{
				factory = new BlockFactoryFacil();
			}
			else if (ronda == 3 || ronda == 4)
			{
				filas++;
				factory = new BlockFactoryMedio();
			}
			else
			{
				filas+= 2;
				factory = new BlockFactoryDificil();
			}
			
			int blockWidth = 70;
			int blockHeight = 26;
		    int y = Gdx.graphics.getHeight();
		    
			for (int i = 0; i < filas; i++)
			{
				y -= blockHeight+10;
				for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth+10)
				{
					int prob = MathUtils.random(1, 100);
					if (prob <= 20)
					{
						blocks.add(factory.crearBlockHP(x, y, blockWidth, blockHeight, ronda));
					}
					else if (prob > 20 && prob <= 25)
					{
						blocks.add(factory.crearBlockDoublePoints(x, y, blockWidth, blockHeight, ronda));
					}
					else
					{
						blocks.add(factory.crearBlockDefault(x, y, blockWidth, blockHeight, ronda));
					}
				}
				
			}
			factory = null;
		    test = new BlockHP(200, 0, 70, 26, 1); // Decoy
		    test2 = new BlockDoublePoints(400, 0, 70, 26, 1);
		    
		}
		public void dibujaTextos() {
			//actualizar matrices de la cámara
			camera.update();
			//actualizar 
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			//dibujar textos
			font.draw(batch, "Puntos: " + puntaje, 10, 25);
			font.draw(batch, "Vidas : " + vidas, Gdx.graphics.getWidth()-20, 25);
			font.draw(batch, "HP+", 255, 25); // Texto sobre el decoy, tambien sirve para indicar que hace el bloque asignado.
			font.draw(batch, "x2", 520, 25);
			batch.end();
		}	
		

		@Override
		public void render () {
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 		
	        shape.begin(ShapeRenderer.ShapeType.Filled);
	        pad.draw(shape);
	        // monitorear inicio del juego
	        if (ball.estaQuieto()) {
	        	ball.setXY(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11);
	        	if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) ball.setEstaQuieto(false);
	        }else ball.update();
	        //verificar si se fue la bola x abajo
	        if (ball.getY()<0) {
	        	vidas--;
	        	ball= PingBall.getPingBall(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10, 5, 7, true);
	        }
	        // verificar game over
	        if (vidas<=0) {
	        	vidas = 3;
	        	ronda = 1;
	        	puntaje = 0;
	        	crearBloques();      	
	        }
	        // verificar si el nivel se terminó
	        if (blocks.size()==0) {
	        	ronda++;
	        	ball = PingBall.getPingBall(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10, 5, 7, true);
	        	crearBloques();
	        	
	        }    	
	        //dibujar bloques
	        for (BlockAbstract b : blocks) {        	
	            b.draw(shape);
	            ball.checkCollision(b, shape, this);
	        }
	        test.draw(shape); // Se crea "decoy" de un objeto de tipo BlockHP
	        test2.draw(shape);
	        
	        // actualizar estado de los bloques 
	        for (int i = 0; i < blocks.size(); i++) {
	            BlockAbstract b = blocks.get(i);
	            
	            if (b.getDestroyed()) {
	            	b.cuandoGolpe(shape, this);
	            	blocks.remove(b);
	                i--; //para no saltarse 1 tras eliminar del arraylist
	            }
	        }
	        
	        ball.checkCollision(pad);
	        ball.draw(shape);
	        shape.end();
	        dibujaTextos();
		}
		
		@Override
		public void dispose () {

		}
		public void setVidas(int vidas)
		{
			this.vidas = vidas;
		}
		public int getVidas()
		{
			return vidas;
		}
		
		public int getPuntaje()
		{
			return puntaje;
		}
		public void setPuntaje(int puntaje)
		{
			this.puntaje = puntaje;
		}
		
		public int getRonda()
		{
			return ronda;
		}
	}
