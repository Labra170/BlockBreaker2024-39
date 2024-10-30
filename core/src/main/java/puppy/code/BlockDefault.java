package puppy.code;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class BlockDefault extends BlockAbstract {
	private Color cc;
    
    public BlockDefault(int x, int y, int width, int height) {
    	super(x, y, width, height);
        cc = new Color(0xa020f0ff);
  
    }
    public void draw(ShapeRenderer shape){
    	shape.setColor(cc);
        shape.rect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}
