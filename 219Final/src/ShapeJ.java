
public class ShapeJ extends Shape
{
	private static final int[][][][] SHAPE_MAP = {
									   	{
				/*INITIAL ROTATION*/		{ {0,0}, {0,1} },
									   		{ {0,2}, {-1,2} } 
									   	},
									   	{
				/*ROTATE LEFT*/ 			{ {-1,1}, {0,1} },
									   		{ {1,1}, {1,2} } 
									   	},
									   	{
				/*UPSIDE DOWN*/ 			{ {0,0}, {0,1} },
		   									{ {0,2}, {1,0} }  
									   	},
									   	{
				/*ROTATE RIGHT*/			{ {-1,1}, {0,1} },
		   									{ {1,1}, {-1,0} } 
									   	}
									};
	
	public ShapeJ(int startX, int startY, Cell[][] cells)
	{
		rotation = 0;
		x = startX;
		y = startY;
		type = 5;
		isfrozen = false;
		freezespd = FREEZE_TIME;
		
		occupiedcells = new Cell[2][2];
		oldcells = new Cell[2][2];
		setCells(cells, SHAPE_MAP);
	}
	
	
	public static int[][][][] getMap()
		{ return SHAPE_MAP; }
}
