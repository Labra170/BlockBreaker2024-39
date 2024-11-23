package puppy.code;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

// Clase concreta de BlockAbstract
public class BlockDefault extends BlockAbstract{
	private Color cc;
    
	// Constructor para que tenga un color predeterminado
    public BlockDefault(int x, int y, int width, int height) {
    	super(x, y, width, height);
        cc = new Color(0xa020f0ff);
    }
    
    // Metodo Drop de la interfaz, en este caso no hace nada.
    public void drop(ShapeRenderer shape, int x, int y, int width, int height, BlockBreakerGame aa)
    {
    	return;
    }
    
    // Dibuja el bloque en el render.
    public void draw(ShapeRenderer shape){
    	shape.setColor(cc);
        shape.rect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}
