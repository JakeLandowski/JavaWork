//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #2, 1/12/17
//
//	This program spells out MISSISSIPPI in large ASCII
//	characters. The main method calls each letter to print 
//	as well as calling a newline. Each letter printing
//	method uses a loop to print each line following a 
//	string pattern based on the iteration.

public class Mississippi 
{
	public static void main(String[] args)
	{
		printM();
		space();
		printI();
		space();
		printS();
		space();
		printS();
		space();
		printI();
		space();
		printS();
		space();
		printS();
		space();
		printI();
		space();
		printP();
		space();
		printP();
		space();
		printI();
	}
	
	public static void printM()
	{					//	ARRAY HOLDS 4 STRINGS
		int i, pat;
		String[] parts = { "M      M",
						   "MM    MM",
						   "M M  M M",
						   "M  MM  M" };
		
		pat = 1;		//	PATTERN SWITCH MULTIPLIES COUNTER
						//	BY 1 OR 0 TO REVERT TO FIRST PATTERN
		for(i = 0; i < 7; i++)
		{
			if(i > 3){ pat = 0; }	//	AFTER 4 LINES REVERTS TO STRING i[0] ONLY
			System.out.println(parts[i * pat]);
		}
	}
	
	public static void printI()
	{
		int i;
		
		String part;
		
		for(i = 0; i < 7; i++)
		{						//	TERNARY SWITCHES BETWEEN 2 PATTERNS EACH ITERATION
			part = (i==0 || i==6) ? "IIIIIIII" : "   II   ";
			System.out.println(part);
		}
		
	}
	
	public static void printS()
	{
		int i;
		
		String part;
		
		for(i = 0; i < 7; i++)
		{							//	NESTED TERNARY TO SWITCH PATTERNS
			part = (i==0 || i==3 || i==6) ? " SSSSSS " : 
				   (i==1 || i==5) ? "S      S" : 
				   (i==2) ? "S       " : "       S";
			System.out.println(part);
		}
	}
	
	public static void printP()
	{
		int i;
		String part;
		
		for(i = 0; i < 7; i++)
		{							//	NESTED TERNARY TO SWITCH PATTERNS
			part = (i==0 || i==3) ? "PPPPPPP " : 
				   (i<3) ? "P      P" : "P       ";
			System.out.println(part);
		}
	}
	
	public static void space()
	{						
		System.out.println();	//	EMPTY LINE TO BREAK CHARACTERS
	}
}
