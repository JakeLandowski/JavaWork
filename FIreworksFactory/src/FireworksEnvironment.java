//	Jacob Landowski
//	Date: 1/28/17
//	IT 219 FIREWORKS

import acm.graphics.*;	//	ACM SHAPES
import acm.program.*;	//	ACM CONSOLE
import acm.util.*;	// ACM VERSION OF TIMER/RNG
import java.awt.*; 	//	COLOR
import java.awt.event.*;	//	EVENT HANDLER
import java.util.ArrayList;

public class FireworksEnvironment extends GraphicsProgram
{
	public static final int APPLICATION_WIDTH = 1200;
	public static final int APPLICATION_HEIGHT = 800;
	private static final int TIMER_RATE = 10;
	
	private static int spawnTimer = 0;
	private static int staggerTimer = 0;
	private static int fireworkCounter = 5;
	private static double groundHeight = 100;
	private static double buildingHeight = 200;
	private static double buildingPlace[] = { APPLICATION_WIDTH/2,
											  APPLICATION_HEIGHT-groundHeight };
	
		//	EACH ARRAYLIST HOLDS FIREWORKS, AND EACH LAYER OF RECURSIVE FIREWORK
	private static ArrayList<Firework> fireworks = new ArrayList<Firework>();
	private static ArrayList<Firework> fireworks2 = new ArrayList<Firework>();
	private static ArrayList<Firework> fireworks3 = new ArrayList<Firework>();
	private static ArrayList<Firework> fireworks4 = new ArrayList<Firework>();
	
	private static RandomGenerator rand = new RandomGenerator();
	
	public static void main(String[] args)
	{
		new FireworksEnvironment().run();
	}
	
	public void init()
	{
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		setBackground(Color.black);
		GRect ground = new GRect(0, APPLICATION_HEIGHT-groundHeight,
								 APPLICATION_WIDTH, groundHeight);
		add(ground);
		ground.setFilled(true);
		ground.setColor(new Color(0, 150, 0));
			
			//	SPAWN FACTORY
		Factory factory = new Factory(500, buildingHeight);
		add(factory, buildingPlace[0], buildingPlace[1]);
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
	
	public void step()
	{
		moveFireworks();
		moveShrapnel();
		moveShrapnel();
		
			//	COOLDOWN ON FIREWORK SPAWNS
		if(spawnTimer >= 650)
		{ 
			fireworkCounter = 5;
			spawnTimer = 0;
		}
		if(fireworkCounter > 0 && staggerTimer >= 50)
		{
			spawnFirework();
			fireworkCounter--;
			staggerTimer = 0;
		}
		staggerTimer++;
		spawnTimer++;
	}
		//	CREATES FIREWORK AND ADDS TO FIREWORK ARRAYLIST
	public void spawnFirework()
	{
		Firework firework = new Firework(
			 rand.nextDouble(-APPLICATION_WIDTH/2 + 100, APPLICATION_WIDTH/2 - 100), 
		 	 rand.nextDouble(-200, 
			 		        (buildingHeight*1.1+groundHeight)-APPLICATION_HEIGHT + 100), 255, 255, 0);
		fireworks.add(firework);
		add(firework, buildingPlace[0], buildingPlace[1]-buildingHeight*1.1);
	}
	
		//	SPAWN THE SHRAPNEL REUSING THE SAME FIREWORK CLASS, DIFFERENT ATTRIBUTES PER RECURSIVE LAYER
	public void spawnExplosion(double x, double y, int level)
	{	//	ENTROPY FOR MORE NATURAL EXPLOSIONS
		int specialFirework = rand.nextInt(0, 1);
		double rad = rand.nextDouble(5,60);
		int red = rand.nextInt(0, 255);
		int green = rand.nextInt(0, 255);
		int blue = rand.nextInt(0, 255);
		for(int i = 0; i < 16; i++)
		{
			if(level == 2 && specialFirework == 1) 
				{ rad += rand.nextDouble(6, 12); }
			else if(level == 3 && specialFirework == 1) 
				{ rad += rand.nextDouble(1, 6); }
			
				//	SPLITS INTO 16 LINES AROUND DETONATION POINT
			Firework shrapnel = new Firework(
					Math.cos(Math.PI*2/16 * i) * rad,
					Math.sin(Math.PI*2/16 * i) * rad,
							 red, green, blue);
				//	LAYERS 2, 3 AND 4
			switch(level)
			{
				case 2:	
					shrapnel.setLength(40);
					fireworks2.add(shrapnel);
					break;
				case 3:
					shrapnel.setLength(25);
					fireworks3.add(shrapnel);
					break;
				case 4:
					shrapnel.setLength(10);
					fireworks4.add(shrapnel);
					break;
			}
			add(shrapnel, x, y);
		}
	}
		//	MOVE SHRAPNEL LAYERS, IF THEY REACH END POINT, DETONATE NEW LAYER
	public void moveShrapnel()
	{
		for(int i = 0; i < fireworks2.size(); i++)
		{
			Firework s = fireworks2.get(i);
			
			s.move();
			if( s.isStartDone() && s.isEndDone() )
			{
				spawnExplosion(s.getEnd()[0], s.getEnd()[1], 3);
				remove(s);
				fireworks2.remove(i);
			}
		}
		
		for(int i = 0; i < fireworks3.size(); i++)
		{
			Firework s = fireworks3.get(i);
			
			s.move();
			if( s.isStartDone() && s.isEndDone() )
			{
				spawnExplosion(s.getEnd()[0], s.getEnd()[1], 4);
				remove(s);
				fireworks3.remove(i);
			}
		}
		
		for(int i = 0; i < fireworks4.size(); i++)
		{
			Firework s = fireworks4.get(i);
			
			s.move();
			if( s.isStartDone() && s.isEndDone() )
			{
				remove(s);
				fireworks4.remove(i);
			}
		}

	}
		//	MOVE FIREWORKS, IF REACH ENDPOINT, DETONATE FIRST LAYER
		//	AND REMOVE THE OBJECT ENTIRELY
	public void moveFireworks()
	{
		for(int i = 0; i < fireworks.size(); i++)
		{
			Firework f = fireworks.get(i);
			
			f.move();
			if( f.isStartDone() && f.isEndDone() )
			{
				spawnExplosion(f.getEnd()[0],
						f.getEnd()[1],
						2);
				remove(f);
				fireworks.remove(i);
			}
		}
	}
	
}
