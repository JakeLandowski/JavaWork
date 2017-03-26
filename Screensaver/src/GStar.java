import acm.graphics.*;
import java.awt.*;

		//	STAR OBJECT THAT USES GCOMPOUND TO HOLD GOVAL
public class GStar extends GCompound
{	
  		//	VELOCITY VARIABLES
	private double xv, yv;
  		//	DELAY COUNTER TO STAGGER STARS
	private double delayCounter;
	private double size;
  		//	ITERATIONS FOR CALCULATING RATE OF CHANGE 
	private double iterations = 0;
  		//	VARIABLE FOR RATE OF CHANGE
	private double scalePerLoop = 1;
  	
		//	GSTAR CONSTRUCTOR
	public GStar(int randShape, double r)	//	PASS RADIUS ON CONSTRUCTOR CALL
	{
		size = 2 * r;	//	SIZE IS DOUBLE RADIUS
		switch(randShape)
		{
		case 0: GOval ball = new GOval(size, size);
							 ball.setFilled(true);	
							 ball.setColor(Color.WHITE);
							 add(ball, -r, -r);	//	ADD GOVAL TO GCOMPOUND
		break;
		case 1: GRect rect = new GRect(size, size);
 							 rect.setFilled(true);	
							 rect.setColor(Color.WHITE);
							 add(rect, -r, -r);	//	ADD GOVAL TO GCOMPOUND
		break;
		case 2: GLine line = new GLine(0, 0, size, size);
							 line.setColor(Color.WHITE);
							 add(line, -r, -r);	//	ADD GOVAL TO GCOMPOUND
		break;
		case 3: GArc arc = new GArc(size, size, 0, 210);
						   arc.setFilled(true);	
						   arc.setColor(Color.WHITE);
						   add(arc, -r, -r);	//	ADD GOVAL TO GCOMPOUND
		break;
		}
		
		markAsComplete();	//	LOCK THE GCOMPOUND BECAUSE DONE
	}
  			//	SET THE VELOCITY OF STAR
	public void setVelocity(double xSpeed, double ySpeed)
	{
		xv = xSpeed;
		yv = ySpeed;
	}
		//	RETURN VELOCITY
	public double getVelocityX()
	{
		return xv;
	}
	
	public double getVelocityY()
	{
		return yv;
	}
	
  
  		//	SET THE STAGGER DELAY ON STAR
	public void setDelay(double i)
	{
		delayCounter = i;
	}
	
  		//	GET STAGGER DELAY ON STAR FOR CHECK
	public double getDelay()
	{
		return delayCounter;
	}
	
        //	DECREMET DELAY COUNTER EVERY LOOP FOR STAR
	public void countDown()
	{
		if(delayCounter > 0)
			{ delayCounter--; }
	}
	
  		//	SET ITERATION NUMBER FOR CALCULATION LATER
	public void setIterations(double times)
	{
		iterations = times;
	}
	
  		//	RETURN ITERATION NUMBER FOR CALCULATION
	public double getIterations()
	{
		return iterations;
	}
	
        //	SET THE RATE OF CHANGE ON STAR FOR LATER
	public void setScalePerLoop(double val)
	{
		scalePerLoop = val;
	}
          // RETURN RATE OF CHANGE VALUE
	public double getScalePerLoop()
	{
		return scalePerLoop;
	}
	
  		//	RETURN ORIGINAL STAR SIZE TO REVERT SCALE
	public double getStarSize()
	{
		return size;
	}
	
}
