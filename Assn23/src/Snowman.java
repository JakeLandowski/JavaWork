//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #23, 3/14/17
//	SNOWMAN CLASS - UTILIZES SnowmanMain.class

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Snowman 
{
	Graphics g;
	int x, y, cursize;
	Color bodyColor;
	Random rng;
	
		//	DEFAULT CONSTRUCTOR - GRAY COLOR
	public Snowman(int startx, int starty,
				   Graphics myG)
	{
		rng = new Random();
		g = myG;
		x = startx;
		y = starty;
		bodyColor = Color.GRAY;
	}
	
	//	CONSTRUCTOR LETS YOU SPECIFY COLOR
	public Snowman(int startx, int starty,
			        Graphics myG, Color c)
	{
		rng = new Random();
		g = myG;
		x = startx;
		y = starty;
		bodyColor = c;
	}
	
		//	RENDER SNOWMAN
	public void drawSnowMan(Graphics g, int size)
	{
		cursize = size;
		
		int head = (int)(size * 0.4);
		
		int middle = (int)(size * 0.6);
		
		int body = (int)(size * 0.8);	
		
		basicCircle(x+size/2-head/2, y, head,
				    bodyColor, g);
		drawFace(x+size/2-head/2, y, head, g);
		
		basicCircle(x+size/2-middle/2, y+((int)(head*0.8)), middle, 
			    bodyColor, g);
		drawArms(x+size/2-middle/2, y+((int)(head*0.8)), 100, g);
		
		basicCircle(x+size/2-body/2, y+((int)(middle*0.8))+(middle/2), body,
			    bodyColor, g);
	}
	
		//	RANDOM COLOR, CALL DRAW AGAIN
	public void changeSnowManColor()
	{
		bodyColor = new Color(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));
		drawSnowMan(g, cursize);
	}
	
		//	DRAW 2 LINES OPPOSITE SIDE OF MIDDLE OVAL
	private void drawArms(int ax, int ay,
						  int size,  Graphics g)
	{
		int lx = ax - (int)(size*0.2);
		int ly = ay - (int)(size*0.3);
		int lx2 = ax + (int)(size*0.2);
		int ly2 = ay + (int)(size*0.3);
		
		g.setColor(Color.BLACK);
		g.drawLine(lx, ly, lx2, ly2);
		
		lx = ax+size - (int)(size*0.2);
		ly = ay + (int)(size*0.3);
		lx2 = ax+size + (int)(size*0.2);
		ly2 = ay - (int)(size*0.3);
		
		g.setColor(Color.BLACK);
		g.drawLine(lx, ly, lx2, ly2);
	}
	
		//	DRAW FACE COMPONENTS ON HEAD
		//	DRAW LEFT EYE 
		//	DRAW RIGHT EYE
		//	DRAW NOSE
		//	DRAW MOUTH
		//	CHANGE OFFETS BETWEEN SHAPE DRAWS
	private void drawFace(int fx, int fy, 
			             int size, Graphics g)
	{
		int xOff = (int)(size*0.25);
		int yOff = (int)(size*0.25);
		
		basicCircle(fx+xOff, fy+yOff, 13, 
			    Color.WHITE, g);
		
		xOff = (int)(size*0.7);
		basicCircle(fx+xOff, fy+yOff, 13, 
				Color.WHITE, g);
		
		xOff = (int)(size*0.6);
		yOff = (int)(size*0.5);
		basicCircle(fx+xOff, fy+yOff, 5, 
				Color.ORANGE, g);
		
		xOff = (int)(size*0.3);
		yOff = (int)(size*0.6);
		g.setColor(Color.WHITE);
		g.fillArc(fx+xOff, fy+yOff, size/2, size/5, 180, 180);
	}
	
		//	CREATE BASIC CIRCLE WITH OUTLINE
	private void basicCircle(int thex, int they, 
				       int size,  Color c, Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawOval(thex, they, size, size);
		g.setColor(c);
		g.fillOval(thex, they, size, size);
	}
}
