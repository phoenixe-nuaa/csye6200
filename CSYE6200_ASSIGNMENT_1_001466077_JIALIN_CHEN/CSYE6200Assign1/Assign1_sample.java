/**
* A starter file for implementing CSYE 6200 Assignment 1
* Filename: Assign1.java
* NUID: (your ID here)
* @author (your name here)
*/

public class Assign1_sample {

	/**
	 * The main entry point... your program starts here
	 * @param args command line arguments
	 */
	
	public static void main(String[] args) throws java.io.IOException {
		
		char inChar = ' ';

		String accounts[][] = {  {"Name1", "1575", "12000.00"},  {"Name2", "7721", "500.00"},  {"Name3",  "3884", "700.00"}};

		System.out.println("(Input prompt statement - say something meaningful)"); // <--- Change this prompt before submitting your code
		   
		do {
		   	  inChar = (char) System.in.read();
		   	  
	          // react to input and take appropriate action

	          // check for password matching

	        } 
	          while (inChar != 'q'); // Exit on quit
		      System.out.println("Quiting...");
	    } 	
}
