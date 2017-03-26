//	ROOM OBJECT FOR RESORTMANAGEMENT CLIENT

public class Building 
{
	private Room[] List;
	private char buildingLetter;
	private int size;
	
	public Building(char letter, int thesize, double price)
	{
		buildingLetter = letter;
		size = thesize;
		
		List = new Room[size];
		for(int i = 0; i < size; i++)
		{
			List[i] = new Room(buildingLetter, i+1, price);
		}
	}
	
	public String toString()
	{
		return "Building " + buildingLetter + 
			   " has " + size + " rooms : " + getNumEmpty();
	}
	
	private int getNumEmpty()
	{
		int count = 0;
		for(int i = 0; i < List.length; i++)
		{
			if(List[i].getOccupied()) { count++; }
		}
		return count;
	}
	
	public void printCurrentStatus()
	{
		System.out.println("Building " + buildingLetter);
		for(int i = 0; i < List.length; i++)
		{
			System.out.println("    " + List[i]);
		}
	}
	
	public double getValue()
	{
		double value = 0;
		for(int i = 0; i < List.length; i++)
		{
			Room room = List[i];
			boolean occupied = room.getOccupied();
			
			if(occupied){ value += List[i].getCost(); }
		}
		return value;
	}
	
	public String listOfEmpty()
	{
		String empty = "";
		
		for(int i = 0; i < List.length; i++)
		{
			Room room = List[i];
			boolean isempty = !room.getOccupied();
			
			if(isempty){ empty += buildingLetter + (i+1); }
		}
		return empty;
	}
	
	public String listOfNonEmpty()
	{
		String empty = "";
		for(int i = 0; i < List.length; i++)
		{
			Room room = List[i];
			boolean occupied = room.getOccupied();
			
			if(occupied)
			{ 
				String roomname = "" + buildingLetter + (i+1) + " ";
				empty += roomname; 
			}
		}
		return empty;
	}
	
	public int currentEmpty()
	{
		int empty = 0;
		for(int i = 0; i < List.length; i++)
		{
			Room room = List[i];
			boolean isempty = !room.getOccupied();
			
			if(isempty){ empty++; }
		}
		return empty;
	}
	
	public String rentRoom()
	{
		for(int i = 0; i < List.length; i++)
		{
			Room room = List[i];
			boolean isempty = !room.getOccupied();
			
			if(isempty)
			{ 
				room.rentRoom();
				return "" + buildingLetter + (i+1); 
			}
		}
		return "error";
	}
	
	public boolean checkOut(String roomname)
	{
		if(roomname.charAt(0) == buildingLetter)
		{
			String extractnum = roomname.substring(1);
			int roomnum = Integer.parseInt(extractnum);
			Room room = List[roomnum-1];
			int range = List.length + 1;
			
			if(roomnum > 0 && roomnum <= range)
			{
				room.emptyRoom();
				return true;
			}
			else
			{
				return false;
			}
		}
		else { return false; }
	}
}
