//	Jacob Landowski
//	Date: 2/23/17
//	IT 219 ASTEROIDS GAME
//	MADE THIS THURSDAY SO ITS A LITTLE RUSHED

import acm.graphics.*;	//	ACM SHAPES
import acm.program.GraphicsProgram;	//	GRAPHICS WINDOW
import acm.util.*;		// ACM VERSION OF TIMER/RNG
import java.awt.*; 	//	COLOR
import java.awt.event.*;	//	EVENT HANDLER
import java.util.ArrayList;


public class BugBotField extends GraphicsProgram
{
	private static final int APPLICATION_WIDTH = 1200;
	private static final int APPLICATION_HEIGHT = 800;
	private static final int TIMER_RATE = 5;
	private static final int OVALS = 8;
	private static final int RECTS = 8;
	private static final double SIZE = 10;
	
	private static RandomGenerator rand;
	private static ArrayList<GOval> ovals;
	private static ArrayList<GRect> rects;
	private static Goal goal;
	private static BugBot bug;
	
	public void init()
	{
		ovals = new ArrayList<GOval>();
		rects = new ArrayList<GRect>();
		rand = new RandomGenerator();
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		setBackground(new Color(200, 200, 230));
		setupField();
	}
	
		//	SPAWN BUGBOT AND GOAL
		//	POPULATE REST OF FIELD WITH OBSTACLES THAT DON'T TOUCH
	private void setupField()
	{
		goal = new Goal(100);
		double[] point = getValidSpawn(goal, "goal");
		add(goal, point[0], point[1]);
		
		
		bug = new BugBot(4, 20);
		double[] point2 = getValidSpawn(bug, "bug");
		add(bug, point2[0], point2[1]);
		
		defineObstacles();
	}
	
		//	LOOP THROUGH RECT/OVAL ARRAYS
		//	POPULATE ARRAYS WITH RANDOM SHAPES
		//	DETERMINE VALID LOCATIONS
	private void defineObstacles()
	{
		
		for(int i = 0 ; i < RECTS; i++)
		{
			double width, height;
			
			if(rand.nextBoolean())
			{
				width = rand.nextDouble(SIZE*2, SIZE*7);
				height = rand.nextDouble(SIZE*5, SIZE*15);
			}
			else
			{
				height = rand.nextDouble(SIZE*2, SIZE*7);
				width = rand.nextDouble(SIZE*5, SIZE*15);
			}
			
			int red = rand.nextInt(35, 205);
			int green = rand.nextInt(35, 205);
			int blue = rand.nextInt(35, 205);
			GRect rect = new GRect(width, height);
			
			rect.setFillColor(new Color(red, green, blue));
			rect.setColor(Color.BLACK);
			rect.setFilled(true);
			
			setObstacleLocation(rect);
			add(rect);
			rects.add(rect);
		}
		
		for(int i = 0 ; i < OVALS; i++)
		{
			double size = rand.nextDouble(SIZE*7, SIZE*15);
			int red = rand.nextInt(65, 195);
			int green = rand.nextInt(65, 195);
			int blue = rand.nextInt(65, 195);
			GOval oval = new GOval(size, size);
			
			oval.setFillColor(new Color(red, green, blue));
			oval.setColor(Color.BLACK);
			oval.setFilled(true);
			
			setObstacleLocation(oval);
			add(oval);
			ovals.add(oval);
		}
		
		
	}
	
		//	FIND RANDOM SPAWN POINT WITHIN WINDOW
		//	FOR BUG/GOAL CHOOSE LEFT/RIGHT COLUMN TO SPAWN 
	private double[] getValidSpawn(GObject shape, String option)
	{
		double radiusX = shape.getWidth() / 2;
		double radiusY = shape.getHeight() / 2;
		
		double leftEdge = 0 + radiusX;
		double rightEdge = APPLICATION_WIDTH - radiusX;
		double topEdge = 0 + radiusY;
		double bottomEdge = APPLICATION_HEIGHT - radiusY;
		
		if(option.equals("bug")) 
		{
			rightEdge /= 8;
		}
		else if(option.equals("goal")) 
		{
			leftEdge += rightEdge*0.9;
		}
		
		double x = rand.nextDouble(leftEdge, rightEdge - radiusX);
		double y = rand.nextDouble(topEdge, bottomEdge - radiusY);
		
		double[] point = { x-radiusX, y-radiusY };
		return point;
	}
	
		//	KEEP FINDING A POINT AS LONG AS SHAPE CAN FIT
		//	IF VALID SET ITS LOCATION TO IT
	private void setObstacleLocation(GObject shape)
	{
		boolean valid = false;
		
		while(!valid)
		{
			double[] point = getValidSpawn(shape, "");
			shape.setLocation(point[0], point[1]);
			
			boolean ovalsclear = true;
			boolean rectsclear = true;
			
			if(theseOverlap(shape, goal) || theseOverlap(shape, bug))
				{ ovalsclear = false; }
			
			for(int i = 0 ; i < ovals.size(); i++)
			{
				GOval oval = ovals.get(i);
				
				if(theseOverlap(shape, oval))
				{
					ovalsclear = false;
				}
			}
			
			for(int i = 0 ; i < rects.size(); i++)
			{
				GRect rect = rects.get(i);
				
				if(theseOverlap(shape, rect))
				{
					rectsclear = false;
				}
			}
			
			if(ovalsclear && rectsclear) valid = true;
		}
	}
		
	
		//	CHECK COLLISION
	private boolean theseOverlap(GObject shape1, GObject shape2)
	{
		if(shape1.getBounds().intersects(shape2.getBounds()))
		{
			return true;
		}
		else return false;
	}
	
	public void run()
	{
		ActionListener listener = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				step();
			}
		};
			//	BASIC TIMER THAT CALLS EVENT HANDLER WHICH RUNS LOOP
		SwingTimer timer = new SwingTimer(TIMER_RATE, listener);
		timer.start();
	}
	
		//	MAIN GAME LOOP
	private void step()
	{
		if( !bug.isDone() )
		{
			progressBug();
			progressBug();
		}
	}
	
		//	TRY TO MOVE BUG FORWARD
		//	IF HIT GOAL, GAME OVER
		//	IF HALT, COOLDOWN
		//	IF COLLISION, HALT, BUMPBACK, NEW DIRECTION
	private void progressBug()
	{
		bug.moveForward();
		if(theseOverlap(bug, goal)) 
		{
			bug.gameOver();
			GLabel victoryText = new GLabel("VICTORY");
			victoryText.setFont("SansSerif-bold-154");
			add(victoryText, APPLICATION_WIDTH/2 - victoryText.getWidth()/2, APPLICATION_HEIGHT/2);
		}
		bug.cooldownHalt();
		if(collidedObstacle() || collidedWall()) 
		{
			bug.halt();
			bug.bumpBack();
			bug.faceRandomDirection();
		}
	}
	
	private boolean collidedWall()
	{
		double x = bug.getX();
		double y = bug.getY();
		double width = bug.getWidth();
		double height = bug.getHeight();
		
		double spd = bug.getSpd();
		
		if(x-spd-width/2 < 0 || x+width/2+spd > APPLICATION_WIDTH ||
		   y-spd-height/2 < 0 || y+height/2+spd > APPLICATION_HEIGHT)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	private boolean collidedObstacle()
	{
		for(int i = 0 ; i < ovals.size(); i++)
		{
			GOval oval = ovals.get(i);
			
			if(theseOverlap(bug, oval))
			{
				return true;
			}
		}
		
		for(int i = 0 ; i < rects.size(); i++)
		{
			GRect rect = rects.get(i);
			
			if(theseOverlap(bug, rect))
			{
				return true;
			}
		}
		
		return false;
	}
	
}