//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #23, 3/14/17
//	MAIN CLASS - UTILIZES Snowman.class

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class SnowmanMain 
{
	public static void main(String[] args)
	{
		DrawingPanel panel = new DrawingPanel(1000, 800);
		Graphics graphics = panel.getGraphics();
		
		Snowman snowmen[] = new Snowman[6];
		
		graphics.setFont(new Font("Serif", Font.BOLD, 72));
		panel.setBackground(new Color(200, 200, 200));
		
			//	CREATE/SET/DRAW SNOWMEN INITIALLY
		for(int i = 0; i < snowmen.length; i++)
		{
			snowmen[i] = new Snowman(150*i + 30, 100*i + 30, graphics);
			snowmen[i].drawSnowMan(graphics, 150);
		}
		
			//	0.2 SEC INTERVAL TIMER TO CHANGE COLORS
		ActionListener listener = new ActionListener() 
	    {
		    public void actionPerformed(ActionEvent e)
		    {
		        changeAllSnowmen(snowmen, graphics);
		    }
		};
		  
		new Timer(200, listener).start();
	}
	
		//	CHANGE SNOWMEN/TEXT TO RANDOM COLORS
	private static void changeAllSnowmen(Snowman[] snowmen, Graphics g)
	{
		for(int i = 0; i < snowmen.length; i++)
		{
			snowmen[i].changeSnowManColor();
		}
		g.drawString("BLEED", 550, 125);
	}
}
