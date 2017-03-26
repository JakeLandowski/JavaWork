import java.awt.Color;
import java.awt.Graphics;

public class Rain 
{
	int x, y, w, h, fallspd;
	
	public Rain(int newx, int newy, int width, int height, int specifiedspd)
	{
		x = newx;
		y = newy;
		w = width;
		h = height;
		fallspd = specifiedspd;
	}
	
	public void fall()
	{
		y+=fallspd;
	}
	
	public void render(Graphics g)
	{
		g.setColor(new Color(100, 100, 255));
		g.fillOval(x, y, w, h);
	}
	
	public int getY()
	{
		return y;
	}
	
}
