//	Jacob Landowski
//	Date: 1/28/17
//	IT 219 PACMAN DEMO


import acm.graphics.*;	//	ACM SHAPES
import acm.program.*;	//	ACM CONSOLE
import acm.util.RandomGenerator;
import acm.util.SwingTimer;		// ACM VERSION OF TIMER
import java.awt.*; 	//	COLOR
import java.awt.event.*;	//	EVENT HANDLER

public class ContainedOval extends GraphicsProgram
{
	public static final int APPLICATION_WIDTH = 1200;
	public static final int APPLICATION_HEIGHT = 800;
	public static final double CX = APPLICATION_WIDTH/2;
	public static final double CY = APPLICATION_HEIGHT/2;
	public static final int TIMER_RATE = 10;
	
	private RandomGenerator rng = new RandomGenerator();
	
	public static double growth = 1.01;
	public static double speed = 10;
	public static double xv, yv, x, y, w, h, goffx, goffy, initH;
	public static double initW = initH = 15;
	GOval ball = new GOval(initW, initH);
	
	public void init()
	{
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		setBackground(Color.black);
		ball.setFillColor(Color.blue);
		ball.setFilled(true);
		add(ball, CX, CY);
			//	DETERMINES A RANDOM DIRECTION
		double[] vel = calcVelocity(CX, CY);
		xv = vel[0] * speed;
		yv = vel[1] * speed;
	}
	
	public void run()
	{
		ActionListener listener = new ActionListener() 
		{	
			public void actionPerformed(ActionEvent e) 
				{ 	step();  }	
		};
				
			//	BASIC TIMER THAT CALLS EVENT HANDLER WHICH RUNS LOOP
		SwingTimer timer = new SwingTimer(TIMER_RATE, listener);
		timer.start();
	}
	
	public void step()
	{
		w = ball.getWidth();
		h = ball.getHeight();
		
			//	TESTS IF TOO BIG OR SMALL
		testSize(w, h);
			
		ball.scale(growth);
		
			//	ACCOUNTS FOR SCALE OFFSETTING BALL 	
		goffx = (w*growth - w)/2;
		goffy = (h*growth - h)/2;
		
		w = ball.getWidth();
		h = ball.getHeight();
		x = ball.getX() + w/2;
		y = ball.getY() + h/2;
		
		testCollision(x, y, w/2, h/2);
		
		ball.move(xv - goffx, yv - goffy);
	}
	
	public void testSize(double w, double h)
	{
		if(w*growth >= APPLICATION_WIDTH ||
		   w*growth <= initW ||  
		   h*growth >= APPLICATION_HEIGHT || 
		   h*growth <= initH)
				{ growth = 1/growth; }
	}
	
	public void testCollision(double x, double y, double rx, double ry)
	{
		if(xv < 0)
		{
			if(x - rx + xv <= 0)
				{ xv = -xv; }
		}
		else if(xv > 0)
		{
			if(x + rx + xv >= APPLICATION_WIDTH)
				{ xv = -xv; }
		}
		
		if(yv < 0)
		{
			if(y - ry + yv <= 0)
				{ yv = -yv; }
		}
		else if(yv > 0)
		{
			if(y + ry + yv >= APPLICATION_HEIGHT)
				{ yv = -yv; }
		}
	}

	public double[] calcVelocity(double x, double y)
	{
      	//	DELTA X, Y
		double xDif = rng.nextDouble(50, APPLICATION_WIDTH-50) - (x);
		double yDif = rng.nextDouble(50, APPLICATION_HEIGHT-50) - (y);
			
      //	CALC DISTANCE BETWEEN STAR AND CENTER OF WINDOW
		double dist = Math.sqrt(xDif*xDif + yDif*yDif);
		double xDir = xDif / dist;
		double yDir = yDif / dist; // FINDS ANGLE FROM SLOPE NOT NEEDED 
								   // Math.atan2(xDif, yDif);
      
        //	VELOCITY 
		double velocity[] = { xDir, yDir };
		
		return velocity;
	}
}
