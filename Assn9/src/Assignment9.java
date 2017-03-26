//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #8, 1/25/17
//
//	This program receives a month and year as input
//	then calculates the days based on which month it is
//	calculating leap years only if input is 2 (February)

import java.util.*;

public class Assignment9 
{
	public static void main(String[] args) 
	{
		Scanner read = new Scanner(System.in);
		
		System.out.print("Please enter a month as a single number: ");
		int mon = read.nextInt();
		System.out.print("\nPlease enter a full year : ");
		int yr = read.nextInt();
		
		int days = daysInMonth(mon, yr);
			
			//	FOR DISPLAYING WHERE ERROR IS
		String err;
		while(days < 0)	//	REDO IF ERROR
		{
				//	ERROR CODE SHOWS WHERE ERROR IS
			err = (days == -1) ? "year" : "month";
			System.out.println("\nIncorrect " + err + "\n"); 
			
			System.out.print("Please enter a month as a number (2) : ");
			mon = read.nextInt();
			System.out.print("\nPlease enter a full year (2017) : ");
			yr = read.nextInt();
			
					//	CALL METHOD TO GET DAYS
			days = daysInMonth(mon, yr);	
		}
	
			//	DISPLAY DAYS IN MONTH AND FORMATTED DATE
		System.out.println("\nThere are " + days + " days in that month");
		System.out.println("Your date : " + mon + "/" +
										days + "/" + 
										yr);
	}
	
	public static int daysInMonth(int month, int year)
	{
			//	BASE DAY NUMBER
		int day = 30;
			//	TESTS IF ODD/EVEN
		int monthMod = month % 2;
		
			//	IF YEAR IS VALID
		if(year > 99)
		{			//	IF MONTH IS VALID
			if(0 < month && month < 13)
			{		//	ODD/EVEN PATTERN CHANGES PAST 8
				if(month < 8)
				{		//	IF FEBRUARY
					if(month == 2)
					{		//	TEST IF LEAP 
							//	IF LEAP -1 / NOT LEAP -2
						return day - (leapDayChange(year));
					}
					else
					{			//	DAY - 1 OR 0 BASED ON MONTH
						return day + monthMod; 
					}
				}
				else
				{				//	DAY - 0 OR 1 BASED ON MONTH (REVERSED)
					return day + (1 - monthMod);
				}
			}
			return -2;	//	ERROR CODE FOR MONTH
		}
		
		return -1;	//	ERROR CODE FOR YEAR
	}
	
	public static int leapDayChange(int year)
	{		//	IF EVENLY DIVISIBLE 4
		if(year % 4 == 0)
		{			//	IF EVENLY DIVISIBLE 100	but not 400
			if(year % 100 == 0 && !(year % 400 == 0))
				{ return 2; }	//	NOT LEAP
			else 
				{ return 1; }	// IS LEAP
		}
		else 
			{ return 2; }  //	NOT LEAP
	}
}
