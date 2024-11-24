package puppy.code;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

// Objeto modHP para el Drop.
public class modHP implements Action{
	private int x,y,width,height, speed;
	private Color cc;
	
	public modHP(int x, int y, int width, int height, int speed)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		cc = new Color(180, 0, 233 ,250);
	}
    public void draw(ShapeRenderer shape){
    	shape.setColor(cc);
    	y -= speed;
        shape.ellipse(this.x, this.y, this.width, this.height);
    }
    
    // Aumenta la vida del jugador.
    public void action(BlockBreakerGame aa)
    {
    	aa.setVidas(aa.getVidas()+1);
    }
}
