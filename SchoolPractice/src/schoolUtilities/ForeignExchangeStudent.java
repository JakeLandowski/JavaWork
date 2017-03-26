/* Subclass for Student to create ForeignExchangeStudent objects
 * P.Turylo 03/02/2017
 */

package schoolUtilities;

public class ForeignExchangeStudent extends Student {
	
	//new instance variable
	private String language;
	
	//default constructor
	public ForeignExchangeStudent()
	{
		//Note: subclass inherits studentID and name
		this.studentID = 0;
		this.name = "";
		this.language = "";
		increaseCount(); //increase the total count of students
	}
	
	//parameterized constructor -an example of method "overloading"
	//Overloading: methods with same name inside the same class -different input parameters!
	public ForeignExchangeStudent(int studentID, String name, String language)
	{
		this.studentID = studentID;
		this.name = name;
		this.language = language;
		increaseCount(); //increase the total count of students
		
	}
	
	//new set method
	public void setLanguage(String language)
	{
		this.language = language;
	}
	
	//new get methods
	public String getLanguage()
	{
		return this.language;
	}
	
	//new method to get name -this one "overrides" the version in the superclass
	//Overriding: methods with same name and same input parameters -in different classes
	//where one class is inheriting from the other.
	public String getName()
	{
		return "Student name is: " + this.name;
	}
	
	
	

}
