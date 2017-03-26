//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #8, 1/25/17
//
//	This program reads 2 doubles from the user.
//	It then calculates 6.5% interest on the starting  
//	amount, then totals, then increments the annual 
//	deposit. I reuse the string padding method from 
//	Assignment7

import java.util.*;
import java.text.*;

public class Assignment8 
{
	public static void main(String[] args) 
	{
		double initDeposit, anuDeposit;
		double interest = 6.5;
		int years = 25;
		
			//	READS USER INPUT
		Scanner reader = new Scanner(System.in);
		
		System.out.print("How much would you like to start with : ");
		initDeposit = reader.nextDouble();
		System.out.print("\nHow much do you deposit each year : ");
		anuDeposit = reader.nextDouble();	
		
		System.out.println("\nThe bank uses 6.5% interest.\n");
		
			//	HANDLES CALCULATION AND TABLE DISPLAY
		printTable(initDeposit, anuDeposit, interest, years);
	}
									//	START		ANNUAL		INTEREST	ITERATIONS
	public static void printTable(double init, double anu, double rate, int years)
	{
			//	FORMATTER FOR 2 DECIMAL PLACES
		DecimalFormat df = new DecimalFormat("#.##");
		String S, I, T, p1, p2, p3, p4;
		double total, gain;
		
			//	*****************************************
		for(int i = 0; i < 20; i++)
			{ System.out.print("*"); }
		
		System.out.println("\n");
		
		for(int i = 1; i <= years; i++)
		{
				//	RETURNS 0.065 * STARTNG
			gain = calcInterest(init, rate);
			
				//	STARTING + INTEREST
			total = init + gain;
			
							//	SHORTENS TO 2 DECIMAL PLACES
			S = "Start = " + df.format(init);
			I = "Interest = " + df.format(gain);
			T = "Total = " + df.format(total) +"\n";
			
				//	RETURNS STRING WITH EXTRA SPACES
				//	HARD CODED BASED ON 25 YEAR LENGTH	
			p1 = padString(("Year " + i), 11);
			p2 = padString(S, 20); 
			p3 = padString(I, 23);
			p4 = T;
			
			
			System.out.print(p1 + p2 + p3 + p4);
			
				//	ADDS ANUAL TO START FOR NEXT ITERATION/YEAR
			init = total + anu;
		}
	}
	
	public static double calcInterest(double base, double perc)
	{
		return base * (perc/100);
	}
	
	public static String padString(String word, int newLength)
	{
		
		int length = word.length();
		
		for(int i = 0; i < (newLength - length); i++)
		{
			word += " ";
		}
	
		return word;
	}
}
