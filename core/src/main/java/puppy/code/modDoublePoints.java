package puppy.code;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class modDoublePoints implements Action {
	private int x,y,width,height, speed;
	private Color cc;
	
	public modDoublePoints(int x, int y, int width, int height, int speed)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		cc = new Color(0x32cd32ff);
	}
    public void draw(ShapeRenderer shape){
    	shape.setColor(cc);
    	y -= speed;
        shape.ellipse(this.x, this.y, this.width, this.height);
    }
    
    // Duplica el puntaje del jugador
    public void action(BlockBreakerGame aa)
    {
    	aa.setPuntaje((aa.getRonda()*2)-1);
    }
}
