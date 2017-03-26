import acm.graphics.*;
import acm.util.*;
import java.awt.Color;
import java.util.Arrays;


public class Asteroid extends GCompound
{
	private double xv = 1;
	private double yv = 1;
	private int mag = 1;
	private double size = 1;
	private double minSize = 0.75;
	private double maxSize = 1.5;
	private double rotateSpeed = 0.5;
	private int newAsteroidGrace = 300;
	private double speed = 70;
	RandomGenerator rand = new RandomGenerator();
	
	private GPolygon ast;
	
	public Asteroid(int howbig)
	{
		speed /= howbig;
		mag = howbig;
		rotateSpeed = rand.nextDouble(1.5, 1.5);
		ast = new GPolygon();
		randShape(ast);
		randGray(ast);
		ast.setFilled(true);
		add(ast);
	}
	
	public int getMagnitude()
	{
		return mag;
	}
	
	public void move()
	{
		this.move(xv, yv);
	}
	
	public void setVelocity()
	{
		double ang[] = getRandAngle();
		double x = ang[0];
		double y = ang[1];
		double dist = getDistance(0, 0, x, y);
		xv = x/dist * speed;
		yv = y/dist * speed;
	}
	
	public void setVelocity(double x, double y, double x2, double y2)
	{
		double dist = getDistance(x2, y2, x, y);
		xv = (x2-x)/dist * speed;
		yv = (y2-y)/dist * speed;
	}
	
	public GRectangle getCollision()
	{
		return this.getBounds();
	}
	
	public double[] getRandAngle()
	{
		double randAngle = rand.nextDouble(0, 360);
		double x = Math.cos(Math.PI*2/360 * randAngle);
		double y = Math.sin(Math.PI*2/360 * randAngle);
		double[] point = {x, y};
		return point;
	}
	
	public double getDistance(double x, double y, double x2, double y2)
	{
		double xDif = x2 - x;
		double yDif = y2 - y;
		return Math.sqrt(xDif*xDif + yDif*yDif);
	}
	
	public double getAstSize()
	{
		return size;
	}
	
	public void setRotateSpeed(double spd)
	{
		rotateSpeed = spd;
	}
	
	public void rotate()
	{
		ast.rotate(rotateSpeed);
	}
	
	public void randShape(GPolygon ast)
	{
		int points = rand.nextInt(12, 800/mag);
		double[] radii = new double[points*2];
		
		for(int i = 0; i < points*2; i++)
		{
			radii[i] = rand.nextDouble(minSize* mag, maxSize*mag);
		}
		
		for(int i = 0; i < points; i++)
		{
			double xAng = rand.nextDouble(Math.cos(Math.PI*2/points * i), 
					   						Math.cos(Math.PI*2/points * (i+1) ));
			double yAng = rand.nextDouble(Math.sin(Math.PI*2/points * i), 
											Math.sin(Math.PI*2/points * (i+1) ));
			
			double x = xAng * radii[i];
			double y = yAng * radii[i*2];
			ast.addVertex(x, y);
		}
		setAstSize(radii);
	}
	
	public void countDownGrace()
	{
		if(newAsteroidGrace > 0)
			{ newAsteroidGrace--; }
	}
	
	public boolean isGraced()
	{
		if(newAsteroidGrace > 1)
			{ return true; }
		else
			{ return false; }
	}
	
	public void setAstSize(double[] radii)
	{
		Arrays.sort(radii);
		size = radii[radii.length-1];
	}
	
	public void randGray(GPolygon ast)
	{
		int gray = rand.nextInt(45, 150);
		ast.setColor(new Color(gray, gray, gray));
	}
}
