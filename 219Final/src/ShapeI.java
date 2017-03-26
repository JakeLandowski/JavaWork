
public class ShapeI extends Shape
{
	private static final int[][][][] SHAPE_MAP = {
									   	{
				/*INITIAL ROTATION*/		{ {0,0}, {0,1} },
									   		{ {0,2}, {0,3} } 
									   	},
									   	{
				/*ROTATE LEFT*/ 			{ {-2,1}, {-1,1} },
									   		{ {0,1}, {1,1} } 
									   	},
									   	{
			/*INITIAL ROTATION*/			{ {0,0}, {0,1} },
								   			{ {0,2}, {0,3} } 
									   	},
									   	{
				/*ROTATE LEFT*/ 			{ {-2,1}, {-1,1} },
									   		{ {0,1}, {1,1} } 
									   	}
									};
	
	public ShapeI(int startX, int startY, Cell[][] cells)
	{
		rotation = 0;
		x = startX;
		y = startY;
		type = 3;
		isfrozen = false;
		freezespd = FREEZE_TIME;
		
		occupiedcells = new Cell[2][2];
		oldcells = new Cell[2][2];
		setCells(cells, SHAPE_MAP);
	}
	
	
	public static int[][][][] getMap()
		{ return SHAPE_MAP; }
}
