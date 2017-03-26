
//	Date: 2/23/17
//	IT 219 ASTEROIDS GAME
//	MADE THIS THURSDAY SO ITS A LITTLE RUSHED

import acm.graphics.*;	//	ACM SHAPES
import acm.program.GraphicsProgram;	//	GRAPHICS WINDOW
import acm.util.*;		// ACM VERSION OF TIMER/RNG
import java.awt.*; 	//	COLOR
import java.awt.event.*;	//	EVENT HANDLER
import java.util.ArrayList;


public class Testing extends GraphicsProgram
{
	ABall[] balls;
	
	public void init()
	{
		setSize(1000, 800);
		balls = new ABall[2];
	}
	
	public void run()
	{
		ABall ball = new ABall(50, 50);
		ABall ball2 = new ABall(50, 50);
		
		add(ball);
		add(ball2, 500, 500);
		
		balls[0] = ball;
		balls[1] = ball2;
		
		
		while(true)
		{
			for(int i = 0; i < balls.length; i++)
			{
				balls[i].moveRight();
			}
			pause(1000);
		}

	}
}