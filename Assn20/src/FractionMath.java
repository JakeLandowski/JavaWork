/* CS141 Practice Object File #2
 * Fractions
 * Use this class to test your first Object
 * You should not change this file in any way
 * but your BankAccount.java file should work
 *  with it.
 */

public class FractionMath
{
  public static void main(String[] arguments)
  {
    // create four fractions accounts
    Fraction w = new Fraction();
    Fraction x = new Fraction(3,4);
    Fraction y = new Fraction(2,5);
    Fraction z = new Fraction(6,0);
 
 
    System.out.println("*** Does the toStringMethod work?");    
    System.out.println("First fraction  : " + w);    
    System.out.println("Second fraction : " + x);    
    System.out.println("Third  fraction : " + y);    
    System.out.println("Forth  fraction : " + z);    
    System.out.println();   
    
    // Does the get() method work?    
    System.out.println("*** Does the get() methods work?");    
    System.out.println("w = " + w.getNumerator() + " over " + w.getDenominator());  
    System.out.println();
    
     // Can we change the values correctly name
    System.out.println("*** Does the set() methods work?");
    System.out.println("w fraction Before : " + w); 
    w.setNumerator(22);
    System.out.println("w fraction After  : " + w); 
    w.setDenominator(55);
    System.out.println("w fraction After  : " + w); 
    w.setDenominator(0);
    System.out.println("w fraction After  : " + w);    
    System.out.println("The above line should be no different");  

    // Does the equality work
    System.out.println("Is " +w+ " eqaul to " +x+ "?");
    System.out.println("    " + w.equals(x) );
    System.out.println("Is " +w+ " eqaul to " +y+ "?");
    System.out.println("    " + w.equals(y) ); 
    System.out.println();
  
    // Does the getDecimal work
    System.out.printf(x + " = %4.2f %n", x.getDecimalValue());
    System.out.printf(y + " = %4.2f %n", y.getDecimalValue());
    System.out.printf(z + " = %4.2f %n", z.getDecimalValue());
    System.out.println();
     
     // Does the multiply work
    System.out.println("x fraction Before  : " + x); 
    System.out.println("y fraction Before  : " + y); 
    x.multiply(y);
    System.out.println("x fraction After   : " + x); 
    System.out.println("y fraction After   : " + y); 
    System.out.println("*** Test #2 ***");
    System.out.println("y fraction Before  : " + y); 
    System.out.println("z fraction Before  : " + z); 
    y.multiply(z);
    System.out.println("y fraction After   : " + y); 
    System.out.println("z fraction After   : " + z); 
   
  }
}