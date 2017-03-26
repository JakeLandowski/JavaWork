import acm.util.*;
import acm.program.ConsoleProgram;

public class GuessingGame extends ConsoleProgram
{
	public static final int APPLICATION_WIDTH = 1200;
	public static final int APPLICATION_HEIGHT = 800;
	
	public void init()
	{
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}
	
		//	POPULATES ARRAY WITH RANDOM INTS
		//	CALLS CHECKINPUT FOR INT INPUT
		//	CHECKS 3 TIMES FOR CORRECT ANSWER
		//	IF NOT CORRECT THEN END GAME
	public void run()
	{
		int[] answers = new int[5];
		int guess;
		int tries = 3;
		boolean finished = false;
		
		for(int i = 0; i < answers.length; i++)
		{
			answers[i] = getRandomPick();
		}
		
		while( !finished )
		{
			guess = checkInput();
			if( !isCorrect(guess, answers) && tries > 1)	
			{ 
				tries--;
				println("Incorrect try again [Lives: " + tries + "]");
			}
			else if( isCorrect(guess, answers) )
			{
				finished = true;
				println("You win!");
			}
			else
			{
				println("Thank you for playing");
				finished = true;
			}
		}
	}
	
		//	CHECK IF GUESSED INT MATCHES RANDOM INT IN ARRAY
	public boolean isCorrect(int guess, int[] correct)
	{
		for(int i = 0; i < correct.length; i++)
		{
			if(guess == correct[i])
				{ return true; }
		}
		
		return false;
	}
	
		//	RANDOM INT 1-20
	public int getRandomPick()
	{
		return new RandomGenerator().nextInt(1, 20);
	}
	
	
		//	GET INPUT FROM USER
		//	VALIDATES INPUT FOR A
		//	NUM BETWEEN 1-20
	public int checkInput()
	{
		String guess = "";
		boolean valid = false;
		String sub = "";
		char pos1, pos2;
		pos1 = pos2 = 'f';
		int num = -1;
		
		do
		{
			print("Guess a number between 1-20 : ");
			guess = readLine();
			int len = guess.length();
			
			if(len > 0)
			{ 
				pos1 = guess.charAt(0);
				sub = guess.substring(0, 1);
				
				if( Character.isDigit(pos1) )
				{
					num = Integer.parseInt(sub);
					if(num > 0 && num < 21)
						{ valid = true; }
				}
			}
			else if(len > 1)
			{
				pos2 = guess.charAt(1); 
				sub = guess.substring(0, 2);
				
				if( Character.isDigit(pos1) && Character.isDigit(pos2) )
				{
					num = Integer.parseInt(sub);
					if(num > 0 && num < 21)
						{ valid = true; }
				}
			}
			
			
		} 
		while( !valid );
		
		return num;
	}
}
