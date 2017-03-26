//	Jacob Landowski
//	Date: 1/28/17
//	IT 219 CONSOLE QUIZ

import acm.program.*;

public class Quiz extends ConsoleProgram
{
	public static final int APPLICATION_WIDTH = 500;
	public static final int APPLICATION_HEIGHT = 500;
	public static final String CORR1 = "b";
	public static final String CORR2 = "c";
	public static final String CORR3 = "a";
	
	public void init()
	{
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
	}
	
	public void run()
	{
		String[] results = { 
				"You need more practice!",
	   		    "You need to warm up!",
			    "You need to warm up!",
			    "You are on your way!",
			    "Well, at least you got the hard one correct!",
			    "You are amazing!",
			    "You are amazing!",
			    "You are a scholar!" 
							};
		int score = 0;
		score += easy();
		score += medium();
		score += hard();
		
		print(results[score]);
	}
	
	public int easy()
	{
		println("Easy: Roses are red, violets are?");
		println("a. green");
		println("b. blue");
		println("c. banana");
		String ans = readLine();
		
		if(ans.compareTo(CORR1) == 0) 
			{ return 1; }
		else 
			{ return 0; }
	}
	
	public int medium()
	{
		println("Medium: Strings in java are?");
		println("a. Primitives");
		println("b. No");
		println("c. Objects");
		String ans = readLine();
		
		if(ans.compareTo(CORR2) == 0) 
			{ return 2; }
		else 
			{ return 0; }
	}
	
	public int hard()
	{
		println("Hard: What was the middle name of late US Author Truman Capote?");
		println("a. Streckfus");
		println("b. Bibbitybobbity");
		println("c. Baggins");
		String ans = readLine();
		
		if(ans.compareTo(CORR3) == 0) 
			{ return 4; }
		else 
			{ return 0; }
	}
}


