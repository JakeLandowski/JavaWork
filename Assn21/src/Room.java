// OBJECT CLASS FOR BUILDING OBJECT CLASS

public class Room 
{
	private double cost;
	private int roomNumber;
	private boolean occupied;
	private char building;
	
	public Room(char letter, int roomnum, double roomcost)
	{
		building = letter;
		roomNumber = roomnum;
		cost = roomcost;
		occupied = false;
	}
	
	public String getRoomName()
	{
		return "" + building + roomNumber;
	}
	
	public String toString()
	{
		String room = "Room " + getRoomName(); 
		if(occupied){ return room + " : is occupied."; }
		else		{ return room + " : is empty."; }
	}
	
	public void rentRoom()
	{
		occupied = true;
	}
	
	public void emptyRoom()
	{
		occupied = false;
	}
	
	public boolean getOccupied()
	{
		return occupied;
	}
	
	public double getCost()
	{
		return cost;
	}
}
