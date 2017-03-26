//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #11, 2/5/17
//
//	This program uses a while loop to count
//	through the factors of 2 of an integer,
//	returning then in a string.

public class Assignment11
{
	public static void main(String[] args)
	{
		System.out.println(showTwos(7));
		System.out.println(showTwos(18));
		System.out.println(showTwos(68));
		System.out.println(showTwos(120));
		System.out.println(showTwos(192));
	}
	
	public static String showTwos(int num)
	{
		String result = num + " = ";
		while(num % 2 == 0)
		{
			result += "2 * ";
			num /= 2;
		}
		return result += num;
	}
}
