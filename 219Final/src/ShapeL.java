
public class ShapeL extends Shape
{
	private static final int[][][][] SHAPE_MAP = {
									   	{
				/*INITIAL ROTATION*/		{ {0,0}, {0,1} },
									   		{ {0,2}, {1,2} } 
									   	},
									   	{
				/*ROTATE LEFT*/ 			{ {-1,1}, {0,1} },
									   		{ {1,1}, {1,0} } 
									   	},
									   	{
				/*UPSIDE DOWN*/ 			{ {0,0}, {0,1} },
		   									{ {0,2}, {-1,0} } 
									   	},
									   	{
				/*ROTATE RIGHT*/			{ {-1,1}, {0,1} },
		   									{ {1,1}, {-1,2} } 
									   	}
									};
	
	public ShapeL(int startX, int startY, Cell[][] cells)
	{
		rotation = 0;
		x = startX;
		y = startY;
		type = 6;
		isfrozen = false;
		freezespd = FREEZE_TIME;
		
		occupiedcells = new Cell[2][2];
		oldcells = new Cell[2][2];
		setCells(cells, SHAPE_MAP);
	}
	
	
	public static int[][][][] getMap()
		{ return SHAPE_MAP; }
}
