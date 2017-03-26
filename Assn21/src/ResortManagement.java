/* CS141 Assignment 21 Start file
 * 
 * Part 1
 *
 * This program is an object file that is designed to 
 * manage a resort of building and their available rooms.
 */

/* Students should not modifiy this file, but add the 
 * neccessary object files to implement it
 */
 
/* Also this file contains methods/algorithms that you may
 * want to copy when completing part 2.  Permission is given
 * for this assignment to plagerize this work to help you 
 * with part 2.
 */
 
 
import java.util.Scanner;

public class ResortManagement
{
 
  public static void main(String[] args)
  {
    Building[] resort = new Building[4];
    resort[0] = new Building('A', 5, 150);
    resort[1] = new Building('B', 4, 250);
    resort[2] = new Building('C', 6, 100);
    resort[3] = new Building('D', 10, 75);
    
    runLoop(resort);
  }
  
  public static void runLoop(Building[] resort )
  {
    boolean keepGoing = true;
    Scanner keyboard = new Scanner(System.in);
    
    while(keepGoing)
    {
      System.out.println("What would you like to do?");
      System.out.println("     1. Rent a room?");
      System.out.println("     2. Check out a room?");
      System.out.println("     3. Print a summary");
      System.out.println("     4. Print a large overview");
      System.out.println("     0. Quit");
      
      int x = keyboard.nextInt();
      
      if(x == 1)
      {rentARoom(resort, keyboard) ;}
      else if(x == 2)
      { checkOutOfARoom(resort, keyboard);    }
      else if(x == 3)
      {printResortSmallStatus(resort); }
      else if(x == 4)
      {printResortLargeStatus(resort); }
       else if(x == 0)
      {keepGoing = false;}
      else {}
      
      try {Thread.sleep(500);} catch(InterruptedException ex) {Thread.currentThread().interrupt();}
    }
  }
  public static void checkOutOfARoom(Building[] resortList, Scanner in)
  {
        printOccupiedRooms(resortList);
        System.out.println("What Room would you like to check out?");
        String room = in.next().toUpperCase();
        checkout(resortList, room);
   }
  public static void rentARoom(Building[] resortList, Scanner in)
  {
        System.out.println("What building would you like to rent from?");
        char let = in.next().toUpperCase().charAt(0);
        int num = let - 'A';
        rentRoom(resortList, num);   
  }
    
  public static void printOccupiedRooms(Building[] resortList)
  {
    System.out.print("The currently occupied rooms are : ");
    for (int i = 0; i < resortList.length ; i ++)
    { 
     System.out.print(resortList[i].listOfNonEmpty() );
    }   
     System.out.println();    
  }
  
  
  public static void checkout(Building[] resortList,  String roomName)
  {
    boolean removed = false;
    for (int i = 0; i < resortList.length ; i ++)
    { 
     if (resortList[i].checkOut(roomName))
     {
      removed = true;
     }
    }
    
    if (removed) System.out.printf("Room %s was cleared. %n",roomName);
    else System.out.printf("No Room with that name was found.%n");
  }
  
  public static void rentRoom(Building[] resortList, int buildingNumber)
  {
    if (buildingNumber >= 0 && buildingNumber < resortList.length)
    {
     String x;  
     x = resortList[buildingNumber].rentRoom();    
     if (!x.equals("error")) System.out.printf("Room %s was rented out.%n",x);
     else System.out.println("Sorry, no rooms in that building available.");
     
    }
    else System.out.println("Sorry, no building of that letter found.");
  }
  
  public static double getValue(Building[] resortList)
  {
    double val = 0;
    for (int i = 0; i < resortList.length ; i ++)
    { 
     val += resortList[i].getValue();
    }
    return val;
  }
  
  public static int getEmpty(Building[] resortList)
  {
    int val = 0;
    for (int i = 0; i < resortList.length ; i ++)
    { 
     val += resortList[i].currentEmpty();
    }
    return val;
  }
    
  public static void printResortSmallStatus(Building[] resortList)
  {
    System.out.println("************************************");
    System.out.println("** Quick Status of the Resort");
    for (int i = 0; i < resortList.length ; i ++)
    { 
     System.out.println("** " + resortList[i]);
    }
    System.out.println("************************************");
    System.out.print("** ");
    printOccupiedRooms(resortList);
    System.out.printf("** The current value of the resort is $%5.2f.%n",getValue(resortList));
    System.out.printf("** There are %4d empty rooms.%n",getEmpty(resortList));
    System.out.println("************************************");

  }
  
  public static void printResortLargeStatus(Building[] resortList)
  {
    System.out.println("**********Expanded Status of the Resort*******");  
    for (int i = 0; i < resortList.length ; i ++)
    { 
     resortList[i].printCurrentStatus();
    }
     System.out.println("#####");  
     System.out.println("#####");  
   
  }

 
}