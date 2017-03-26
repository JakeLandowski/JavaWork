//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #18, 2/24/17
//
//	This program parses a personality data file
//	calculating the personality based on A or B
//	answers, mapped to sections of the string of data

import java.util.*;	//	SCANNER
import java.io.*;	//	PRINTSTREAM

public class Personality 
{
	private static PrintStream so = System.out;
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args)
    throws FileNotFoundException
	{
		intro();
		String sourceFileName = getReadFileName();
		String destinedFileName = getWriteFileName();
		Scanner dataFile = getReadFile(sourceFileName);
		String fileContents = extractFileContents(dataFile);
		dataFile.close();
		
			String output = parseData(fileContents);
		
		createOutputFile(destinedFileName, output);
	}
	
	public static void intro()
	{
		so.println("This program processes a file of answers to the\n" + 
				  "Keirsey Temperament Sorter. It converts the\n" + 
				  "various A and B answers for each person into\n" + 
				  "a sequence of B-percentages and then into a\n" +
				  "four-letter personality type.\n");
	}
	
	public static String getReadFileName()
	{
		so.print("input file name? ");
		String input = in.nextLine();
		
		if( !input.endsWith(".txt") )
			{ input += ".txt"; }
		
		return input;
	}
	
	public static String getWriteFileName()
	{
		so.print("output file name? ");
		String input = in.nextLine();
		
		if( !input.endsWith(".txt") )
			{ input += ".txt"; }
		
		return input;
	}
	
		//	SCANNER CONTAINING DATA FILE
	public static Scanner getReadFile(String name) 
	throws FileNotFoundException
	{
		File file = new File(name);
		Scanner sc = new Scanner(file);
		return sc;
	}
	
		//	DUMP FILE CONTENTS INTO STRING THEN CLOSE
	public static String extractFileContents(Scanner file)
	{
		String contents = "";
		
		while(file.hasNextLine())
		{
			contents += file.nextLine() + "\n";
		}
		
		return contents;
	}
	
	
		//	CREATE FRESH FILE, AND DUMP OUTPUT/RESULTS
		//	THEN CLOSE
	public static void createOutputFile(String name, String output) 
	throws FileNotFoundException
	{
		PrintStream file = new PrintStream(new File(name));
		file.print(output);
		file.close();
		so.println("\nFile has been written.");
	}
	
//------------------------------------------------------------------
//-----------------------PARSING PHASE------------------------------
//------------------------------------------------------------------
	
	
		//  MAIN PARSING METHOD
		//	LOOP THROUGH LINES
		//	EVERY ODD = NAME
		//	EVERY EVEN = DATA
		//	CALL PARSING METHODS ON DATA
		//	APPEND ALL RELEVANT INFO TO STRING TO RETURN
	public static String parseData(String contents)
	{
		Scanner doc = new Scanner(contents);
		String output = "";
		String line; 
		boolean isName = true;
		
		while(doc.hasNextLine())
		{
			line = doc.nextLine();
			if(isName)
			{
				output += line + ": "; 
				isName = false;
			}
			else
			{ 
				String[] chunks = chunkifyLine(line);
				long[] encryptedData = encryptChunks(chunks);
				int[] results = getResults(encryptedData);
				output += Arrays.toString(results) + " = ";
				output += determinePersonality(results) + "\n";
				isName = true;
			}
		}
		
		return output;
	}
	
	
		//	SPLIT DATA LINE INTO 10 SIZE-7 CHUNKS
		//	RETURN ARRAY OF STRING CHUNKS
	public static String[] chunkifyLine(String line)
	{
		String[] Chunks = new String[10];
		for(int i = 0; i < Chunks.length; i++)
		{
			Chunks[i] = line.substring(i*7, i*7 + 7);
		}
		return Chunks;
	}
	
		//	PARSE STRING CHUNKS AND ENCRYPT DATA INTO NUMBER
		//	DIMENSIONS = { E/I, S/N, T/F, J/P}
		//	MAP = KEY FOR CHUNK TO DIMENSION
		//	A = 2, B = 3, DASH = 5
		//	MULT CHARS TOGETHER FOR SINGLE NUM
		//	PLACE NUM IN DIMENSION, RETURN
	public static long[] encryptChunks(String[] Chunks)
	{
		long[] dimensions = { 1, 1, 1, 1 };
		int[] map = { 0, 1, 1, 2, 2, 3, 3 };
		
		for(int i = 0; i < Chunks.length; i++)
		{
			String chunk = Chunks[i];
			for(int j = 0; j < chunk.length(); j++)
			{
				char answer = Character.toUpperCase(chunk.charAt(j));
				switch(answer)
				{
					case 'A':
						dimensions[map[j]] *= 2;
						break;
					case 'B':
						dimensions[map[j]] *= 3;
						break;
					/*case '-':
						dimensions[map[j]] *= 5;
						break;*/
				}
			}
		}
		return dimensions;
	}
	
		//	GET ARRAY OF B ANSWER PERCENTAGES 
		//	FOR EACH ATTRIBUTE/DIMENSION
	public static int[] getResults(long[] encrypted)
	{
		int[] dimensions = new int[4];
		for(int i = 0; i < encrypted.length; i++)
		{
			long blob = encrypted[i];
			int A = decipherA(blob);
			int B = decipherB(blob);
			//int Dash = decipherDash(blob);
			
			double total = (double) (A + B);
			dimensions[i] =  (int) Math.round((B/total * 100));
		}
		return dimensions;
	}
	
		//	IF ENCRYPTED NUM EVENLY DIVISIBLE
		//	BY 2, INCREMENT NUM OF A's
	public static int decipherA(long num)
	{
		int a = 0;
		while(num % 2 == 0)
			{ 
				a++;
				num /= 2;
			}
		return a;
	}
		//	IF ENCRYPTED NUM EVENLY DIVISIBLE
		//	BY 3, INCREMENT NUM OF B's
	public static int decipherB(long num)
	{
		int b = 0;
		while(num % 3 == 0)
			{ 
				b++;
				num /= 3;
			}
		return b;
	}
	/*public static int decipherDash(int num)
	{
		int d = 0;
		while(num % 5 == 0)
			{ 
				d++; 
				num /= 2;
			}
		return d;
	}*/
	
		//	DETERMINE END PERSONALITY FROM
		//	B PERCENTAGES IN DIMENSIONS ARRAY
	public static String determinePersonality(int[] results)
	{
		char[][] possible = { {'E', 'I'},
							  {'S', 'N'},
							  {'T', 'F'},
							  {'J', 'P'} };
		String personality = "";
		for(int i = 0; i < results.length; i++)
		{
			if(results[i] > 50)
				{ personality += possible[i][1]; }
			else if(results[i] < 50)
				{ personality += possible[i][0]; }
			else
				{ personality += 'X'; }
		}
		return personality;
	}
}
