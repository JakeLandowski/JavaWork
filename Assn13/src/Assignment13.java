//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #13, 2/12/17
//
//	This program creates a scanner from a text file full of 
// 	integers, then passes the file scanner to a method
//	that loops through the file and adds all integers
//	returning the total to be printed.

import java.io.*;
import java.util.*;

public class Assignment13 
{
	public static void main(String[] args)
			throws FileNotFoundException
	{
		File numbersFile = new File("CS141 Assign13.txt");
		Scanner fileRead = new Scanner(numbersFile);
		System.out.println(sumNumbers(fileRead));
	}
	
	public static int sumNumbers(Scanner fileOfNums)
	{
		int sum = 0;
		while(fileOfNums.hasNext())
		{
			int num = fileOfNums.nextInt();
			sum += num;
		}
		return sum;
	}
}
