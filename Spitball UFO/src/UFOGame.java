// Jacob Landowski, Sarah Elkhart, Tyler Bezera
// IT 219 Winter - 11/17/17
// UFO Spitball Simulator

import acm.program.*;
import acm.graphics.*;
import java.awt.*;

public class UFOGame extends GraphicsProgram
{
	public static final int APPLICATION_WIDTH = 800;
	public static final int APPLICATION_HEIGHT = 500;
	
	public void run()
	{
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		
		int score = 0; 
		double ufoSpeedX = 10;
		double ufoSpeedY = 0;
		double ufoScale = 1.01;
		double ballSpeedX = 0;
		double ballSpeedY = -5;
		double ballScale = 1.01;
		double ufoX, ufoY, ufoWidth, ufoHeight, 
			   ballX, ballY, ballWidth, ballHeight;
		
		String msg = "Score is: " + score; 
		GLabel scoreLabel = new GLabel(msg, 10, 390); 
		scoreLabel.setFont("Serif-BOLD-60"); 
		add(scoreLabel);
		
			//	SPITBALL
		GOval ball = new GOval(5, 5); 
		add(ball, APPLICATION_WIDTH/2, APPLICATION_HEIGHT - 6); 
		ball.setColor(Color.RED); 
		ball.setFilled(true);
		
			//	UFO
		GOval ufo = new GOval(100, 20); 
		add(ufo, APPLICATION_WIDTH/2, 0); 
		ufo.setColor(Color.green); 
		ufo.setFilled(true);
		
		waitForClick();
		
		while(true) 
		{		//	SCALE BALL/UFO PER FRAME
				//	CALC POSITION/SIZE
			ball.scale(ballScale);
			ufo.scale(ufoScale, 1);
			
			ufoX = ufo.getX();
			ufoY = ufo.getY();
			ufoWidth = ufo.getWidth();
			ufoHeight = ufo.getHeight();
			
			ballX = ball.getX();
			ballY = ball.getY();
			ballWidth = ball.getWidth();
			ballHeight = ball.getHeight();
			
				//	IF BALL/UFO COLLIDE SCORE++
				//	AND FLIP BALL DIRECTION
			if(ballY+ballSpeedY <= ufoY+ufoSpeedY+ufoHeight && 
			   ballY+ballSpeedY+ballHeight >= ufoY+ufoSpeedY && 
			   ballX+ballSpeedX+ballWidth >= ufoX+ufoSpeedX && 
			   ballX+ballSpeedX <= ufoX+ufoSpeedX+ufoWidth) 
			{
				score++; 
				msg = "Score is " + score; 
				scoreLabel.setLabel(msg);
				ballSpeedX = -ballSpeedX;
				ballSpeedY = -ballSpeedY;
			}
				//	FLIP UFO DIRECTION IF COLLIDING EDGE
			if (ufoX+ufoSpeedX+ufoWidth >= APPLICATION_WIDTH ||
			    ufoX+ufoSpeedX <= 0) 
					{ ufoSpeedX = -ufoSpeedX; } 
				//	FLIP BALL DIRECTION IF COLLIDING EDGE
			if(ballY+ballSpeedY <= 0 || 
			   ballY+ballSpeedY+ballHeight >= APPLICATION_HEIGHT)
					{ ballSpeedY = -ballSpeedY; } 
				//	INVERT SCALING IF TOO BIG OR TOO SMALL
			if(ufoWidth*ufoScale >= APPLICATION_WIDTH || 
		       ufoWidth*ufoScale <= 50)
					{ ufoScale  = 1/ufoScale; }
				//	INVERT SCALING IF TOO BIG OR TOO SMALL
			if(ballWidth*ballScale >= APPLICATION_WIDTH ||
			   ballHeight*ballScale >= APPLICATION_HEIGHT-ufoHeight ||
			   ballWidth*ballScale <= 2.5)
					{ ballScale  = 1/ballScale; }
			
			ufo.move(ufoSpeedX, ufoSpeedY);
			ball.move(ballSpeedX, ballSpeedY);
			
			pause(1000/60);
		}
	}
	
}
