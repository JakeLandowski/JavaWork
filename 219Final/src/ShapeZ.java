
public class ShapeZ extends Shape
{		//	FIX MAPPING ON ALL SHAPES SO IT IS CONSISTENT IN ROTATION
	private static final int[][][][] SHAPE_MAP = {
									   	{
				/*INITIAL ROTATION*/		{ {0,0}, {1,0} },
									   		{ {1,1}, {2,1} } 
									   	},
									   	{
				/*ROTATE LEFT*/ 			{ {0,2}, {0,1} },
									   		{ {1,1}, {1,0} } 
									   	},
									   	{
				/*UPSIDE DOWN*/ 			{ {2,2}, {1,2} },
									   		{ {1,1}, {0,1} } 
									   	},
									   	{
				/*ROTATE RIGHT*/			{ {2,0}, {2,1} },
									   		{ {1,1}, {1,2} } 
									   	}
									};
	
	public ShapeZ(int startX, int startY, Cell[][] cells)
	{
		rotation = 0;
		x = startX;
		y = startY;
		type = 8;
		isfrozen = false;
		freezespd = FREEZE_TIME;
		
		occupiedcells = new Cell[2][2];
		oldcells = new Cell[2][2];
		setCells(cells, SHAPE_MAP);
	}
	
	
	public static int[][][][] getMap()
		{ return SHAPE_MAP; }
}
