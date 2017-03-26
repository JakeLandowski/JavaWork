import acm.program.*;
import acm.graphics.*;
import java.awt.*;

public class TrafficLights extends GraphicsProgram
{
	public static final int APPLICATION_WIDTH = 1000;
	public static final int APPLICATION_HEIGHT = 800;
	
	public void run()
	{
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		
		boolean flip = true;
		
		GRect leftLight = new GRect(200, 200, 200, 400);
		GRect rightLight = new GRect(600, 200, 200, 400);
		GOval leftRed = new GOval(250, 230, 100, 100);
		GOval leftYel = new GOval(260, 360, 80, 80);
		GOval leftGre = new GOval(260, 470, 80, 80);
		GOval rightRed = new GOval(650, 240, 100, 100);
		GOval rightYel = new GOval(660, 370, 80, 80);
		GOval rightGre = new GOval(660, 480, 80, 80);
		
		Color red = new Color(255, 0, 0);
		Color yellow = new Color(255, 255, 0);
		Color green = new Color(0, 255, 0);
		Color gray = new Color(110, 110, 110);
		Color black = new Color(60, 60, 60);
		
		leftLight.setColor(black);
		leftLight.setFilled(true);
		rightLight.setColor(black);
		rightLight.setFilled(true);
		
		leftRed.setColor(gray);
		leftYel.setColor(gray);
		leftGre.setColor(gray);
		rightRed.setColor(gray);
		rightYel.setColor(gray);
		rightGre.setColor(gray);
		
		add(leftLight);
		add(rightLight);
		add(leftRed);
		add(leftYel);
		add(leftGre);
		add(rightRed);
		add(rightYel);
		add(rightGre);
		
		on(rightRed, red);
		while(true)
		{
			if(flip)
			{
				off(leftRed, gray);
				on(leftGre, green);
				pause(3000);
				off(leftGre, gray);
				on(leftYel, yellow);
				pause(2000);
				off(leftYel, gray);
				on(leftRed, red);
				pause(1000);			
			}
			else
			{
				off(rightRed, gray);
				on(rightGre, green);
				pause(3000);
				off(rightGre, gray);
				on(rightYel, yellow);
				pause(2000);
				off(rightYel, gray);
				on(rightRed, red);
				pause(1000);
			}
			
			flip = (flip) ? false : true;
		}
	}
	
	public void on(GOval light, Color color)
	{
		light.setColor(color);
		light.setFilled(true);
	}
	
	public void off(GOval light, Color gray)
	{
		light.setColor(gray);
		light.setFilled(false);
	}
	
	
}
