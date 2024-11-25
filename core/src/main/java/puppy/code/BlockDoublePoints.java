package puppy.code;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class BlockDoublePoints extends BlockAbstract{
	private Color cc;
	private ShapeRenderer shape;
	
	// Constructor para que tenga un color predeterminado
	public BlockDoublePoints(int x, int y, int width, int height, int vida)
	{
		super(x, y, width, height, vida);
		cc = new Color(0x32cd32ff);
	}
	
	// Metodo Drop de la clase abstracta, en este caso aumenta la vida del jugador.
	public void drop(ShapeRenderer shape, BlockBreakerGame aa)
	{
		modDoublePoints mod = new modDoublePoints(getX(), getY(), getWidth(), getHeight(), 2);
		mod.draw(shape);
		mod.action(aa);
	}
	
    public void draw(ShapeRenderer shape){
    	shape.setColor(cc);
        shape.rect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
    
    public ShapeRenderer getShape()
    {
    	return shape;
    }


}
