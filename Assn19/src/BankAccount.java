//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #19, 3/1/17
//
//	Object Class for BankAccountMainProgram
//	Has : Name, ID, Balance
//	Set : Name
//	Get : Name, ID, Balance
//	Can : changeBalance(double)

public class BankAccount 
{
	private int ID;
	private String Name;
	private double balance;
	
		//	CONSTRUCTOR
		//	DEFAULT
	public BankAccount()
	{
		ID = 0;
		balance = 0;
		Name = "unknown";
	}
	
		//	CONSTRUCTOR
		//	INT, DOUBLE, STRING
	public BankAccount(int newID, double newBalance, String newName)
	{
		ID = newID;
		balance = newBalance;
		setName(newName);
	}
	
	public String toString()
	{
		return Name + " [" + ID + "] = $" + balance;
	}
	
	public String getName()
	{
		return Name;
	}
	
	public double getBalance()
	{
		return balance;
	}
	
	public int getID()
	{
		return ID;
	}
	
	public void setName(String newName)
	{
		Name = newName;
	}
	
		//	ADDS DOUBLE TO BALANCE
		//	BALANCE CANNOT GO BELOW ZERO
	public void changeBalance(double x)
	{
		balance += x;
		if(balance < 0)
			{ balance = 0; }
	}
}
