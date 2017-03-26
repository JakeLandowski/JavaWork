import acm.graphics.*;
import acm.util.*;
import java.awt.Color;

public class Bullet extends GCompound
{
	GPolygon body;
	private double speed = 4;
	private double xv, yv;
	
	public Bullet(double ang, double x, double y)
	{
		body = new GPolygon();
		body.addVertex(5, 5);
		body.addVertex(-5, 5);
		body.addVertex(-5, -5);
		body.addVertex(5, -5);
		
		body.setFilled(true);
		body.setColor(Color.WHITE);
		add(body);
		
		double[] angPoint = getAngPoint(ang);
		setVelocity(x, y, angPoint[0] + x, angPoint[1] + y);
	}
	
	public GRectangle getCollision()
	{
		return this.getBounds();
	}
	
	public void spin()
	{
		body.rotate(5);
	}
	
	public void move()
	{
		this.sendToFront();
		this.move(xv, yv);
		spin();
	}
	
	public double[] getAngPoint(double ang)
	{
		double x = Math.cos(Math.PI*2/360 * ang);
		double y = Math.sin(Math.PI*2/360 * ang);
		double[] point = {x, y};
		return point;
	}
	
	public void setVelocity(double x, double y, double x2, double y2)
	{
		double dist = getDistance(x2, y2, x, y);
		xv = (x2-x)/dist;
		yv = (y2-y)/dist;
		
		xv *= speed;
		yv *= speed;
	}
	
	public double getDistance(double x, double y, double x2, double y2)
	{
		double xDif = x2 - x;
		double yDif = y2 - y;
		return Math.sqrt(xDif*xDif + yDif*yDif);
	}
}
