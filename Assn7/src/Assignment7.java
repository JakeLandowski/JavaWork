//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #7, 1/22/17
//
//	This program passes a string to method padString
//	who checks if the new length requested is longer
//	than the current word length. If so, it will add
//	spaces equivalent to the difference. Also added
//	quotes in the literal for clarity.

public class Assignment7 
{
	public static void main(String[] args) 
	{
		System.out.println( "To 8  : " + padString("Hello", 8) );
		System.out.println( "To 5  : " + padString("Hello", 5) );
		System.out.println( "To 3  : " + padString("Hello", 3) );
		System.out.println( "To 13 : " + padString("Hello", 13) );
	}

	public static String padString(String word, int newLength)
	{
		String newString = "\"" + word;
		int length = word.length();
		
			//	ONLY ADDS SPACES IF NEW LENGTH > OLD LENGTH	
		if(length < newLength)
		{					//	LOOPS BASED ON DIFFERENCE
			for(int i = 0; i < (newLength - length); i++)
			{
				newString += " ";
			}
		}
		
		newString += "\"";
		return newString;
	}
}
