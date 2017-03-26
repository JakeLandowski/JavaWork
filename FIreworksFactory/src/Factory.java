//	BUILDING FOR FIREWORKS FACTORY

import acm.graphics.*;
import java.awt.*;

public class Factory extends GCompound
{
	public Factory(double w, double h)
	{
		//	TOP LEFT CORNER ORIGIN OF BUILDING
		double x = -w/2;
		double y = -h;
		
		GRect base = new GRect(w, h);
		GRect roof = new GRect(w*1.1, h*0.1);
		GRect door = new GRect(w*0.05, h*0.25);
		GLabel logo = new GLabel("Firework Factory!");
		
		base.setFilled(true);
		base.setColor(new Color(161, 120, 43));
		roof.setFilled(true);
		roof.setColor(new Color(80, 60, 21));
		door.setFilled(true);
		door.setColor(Color.BLACK);
		logo.setFont("Impact-BOLD-26");
		logo.setColor(Color.YELLOW);
		
			// 	ASSEMBLE BUILDING PIECES ON GCOMPOUND
		add(base, x, y);
		add(roof, x - w*0.05, y - h*0.1);
		add(door, -w/2 + w*0.1, -h*0.25);
		add(logo, -w/5, -h/5);
		
		double winSize = w*0.1;
		
			//	SPAWN SPACED MULTIPLE WINDOWS
		for(int i = 0; i < 5; i++)
				{ 
				  add(window(winSize, winSize), 
					 ((-w/2 + winSize*2.5 + i*1.5*winSize) - winSize/2),
					 (-h/1.5 - winSize/2) ); 
				}
	}
		//	RETURN A FRESH WINDOW OBJECT
	public GRect window(double w, double h)
	{
		GRect win = new GRect(w, h);
		win.setFillColor(Color.YELLOW);
		win.setFilled(true);
		return win;
	}
}
