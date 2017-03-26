import acm.graphics.*;	//	ACM SHAPES
import acm.program.*;	//	ACM CONSOLE || GRAPHICS WINDOW
import acm.util.*;		// ACM VERSION OF TIMER/RNG
import java.awt.*; 	//	COLOR
import java.awt.event.*;	//	EVENT HANDLER

public class PacmanDemo extends GraphicsProgram
{
	public static final int APPLICATION_WIDTH = 1200;
	public static final int APPLICATION_HEIGHT = 800;
	public static final int TIMER_RATE = 10;
	
	public void init()
	{
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		setBackground(Color.black);
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
		
	}
	
}
