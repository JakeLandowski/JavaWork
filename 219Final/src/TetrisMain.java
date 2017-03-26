//	Jacob Landowski
//	Date: 3/11/17
//	IT 219 TETRIS GAME
//	

import acm.program.GraphicsProgram;	//	GRAPHICS WINDOW
import acm.util.*;		// ACM VERSION OF TIMER/RNG
import java.awt.*; 	//	COLOR
import java.awt.event.*;	//	EVENT HANDLER



public class TetrisMain extends GraphicsProgram
{
	public static final int APPLICATION_WIDTH = 1000;
	public static final int APPLICATION_HEIGHT = 800;
	public static final int TIMER_RATE = 5;
	public static final int CELL_SIZE = 50;
	
	public static boolean[] keyspressed;
					//	{ W, S, A, D, SPACE }
	

	public static Grid grid;
	
	
	public void init()
	{
		getGCanvas().setFocusable(true);
		getGCanvas().requestFocus();
		keyspressed = new boolean[5];

		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		setBackground(Color.black);	
		
		grid = new Grid(APPLICATION_WIDTH, APPLICATION_HEIGHT, CELL_SIZE);
		add(grid, 0, 0);
		
		addKeyListeners();
	}
	
	public void run()
	{
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
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
		grid.fall(false);
	}
	
	
//================================================================================
//---------------------------------KEY EVENTS-------------------------------------
//================================================================================
	public void keyPressed(KeyEvent k)
	{
		int kode = k.getKeyCode();
	
		if(kode == KeyEvent.VK_W || kode == KeyEvent.VK_UP)
		{
			keyspressed[0] = true;
			grid.rotateShapes();
		}
		else if(kode == KeyEvent.VK_S || kode == KeyEvent.VK_DOWN)
		{
			keyspressed[1] = true;
			grid.fall(true);
		}
		else if(kode == KeyEvent.VK_A || kode == KeyEvent.VK_LEFT)
		{
			keyspressed[2] = true;
			grid.moveLeft();
		}
		else if(kode == KeyEvent.VK_D || kode == KeyEvent.VK_RIGHT)
		{
			keyspressed[3] = true;
			grid.moveRight();
		}
		else if(kode == KeyEvent.VK_SPACE)
		{
			keyspressed[4] = true;
			if(grid.gameIsOver()) grid.acceptRestart();
		}
	}
	
	public void keyReleased(KeyEvent k)
	{
		int kode = k.getKeyCode();
		
		if(kode == KeyEvent.VK_W || kode == KeyEvent.VK_UP)
		{
			keyspressed[0] = false;
		}
		else if(kode == KeyEvent.VK_S || kode == KeyEvent.VK_DOWN)
		{
			keyspressed[1] = false;
		}
		else if(kode == KeyEvent.VK_A || kode == KeyEvent.VK_LEFT)
		{
			keyspressed[2] = false;
		}
		else if(kode == KeyEvent.VK_D || kode == KeyEvent.VK_RIGHT)
		{
			keyspressed[3] = false;
		}
		else if(kode == KeyEvent.VK_SPACE)
		{
			keyspressed[4] = false;
		}
	}
	

}
