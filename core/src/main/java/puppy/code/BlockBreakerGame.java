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
	private ArrayList<BlockAbstract> blocks = new ArrayList<>();
	private int vidas;
	private int puntaje;
	private int nivel;
	private BlockHP test;
    
		@Override
		public void create () {	
			camera = new OrthographicCamera();
		    camera.setToOrtho(false, 800, 480);
		    batch = new SpriteBatch();
		    font = new BitmapFont();
		    font.getData().setScale(3, 2);
		    nivel = 1;
		    crearBloques(2+nivel);
			
		    shape = new ShapeRenderer();
		    ball = new PingBall(Gdx.graphics.getWidth()/2-10, 41, 10, 5, 7, true);
		    pad = new Paddle(Gdx.graphics.getWidth()/2-50,40,100,10);
		    vidas = 3;
		    puntaje = 0;    
		}
		public void crearBloques(int filas) {
			blocks.clear();
			int blockWidth = 70;
		    int blockHeight = 26;
		    int y = Gdx.graphics.getHeight();
		    for (int cont = 0; cont<filas; cont++ ) {
		    	y -= blockHeight+10;
		    	for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
		    		if (MathUtils.random(1,100) <= 10) // Se determina la probabilidad del tipo de bloque.
		    		{
		    			blocks.add(new BlockHP(x, y, blockWidth, blockHeight)); // Bloque de vida
		    		}
		    		else
		    		{
		    			blocks.add(new BlockDefault(x, y, blockWidth, blockHeight)); // Bloque Default
		    		}
		        }
		    }
		    test = new BlockHP(200, 0, 70, 26); // Decoy
		    
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
	        	//nivel = 1;
	        	ball = new PingBall(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10, 5, 7, true);
	        }
	        // verificar game over
	        if (vidas<=0) {
	        	vidas = 3;
	        	nivel = 1;
	        	crearBloques(2+nivel);
	        	//ball = new PingBall(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10, 5, 7, true);	        	
	        }
	        // verificar si el nivel se terminó
	        if (blocks.size()==0) {
	        	nivel++;
	        	crearBloques(2+nivel);
	        	ball = new PingBall(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11, 10, 5, 7, true);
	        }    	
	        //dibujar bloques
	        for (BlockAbstract b : blocks) {        	
	            b.draw(shape);
	            ball.checkCollision(b);
	        }
	        test.draw(shape); // Se crea "decoy" de un objeto de tipo BlockHP
	        // Desde este se llama al drop, por el momento.
	        // actualizar estado de los bloques 
	        for (int i = 0; i < blocks.size(); i++) {
	            BlockAbstract b = blocks.get(i);
	            
	            if (b instanceof BlockHP && b.getDestroyed()) // Si el bloque destruido es de tipo BlockHP
	            {
	            	test.Drop(shape, b.getX(), b.getY(), b.getWidth(), b.getHeight(), this);
	            }
	            	
	            if (b.getDestroyed()) {
	            	puntaje++;
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
	}
