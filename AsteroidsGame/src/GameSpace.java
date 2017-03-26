//	Jacob Landowski
//	Date: 2/23/17
//	IT 219 ASTEROIDS GAME
//	MADE THIS THURSDAY SO ITS A LITTLE RUSHED

import acm.graphics.*;	//	ACM SHAPES
import acm.program.GraphicsProgram;	//	GRAPHICS WINDOW
import acm.util.*;		// ACM VERSION OF TIMER/RNG
import java.awt.*; 	//	COLOR
import java.awt.event.*;	//	EVENT HANDLER
import java.util.ArrayList;


public class GameSpace extends GraphicsProgram
{
	public static final int APPLICATION_WIDTH = 1200;
	public static final int APPLICATION_HEIGHT = 800;
	public static final int TIMER_RATE = 10;
	public static final int maxAsteroidMag = 100;
	public static int score = 0;
	
	public static boolean up, down, left, right, space;
	
	public static RandomGenerator rand = new RandomGenerator();
	public static ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public static Ship ship;
	public static GLabel scoreLabel;
	
	public void init()
	{
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		setBackground(Color.black);
		starBackground();
		spawnAsteroids();
		ship = new Ship();
		scoreLabel = new GLabel("SCORE: " + score);
		scoreLabel.setColor(Color.WHITE);
		scoreLabel.setFont("SansSerif-BOLD-26");
		add(scoreLabel, 1000, 50);
		add(ship, APPLICATION_WIDTH/2, APPLICATION_HEIGHT/2);
		addKeyListeners();
	}
	
	public void run()
	{
		ActionListener listener = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				step();
			}
		};
			//	BASIC TIMER THAT CALLS EVENT HANDLER WHICH RUNS LOOP
		SwingTimer timer = new SwingTimer(TIMER_RATE, listener);
		timer.start();
	}
	
	public void keyPressed(KeyEvent k)
	{
		int kode = k.getKeyCode();
		
		switch(kode)
		{
			case KeyEvent.VK_W:
				up = true;
				break;
			case KeyEvent.VK_S:
				down = true;
				break;
			case KeyEvent.VK_A:
				left = true;
				break;
			case KeyEvent.VK_D:
				right = true;
				break;
			case KeyEvent.VK_SPACE:
				space = true;
				break;
		}
	}
	
	public void keyReleased(KeyEvent k)
	{
		int kode = k.getKeyCode();
		switch(kode)
		{
			case KeyEvent.VK_W:
				up = false;
				break;
			case KeyEvent.VK_S:
				down = false;
				break;
			case KeyEvent.VK_A:
				left = false;
				break;
			case KeyEvent.VK_D:
				right = false;
				break;
			case KeyEvent.VK_SPACE:
				space = false;
				break;
		}
	}
		//	UPDATE OBJECTS AND CHECK COLLISIONS
	public void step()
	{
		updateScore();
		progressAsteroids();
		progressShip();
		progressBullets();
		checkBulletAsteroid();
		checkShipAsteroid();
	}
	
		//	CHECK COLLISION BETWEEN BULLETS AND ASTEROIDS
		//	KILL BOTH ON COLLIDE AND SPAWN FRAGMENTS
	public void checkBulletAsteroid()
	{
		ArrayList<Bullet> bulletsRemoved = new ArrayList<Bullet>();
		ArrayList<Asteroid> asteroidsRemoved = new ArrayList<Asteroid>();
		int numBullets = bullets.size();
		int numAsteroids = asteroids.size();
		
		for(int i = 0; i < numBullets; i++)
		{
			Bullet b = bullets.get(i);	
			for(int j = 0; j < numAsteroids; j++)
			{
				Asteroid a = asteroids.get(j);
				if(b.getCollision().intersects(a.getCollision()))
				{
					score += a.getMagnitude();
					bulletsRemoved.add(b);
					asteroidsRemoved.add(a);
				}
			}
		}
		
		int numBulletsRemoved = bulletsRemoved.size();
		int numAsteroidsRemoved = asteroidsRemoved.size();
		for(int i = 0; i < numBulletsRemoved; i++)
		{
			Bullet b = bulletsRemoved.get(i);
			remove(b);
			bullets.remove(b);
		}
		for(int i = 0; i < numAsteroidsRemoved; i++)
		{
			Asteroid a = asteroidsRemoved.get(i);
			int mag = a.getMagnitude();
			double x = a.getX();
			double y = a.getY();
			
			remove(a);
			asteroids.remove(a);
			spawnFragments(mag, x, y);
		}
	}
	
		//	RESET LOCATION OF SHIP
		//	MAKE INVULNERABLE FOR 4 SECONDS
	public void dieRespawn()
	{
		ship.stopShip();
		ship.setInvuln();
		ship.setLocation(APPLICATION_WIDTH/2, APPLICATION_HEIGHT/2);
	}
	
	
		//	CHECK COLLISION BETWEEN SHIP AND ASTEROIDS
		//	KILL BOTH, RESPAWN SHIP, -150 SCORE
	public void checkShipAsteroid()
	{
		ArrayList<Asteroid> asteroidsRemoved = new ArrayList<Asteroid>();
		int numAsteroids = asteroids.size();
		
		for(int i = 0; i < numAsteroids; i++)
		{
			Asteroid a = asteroids.get(i);
			if(ship.getCollision().intersects(a.getCollision()) && !ship.isInvuln())
			{
				dieRespawn();
				score -= 150;
				asteroidsRemoved.add(a);
			}
		}
		
		int numAsteroidsRemoved = asteroidsRemoved.size();
		
		for(int i = 0; i < numAsteroidsRemoved; i++)
		{
			Asteroid a = asteroidsRemoved.get(i);
			int mag = a.getMagnitude();
			double x = a.getX();
			double y = a.getY();
			
			remove(a);
			asteroids.remove(a);
			spawnFragments(mag, x, y);
		}
	}
	
	
		//	MOVE BULLETS, IF OFF SCREEN KILL IT
	public void progressBullets()
	{
		ArrayList<Bullet> toBeRemoved = new ArrayList<Bullet>();
		int numBullets= bullets.size();
		double x, y;
		int width = APPLICATION_WIDTH;
		int height = APPLICATION_HEIGHT;
		int size = 10;
		
		
		for(int i = 0; i < numBullets; i++)
		{
			Bullet b = bullets.get(i);
			b.move();
			x = b.getX();
			y = b.getY();
			
			if(x - size > width || y - size > height ||
					   x + size < 0 || y + size < 0)
			{
				toBeRemoved.add(b);
			}
		}
		
		int numRemoved = toBeRemoved.size();
		for(int i = 0; i < numRemoved; i++)
		{
			Bullet b = toBeRemoved.get(i);
			remove(b);
			bullets.remove(b);
		}
	}
	
		//	KEEP SCORE CURRENT
	public void updateScore()
	{
		scoreLabel.setLabel("SCORE: " + score);
	}
	
		//	MOVE SHIP
		//	COUNT DOWN ANY COOLDOWNS
		//	CHANGE SPEED/ROTATE BASED ON KEYS PRESSED
		//	IF OFF SCREEN RESPAWN SHIP
	public void progressShip()
	{
		ship.move();
		ship.cooldown();
		ship.invulnDecay();
		
		if(up)
			{ ship.accelerate(); }
		else if(down)
			{ ship.decelerate(); }
		
		if(left)
			{ ship.chDir(true); }
		else if(right)
			{ ship.chDir(false); }
		
		if(space && ship.getCooldown() == 0)
		{
			fireBullet();
			ship.setCooldown();
		}
		double x = ship.getX();
		double y = ship.getY();
		int width = APPLICATION_WIDTH;
		int height = APPLICATION_HEIGHT;
		int size = 30;
		if(x - size > width || y - size > height ||
		   x + size < 0 || y + size < 0)
		{
			dieRespawn();
		}
	}
		//	SPAWN BULLET IN FRONT OF SHIP FACE
	public void fireBullet()
	{
		double ang = ship.getFacing();
		double offset = 10;
		double x = ship.getX() + Math.cos(Math.PI*2/360 * ang) * offset;
		double y = ship.getY() + Math.sin(Math.PI*2/360 * ang) * offset;
		Bullet b = new Bullet(ang, x, y);
		bullets.add(b);
		add(b, x, y);
				
	}
		//	MOVE ASTEROIDS, IF OFF SCREEN
		//	KILL ASTEROID, SPAWN NEW RANDOM ONE
	public void progressAsteroids()
	{
		ArrayList<Asteroid> toBeRemoved = new ArrayList<Asteroid>();
		int numAsteroids = asteroids.size();
		for(int i = 0; i < numAsteroids; i++)
		{
			Asteroid a = asteroids.get(i);
			a.move();
			a.rotate();
			a.countDownGrace();
			if( astLeftWindow(a) )
			{ 
				toBeRemoved.add(a);
			}
		}
		
		int numRemoved = toBeRemoved.size();
		for(int i = 0; i < numRemoved; i++)
		{
			Asteroid a = toBeRemoved.get(i);
			int mag = a.getMagnitude();
			remove(a);
			asteroids.remove(a);
			
			spawnFreshAsteroid(mag);
		}
	}
	
		//	SPAWN RANDOM NUM OF FRAGMENTS
		//	LOWER THEIR MAGNITUDE
	public void spawnFragments(int mag, double x, double y)
	{
		if(mag > 40)
		{
			int frags = rand.nextInt(2, 4);
			for(int i = 0; i < frags; i++)
			{
				Asteroid a = new Asteroid((mag+50)/frags);
				
				a.setVelocity();
				asteroids.add(a);
				add(a, x + Math.cos(Math.PI*2/frags * frags) * mag,
					   y + Math.sin(Math.PI*2/frags * frags) * mag);
			}
		}
	}
	
		//	SPAWN A RANDOM ASTEROID OUTSIDE WINDOW
	public void spawnFreshAsteroid(int mag)
	{
		double[] point = getRandPos(false);
		
		Asteroid a = new Asteroid(mag);
		
		double[] velPos = getRandPos(true);
		
		a.setVelocity(point[0], point[1], velPos[0], velPos[1]);
		asteroids.add(a);
		add(a, point[0], point[1]);
	}
	
		//	CHECK IF ASTEROID LEAVES WINDOW
	public boolean astLeftWindow(Asteroid ast)
	{
		double x, y, size, width, height;
		x = ast.getX();
		y = ast.getY();
		size = ast.getAstSize();
		width = APPLICATION_WIDTH;
		height = APPLICATION_HEIGHT;
		
		if( !ast.isGraced() && 
		   x - size > width || y - size > height ||
		   x + size < 0 || y + size < 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
		//	FETCH RANDOM POINT IN WINDOW OR OUTSIDE OF WINDOW
	public double[] getRandPos(boolean inWindow)
	{
		double x, y;
		double point[] = new double[2];
		
		if(inWindow)
		{		
			x = rand.nextDouble(0, APPLICATION_WIDTH);
			y = rand.nextDouble(0, APPLICATION_HEIGHT);
			point[0] = x;
			point[1] = y;
		}
		else
		{
			double width, height, outerDist;
			outerDist = 50;
			width = APPLICATION_WIDTH;
			height = APPLICATION_HEIGHT;
			
			double[][] randEdge = new double[4][2];
			randEdge[0][0] = rand.nextDouble(-outerDist, width+outerDist);
			randEdge[0][1] = -outerDist;
			
			randEdge[1][0] = width+outerDist;
			randEdge[1][1] = rand.nextDouble(-outerDist, height+outerDist);
			
			randEdge[2][0] = rand.nextDouble(-outerDist, width+outerDist);
			randEdge[2][1] = height+outerDist;
			
			randEdge[3][0] = -outerDist;
			randEdge[3][1] = rand.nextDouble(-outerDist, height+outerDist);
			
	      	//	PICKS RANDOM EDGE OUTSIDE WINDOW FOR X Y POSITION
	      	
			int edge = rand.nextInt(0, 3);
			
			point[0] = randEdge[edge][0];
			point[1] = randEdge[edge][1];
		}
		return point;
	}
	
		//	INITIAL ASTEROIDS ON START OF GAME
	public void spawnAsteroids()
	{
		for(int i = 0; i < 7; i++)
		{
			double[] point = getRandPos(false);
			double[] velPos = getRandPos(true);
			Asteroid a = new Asteroid(maxAsteroidMag);
			a.setVelocity(point[0], point[1], velPos[0], velPos[1]);
			asteroids.add(a);
			add(a, point[0], point[1]);
		}
	}
	
	public void starBackground()
	{
		RandomGenerator rand = new RandomGenerator();
		int width = APPLICATION_WIDTH;
		int height = APPLICATION_WIDTH;
		int map = width * height;
		
		for(int i = 0; i < map/1000; i++)
		{
			GOval star = new GOval(rand.nextDouble(0.1, 2),
								   rand.nextDouble(0.1, 2));
			int brightness = rand.nextInt(0, 255);
			star.setColor(new Color(brightness, brightness, brightness));
			star.setFilled(true);
			add(star, rand.nextDouble(0, width+1),
					  rand.nextDouble(0, height+1));
		}
	}
}
