//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #15, 2/13/17
//
//	This program opens either Raven or Farm mad lib file
//	then prompts user to replace the words found in the file.
//	It creates an empty file and appends the new contents to it.


import java.util.*;
import java.io.*;

public class Assignment15 
{
	private static final PrintStream o = System.out;
	private static final Scanner in = new Scanner(System.in);
	
	public static void main(String[] args)
		      throws FileNotFoundException
	{
		String fileName = getFileName("open");
		String newFileName = getFileName("save to");
		
		Scanner readFile = openFile(fileName);
		String newFileContents = startLibSearch(readFile);
		
		writeNewFile(newFileName, newFileContents);
	}
	
		//	ASK USER FOR FILE NAME, RETURNS AS STRING
	public static String getFileName(String which)
	{
		o.print("Please enter a file name to " + which + " : ");
		String input = in.nextLine();
		
		if( !input.endsWith(".txt") )
			{ input += ".txt"; }
		
		o.println();
		
		return input;
	}
	
		//	CREATE FILE OBJ OF CHOSEN NAME, OPEN WITH SCANNER, RETURN SCANNER
	public static Scanner openFile(String name)
			       throws FileNotFoundException
	{
		File theFile = new File(name);
		return new Scanner(theFile);
	}
	
	
		//	SCAN A LINE, CALL LINESEARCH
		//	APPEND NEW STRING LINE TO CONTENT STRING
		//	RETURN CONTENT STRING
	public static String startLibSearch(Scanner file)
	{
		String newFileContents = "";
		Scanner lineRead;
		
		while(file.hasNextLine())
		{
			lineRead = new Scanner( file.nextLine() );
			newFileContents += lineSearch(lineRead);
		}
		
		return newFileContents;
		
	}
	
		//	LOOP THROUGH LINE, PROMPT FOR NEW WORDS
		//	APPEND TO CONTENT STRING, RETURN STRING TO LIBSEARCH
	public static String lineSearch(Scanner line)
	{
		String contents = "";
		
		while(line.hasNext())
		{
			String cur = line.next();
			
			if( cur.startsWith("("))
			{
				contents += promptNewWord(cur);
				
					 if(cur.endsWith(".")) { contents += ". "; }
				else if(cur.endsWith(",")) { contents += ", "; }
				else { contents += " "; }
			}
			else
			{
				contents += cur + " ";
			}
		}
		
		contents += "\n";
		
		return contents;
	}
	
		//	PROMPT FOR USER TO ADD A NEW WORD
		//	RETURN NEW WORD AS STRING
	public static String promptNewWord(String oldWord)
	{
		if( oldWord.endsWith(",") || oldWord.endsWith(".") )
		{
			oldWord = oldWord.substring(0, oldWord.length() - 1); 
		}
		
		o.print("While reading your file I found " + oldWord +
				", please type in the replacement : ");
		
		return in.nextLine();
	}
	
		//	OPEN A NEW FILE OBJECT OF CHOSEN NAME
		//	OPEN PRINTSTREAM
		//	WRITE TO FILE, THEN CLOSE FILE
		//	FIN
	public static void writeNewFile(String newName, String contents) 
									    throws FileNotFoundException
	{
		if( !newName.endsWith(".txt") )
			{ newName += ".txt"; }
		
		PrintStream fileWrite = new PrintStream(new File(newName));
		fileWrite.print(contents);
		fileWrite.close();
		
		o.println("Your file \"" + newName + "\" has been created");
	}
	
	
	
}
