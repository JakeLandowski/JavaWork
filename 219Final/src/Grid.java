import java.awt.Color;
import java.awt.Font;

import acm.graphics.*;
import acm.util.RandomGenerator;

public class Grid extends GCompound
{
	private final int FALL_TIME = 70;
	
	private Cell[][] cells;
	private Shape shape;
	private int spawnCellX, spawnCellY;
	private int fallDirectionTimer;
	private boolean gameIsOver, restartAccepted;
	private RandomGenerator rng;
	private GLabel gameOverLabel, restartGameLabel1, restartGameLabel2;
	
	
//================================================================================
//---------------------------------CONSTUCTION------------------------------------
//================================================================================
	
	public Grid(int width, int height, int cellsize)
	{
		int smaller = (width < height) ? width : height;
		setLabels(smaller);
		
		rng = new RandomGenerator();
		gameIsOver = restartAccepted = false;
		fallDirectionTimer = FALL_TIME;
		cells = new Cell[width/cellsize][height/cellsize];
		
		populateGrid(cellsize);
		
		spawnCellX = cells[cells.length/2-1][0].getCellX();
		spawnCellY = 0;
		
		setBoundaryCells();
		spawnShape();
	}
	
	private void setLabels(int fontsizer)
	{
		int fontsize = (int) (fontsizer/8.92857142857);
		Font font = new Font("Sans-Serif", Font.BOLD, fontsize);
		gameOverLabel = new GLabel("GAME OVER");
		restartGameLabel1 = new GLabel("Press Spacebar");
		restartGameLabel2 = new GLabel("To Restart");
		
		gameOverLabel.setFont(font);
		gameOverLabel.setColor(Color.WHITE);
		
		restartGameLabel1.setFont(font);
		restartGameLabel1.setColor(Color.WHITE);
		restartGameLabel2.setFont(font);
		restartGameLabel2.setColor(Color.WHITE);
	}
	
	private void populateGrid(int cellsize)
	{
		for(int i = 0; i < cells.length; i++)
		{
			for(int j = 0; j < cells[i].length; j++)
			{
				int x = i * cellsize;
				int y = j * cellsize;
				
				Cell cell = new Cell(i, j, cellsize);
				add(cell, x, y);
				cells[i][j] = cell;
			}
		}
	}
	

	private void setBoundaryCells()
	{
		int rowLen = cells.length;
		int colLen = cells[0].length;
		
		for(int i = 0; i < rowLen; i++)
			{ cells[i][0].markAsTopCell(); }
		
		for(int i = 0; i < rowLen; i++)
			{ cells[i][colLen-1].markAsBottomCell(); }
		
		for(int i = 0; i < colLen; i++)
			{ cells[0][i].markAsLeftCell(); }
		
		for(int i = 0; i < colLen; i++)
			{ cells[rowLen-1][i].markAsRightCell(); }
	}
//================================================================================
//---------------------------------CONSTUCTION------------------------------------
//================================================================================

//================================================================================
//---------------------------------ACTIONS----------------------------------------
//================================================================================
	private void spawnShape()
	{
		if( spawnIsSafe() ) 
		{
			fallDirectionTimer = FALL_TIME;
			shape = getRandomShape();
		}
		else gameOver();
	}
	private boolean spawnIsSafe()
	{
		if(shape != null)
		{
			int[][][][] form = getMapFromType();
			int rotation = shape.getRotation();
			
			for(int i = 0; i < form[rotation].length; i++)
			{
				for(int j = 0; j < form[rotation][i].length; j++)
				{
					int xOff = form[rotation][i][j][0];
					int yOff = form[rotation][i][j][1];
					Cell cell = cells[spawnCellX + xOff][spawnCellY + yOff];
					
					if( cell.isFrozen() ) return false;
				}
			}
		}
		return true;
	}
	
	protected void rotateShapes()
	{
		if(shape != null) 
		{
			int[][][][] map = getMapFromType();
			if( shape.rotationClear(cells, map) ) shape.rotate(cells, map);
		}
	}
	
	public void moveShapes(int moveX, int moveY)
	{	
		if( !gameIsOver() )
		{	
			shape.checkThenMove(moveX, moveY, cells, getMapFromType());
		}
		else if(restartAccepted) restartGame(); 
	}
	
	public void fall(boolean downpressed)
	{
		if(shape != null)
		{
			if(downpressed || fallDirectionTimer < 1) 
			{
				moveShapes(0, 1);
				checkFrozen();
				fallDirectionTimer = FALL_TIME;
			}
			else fallDirectionTimer--;
		}
	}
	
	public void moveLeft()
		{ if(shape != null) moveShapes(-1, 0); }
	
	public void moveRight()
		{ if(shape != null) moveShapes(1, 0); }
//================================================================================
//---------------------------------ACTIONS----------------------------------------
//================================================================================
	
	

//================================================================================
//---------------------------------GET----------------------------------------
//================================================================================	
	private int[][][][] getMapFromType()
	{	
		switch(shape.getType())
		{
			case 2: return ShapeO.getMap();				
			case 3: return ShapeI.getMap();
			case 4:	return ShapeT.getMap();
			case 5: return ShapeJ.getMap();
			case 6: return ShapeL.getMap();
			case 7: return ShapeS.getMap();
			case 8: return ShapeZ.getMap();
			default: return ShapeO.getMap();
		}
	}
	
	
	private Shape getRandomShape()
	{
		switch(rng.nextInt(2, 8))
		{
			case 2: return new ShapeO(spawnCellX, spawnCellY, cells);				
			case 3: return new ShapeI(spawnCellX, spawnCellY, cells);
			case 4:	return new ShapeT(spawnCellX, spawnCellY, cells);
			case 5: return new ShapeJ(spawnCellX, spawnCellY, cells);
			case 6: return new ShapeL(spawnCellX, spawnCellY, cells);
			case 7: return new ShapeS(spawnCellX, spawnCellY, cells);
			case 8: return new ShapeZ(spawnCellX, spawnCellY, cells);
			default: return new ShapeO(spawnCellX, spawnCellY, cells);
		}
	}
//================================================================================
//---------------------------------GET--------------------------------------------
//================================================================================	

	
//================================================================================
//---------------------------------DELETE FULL ROWS-------------------------------
//================================================================================		
	private void checkFrozen()
	{
		if(shape.isFrozen()) 
		{
			checkForFullRow();
			spawnShape(); 
		}
	}
	
	private void checkForFullRow()
	{
		
			//	COUNT COL LENGTH AKA NUM OF ROWS
		for(int i = 0; i < cells[0].length; i++)
		{		
			boolean rowfull = true;
				//	COUNT ROW FORWARD
			for(int j = 0; j < cells.length; j++)
			{
					//	J = CELLX COUNTING FORWARD
					//	I = CELLY COUNTING FORWARD
				if( !cells[j][i].isFrozen() ) rowfull = false;
			}
			
			if(rowfull)
			{
				deleteRow(i);
				pullAboveRowsDown(i);
			}
		}
	}
	
	private void deleteRow(int row)
	{
		
		for(int i = 0; i < cells.length; i++)
		{
				//	I = CELL IN ROW
				//	ROW = CELLX
			cells[i][row].unFreeze();
			cells[i][row].setType(0);
			cells[i][row].updateColor();
			cells[i][row].hide();
		}
	}
	
	private void pullAboveRowsDown(int row)
	{
		for(int i = 0; i < row; i++)
			for(int j = 0; j < cells.length; j++)
			{
				if(cells[j][i].isFrozen())
				{
						//	I = CELL IN ROW
						//	ROW/OLDROW = CELLX
					cells[j][i].unFreeze();
					cells[j][i].setType(0);
					cells[j][i].updateColor();
					cells[j][i].hide();
					
					cells[j][row].freeze();
					cells[j][row].setType(1);
					cells[j][row].updateColor();
					cells[j][row].show();
				}
			}
	}
//================================================================================
//---------------------------------DELETE FULL ROWS-------------------------------
//================================================================================

	
	
//================================================================================
//---------------------------------GAME OVER METHODS-----------------------------
//================================================================================
	private void showGameOver()
	{
		add(gameOverLabel, this.getWidth()/2-gameOverLabel.getWidth()/2, this.getHeight()/3);
		add(restartGameLabel1, this.getWidth()/2-restartGameLabel1.getWidth()/2, this.getHeight()/3 + this.getHeight()/3/2);
		add(restartGameLabel2, this.getWidth()/2-restartGameLabel2.getWidth()/2, this.getHeight()/3 + this.getHeight()/3);
	}
	
	private void hideGameOver()
	{
		remove(gameOverLabel);
		remove(restartGameLabel1);
		remove(restartGameLabel2);
	}
	
	
	private void gameOver()
		{ 
			gameIsOver = true; 
			showGameOver();
		}
	public boolean gameIsOver()
		{ return gameIsOver; }
	
	private void refreshGrid()
	{
		for(int i = 0; i < cells.length; i++)
		{
			for(int j = 0; j < cells[i].length; j++)
			{
				Cell cell = cells[i][j];
				if(cell.isFrozen() || cell.getType() != 0)
				{
					cell.unFreeze();
					cell.setType(0);
					cell.updateColor();
					cell.hide();
				}
			}
		}
	}
	
	private void restartGame()
	{
		hideGameOver();
		gameIsOver = false;
		restartAccepted = false;
		refreshGrid();
	}
		
	public void acceptRestart()
		{ restartAccepted = true; }
//================================================================================
//---------------------------------GAME OVER METHODS-----------------------------
//================================================================================
	
}