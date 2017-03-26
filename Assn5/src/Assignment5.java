//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #5, 1/19/17
//
//	This program prints a triangle of numbers filled 
//	with dashes to complete a square. The numbers count
//	up by 2 starting with 1, printing only odd numbers.
//	It prints only the last digit of a number (modulo 10)

public class Assignment5 
{
	public static void main(String[] args)
	{
		int height = 5;
//		int height = 9;
//		int height = 10;
		int width = 15;
		printDesign(height, width);	//	CALL METHOD THAT PRINTS DESIGN
	}
						//	HEIGHT = ROWS, Y && WIDTH = COLUMNS, X
	public static void printDesign(int rows, int cols)
	{
		int dashCalc;		//	CALCS NUMBER OF DASHES ON BOTH SIDES
		char dash = '-';	//	ADJACENT TO NUMBER PRINT
		
		for(int y = 1; y <= rows*2; y+=2)	//	1, 3, 5, 7, 9...
		{
			dashCalc = (cols - y) / 2;	
			int x;
			
			for(x = 0; x < dashCalc; x++)	//	PRINTS LEFT SIDE DASHES
			{
				System.out.print(dash);
			}				 //	PREVENTS OVERFLOW
							 //	REMOVED TO MATCH ASSNMNT
			for(x = 0; x < y /*&& x < cols*/; x++)	//	PRINTS NUMBERS
			{
				System.out.print(y % 10);	//	MOD 10 RETURNS ONLY LAST DIGIT
			}
			
			for(x = 0; x < dashCalc; x++)	//	PRINTS RIGHT SIDE DASHES
			{
				System.out.print(dash);
			}
			System.out.println();
		}
		
	}
}
