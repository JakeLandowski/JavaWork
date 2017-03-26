/* Subclass for Student to create ForeignExchangeStudent objects
 * P.Turylo 03/02/2017
 */

package schoolUtilities;

public class DomesticStudent extends Student {
	
	//new instance variable
	private int fafsaID;
	
	//default constructor
	public DomesticStudent()
	{
		//Note: subclass inherits studentID and name
		this.studentID = 0;
		this.name = "";
		this.fafsaID = 0;
		increaseCount(); //increase the total count of students
	}
	
	//parameterized constructor -an example of method "overloading"
	//Overloading: methods with same name inside the same class but with different input parameters!
	public DomesticStudent(int studentID, String name, int fafsaID)
	{
		this.studentID = studentID;
		this.name = name;
		this.fafsaID = fafsaID;
		increaseCount(); //increase the total count of students
		
	}
	
	//new set method
	public void setFafsaID(int fafsaID)
	{
		this.fafsaID = fafsaID;
	}
	
	//new get methods
	public int getFafsaID()
	{
		return this.fafsaID;
	}
	

	
	

}
