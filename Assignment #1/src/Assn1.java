//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #1, 1/10/17
//
//	This program uses a loop method to print each line reusing the same strings as templates
//	and is fed information when called in main() through a String array. The loop is a simple 
//	for loop that creates each line within the box in 3 parts, the left border, the information,
//	then the remaining whitespace and right border. Whitespace is determined by length of information.
//
//	I joined the class late.

public class Assn1 {

	public static void main(String[] args) {
		String[] info = new String[]{"Jacob R. Landowski", "Jake", "Soup", "Vermintide (Amazing, google it)"};
		printLoop(info);
	}
	
	public static void printLoop(String[] meat){
		String bread = "/////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\";
		String ketchup = "// ";
		String mayo = "                                  \\\\";
		int count;
		
		System.out.println(bread);			//	meat is the information, length
											//	of meat determines substring of	
		for(count = 0; count < 4; count++){ //	mayo which is the whitespace + right border	  
			System.out.println(ketchup + meat[count] + mayo.substring(meat[count].length() ));
		}
		
		System.out.println(bread);
	}

}
