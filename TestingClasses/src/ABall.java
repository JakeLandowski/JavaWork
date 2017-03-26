import java.awt.Color;

import acm.graphics.*;
import java.awt.*;

public class ABall extends GCompound
{
	GOval body;
	
	public ABall(int width, int height)
	{
		body = new GOval(width, height);
		body.setColor(Color.BLACK);
		body.setFilled(true);
		add(body);
	}
	
	public void moveRight()
	{
		body.move(1, 0);
	}
}
