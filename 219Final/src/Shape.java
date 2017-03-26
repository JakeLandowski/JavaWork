
public class Shape 
{
	protected Cell[][] occupiedcells;
	protected Cell[][] oldcells;
	protected int x, y, type;
	protected boolean isfrozen;
	protected int rotation, freezespd;
	protected final int FREEZE_TIME = 2;
	
	
	
//================================================================================
//---------------------------------CELL RENDERING LOGIC---------------------------
//================================================================================
	protected void setCells(Cell[][] cells, int[][][][] shapemap)
	{
		for(int i = 0; i < shapemap[rotation].length; i++)
		{
			for(int j = 0; j < shapemap[rotation][i].length; j++)
			{
				int xOff = shapemap[rotation][i][j][0];
				int yOff = shapemap[rotation][i][j][1];
				occupiedcells[i][j] = cells[x + xOff][y + yOff];
				Cell cell = occupiedcells[i][j];
				cell.setType(type);	
				cell.updateColor();
				cell.show();
			}
		}
	}
	
	private void updateCells(Cell[][] cells, int[][][][] shapemap) 
	{
		for(int i = 0; i < occupiedcells.length; i++)
		{
			for(int j = 0; j < occupiedcells[i].length; j++)
			{
				oldcells[i][j] = occupiedcells[i][j];
			}
		}
		
		for(int i = 0; i < shapemap[rotation].length; i++)
		{
			for(int j = 0; j < shapemap[rotation][i].length; j++)
			{
				int xOff = shapemap[rotation][i][j][0];
				int yOff = shapemap[rotation][i][j][1];
				occupiedcells[i][j] = cells[x + xOff][y + yOff];
				Cell cell = occupiedcells[i][j];
				cell.setType(type);	
				cell.updateColor();
				cell.show();
			}
		}
		
		for(int i = 0; i < oldcells.length; i++)
		{
			for(int j = 0; j < oldcells[i].length; j++)
			{
				if( isActuallyOldCell(oldcells[i][j]) )
				{ 
					oldcells[i][j].setType(0); 
					oldcells[i][j].hide();
				}
			}
		}
	}
	
	private boolean isActuallyOldCell(Cell oldcell)
	{
		for(int i = 0; i < occupiedcells.length; i++)
		{
			for(int j = 0; j < occupiedcells[i].length; j++)
			{
				if(oldcell.equals(occupiedcells[i][j]))
				{ 
					return false;
				}
			}
		}
		return true;
	}
//================================================================================
//---------------------------------CELL RENDERING LOGIC---------------------------
//================================================================================
	
	

//================================================================================
//---------------------------------ACTIONS----------------------------------------
//================================================================================
	protected void checkThenMove(int newx, int newy, Cell[][] cells, int[][][][] map)
	{
		if( !isfrozen )
		{
			if(newx < 0)
				{ if( leftClear(cells) ) moveX(newx, cells, map); }
			else if (newx > 0)
				{ if( rightClear(cells) ) moveX(newx, cells, map);  }
			
			if(newy < 0)
				{ if( topClear(cells) ) moveY(newy, cells, map);  }
			else if (newy > 0)
				{ 
					if( bottomClear(cells) ) moveY(newy, cells, map);
					else if(freezespd < 1) { freeze(); freezespd = FREEZE_TIME; }
					else freezespd--;
				}
		}
	}
	
	private void moveX(int newx, Cell[][] cells, int[][][][] map)
	{
		x += newx;
		updateCells(cells, map);
		delayFreeze();
	}
	private  void moveY(int newy, Cell[][] cells, int[][][][] map)
	{
		y += newy;
		updateCells(cells, map);
		delayFreeze();
	}

//================================================================================
//---------------------------------ACTIONS----------------------------------------
//================================================================================

	
	
	
//================================================================================
//---------------------------------FREEZING---------------------------------------
//================================================================================
	private void freeze()
	{ 
		isfrozen = true; 
		for(int i = 0; i < occupiedcells.length; i++)
		{
			for(int j = 0; j < occupiedcells[i].length; j++)
			{ 
				Cell cell = occupiedcells[i][j];
				cell.freeze(); 
				cell.setType(1);
				cell.updateColor();
				cell.show();
			}
		}
	}
	
	private void delayFreeze()
	{ if(freezespd < FREEZE_TIME) freezespd++; }

//================================================================================
//---------------------------------FREEZING---------------------------------------
//================================================================================
	


//================================================================================
//---------------------------------COLLISION DETECTORS----------------------------
//================================================================================
	private boolean leftClear(Cell[][] cells)
	{
		for(int i = 0; i < occupiedcells.length; i++)
		{
			for(int j = 0; j < occupiedcells[i].length; j++)
			{
				Cell cell = occupiedcells[i][j];
				
				if(cell.isLeftCell()) return false;
				else
				{
					int x = cell.getCellX() - 1;
					int y = cell.getCellY();
					Cell nextCell = cells[x][y];
					if(nextCell.isFrozen()) return false;
				}
			}
		}
		return true;
	}

	private boolean rightClear(Cell[][] cells)
	{
		for(int i = 0; i < occupiedcells.length; i++)
		{
			for(int j = 0; j < occupiedcells[i].length; j++)
			{
				Cell cell = occupiedcells[i][j];
				
				if(cell.isRightCell()) return false;
				else
				{
					int x = cell.getCellX() + 1;
					int y = cell.getCellY();
					Cell nextCell = cells[x][y];
					if(nextCell.isFrozen()) return false;
				}
			}
		}
		return true;
	}
	private boolean topClear(Cell[][] cells)
	{
		for(int i = 0; i < occupiedcells.length; i++)
		{
			for(int j = 0; j < occupiedcells[i].length; j++)
			{
				Cell cell = occupiedcells[i][j];
				
				if(cell.isTopCell()) return false;
				else
				{
					int x = cell.getCellX() + 1;
					int y = cell.getCellY();
					Cell nextCell = cells[x][y];
					if(nextCell.isFrozen()) return false;
				}
			}
		}
		return true;
	}
	
	private boolean bottomClear(Cell[][] cells)
	{
		for(int i = 0; i < occupiedcells.length; i++)
		{
			for(int j = 0; j < occupiedcells[i].length; j++)
			{
				Cell cell = occupiedcells[i][j];
						
				if(cell.isBottomCell()) return false;
				else
				{
					int x = cell.getCellX();
					int y = cell.getCellY() + 1;
					Cell nextCell = cells[x][y];
					if(nextCell.isFrozen()) return false;
				}
			}
		}
		return true;
	}
//================================================================================
//---------------------------------COLLISION DETECTORS---------------------------
//================================================================================
	
	

//================================================================================
//---------------------------------ROTATIONS--------------------------------------
//================================================================================	
	protected void rotate(Cell[][] cells, int[][][][] shapemap)
	{
		updateCells(cells, shapemap);
		delayFreeze();
	}
	
	protected int newRotation(int current)
	{
		if(current>= 3) return 0;
		else return current + 1;
	}
	
	protected boolean rotationClear(Cell[][] cells, int[][][][] shapemap)
	{
		int newRot = newRotation(rotation);
		
		int curX = occupiedcells[0][0].getCellX();
		int curY = occupiedcells[0][0].getCellY();
		
		for(int k = 0; k < 3; k++)
		{
			boolean blocksafe = true;
			
			for(int i = 0; i < occupiedcells.length; i++)
			{
				for(int j = 0; j < occupiedcells[i].length; j++)
				{ 	
					int mapX = shapemap[newRot][i][j][0];
					int mapY = shapemap[newRot][i][j][1];
					int newX = curX + mapX;
					int newY = curY + mapY;
					
					if(newX < 0 || newX > cells.length-1 || 
					   newY < 0 || newY > cells[0].length-1)
						{ blocksafe = false; }
					else if ( cells[newX][newY].isFrozen() ) 
						{ blocksafe = false; }
				}
			}
			if(blocksafe) { rotation = newRot; return true; }
			else newRot = newRotation(newRot);
		}
		return false;
	}
//================================================================================
//---------------------------------ROTATIONS--------------------------------------
//================================================================================

	
	
	
//================================================================================
//---------------------------------GETTERS----------------------------------------
//================================================================================
	protected int getRotation()
		{ return rotation; }
	protected boolean isFrozen()
		{ return isfrozen; }
	protected int getType()
		{ return type; }
//================================================================================
//---------------------------------GETTERS----------------------------------------
//================================================================================
}
