import acm.program.*;
import acm.graphics.*;
import java.awt.*;

public class BouncyBall extends GraphicsProgram{
	public static final int APPLICATION_WIDTH = 640;
	public static final int APPLICATION_HEIGHT = 640;
	
	public void run(){
		
		
		double xBall = 0;
		double yBall = 0;
		double xMove = 0;
		double yMove = 10;
		GLabel l1 = new GLabel("p1", 15, 15);
		GLabel l2 = new GLabel("p2", 65, 15);
		GLabel l3 = new GLabel("p3", 15, 65);
		GLabel l4 = new GLabel("p4", 65, 65);
		GLabel l5 = new GLabel("p5", 115, 35);
		
		add(l1);
		add(l2);
		add(l3);
		add(l4);
		add(l5);
		
		l1.sendToFront();
		l2.sendToFront();
		l3.sendToFront();
		l4.sendToFront();
		l5.sendToFront();
		
		GPoint p1 = new GPoint(15, 15);
		GPoint p2 = new GPoint(65, 15);
		GPoint p3 = new GPoint(15, 65);
		GPoint p4 = new GPoint(65, 65);
		GPoint p5 = new GPoint(115, 35);
		GPoint[] coords = { p1, p2, p3, p4, p5 };
		
		GPolygon ball = new GPolygon(coords);
		ball.setColor(Color.MAGENTA);
		ball.setFilled(true);
		add(ball);
		l1.sendToFront();
		l2.sendToFront();
		l3.sendToFront();
		l4.sendToFront();
		l5.sendToFront();
		
		GImage spunky = new GImage("../../../spunky_head.png");
		add(spunky, 150, 150);
	
		
		String msg = "Currently the ball is at: " + xBall + ", " + yBall;
		GLabel label1 = new GLabel(msg, 300, 400);
		label1.setFont("Serif-BOLD-20");
		add(label1);
		
		waitForClick();
		
		while(true){
			pause(200);
			
			if(yBall == 400){
				yMove = -10;
			}
			else if(yBall == 0){
				yMove = 10;
			}
			
			ball.move(0, yMove);
			
			xBall = ball.getX();
			yBall = ball.getY();
			
			msg = "Currently the ball is at: " + xBall + ", " + yBall;
			label1.setLabel(msg);
		}
	}
}
