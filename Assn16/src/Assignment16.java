//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #16, 2/18/17
//
//	This program creates an size 10 String array,
//	then populates it with single words from user.
//	Then it upper or lower cases each word based
//	on odd or even position. Then displays twice,
//	once as a normal sentence, then as a plain array.

import java.util.*; // ARRAYS AND SCANNER CLASSES

public class Assignment16 
{
		//	DOES MAIN STUFF DUH
	public static void main(String[] args)
	{
		String[] wordList = new String[10];
		fillList(wordList);
		l33tifyList(wordList);
		displaySentence(wordList);
		displayArray(wordList);
	}
	
		//	POPULATE ARRAY FROM USER INPUT x10
	public static void fillList(String[] list)
	{
		for(int i = 0; i < 10; i++)
		{
			list[i] = getWord(i + 1);
		}
	}
	
		//	GET SINGLE STRING TOKEN FROM USER INPUT
		//	INT FOR PROMPT NUMBER LABEL
	public static String getWord(int iter)
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Please type in word #" + iter + " : ");
		return in.next();
	}
	
		//	MAKE WORDS UPPER OR LOWER IN ARRAY
		//	INT USED TO FLIP BACK N FORTH
	public static void l33tifyList(String[] list)
	{
		int flip = 0;
		
		for(int i = 0; i < list.length; i++)
		{
			if(flip == 0)
				list[i] = list[i].toLowerCase();
			else
				list[i] = list[i].toUpperCase();
			
			flip = 1 - flip;
		}
	}
	
	
		//	DISPLAY AS REGULAR SENTENCE
	public static void displaySentence(String[] list)
	{
		String sent = "\nYour sentence is:\n\n";
		
		sent += list[0];
		
		for(int i = 1; i < list.length; i++)
		{
			sent += " " + list[i];
		}
		
		sent += ".";
		
		System.out.println(sent + "\n");
	}
	
		//	DISPLAY PLAIN ARRAY AS STRING
	public static void displayArray(String[] list)
	{
		System.out.println("As a string it would look like:\n" +
							Arrays.toString(list));
	}
}
