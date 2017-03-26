/* CS141 Assignment 21 Start file
 * 
 * Part 2
 *
 * This program is an object file that is designed to 
 * manage a resort of building and their available rooms.
 */

/* Students should not modifiy this file, but add the 
 * neccessary object files to implement it  
 *
 * This file assumes that you completed part 1 and have the 
 * room and building classes done, and are ready to 
 * implement the resort class.
 * 
 */
 
import java.util.Scanner;

public class ResortManagement2
{
 
  public static void main(String[] args)
  {
    Resort myResort = new Resort(4);
    myResort.setBuilding(0,'A', 5, 150);
    myResort.setBuilding(1,'B', 3, 166);
    myResort.setBuilding(2,'C', 7, 250);
    myResort.setBuilding(3,'D', 10, 750);
    
    runLoop(myResort);
  }
  
  public static void runLoop(Resort resortVar )
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
      {
        System.out.println("What building would you like to rent from?");
        char let = keyboard.next().toUpperCase().charAt(0);
        resortVar.rentRoom(let);      
      }
      else if(x == 2)
      { 
        resortVar.printOccupiedRooms();
        System.out.println("What Room would you like to check out?");
        String room = keyboard.next().toUpperCase();
        resortVar.checkout(room);
       }
      else if(x == 3)
      {
        resortVar.printResortSmallStatus(); 
      }
      else if(x == 4)
      {
        resortVar.printResortLargeStatus(); 
      }
      else if(x == 0)
      {
        keepGoing = false;
      }
      else {}
      
      try {Thread.sleep(500);} catch(InterruptedException ex) {Thread.currentThread().interrupt();}
    }
  }
   
}