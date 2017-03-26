import java.awt.Color;

import acm.graphics.*;
import acm.util.RandomGenerator;

public class BugBot extends GCompound
{
	private final int HALT_DURATION = 15;
	
	private GOval body; 
	private GOval head;
	private GLine leg1, leg2, leg3; 
	private int facing;
	private int prevfacing;
	private double spd;
	private double xv, yv;
	private int halt;
	private RandomGenerator rand;
	private boolean done;
	private double size;
	
		//	CONSTRUCTOR: SETUP PARTS AND FIELDS
	public BugBot(int newfacing, double newsize)
	{
		size = newsize;
		done = false;
		rand = new RandomGenerator();
		halt = 0;
		xv = yv = 0;
		facing = newfacing;
		spd = 3;
		
		body = new GOval(size, size);
		body.setColor(Color.BLACK);
		body.setFilled(true);
		add(body, -size/2, -size/2);
		
		head = new GOval(size*0.6, size*0.6);
		head.setColor(Color.BLACK);
		head.setFilled(true);
		
		
		leg1 = new GLine(0, 0, 0, 0);
		leg2 = new GLine(0, 0, 0, 0);
		leg3 = new GLine(0, 0, 0, 0);
		
		setBodyParts();
		add(head);
		add(leg1);
		add(leg2);
		add(leg3);
		
		markAsComplete();
	}
	
		//	CHANGE BODY PART ORIENTATION BASED ON FACING ANGLE
	private void setBodyParts()
	{
		if(facing == 1)
		{
			head.setLocation(-size*0.6/2, -size*0.6/2 - size/2);
			leg1.setStartPoint(Math.sin(Math.PI*2/360 * (135))*size,
					 		   Math.cos(Math.PI*2/360 * (135))*size);
			leg1.setEndPoint(Math.sin(Math.PI*2/360 * (315))*size,
					 		 Math.cos(Math.PI*2/360 * (315))*size);
	
			leg2.setStartPoint(Math.sin(Math.PI*2/360 * (90))*size, 
					 		   Math.cos(Math.PI*2/360 * (90))*size);
			leg2.setEndPoint(Math.sin(Math.PI*2/360 * (270))*size,
					 		 Math.cos(Math.PI*2/360 * (270))*size);
			
			leg3.setStartPoint(Math.sin(Math.PI*2/360 * (225))*size, 
					 		   Math.cos(Math.PI*2/360 * (225))*size); 
			leg3.setEndPoint(Math.sin(Math.PI*2/360 * (45))*size,
					 		 Math.cos(Math.PI*2/360 * (45))*size);
		}
		else if(facing == 2)
		{
			head.setLocation(-size*0.6/2- size/2, -size*0.6/2);
			
			leg1.setStartPoint(Math.sin(Math.PI*2/360 * (135))*size,
							   Math.cos(Math.PI*2/360 * (135))*size);
			leg1.setEndPoint(Math.sin(Math.PI*2/360 * (315))*size,
					 		 Math.cos(Math.PI*2/360 * (315))*size);
		
			leg2.setStartPoint(Math.sin(Math.PI*2/360 * (0))*size, 
					 		   Math.cos(Math.PI*2/360 * (0))*size);
			leg2.setEndPoint(Math.sin(Math.PI*2/360 * (180))*size,
					 		 Math.cos(Math.PI*2/360 * (180))*size);
			
			leg3.setStartPoint(Math.sin(Math.PI*2/360 * (225))*size, 
					 		   Math.cos(Math.PI*2/360 * (225))*size); 
			leg3.setEndPoint(Math.sin(Math.PI*2/360 * (45))*size,
					 		 Math.cos(Math.PI*2/360 * (45))*size);
		}
		else if(facing == 3)
		{
			head.setLocation(-size*0.6/2, -size*0.6/2 + size/2);
			
			leg1.setStartPoint(Math.sin(Math.PI*2/360 * (135))*size,
					 		   Math.cos(Math.PI*2/360 * (135))*size);
			leg1.setEndPoint(Math.sin(Math.PI*2/360 * (315))*size,
					 		 Math.cos(Math.PI*2/360 * (315))*size);
	
			leg2.setStartPoint(Math.sin(Math.PI*2/360 * (90))*size, 
					 		   Math.cos(Math.PI*2/360 * (90))*size);
			leg2.setEndPoint(Math.sin(Math.PI*2/360 * (270))*size,
					 		 Math.cos(Math.PI*2/360 * (270))*size);
			
			leg3.setStartPoint(Math.sin(Math.PI*2/360 * (225))*size, 
					 		   Math.cos(Math.PI*2/360 * (225))*size); 
			leg3.setEndPoint(Math.sin(Math.PI*2/360 * (45))*size,
					 		 Math.cos(Math.PI*2/360 * (45))*size);
		}
		else if(facing == 4)
		{
			head.setLocation(-size*0.6/2 + size/2, -size*0.6/2);
			
			leg1.setStartPoint(Math.sin(Math.PI*2/360 * (135))*size,
							   Math.cos(Math.PI*2/360 * (135))*size);
			leg1.setEndPoint(Math.sin(Math.PI*2/360 * (315))*size,
					 		 Math.cos(Math.PI*2/360 * (315))*size);
		
			leg2.setStartPoint(Math.sin(Math.PI*2/360 * (0))*size, 
					 		   Math.cos(Math.PI*2/360 * (0))*size);
			leg2.setEndPoint(Math.sin(Math.PI*2/360 * (180))*size,
					 		 Math.cos(Math.PI*2/360 * (180))*size);
			
			leg3.setStartPoint(Math.sin(Math.PI*2/360 * (225))*size, 
					 		   Math.cos(Math.PI*2/360 * (225))*size); 
			leg3.setEndPoint(Math.sin(Math.PI*2/360 * (45))*size,
					 		 Math.cos(Math.PI*2/360 * (45))*size);
		}
	}
	
		//	MOVE TOWARD FACING ANGLE
	public void moveForward()
	{	
		if(halt < 1)
		{	
			switch(facing)
			{
				case 1:
					yv = -spd;
					xv = 0;
					break;
				case 2:
					xv = -spd;
					yv = 0;
					break;
				case 3:
					yv = spd;
					xv = 0;
					break;
				case 4:
					xv = spd;
					yv = 0;
					break;
			}
			this.move(xv, yv);
		}
	}
	
		//	COUNTDOWN HALT DURATION
	public void cooldownHalt()
	{
		if(halt > 0) halt--;
	}
	
		//	SET HALT DURATION
		//	MARK PREVIOUS FACING ANGLE
	public void halt()
	{
		halt = HALT_DURATION;
		prevfacing = facing;
	}
	
		//	BUMP BACKWARDS RANDOM DISTANCE
	public void bumpBack()
	{
		switch(facing)
		{
			case 1:
				yv = spd;
				xv = 0;
				break;
			case 2:
				xv = spd;
				yv = 0;
				break;
			case 3:
				yv = -spd;
				xv = 0;
				break;
			case 4:
				xv = -spd;
				yv = 0;
				break;
		}
		this.move(xv*rand.nextDouble(3, 20), 
				  yv*rand.nextDouble(3, 20));
	}
	
	public int getHalt()
	{
		return halt;
	}
	
		//	SET RANDOM FACING DIRECTION, NOT PREVIOUS DIRECTION
	public void faceRandomDirection()
	{
		do
		{
			facing = rand.nextInt(1, 4);
		}
		while(facing == prevfacing);
		setBodyParts();
	}
	
	public double getSpd()
	{
		return spd;
	}
	
	public void gameOver()
	{
		done = true;
	}
	
	public boolean isDone()
	{
		return done;
	}
	
	
}
