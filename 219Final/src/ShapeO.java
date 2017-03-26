
public class ShapeO extends Shape
{
	private static final int[][][][] SHAPE_MAP = {
									   	{
				/*INITIAL ROTATION*/		{ {0,0}, {1,0} },
									   		{ {0,1}, {1,1} } 
									   	},
									   	{
				/*ROTATE LEFT*/ 			{ {0,0}, {1,0} },
									   		{ {0,1}, {1,1} } 
									   	},
									   	{
				/*UPSIDE DOWN*/ 			{ {0,0}, {1,0} },
									   		{ {0,1}, {1,1} } 
									   	},
									   	{
				/*ROTATE RIGHT*/			{ {0,0}, {1,0} },
									   		{ {0,1}, {1,1} } 
									   	}
									};
	
	public ShapeO(int startX, int startY, Cell[][] cells)
	{
		rotation = 0;
		x = startX;
		y = startY;
		type = 2;
		isfrozen = false;
		freezespd = FREEZE_TIME;
		
		occupiedcells = new Cell[2][2];
		oldcells = new Cell[2][2];
		setCells(cells, SHAPE_MAP);
	}
	
	
	public static int[][][][] getMap()
		{ return SHAPE_MAP; }
}
