//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #3, 1/16/17
//
//	This program calculates the salary and take home pay
//  of employee Bobbu. It calculates the tax and bonus
//	amounts separately, then calculates the total pay
//	and prints the results of all calculations.

public class Assignment3 
{	
	public static void main(String[] args)
	{		//	CONSTANTS REPRESENT THE % RATES
		final double EMPLOYEE_TAX = 40.00;
		final double EMPLOYEE_BONUS = 15.00;
		double employeeSalary = 45000.00;
			//	Calc DOUBLES HOLD THE AMOUNT AFTER % RATE APPLIED TO SALARY
		double taxCalc = employeeSalary * (EMPLOYEE_TAX / 100.0);
		double bonusCalc = employeeSalary * (EMPLOYEE_BONUS / 100.0);
		double payTotal = employeeSalary - taxCalc + bonusCalc;
		
		String employeeName = "Bobbu";
		
		System.out.println(employeeName + " has a salary of $" + employeeSalary + ".");
		System.out.println(employeeName + " has gets a bonus of $" + bonusCalc + " (" + EMPLOYEE_BONUS + "%).");
		System.out.println(employeeName + " has to pay taxes of $" + taxCalc + " (" + EMPLOYEE_TAX + "%).");
		System.out.println(employeeName + " gets payed $" + payTotal + ".");
	}
}
