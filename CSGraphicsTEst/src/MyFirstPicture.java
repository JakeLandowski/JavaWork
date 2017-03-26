import java.awt.Graphics;

public class MyFirstPicture 
{
	public static void main(String[] args)
	{
		DrawingPanel myPanel = new DrawingPanel(400, 300);
		Graphics myG = myPanel.getGraphics();
	}
}
