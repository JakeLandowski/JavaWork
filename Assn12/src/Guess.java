//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #12 Guess, 2/5/17
//
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class Guess 
{
	private static PrintStream o = System.out;
	private static Scanner in = new Scanner(System.in);

	private static final int MAX = 100;
	
		//	VARIABLES GATHER GAME STATS
		//	LOOP WHILE YES
		//	ASK IF PLAY AGAIN AT END
	public static void main(String[] args)
	{
		Random rng = new Random();
		boolean playAgain =  true;
		int guess = 0;
		int bestGuess = 10000;
		int totalGuesses = 0;
		int totalGames = 0;
		
		intro();
		while(playAgain)
		{
			totalGames++;
			guess = game(rng.nextInt(MAX) + 1);
			bestGuess = (guess < bestGuess) ? guess : bestGuess;
			totalGuesses += guess;
			
			playAgain = yorn();
		}
		results(totalGames, totalGuesses, bestGuess);
	}
	
	public static void intro()
	{
		o.println("This program allows you to play a guessing game.");
		o.println("I will think of a number between 1 and");
		o.println("100 and will allow you to guess until");
		o.println("you get it. For each guess, I will tell you");
		o.println("whether the right answer is higher or lower");
		o.println("than your guess.\n");
	}
	
		//	PLAY A FULL GAME
		//	LOOP WHILE GUESS INCORRECT
		//	CALL METHOD TO TEST IF LOWER/HIGHER IF INCORRECT
	public static int game(int answer)
	{
		int guesses = 0;
		boolean done = false;
		o.println("I'm thinking of a number between 1 and " + MAX);
		while(!done)
		{
			guesses++;
			int guess = getGuess();
			if(answer != guess) { o.println(lowerHigher(guess, answer)); }
			else { done = true; }
		}
		if(guesses == 1)
			{ o.println("You got it right in " + guesses + " guess.\n"); }
		else
			{ o.println("You got it right in " + guesses + " guesses.\n"); }
		return guesses;
	}
		//	GET INPUT DURING GAME
	public static int getGuess()
	{
		o.print("Your guess? ");
		int resp = in.nextInt();
		return resp;
	}
		//	RETURN STRING AS FEEDBACK HOT OR COLD
		//	NOT THE BEST CODE
	public static String lowerHigher(int guess, int answer)
	{
			 if(guess > answer) { return "It's lower."; }
		else if(guess < answer) { return "It's higher."; }
		return ""; // NOT USED
	}
	
		//	FORMATS AND DISPLAYS THE TOTAL RESULTS GATHERED 
	public static void results(int games, int guesses, int best)
	{
		double avgGuess = (double) guesses/games;
		o.println("Overall results:\n");
		o.printf("%-16s = %d%n%n", "Total Games", games);
		
		o.printf("%-16s = %d%n", "Total Guesses", guesses);
		o.printf("%-16s = %.1f%n", "Guesses per Game", avgGuess);
		o.printf("%-16s = %d", "Best Game", best);
	}
	
		//	PLAY AGAIN? YES OR NO
		//	RETURN BOOL
	public static boolean yorn()
	{
		o.print("Do you want to play again? ");
		while(true)
		{
			String resp = in.next();
			if(resp.substring(0, 1).compareToIgnoreCase("y") == 0)
				{ return true; }
			else if(resp.substring(0, 1).compareToIgnoreCase("n") == 0)
				{ return false; }
		}
	}
}

