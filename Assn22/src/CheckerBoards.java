import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class CheckerBoards 
{
	private static final int PANEL_WIDTH = 1000;
	private static final int PANEL_HEIGHT = 800;
	
	public static void main(String[] args)
	{
		DrawingPanel panel = new DrawingPanel(PANEL_WIDTH, PANEL_HEIGHT);
		Graphics graphics = panel.getGraphics();
		
		panel.setBackground(new Color(50, 50, 50));
		drawCheckerboard(graphics, 0, 0, 50);
	}	
	
		//	LOOPS THROUGH ROWS
		//	ROWS LOOP THROUGH COLS
		//	MULT ROW INDEX BY SQUARE SIZE ADD X
		//	MULT COL INDEX BY SQUARE SIZE ADD Y
	private static void drawCheckerboard(Graphics g, int x, int y, int numsquares)
	{
		Random rand = new Random();
		
			//	DETERMINE SMALLEST WINDOW SIZE
		int smaller = (PANEL_WIDTH < PANEL_HEIGHT) ? PANEL_WIDTH : PANEL_HEIGHT;
		
			//	TO FIT ON SCREEN
		int size = (int)(smaller*0.9)/numsquares;
		
		for(int i = 0 ; i < numsquares; i++)
		{
			for(int j = 0 ; j < numsquares; j++)
			{
				g.setColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
				g.fillRect(x + i*size, y + j*size, size, size);
			}
		}
	}
}
