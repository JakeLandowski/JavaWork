//	FIREWORKS
//	LINE DRAWER

import acm.graphics.*;
import java.awt.*;

public class Firework extends GCompound
{
	private double endX, endY, xv, yv;
	private double length = 15;
	private boolean longEnough = false;
	private boolean endDone = false;
	private boolean startDone = false;

	GLine tail = new GLine(0, 0, 0, 0);
	
	public Firework(double x, double y, int red, int green, int blue)
	{		//	END POINT TO REACH
		endX = x; endY = y;
			//	DISTANCE AND VELOCITY CALCULATIONS
		double dist = Math.sqrt(x*x + y*y);
		xv = x/dist*1.7;
		yv = y/dist*1.7;
		
		tail.setColor(new Color(red, green, blue));
		add(tail);
		markAsComplete();
	}
	
	public void move()
	{		//	GET ALL REQUIRED DATA FOR LINE COLLISION DETECTION
		double curEndX = tail.getEndPoint().getX();
		double curEndY = tail.getEndPoint().getY();
		double curStartX = tail.getStartPoint().getX();
		double curStartY = tail.getStartPoint().getY();
		double dist, distEnd, distStart, difX, difY;
		dist = distEnd = distStart = 10;
			
			//	IF END OF LINE NOT REACHED POINT, CONTINUE
		if(!endDone)
		{ 
			tail.setEndPoint(curEndX+xv, curEndY+yv); 
			
			difX = curEndX-endX;
			difY =  curEndY-endY;
			
			distEnd = Math.sqrt(difX*difX + difY*difY);
		}
			//	IF LINE GROWS TO LENGTH, MOVE START POINT
		if(longEnough && !startDone)
			{ 
				tail.setStartPoint(curStartX+xv, curStartY+yv);
				
				difX = curStartX-endX;
				difY = curStartY-endY;
				
				distStart = Math.sqrt(difX*difX + difY*difY);
			}
		else
			{ dist = Math.sqrt(curEndX*curEndX + curEndY*curEndY); }
		
		if(!longEnough && dist >= length || endDone && !startDone)
			{ longEnough = true; }
		
		if(!endDone && distEnd <= 2)
			{ endDone = true; }
		
		if(!startDone && distStart <= 2)
			{ startDone = true; }
	}
	
	public void setLength(double len)
	{
		length = len;
	}
	
	public boolean isEndDone()
	{
		return endDone;
	}
	public boolean isStartDone()
	{
		return startDone;
	}
		//	RETURN THE POINT WHERE FIREWORK LINE FINISHED, THIS IS THE DETONATION POINT
	public double[] getEnd()
	{
		double p[] = { this.getX() + tail.getEndPoint().getX(),
					   this.getY() + tail.getEndPoint().getY() };
		return p;
	}
}

