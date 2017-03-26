//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #20, 3/2/17
//
//	Object Class for FractionMath.java
//	Con : default, 2 ints
//	Has : int numerator, int denominator
//	Set : setNumerator(), setDenominator()
//	Get : getNumerator(). getDenominator(), getDecimalValue()
//	Can : toString(), equals(), multiply()

public class Fraction 
{
	private int numerator;
	private int denominator;
	
//------------CONSTRUCTORS-----------------
	
	public Fraction()
	{
		numerator = 1;
		denominator = 1;
	}
	
	public Fraction(int newNumer, int newDenom)
	{
		if(newDenom == 0){ newDenom = 1; }
		
		numerator = newNumer;
		denominator = newDenom;
	}
	
//------------CONSTRUCTORS-----------------

//------------SETTERS----------------------
	
	public void setNumerator(int newNumer)
	{
		numerator = newNumer;
	}
	
	public void setDenominator(int newDenom)
	{
		if(newDenom != 0){ denominator = newDenom; }
	}
	
//------------SETTERS----------------------
	
//------------GETTERS----------------------
	
	public int getNumerator()
	{
		return numerator;
	}
	
	public int getDenominator()
	{
		return denominator;
	}
	
	public double getDecimalValue()
	{
		return (double) numerator / denominator;
	}
	
//------------GETTERS----------------------

	public String toString()
	{
		if(denominator == 1)
		{
			return " " + numerator + " ";
		}
		else
		{
			return " " + numerator + "/" + denominator + " ";
		}
	}
	
		//	DECIMAL == DECIMAL
	public boolean equals(Fraction other)
	{
		double otherDec = other.getDecimalValue();
		double thisDec = getDecimalValue(); 
		if(thisDec == otherDec)
			{ return true; }
		else 
			{ return false; }
	}
	
		//	MULT N*N  D*D
	public void multiply(Fraction other)
	{
		int otherN = other.getNumerator();
		int otherD = other.getDenominator();
		numerator *= otherN;
		denominator *= otherD;
	}
	
	
	
	
	
	
}
