//	Jacob Landowski
//	Date: 2/22/17
//	IT 219 PASSWORD GENERATOR

import acm.program.*;	//	ACM CONSOLE
import acm.util.*;	//	ACM RANDOM

public class PWGenerator extends ConsoleProgram
{
	public static final int APPLICATION_WIDTH = 1200;
	public static final int APPLICATION_HEIGHT = 800;
	public static final int[] charSet = new int[55];
	
		//	SETUP COMPLEXITY CHARSET AND CONSOLE SIZE
	public void init()
	{
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		setComplexity(charSet);
	}
	
		//	GET LENGTH FROM USER, VALIDATE FOR PROPER
		//	LENGTH, IF 0 EXIT
		//	GET NEW PASS, CHECK PASS, REPEAT TIL GOOD ENOUGH
	public void run()
	{
		boolean finished = false;
		
		while( !finished )
		{	
			int len;
			do
			{
				print("Enter the length of your password\n" +
					  "[Only creates lengths 6-9]\n" +
					  "[Enter 0 to exit the program]: ");
				len = readInt();
				
				if(len == 0)
				{
					finished = true;
					println("\nExiting...");
				}
			}
			while(!finished && len < 6 || len > 9);
				
			if( len != 0)
			{
				boolean meetsComplexity = false;
				String pass = "undetermined";
				
				while( !meetsComplexity )
				{
					pass = getNewPass(len);
					if(isComplex(pass))
						meetsComplexity = true;
				}
				println("\nYour password is: " + pass + "\n");
			}
		}
	}
		
	
		//	CAST RANDOM INTS FROM CHARSET ARRAY
		//	INTO CHARS AND APPEND TO PASSWORD
		//	RETURN NEW PASS
	public String getNewPass(int len)
	{
		String pw = "";
		RandomGenerator rand = new RandomGenerator();
		for(int i = 0; i < len; i++)
		{
			int randChar = rand.nextInt(55);
			int ch = charSet[randChar];
			pw += (char) ch;
		}
		return pw;
	}
	
		//	CHECK IF EACH CHAR HAS ATLEAST
		//	ONE UPPER, ONE LOWER, ONE SPECIAL
		//	RETURN TRUE IF SO, OTHERWISE FALSE
	public boolean isComplex(String token)
	{
		boolean hasUpper = false;
		boolean hasLower = false;
		boolean hasSpecial = false;
		
		for(int i = 0; i < token.length(); i++)
		{
			if( Character.isUpperCase(token.charAt(i)) )
			{
				hasUpper = true;
			}
			else if( Character.isLowerCase(token.charAt(i)) )
			{
				hasLower= true;
			}
			else if( token.charAt(i) == '#' || 
					 token.charAt(i) == '!' ||
					 token.charAt(i) == '@')
			{
				hasSpecial = true;
			}
		}
		if(hasUpper && hasLower && hasSpecial)
			return true;
		else
			return false;
	}
	
		//	LOOPS THROUGH ARRAY 
		//	APPEND CHARSET CODES TO ARRAY
	public void setComplexity(int[] set)
	{
		for(int i = 0; i < 26; i++)
		{
			set[i] = 97 + i;
		}
		
		for(int i = 26; i < 52; i++)
		{
			set[i] = 39 + i;
		}
		
		set[52] = 64;
		set[53] = 33;
		set[54] = 35;
	}
	
}
