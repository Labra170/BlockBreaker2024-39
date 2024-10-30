package puppy.code;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class BlockHP extends BlockAbstract {
	private Color cc;
	
	public BlockHP(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		cc = new Color(0xffd700ff);
		
	}
    public void draw(ShapeRenderer shape){
    	shape.setColor(cc);
        shape.rect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

}
