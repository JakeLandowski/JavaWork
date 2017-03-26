import java.awt.Color;
import java.awt.Graphics;

// HOUSE CLASS - UTILIZED BY: HouseAndStuffMain.class

public class House 
{
	Graphics g;
	int x, y, w, h;
	
	public House(int startx, int starty, int width, int height, Graphics graphics)
	{
		g = graphics;
		x = startx;
		y = starty;
		w = width;
		h = height;
	}
	
	public void render()
	{
		int quartW = w/5;
		int quartH = h/5;
		
		drawBase();
		drawDoor();
		drawWindow(x+quartW-quartW/2, y+quartH-quartH/3, quartW, quartH);
		drawWindow(x+quartW*3+quartW/2, y+quartH-quartH/3, quartW, quartH);
		drawWindow(x+quartW-quartW/2, y+quartH*4-quartH, quartW, quartH);
		drawWindow(x+quartW*3+quartW/2, y+quartH*4-quartH, quartW, quartH);
		drawRoof();
	}	
	
	private void drawRoof()
	{
		int roofW = w*2-w/2;
		
		int[] polyX = new int[4];
		int[] polyY = new int[4];
		
		polyX[0] = x + (-(roofW-w)/2);
		polyY[0] = y;
		
		polyX[1] = x;
		polyY[1] = y + (-h/4);
		
		polyX[2] = x + w;
		polyY[2] = y + (-h/4);
		
		polyX[3] = x + w + (roofW-w)/2;
		polyY[3] = y;
		
		g.setColor(new Color(0, 0, 0));
		g.drawPolygon(polyX, polyY, 4);
		g.setColor(new Color(255, 100, 190));
		g.fillPolygon(polyX, polyY, 4);
	}
	
	private void drawBase()
	{
		g.setColor(new Color(0, 0, 0));
		g.drawRect(x, y, w, h);
		g.setColor(new Color(60, 60, 30));
		g.fillRect(x, y, w, h);
	}
	
	private void drawDoor()
	{
		g.setColor(new Color(0, 0, 0));
		g.drawRect(x+w/2-w/5/2, y+h-h/4, w/5, h/4);
		g.setColor(new Color(150, 80, 30));
		g.fillRect(x+w/2-w/5/2, y+h-h/4, w/5, h/4);

		g.setColor(new Color(0, 0, 0));
		g.drawOval(x+w/2+w/23, y+h-h/6, w/20, h/20);
		g.setColor(new Color(150, 150, 150));
		g.fillOval(x+w/2+w/23, y+h-h/6, w/20, h/20);
	}
	
	private void drawWindow(int startx, int starty, int winW, int winH)
	{	
		int sillW = winW/5;
		int sillH = winH/5;
		
		g.setColor(new Color(0, 0, 0));
		g.drawRect(startx, starty, winW, winH);
		g.setColor(new Color(255, 255, 0));
		g.fillRect(startx, starty, winW, winH);
		
		g.setColor(new Color(0, 0, 0));
		g.drawRect(startx+winW/2-sillW/2, starty, sillW, winH);
		g.setColor(new Color(100, 60, 0));
		g.fillRect(startx+winW/2-sillW/2, starty, sillW, winH);
		
		g.drawRect(startx, starty+winH/2-sillH/2, winW, sillH);
		g.fillRect(startx, starty+winH/2-sillH/2, winW, sillH);
	}
}
