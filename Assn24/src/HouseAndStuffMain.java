import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #24, 3/16/17
//	MAIN CLASS - UTILIZES: DrawingPanel.class
//						 : 


//	WHY IS LOOPED RENDERING SO BAD?????

public class HouseAndStuffMain 
{
	public static void main(String[] args)
	{
		DrawingPanel panel = new DrawingPanel(1000, 800);
		Graphics g = panel.getGraphics();
		Random rng = new Random();
		ArrayList<Rain> raindrops = new ArrayList<Rain>();
		
		panel.setBackground(Color.WHITE);
		
		for(int i = 0; i < 50; i++)
		{
			raindrops.add(new Rain(rng.nextInt(1000), rng.nextInt(400), 
					2, rng.nextInt(20)+20, rng.nextInt(30)+70));
		}
		
		
		while(true)
		{
			step(g, rng, raindrops);
			panel.sleep(10);
		}
	}
	
	private static void step(Graphics g, Random rng, ArrayList<Rain> raindrops)
	{
		drawGround(g);
		drawSky(g);
		
		House h = new House(500-250, 400-250, 500, 500, g);
		h.render();
		
		rainFall(g, raindrops);
		removeRain(g, rng, raindrops);
	}
	
	private static void drawGround(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawRect(0, 500, 1000, 300);
		g.setColor(new Color(30, 100, 30));
		g.fillRect(0, 500, 1000, 300);
	}
	
	private static void drawSky(Graphics g)
	{
		g.setColor(new Color(80, 80, 80));
		g.fillRect(0, 0, 1000, 500);
	}
	
	private static void rainFall(Graphics g, ArrayList<Rain> raindrops)
	{
		for(int i = 0; i < raindrops.size(); i++)
		{
			Rain drop = raindrops.get(i);
			drop.fall();
			drop.render(g);
		}
	}
	
	private static void addRain(Graphics g, Random rng, ArrayList<Rain> raindrops)
	{
		raindrops.add(new Rain(rng.nextInt(1000), 0, 2, rng.nextInt(20)+20, rng.nextInt(30)+20));
	}
	
	private static void removeRain(Graphics g, Random rng, ArrayList<Rain> raindrops)
	{
		for(int i = 0; i < raindrops.size(); i++)
		{
			Rain drop = raindrops.get(i);
			if(drop.getY() > 800)
			{
				raindrops.remove(drop);
				addRain(g, rng, raindrops);
			}
		}
	}
	
}
