//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #17, 2/18/17
//
//	This program grabs a file specified by the user,
//	extracts the text to a string, then scans the words
//	counting the number of chars in each, appending a *
//	to an array, then displaying the array.

import java.util.*;
import java.io.*;

public class Assignment17 
{
	public static void main(String[] args)
	throws FileNotFoundException
	{
		wordLengths( getFileName() );
	}
	
	
		//	GET NAME OF FILE FROM USER
	public static String getFileName()
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the name of the file : ");
		String name = in.nextLine();
		
		if( !name.endsWith(".txt") )
			 name += ".txt";
		
		return name;
	}
	
		//	KINDA DOES EVERYTHING ACCORDING TO ASSIGNMENT
	public static void wordLengths(String name) 
	throws FileNotFoundException
	{
		String[] charCounter = new String[20];
		
		File file = new File(name);
		Scanner scan = new Scanner(file);
		String contents = fileToString(scan);
		scan.close();
		
		popCounter(charCounter);
		countChars(contents, charCounter);
		
		displayCount(charCounter);
	}
	
		
		//	OPEN FILE, COPY LINES TO STRING
		//	CLOSE FILE, RETURN STRING
	public static String fileToString(Scanner file)
	{
		String contents = "";
		
		while( file.hasNextLine() )
		{
			contents += file.nextLine() + "\n";
		}
		return contents;
	}
	
	
		//	POPULATE THE COUNTER ARRAY WITH 0's
	public static void popCounter(String[] counter)
	{
		for(int i = 0; i < counter.length; i++)
		{
			counter[i] = "0";
		}
	}
	
		//	LOOP THROUGH THE STRING, CALC WORD LENGTH
		//	ADD TO COUNTER ARRAY
	public static void countChars(String contents, String[] counter)
	{
		Scanner page = new Scanner(contents);
		while( page.hasNextLine() )
		{
			Scanner line = new Scanner( page.nextLine() );
			while( line.hasNext() )
			{
				String token = line.next();
				int chars = token.length();
				
				if(chars < 21)
					counterAdd(chars, counter);
			}
		}
	}
	
	
		//	IF FIRST TIME APPENDING, CLEAR STRING POSITION
		//	APPEND * BASED ON NUMBER PASSEd IN
	public static void counterAdd(int num, String[] counter)
	{
		if( counter[num].startsWith("0") )
			counter[num] = "";
			
		for(int i = 0; i < num; i++)
			counter[num] += "*";
	}
	
	
		//	DISPLAY THE DIAGRAM
	public static void displayCount(String[] counter)
	{
		for(int i = 0; i < counter.length; i++)
		{
			System.out.printf("%3d: " + counter[i] + "%n", i+1);
		}
	}
}
