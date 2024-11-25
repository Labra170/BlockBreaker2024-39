package puppy.code;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class BlockHP extends BlockAbstract{
	private Color cc;
	private ShapeRenderer shape;
	
	// Constructor para que tenga un color predeterminado
	public BlockHP(int x, int y, int width, int height, int vida)
	{
		super(x, y, width, height, vida+1);
		cc = new Color(0xffd700ff);
	}
	
	// Metodo Drop de la clase abstracta, en este caso aumenta la vida del jugador.
	public void drop(ShapeRenderer shape, BlockBreakerGame aa)
	{
		modHP mod = new modHP(getX(), getY(), getWidth(), getHeight(), 2);
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
