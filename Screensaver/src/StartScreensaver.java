//	Jacob Landowski / Sarah Elkhart / Tyler Bezera
//	Date : 1/26/17
//  IT 219 SCREENSAVER
//	EACH STAR IS A RANDOM OVAL, SHAPE, LINE, OR HALF CIRCLE
//	MEETING THE REQUIREMENTS. 

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;	//	NEEDED FOR TIMER FUNCTION
import java.awt.*;	//	NEEDED FOR SOMETHING

public class StartScreensaver extends GraphicsProgram
{
	public static final int STAR_DENSITY = 1024;		//	AMOUNT OF STARS
	public static final int APPLICATION_WIDTH = 1280;	//	WINDOW SIZE
	public static final int APPLICATION_HEIGHT = 800;
	public static final double CENTER_X = APPLICATION_WIDTH/2;	// CENTER OF WINDOW POSITION
	public static final double CENTER_Y = APPLICATION_HEIGHT/2;
	public static final int TIMER_RATE = 10;	//	EVERY 10 MILLISECONDS FOR TIMER
	

	//GImage spaceship = new GImage("http://i.imgur.com/itbJjxb.png", 20, 20);
	
	private RandomGenerator rand = new RandomGenerator();	//	RANDOM NUMBER GENERATOR
	
	private GStar[] starArray = new GStar[STAR_DENSITY];	//	
	private double[][] randEdge = new double[4][2]; 	//	[ [x, y], [x, y], [x, y], [x, y]]
	
	/* public static void main(String[] args) 
	{
		new StartScreensaver().start(args);
	} */ 	//	MAIN METHOD ONLY FOR PACKAGING AS EXECUTABLE
	
	public void init()	//	INITIALIZATION METHOD, BUILT IN ACM METHOD, RUNS BEFORE STARTING
	{
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);	//	SETS THE SIZE OF WINDOW
		setBackground(Color.BLACK);						//	MAKES BACKGROUND BLACK
		
		for(int i = 0; i < STAR_DENSITY; i++)	//	LOOP THROUGH STAR ARRAY AND MAKE STARS
		{
			starArray[i] = new GStar(rand.nextInt(0, 3), 4);	//	MAKES STAR WITH WIDTH/HEIGHT OF 4 
			
			GStar star = starArray[i];	//	VARIABLE TO REFER TO CURRENT ARRAY SPOT
			
			double[] coord = getRandOuterPos();		//	GETS A RANDOM X, Y POSITION OUTSIDE OF WINDOW
			double[] velocity = calcVelocity(coord[0], coord[1]);	//	GET X, Y VELOCITY TO MOVE STAR TOWARDS CENTER
							
			add(star, coord[0], coord[1]);	//	RENDER STAR OUTSIDE WINDOW
			star.setVelocity(velocity[0], velocity[1]);	//	POINT STAR TOWARDS CENTER AND SET SPEED
			star.setDelay(i * 0.2);				//	DELAY ADDED TO STAGGER STARS
			
			// START + VELOCITY * DIST = END  (CALCS HOW MANY TIMES LOOP RUNS BEFORE STAR REACHES CENTER)	
			//	SAME NUM FROM X OR Y BECAUSE MATH IS RIGHT
			double loopsRequired = calcLoopsReq(coord[0], star.getVelocityX());
			star.setIterations(loopsRequired)
              ;			
			//	CALCS THE RATE OF CHANGE STORES IN OBJECT
			double scaleVal = calcScaleVal(1, star.getStarSize(),loopsRequired);
			star.setScalePerLoop(scaleVal);
		}
	}
	
	public double calcLoopsReq(double startX, double xVel)
	{	//	FORMULA, START + VELOCITY * DIST = END
		return (CENTER_X - startX) / xVel;
	}
  
  
  
	public double calcScaleVal(double to, double from, double loops)
	{	//	CALCS EXPONENTIAL DECAY RATE USED FOR SCALING STAR PER LOOP
		return ((Math.log(to / from)) / loops) + 1;
	} 
	public double[] calcVelocity(double x, double y)
	{
      	//	DELTA X, Y
		double xDif = (CENTER_X) - (x);
		double yDif = (CENTER_Y) - (y);
			
      //	CALC DISTANCE BETWEEN STAR AND CENTER OF WINDOW
		double dist = Math.sqrt(xDif*xDif + yDif*yDif);
		double xDir = xDif / dist;
		double yDir = yDif / dist; // FINDS ANGLE FROM SLOPE NOT NEEDED 
								   // Math.atan2(xDif, yDif);
      
        //	VELOCITY * SPEED OF 8
		double velocity[] = { xDir*8, yDir*8 };
		
		return velocity;
	}
	
	public double[] getRandOuterPos()
	{	
		randEdge[0][0] = rand.nextDouble(-500, APPLICATION_WIDTH+500);
		randEdge[0][1] = -500;
		randEdge[1][0] = APPLICATION_WIDTH+500;
		randEdge[1][1] = rand.nextDouble(-500, APPLICATION_HEIGHT+500);
		randEdge[2][0] = rand.nextDouble(-500, APPLICATION_WIDTH+500);
		randEdge[2][1] = APPLICATION_HEIGHT+500;
		randEdge[3][0] = -500;
		randEdge[3][1] = rand.nextDouble(-500, APPLICATION_HEIGHT+500);
		
      	//	PICKS RANDOM EDGE OUTSIDE WINDOW FOR X Y POSITION
      	
		int rand4 = rand.nextInt(0, 3);
		double x = randEdge[rand4][0];
		double y = randEdge[rand4][1];
		double[] coord = { x, y };
			//	 RETURNS RANDOMIZED X, Y POSITION
		return coord;
	}
	
	public void run()
	{
		


       // add(spaceship);
      	//spaceship.sendToFront();
      	
      	//	WAIT FOR  CLICK TO START
		waitForClick();
      
      	//	EVENT HANDLER TO RUN LOOP
		ActionListener listener = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				nextStep();
			}
		};
			//	BASIC TIMER THAT CALLS EVENT HANDLER WHICH RUNS LOOP
		SwingTimer timer = new SwingTimer(TIMER_RATE, listener);
		timer.start();
	}
	
	public void nextStep()
	{
	//	spaceship.scale(1.01); 
      //	spaceship.move(10, 2);

      		//	LOOP THROUGH ALL STARS
		for(int i = 0; i < STAR_DENSITY; i++)
		{
          	//	VARIABLE TO REFER TO CURRENT STAR IN LOOP FOR READABILITY
			GStar star = starArray[i];
			
          		//	IF STAR DELAY FINISHED COUNTING DOWN, FIRE IT OFF
			if(star.getDelay() < 1)
			{	
              	//	MOVE STAR BASED ON VELOCITY
				star.move(star.getVelocityX(), star.getVelocityY());
                
              	//	GET STAR POSITION INFORMATION
				double x = star.getX();
				double y = star.getY();
				double xDif = CENTER_X - x;
				double yDif = CENTER_Y - y;
				double dist = Math.sqrt(xDif*xDif + yDif*yDif);
				
                //	SCALE STAR DOWN BASED ON CALCULATED RATE WHICH IS
              	//	CALCULATED BASED ON DISTANCE TO CENTER
				star.scale(star.getScalePerLoop());
				
              	//	IF STAR IS CLOSE TO CENTER, RESET IT 
				if(dist < 9)
				{
                  	//	GET NEW RANDOM POSITION OUTSIDE WINDOW
					double coord[] = getRandOuterPos();
                  	//	GET NEW VELOCITY/DIRECTION
					double velocity[] = calcVelocity(coord[0], coord[1]);
					
                  	//	STORE NEW POSITION/VELOCITY IN OBJECT VARIABLE FOR LATER
					star.setLocation(coord[0], coord[1]);
					star.setVelocity(velocity[0], velocity[1]);
					
                      //	THIS INVERTS SCALE CALC UNTIL STAR IS NORMAL SIZED AGAIN
						//	BUILT IN 		//	CUSTOM
					while(star.getWidth() < star.getStarSize()) // 	GETSTARSIZE HOLDS ORIGINAL SIZE
					{											//	SCALE BACK UP UNTIL NORMAL
						star.scale(1/star.getScalePerLoop());
					}
								
                  
                  		//	GET NEW NUMBER OF LOOPS FOR RATE OF CHANGE CALC
					double loopsRequired = calcLoopsReq(coord[0], star.getVelocityX());
					star.setIterations(loopsRequired);
					
                  		//	GET NEW RATE OF CHANGE BASED ON DISTANCE
					double scaleVal = calcScaleVal(1, star.getStarSize(),loopsRequired);
					star.setScalePerLoop(scaleVal);
					
				}
			}
			else
			{
              	//	DECREMENT DELAY COUNTER ON STAR EACH LOOP
				star.countDown();
			}
		}
	}
}
