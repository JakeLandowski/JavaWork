//	Jacob Landowski
//	Date: 1/28/17
//	IT 219 PACMAN DEMO


import acm.program.*;

public class ConsoleLoops extends ConsoleProgram
{
	public static final int APPLICATION_WIDTH = 1200;
	public static final int APPLICATION_HEIGHT = 800;
	
	public void init()
	{
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}
	public void run()
	{
		loop1();
		cont();
		loop2();
		cont();
		loop3();
		cont();
		loop4();
		cont();
		new ContainedOval().start();
	}
	
	public void cont()
	{
		println("\nPress enter for next example...\n");
		readLine();
	}
	
	public void loop1()
	{
		println("1.\n");
		
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
				{ print("*"); }
			
			println();
		}
	}
	
	public void loop2()
	{
		println("2.\n");
		
		for(int i = 0; i < 3; i++)
		{
			if( !(i == 1) )
			{
				for(int j = 0; j < 10; j++)
					{ print("*"); }
			}
			
			println();
		}
	}
	
	public void loop3()
	{
		println("3.\n");
		
		for(int i = 1; i <= 5; i++)
		{
			for(int j = 0; j < i; j++)
				{ print("*"); }
			
			println();
		}
	}
	
	public void loop4()
	{
		int x, flip;
		
		println("4.\n");
		
		for(int i = 1; i <= 9; i++)
		{
				//	FLIP = 0 or 1 DEPENDING ON < 5
			flip = i/5;
			
				//	FORMULA TO FLIP LOOP DIRECTION AT 5
				//	MULT BY FLIP TO EMULATE BOOLEAN			1 - flip reverses 0/1
			x = ( (flip * 5) - ( (i * flip) % 5 ) ) + ( i * (1 - flip) );
			
			for(int j = 0; j < x; j++)
				{ print("*"); }
			
			println();
		}
	}
}
