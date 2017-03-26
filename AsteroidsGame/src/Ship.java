import acm.graphics.*;
import acm.util.*;
import java.awt.Color;

public class Ship extends GCompound
{
	GPolygon body;
	private double speed = 0.25;
	private double maxSpd = 2.5;
	private double turn = 2;
	private double xv, yv = 0;
	private double facing = -90;
	private final int FIRE_COOLDOWN = 25;
	private int cooldown = 0;
	private int invuln = 0;
	
	public Ship()
	{
		body = new GPolygon();
		body.addVertex(0, -15);
		body.addVertex(-10, 15);
		body.addVertex(0, 0);
		body.addVertex(10, 15);
		
		body.setFilled(true);
		body.setColor(Color.WHITE);
		add(body);
	}
	
	public void invulnDecay()
	{
		if(invuln > 0)
			{ 
				invuln--;
				if(invuln % 5 == 0 && this.isVisible())
				{
					this.setVisible(false);
				}
				else if(invuln % 5 == 0 && !this.isVisible())
				{
					this.setVisible(true);
				}
			}
		else
		{
			if(!this.isVisible())
			{
				this.setVisible(true);
			}
		}
	}
	
	public boolean isInvuln()
	{
		if(invuln > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void setInvuln()
	{
		invuln = 400;
	}
	
	public GRectangle getCollision()
	{
		return this.getBounds();
	}
	
	public void chDir(boolean pos)
	{
		
		if(pos)
		{
			body.rotate(turn);
			facing -= turn;
		}
		else
		{
			body.rotate(-turn);
			facing += turn;
		}
	}
	
	public void stopShip()
	{
		xv = yv = 0;
		speed = 0.25;
	}
	
	public void move()
	{
		this.sendToFront();
		double[] ang = getAngPoint();
		setVelocity(this.getX(), this.getY(), ang[0], ang[1]);
		this.move(xv, yv);
	}
	
	public double[] getAngPoint()
	{
		double x = Math.cos(Math.PI*2/360 * facing) + this.getX();
		double y = Math.sin(Math.PI*2/360 * facing) + this.getY();
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
	
	
	public double getFacing()
	{
		return facing;
	}
	
	
	public void accelerate()
	{
		if(speed < maxSpd)
			{ speed += 0.05; }
	}
	
	public void decelerate()
	{
		if(speed > 0.25)
			{ speed -= 0.05; }
	}
	
	public int getCooldown()
	{
		return cooldown;
	}
	
	
	public void setCooldown()
	{
		cooldown = FIRE_COOLDOWN;
	}
	
	public void cooldown()
	{
		if(cooldown > 0)
			{ cooldown--; }
	}
	
}
