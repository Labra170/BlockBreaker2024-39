package puppy.code;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

// Clase abstracta, usada como base para el resto de bloques.
// Por el momento solo utilizada por BlockDefault y BlockHP
public abstract class BlockAbstract
{
	    private int x,y,width,height, vida;
	    private boolean destroyed;
	    
	    // Constructor base para los bloques.
	    // A pesar de ser una clase abstracta, el constructor existe para simplificar la construccion de sus clases concretas.
	    public BlockAbstract(int x, int y, int width, int height, int vida) {
	        this.x = x;
	        this.y = y;
	        this.width = width;
	        this.height = height;
	        this.vida = vida;
	        destroyed = false;
	    }
	    
	    public abstract void drop(ShapeRenderer shape, BlockBreakerGame aa);
	    
	    // Aplicacion Template Method
	    public final void cuandoGolpe(ShapeRenderer shape, BlockBreakerGame aa)
	    {
	    	vida --;
	    	System.out.println("Vida reducida a: " + vida); //BORRAR
	    	
	    	if (vida == 0)
	    	{
	    		drop(shape, aa);
	    		setDestroyed(true);
	    		eliminar(aa);
	    	}
	    }
	    
	    public final void eliminar(BlockBreakerGame aa)
	    {
	    	aa.setPuntaje(aa.getPuntaje()+1);
	    }
	    
	    // Para dibujar el bloque en el render.
	    public void draw(ShapeRenderer shape){
	        shape.rect(x, y, width, height);
	    }
	    
	    
	    // Setters y Getters
	    public void setX(int x)
	    {
	    	this.x = x;
	    }
	    public void setY(int y)
	    {
	    	this.y = y;
	    }
	    public void setWidth(int width)
	    {
	    	this.width = width;
	    }
	    public void setHeight(int height)
	    {
	    	this.height = height;
	    }
	    public void setVida(int vida)
	    {
	    	this.vida =  vida;
	    }
	    public void setDestroyed(boolean destroyed)
	    {
	    	this.destroyed = destroyed;
	    }
	    
	    public int getX()
	    {
	    	return x;
	    }
	    public int getY()
	    {
	    	return y;
	    }
	    public int getWidth()
	    {
	    	return width;
	    }
	    public int getHeight()
	    {
	    	return height;
	    }
	    public int getVida()
	    {
	    	return vida;
	    }
	    public boolean getDestroyed()
	    {
	    	return destroyed;
	    }
}
