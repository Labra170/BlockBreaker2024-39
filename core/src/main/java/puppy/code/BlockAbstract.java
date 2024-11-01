package puppy.code;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

// Clase abstracta, usada como base para el resto de bloques.
// Por el momento solo utilizada por BlockDefault y BlockHP
public abstract class BlockAbstract implements DropInterface
{
	    private int x,y,width,height;
	    private boolean destroyed;
	    
	    // Constructor base para los bloques.
	    // A pesar de ser una clase abstracta, el constructor existe para simplificar la construccion de sus clases concretas.
	    public BlockAbstract(int x, int y, int width, int height) {
	        this.x = x;
	        this.y = y;
	        this.width = width;
	        this.height = height;
	        destroyed = false;
	  
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
	    public boolean getDestroyed()
	    {
	    	return destroyed;
	    }
}
