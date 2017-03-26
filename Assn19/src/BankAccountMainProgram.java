/* CS141 Practice Object File
 * Use this class to test your first Object
 * You should not change this file in any way
 * but your BankAccount.java file should work
 *  with it.
 */

public class BankAccountMainProgram
{
  public static void main(String[] arguments)
  {
    // create three bank accounts
   BankAccount accountNum1 = new BankAccount();
   BankAccount accountNum2 = new BankAccount(1233203, 105.51, "Betty");
   BankAccount accountNum3 = new BankAccount(6542345, 33.11, "Veronica");   
   
   // Test the two String Method
   System.out.println("*** Does the toStringMethod work?");
   System.out.println("First account  : " + accountNum1); 
   System.out.println("Second account : " + accountNum2); 
   System.out.println("Third  account : " + accountNum3); 
   System.out.println();
   
   // Do the get() method work?
   System.out.println("*** Does the get() methods work?");
   System.out.println(accountNum2.getName() + " has  $" + accountNum2.getBalance() +
                      " in account number " + accountNum2.getID() + "."); 
   System.out.println();

   // Can we change the account name
    System.out.println("*** Does the set() methods work?");
    System.out.println("Second account Before : " + accountNum2); 
    accountNum2.setName("Archie");
    System.out.println("Second account After  : " + accountNum2); 

   // Can we change the account name
    System.out.println();
    System.out.println("*** Does the changeBalance() methods work?");
    System.out.println("Third account Before : " + accountNum3); 
    accountNum3.changeBalance(10.10);
    System.out.println("Third account After  : " + accountNum3); 
    accountNum3.changeBalance(-50);
    System.out.println("Third account After  : " + accountNum3); 
  
  }
}