//	Jacob Landowski
//	Date: 1/28/17
//	IT 219 PACMAN DEMO


import acm.graphics.*;	//	ACM SHAPES
import acm.program.*;	//	ACM CONSOLE
import acm.util.SwingTimer;		// ACM VERSION OF TIMER
import java.awt.*; 	//	COLOR
import java.awt.event.*;	//	EVENT HANDLER

public class PacmanDemo extends GraphicsProgram
{
	public static final int APPLICATION_WIDTH = 1200;
	public static final int APPLICATION_HEIGHT = 800;
	public static final double XCENTER = APPLICATION_WIDTH/2;
	public static final double YCENTER = APPLICATION_HEIGHT/2;
	public static final int TIMER_RATE = 10;
	
	public static final double WIDE = 70;	//	DEGREE OF OPEN MOUTH
	public static final double CLOSED = 0;	//	DEGREE OF CLOSED MOUTH
	public static boolean mouthOpen = true;	//	SWITCH TO DETERMINE DIRECTION
	public static double xv = 5;	//	VELOCITY'S
	public static double yv = 0;
	
	public static GArc pacman = new GArc(300, 300, WIDE/2, 360-WIDE);
	public static GArc pacmanDop = new GArc(300, 300, WIDE/2, 360-WIDE);
	//	DOPPLEGANGER TO MIRROR ON WINDOW EXIT
	
		//	ORI = ORIGINAL // DOP = DOPPLEGANGER
	public static boolean oriMoving = true;
	public static boolean dopMoving = false;
	
	public void init()
	{
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		setBackground(Color.black);
		
		pacman.setFillColor(Color.yellow);
		pacman.setFilled(true);
		pacmanDop.setFillColor(Color.yellow);
		pacmanDop.setFilled(true);
		
		add(pacman, 50, YCENTER-150);
		add(pacmanDop, -300, YCENTER-150);
	}
	
	public void run()
	{
		ActionListener listener = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				step();	//	CALLS LOOP WHEN TIMER FIRES
			}
		};
			//	BASIC TIMER THAT CALLS EVENT HANDLER WHICH RUNS LOOP
		SwingTimer timer = new SwingTimer(TIMER_RATE, listener);
		timer.start();
	}
	
	public void step()
	{
		double ang = pacman.getStartAngle();
		double ang2 = pacman.getSweepAngle();
		
		
		
		if(oriMoving)
			{ pacman.move(xv, yv); }
		if(dopMoving)
			{ pacmanDop.move(xv, yv); }
		
		
			//	IF PEAKING OUT OF WINDOW, START DOPPLEGANGER MOVEMENT
			//	TO CREATE MIRROR EFFECT
		if(pacman.getX() + 300 > APPLICATION_WIDTH)
			{ dopMoving = true; }
		if(pacmanDop.getX() + 300 > APPLICATION_WIDTH)
			{ oriMoving = true; }
		
			//	AFTER LEAVING WINDOW COMPLETELY, RESETS LOCATION TO LEFT
		if(pacman.getX() > APPLICATION_WIDTH)
			{ oriMoving = false;
			  pacman.setLocation(-300, YCENTER-150); }
		
		if(pacmanDop.getX() > APPLICATION_WIDTH)
			{ dopMoving = false;
			  pacmanDop.setLocation(-300, YCENTER-150); }
			
		
			//	DETERMINES IF MOUTH CLOSED OR OPENED
		if(ang >= WIDE/2 || ang <= CLOSED)
			{ mouthOpen = (mouthOpen) ? false : true; }
		
			//	SETS ANGLE PER LOOP
		if(mouthOpen)
			{ pacman.setStartAngle(ang + 1);
			  pacmanDop.setStartAngle(ang + 1);}
		else
			{ pacman.setStartAngle(ang - 1);
			  pacmanDop.setStartAngle(ang - 1);}
		
			//	MATCHES SWEEP TO START EACH LOOP
		pacman.setSweepAngle(360 - ang*2);
		pacmanDop.setSweepAngle(360 - ang*2);
	}
	
}
