import acm.graphics.*;
import java.awt.Color;

public class Goal extends GCompound
{
	private GOval body;
	private GLine line1, line2;
	
	public Goal(double size)
	{
		body = new GOval(size, size);
		body.setColor(Color.RED);
		body.setFilled(true);
		add(body, -size/2, -size/2);
		
		line1 = new GLine(-size/8, Math.cos(Math.PI*2/360 * 45)*(size/8), 
				  		  size/8, Math.cos(Math.PI*2/360 * 225)*(size/8));
		line1.setColor(Color.WHITE);
		add(line1);
		
		line2 = new GLine(-size/8, Math.cos(Math.PI*2/360 * 135)*(size/8), 
				  		  size/8, Math.cos(Math.PI*2/360 * 315)*(size/8));
		line2.setColor(Color.WHITE);
		add(line2);

		markAsComplete();
	}
}
