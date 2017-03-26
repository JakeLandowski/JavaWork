//	Jacob Landowski, CS141, Winter 2017, Section 2751
//	Programming Assignment #10, 1/29/17
//
//	This program gathers data from 2 applicants
//	regarding their ACT/SAT exam scores and GPA
//	then calculates a total score. Finally the
//	program compares the two to see who is better

import java.io.PrintStream;
import java.util.*;

public class Assignment10
{		//	SHORTCUTS FOR IN AND OUT	
	public static PrintStream o = System.out;
	public static Scanner in = new Scanner(System.in);
	
		//	CALL INTRO -> FIRST DATA -> SECOND DATA -> 
		//	TOTAL FIRST -> TOTAL SECOND -> COMPARE
	public static void main(String[] args)
	{
		double first, second;
		
		intro();
		
		o.println("Information for applicant #1");
		first = getScore();
		
		o.println("Information for applicant #2");
		second = getScore();
		
		o.printf("First applicant overall score : %.1f\n", first);
		o.printf("Second applicant overall score : %.1f\n", second);
		
		compareApps(first, second);
	}
	
		//	CHOOSE EXAM -> GET EXAM SCORES -> TOTAL EXAM
		//	GET GPA SCORES -> TOTAL GPA -> RETURN BOTH TOTALS
	public static double getScore()
	{
		double exam, gpa;
		
		if(whichExam() == 1) { exam = getSAT(); }
		else				 { exam = getACT(); }
		
		o.printf("\n\tExam Score = %.1f\n\n", exam);
		
		gpa = getGPA();
		o.printf("\n\tGPA Score = %.1f\n\n", gpa);
		
		return exam + gpa;
	}
	public static void intro()
	{
		o.println("This program compares two applications to\n" + 
				  "determine which one seems like the stronger\n" + 
				  "applicant. For each candidate I will need\n" + 
				  "either SAT or ACT scores plus a weighted GPA.\n\n");
	}
	
		//	GET ACT SCORES, CALL CALCULATION, RETURN RESULT
	public static double getACT()
	{
		int e, m, r, s;
		
		o.print("\tACT English? : ");
		e = in.nextInt();
		
		o.print("\tACT Math? : ");
		m = in.nextInt();
		
		o.print("\tACT Reading? : ");
		r = in.nextInt();
		
		o.print("\tACT Science? : ");
		s = in.nextInt();
		
		return calcACT(e, m, r, s);
	}
	
		//	GET SAT SCORES, CALL CALCULATION, RETURN RESULT
	public static double getSAT()
	{
		int m, r, w;
		
		o.print("\tSAT Math? : ");
		m = in.nextInt();
		
		o.print("\tSAT Critial Reading? : ");
		r = in.nextInt();
		
		o.print("\tSAT Writing? : ");
		w = in.nextInt();
		
		return calcSAT(m, r, w);
	}
	
		//	GET GPA SCORES, CALL CALCULATION, RETURN RESULT
	public static double getGPA()
	{
		double over, max, mult;
		
		o.print("\tOverall GPA? : ");
		over = in.nextDouble();
		
		o.print("\tMax GPA? : ");
		max = in.nextDouble();
		
		o.print("\tTranscript Multiplier? : ");
		mult = in.nextDouble();
		
		return calcGPA(over, max, mult);
	}
	
		//	CALCULATIONS ----------------------------------------------
	public static double calcGPA(double over, double max, double mult)
	{
		return over/max * 100 * mult;
	}
	
	public static double calcACT(int e, int m, int r, int s)
	{
		return (e + 2*m + r + s) / 1.8;
	}
	
	public static double calcSAT(int m, int r, int w)
	{
		return (2*m + r + w) / 32.0;
	}
		//	CALCULATIONS ----------------------------------------------
	
	
	public static void compareApps(double one, double two)
	{
		if(one > two) 
			{ o.println("The first application seems to be better."); }
		else if(two > one) 
			{ o.println("The second application seems to be better."); }
		else 
			{ o.println("The two applicants seem to be equal."); }
	}
	
		//	ASK WHICH EXAM, RETURN INT CODE
	public static int whichExam()
	{
		o.print("\tDo you have?\n\t[1] SAT scores\n\t[2] ACT scores\n\t\t  : ");
		return in.nextInt();
	}
}
