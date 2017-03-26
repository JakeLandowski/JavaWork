//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #6, 1/22/17
//
//	This program prints the roots of a polynomial
//	using the quadratic formula.
//
//	NOTE: Ignore the 3 methods below printQuadratic()
//	their only purpose is to format a, b, and c 
//	to look pretty.

public class Assignment6 
{
	public static void main(String[] args)
	{
		double a = 1;
		double b = -7;
		double c = 12;
		
//		double a = 2;
//		double b = 6;
//		double c = 4;
		
		printQuadratic(a, b, c);
	}
	
	public static void printQuadratic(double a, double b, double c)
	{
			//	CALLED METHODS FORMAT NUMBERS
			//	FOR PRETTY PRINTING
		String aText = formatA(a);
		String bText = formatB(b);
		String cText = formatC(c);
		
			//	THE QUADRATIC FORMULA CALC
		double xReal1 = ( -(b) + (Math.sqrt((b * b) - (4 * a * c))) ) / (2 * a);
		double xReal2 = ( -(b) - (Math.sqrt((b * b) - (4 * a * c))) ) / (2 * a);
		
			//	TRUNCATED VERSIONS OF ROOTS
		int xTrunc1 = (int)xReal1;
		int xTrunc2 = (int)xReal2;
		
			//	IF ROOT IS WHOLE TRUNCATE THE .0
			//	SO IT PRINTS AS INTEGER
			//	xReal1 % 1 TESTS IF DECIMAL IS WHOLE
		String x1 = (xReal1 % 1 == 0) ? 
				("" + xTrunc1) : ("" + xReal1);
				
		String x2 = (xReal2 % 1 == 0) ? 
				("" + xTrunc2) : ("" + xReal2);
		
			//	IF NEGATIVE IN SQUARE ROOT = NO SOLUTION
		String xResult = (Double.isNaN(xReal1)) ? 
				("No Solution") : ("x = " + x1 + "\nx = " + x2);

			//	PRINT EVERYTHING TO CONSOLE
		System.out.println(aText + bText + cText + " = 0");
		System.out.println(xResult);
	}
	
	
//	-----------------------------------------------------------------------	
//	CAN IGNORE THESE IT JUST MAKES THEM LOOK PRETTY BUT IS KINDA MESSY CODE
//	-----------------------------------------------------------------------
	
	public static String formatA(double n)
	{
			//	TRUNCATED AND ABSOLUTE VERSIONS
		int nTrunc = (int)n;
		int nTruncAbs = Math.abs(nTrunc);
		double nAbs = Math.abs(n);
		
			//	x^2 AND '-' SYMBOLS FOR PRINTING
		String x = "x\u00B2";
		String neg = "-";
		
			//	n % 1 TESTS IF DECIMAL
		return (n % 1 != 0) ? 
					(			//	IF NEG PREPEND '-' 
						(n < 0) ? (neg + nAbs + x) : (n + x) 
/*IS NOT DECIMAL*/  ):  		
					( 
						(n < 0) ? 
							(	//	IF 1 PRINT X BY ITSELF
/*IF NEG PREPEND '-'*/			(nTruncAbs == 1) ? (neg + x) : (neg + nTruncAbs + x) 
							): 
							( 
/*IF POS PREPEND "+'*/			(nTruncAbs == 1) ? (x) : (nTrunc + x)
							) 
					);
	}
	
	public static String formatB(double n)
	{
		int nTrunc = (int)n;
		int nTruncAbs = Math.abs(nTrunc);
		double nAbs = Math.abs(n);
		
		String x = "x";
		String pos = " + ";
		String neg = " - ";					

		return (n % 1 != 0) ? 
					( 
						(n < 0) ? (neg + nAbs + x) : (pos + n + x) 
					):  
					( 
						(n < 0) ? 
							( 
								(nTruncAbs == 1) ? (neg + x) : (neg + nTruncAbs + x) 
							): 
							( 
								(nTruncAbs == 1) ? (pos + x) : (pos + nTrunc + x) 
							) 
					);
	
	}
	public static String formatC(double n)
	{
		int nTrunc = (int)n;
		int nTruncAbs = Math.abs(nTrunc);
		double nAbs = Math.abs(n);
		
		String pos = " + ";
		String neg = " - ";
	
		return (n % 1 != 0) ? 
					( 
						(n < 0) ? (neg + nAbs) : (pos + n) 
					):  
					(
						(n < 0) ? (neg + nTruncAbs) : (pos + nTrunc)  
					);
	}
}
