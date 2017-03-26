//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #4, 1/16/17
//
//	This PRogram prints the Fibonacci Sequence with 
//	seed numbers -1 and 3. 
//
//	You said only 3 variables, but hopefully you 
//	don't count the for loop variable. =p

public class Assignment4 
{
	public static void main(String[] args)
	{
		int i;
		int x = -1;
		int y = 3;
		int f = x + y;
		
		System.out.print(x + ", " + y + ", ");
		for(i = 0; i < 19; i++)
		{
			System.out.print(f + ", ");
			x = y;
			y = f;
			f  = x + y;
		}
		System.out.print(f);
	}
}
