import acm.graphics.*;
import java.awt.Color;

public class Cell extends GCompound
{
	private final Color[] TYPE_COLOR = { Color.BLACK, Color.GRAY, Color.RED,
										 Color.BLUE, Color.ORANGE, Color.CYAN,
										 Color.MAGENTA, Color.GREEN, Color.YELLOW};
	
	private int xindex, yindex;
	private GRect cellbody;
	private int type;
	private boolean isfrozen, isleftcell, 
		isrightcell, isbotcell, istopcell;
	
	public Cell(int newx, int newy, int size)
	{
		cellbody = new GRect(size, size);
		cellbody.setFilled(true);
		add(cellbody);
		
		xindex = newx;
		yindex = newy;
	}

	public void setType(int newtype)
		{ type = newtype; }
	public void updateColor()
		{ cellbody.setColor(TYPE_COLOR[type]); }
	public void show()
		{ cellbody.setVisible(true); }
	public  void hide()
		{ cellbody.setVisible(false); }
	public void freeze()
		{ isfrozen = true; }
	public void unFreeze()
		{ isfrozen = false; }
	
	public void markAsLeftCell()
		{ isleftcell = true; }
	public void markAsRightCell()
		{ isrightcell = true; }
	public void markAsBottomCell()
		{ isbotcell = true; }
	public void markAsTopCell()
		{ istopcell = true; }
	
	public boolean isLeftCell()
		{ return isleftcell;  }
	public boolean isRightCell()
		{ return isrightcell; }
	public boolean isBottomCell()
		{ return isbotcell; }
	public boolean isTopCell()
		{ return istopcell; }
	public boolean isFrozen()
		{ return isfrozen; }

	public int getCellX() 
		{ return xindex; }
	public int getCellY() 
		{ return yindex; }
	public int getType()
		{ return type; }
}

