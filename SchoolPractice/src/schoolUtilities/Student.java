/* Superclass for Student 
 * P.Turylo 03/02/2017
 */

package schoolUtilities;

public class Student {
	
	//instance variables
	//Note! Cannot set these to private
	//or they cannot be seen in child classes.
	//Protected allows only classes inside the same package to access them
	protected int studentID;
	protected String name;
	
	//special static variable to count total number of students created
	//NOTE: this is static -only one copy of this variable!
	//Unlike all the other instance variables a copy of this does not
	//get created each time a new object is created.
	private static int counter = 0;
	
	//default constructor
	public Student()
	{
		this.studentID = 0;
		this.name = "";
	}
	
	//parameterized constructor -an example of method "overloading"
	//Overloading: methods with same name inside the same class but with different input parameters!
	public Student(int studentID, String name)
	{
		this.studentID = studentID;
		this.name = name;
	}
	
	//set methods - use these methods to set instance variable values
	public void setStudentID(int studentID)
	{
		this.studentID = studentID;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	//NOTE: this method is special! Static -therefore version of this at the class level.
	//Objects of type student do not get a copy of this method.
	public static void increaseCount()
	{
		counter++;
	}
	
	//get methods -use these methods to see instance variable values
	public int getStudentID()
	{
		return studentID;
	}
	public String getName()
	{
		return name;
	}
	//NOTE: this method is special! Static -therefore version of this at the class level.
	//Objects of type student do not get a copy of this method.	
	public static int getCount()
	{
		return counter;
	}
	
}
