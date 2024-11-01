package puppy.code;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

// Objeto modHP para el Drop.
public class modHP{
	private int x,y,width,height;
	private Color cc;
	
	public modHP(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		cc = new Color(180, 0, 233 ,250);
	}
    public void draw(ShapeRenderer shape){
    	shape.setColor(cc);
        int y2 = y;
        shape.ellipse(this.x, y2, this.width, this.height);
    }
    
    // Aumenta la vida del jugador.
    public void sumarVida(BlockBreakerGame aa)
    {
    	aa.setVidas(aa.getVidas()+1);
    }
}
