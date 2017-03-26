//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #21, 3/3/17

public class Resort 
{
	int size;
	Building[] buildings;
	String buildingmap;
	
	public Resort(int numbuildings)
	{
		size = numbuildings;
		buildings = new Building[numbuildings];
		buildingmap = "";
		
		for(int i = 0; i < numbuildings; i++)
			{ buildingmap += "0"; }
	}
	
//--------------------------------------------------------------------------------
//---------------------------PUBLIC SETTERS---------------------------------------
//--------------------------------------------------------------------------------
	public void setBuilding(int index, char letter, int rooms, double price)
	{
		buildings[index] = new Building(letter, rooms, price);
		replaceMapIndex(index, letter);
	}
	
//--------------------------------------------------------------------------------
//---------------------------PUBLIC SETTERS---------------------------------------
//--------------------------------------------------------------------------------
	

//--------------------------------------------------------------------------------
//---------------------------PUBLIC UTILITIES-------------------------------------
//--------------------------------------------------------------------------------
	
		//	RENT FIRST ROOM IN BUILDING SPECIFIED
		//	USE BUILDINGMAP STRING TO FIND INDEX OF BUILDING FROM LETTER
	public void rentRoom(char letter)
	{
		if(isInCharMap(letter))
		{
			int index = buildingmap.indexOf(letter);
			String x = buildings[index].rentRoom();
		
			if (!x.equals("error")) System.out.printf("Room %s was rented out.%n",x);
			else System.out.println("Sorry, no rooms in that building available.");
	     
		}
	    else System.out.println("Sorry, no building of that letter found.");
	}
		
		//	EXTRACT LETTER FROM NAME
		//	FIND BUILDING INDEX USING BUILDINGMAP STRING
		//	CALL CHECKOUT() ON BUILDING PASSING NAME
	public void checkout(String roomname)
	{
		char letter = roomname.charAt(0);
		int index = buildingmap.indexOf(letter);
		boolean removed = false;
	    
	    if (roomname.length() > 1 && buildings[index].checkOut(roomname))
	    {
	      removed = true;
	    }
	    
	    
	    if (removed) System.out.printf("Room %s was cleared. %n",roomname);
	    else System.out.printf("No Room with that name was found.%n");  
	}

//--------------------------------------------------------------------------------
//---------------------------PUBLIC UTILITIES-------------------------------------
//--------------------------------------------------------------------------------
	
	
//--------------------------------------------------------------------------------
//-----------------------PUBLIC PRINT STATUS METHODS------------------------------
//--------------------------------------------------------------------------------
	public void printOccupiedRooms()
	{
		System.out.print("The currently occupied rooms are : ");
	    for (int i = 0; i < buildings.length ; i ++)
	    { 
	     System.out.print(buildings[i].listOfNonEmpty() );
	    }   
	    System.out.println();
	}
	
	public void printResortSmallStatus()
	  {
	    System.out.println("************************************");
	    System.out.println("** Quick Status of the Resort");
	    for (int i = 0; i < buildings.length ; i ++)
	    { 
	     System.out.println("** " + buildings[i]);
	    }
	    System.out.println("************************************");
	    System.out.print("** ");
	    printOccupiedRooms(buildings);
	    System.out.printf("** The current value of the resort is $%5.2f.%n",getValue(buildings));
	    System.out.printf("** There are %4d empty rooms.%n",getEmpty(buildings));
	    System.out.println("************************************");
	  }
	  
	  public void printResortLargeStatus()
	  {
	    System.out.println("**********Expanded Status of the Resort*******");  
	    for (int i = 0; i < buildings.length ; i ++)
	    { 
	     buildings[i].printCurrentStatus();
	    }
	     System.out.println("#####");  
	     System.out.println("#####");  
	   
	  }
//--------------------------------------------------------------------------------
//-----------------------PUBLIC PRINT STATUS METHODS------------------------------
//--------------------------------------------------------------------------------
	  
	  
//--------------------------------------------------------------------------------
//----------------------------PRIVATE GETTERS-------------------------------------
//--------------------------------------------------------------------------------
	  private double getValue(Building[] resortList)
	  {
	    double val = 0;
	    for (int i = 0; i < resortList.length ; i ++)
	    { 
	     val += resortList[i].getValue();
	    }
	    return val;
	  }
	  
	  private int getEmpty(Building[] resortList)
	  {
	    int val = 0;
	    for (int i = 0; i < resortList.length ; i ++)
	    { 
	     val += resortList[i].currentEmpty();
	    }
	    return val;
	  }
//--------------------------------------------------------------------------------
//----------------------------PRIVATE GETTERS-------------------------------------
//--------------------------------------------------------------------------------

	  
//--------------------------------------------------------------------------------
//---------------------------PRIVATE HELPERS--------------------------------------
//--------------------------------------------------------------------------------
	private void printOccupiedRooms(Building[] resortList)
	{
	    System.out.print("The currently occupied rooms are : ");
	    for (int i = 0; i < resortList.length ; i ++)
	    { 
	     System.out.print(resortList[i].listOfNonEmpty() );
	    }   
	     System.out.println();    
	}
	  
	private void replaceMapIndex(int index, char letter)
	{
		String chunk1 = buildingmap.substring(0, index);
		String chunk2 = buildingmap.substring(index + 1);
		buildingmap = chunk1 + letter + chunk2;
	}
	
	private boolean isInCharMap(char letter)
	{
		for(int i = 0; i < buildingmap.length(); i++)
		{
			if(buildingmap.charAt(i) == letter) return true;
		}
		return false;
	}
//--------------------------------------------------------------------------------
//---------------------------PRIVATE HELPERS--------------------------------------
//--------------------------------------------------------------------------------
}