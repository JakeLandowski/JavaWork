//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #14, 2/12/17
//
//	This program reads a file of employee information
//	and loops through each line in the file and 
//	extracts the data and calculates pay, then displays it.

import java.io.*;
import java.util.*;

public class Assignment14
{						//	CONST FOR TAX %
	private static final double TAX = 25.00;
	
	public static void main(String[] args) throws FileNotFoundException
	{
		File employeeFile = new File("CS141 Assign14.txt");
		Scanner fileRead = new Scanner(employeeFile);
		extractData(fileRead);
	}
	
		//	LOOPS THROUGH FILE, CALLS METHOD TO EXTRACT DATA FROM LINE
		//	THEN CALLS DISPLAY METHOD TO DISPLAY FORMATTED DATA
	public static void extractData(Scanner file)
	{
		while(file.hasNext())
		{
			Scanner lineScan = new Scanner(file.nextLine());
			Object data[] = decipherLine(lineScan);
			
			String name = (String) data[0];
			double rate = (double) data[1];
			int hours = (int) data[2];
			
			displayInfo(name, rate, hours);
		}
	}
	
		//	LOOP THROUGH A LINE, SUM THE HOURS, RETURN DATA
	public static Object[] decipherLine(Scanner line)
	{
		Object[] data = new Object[3];
		
		data[0] = line.next();
		data[1] = line.nextDouble();
		int sum = 0;
		 
		while(line.hasNext())
		{
			sum += line.nextInt();
		}
		
		data[2] = sum;
		
		return data;
	}
	
		//	DISPLAY INFORMATION FORMATTED
	public static void displayInfo(String name, double rate, int hours)
	{
		System.out.printf(name + " worked for a total of " + 
						  hours + " hours at $%.2f an hour for " +
						  "a gross pay of $%.2f%n",
						  rate, (rate*hours));
		
		System.out.printf("After %.0f%% taxes their total net " + 
						  "pay should be $%.2f%n",
						  TAX, (rate*hours) * ((100-TAX)/100));
		System.out.println("\n------------------------------------" +
						   "--------------------------------------\n");
	}
}
