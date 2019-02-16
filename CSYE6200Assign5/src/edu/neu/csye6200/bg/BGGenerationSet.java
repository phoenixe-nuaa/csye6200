/**
 * 
 */
package edu.neu.csye6200.bg;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import edu.neu.csye6200.ui.BGApplication;

/**
 * @author Chen.JL
 * 
*/
public class BGGenerationSet implements Runnable {
	// Number of GenerationSets
	private Integer number;
	private Double radians;
	
	// Store BGRules and corresponding BGGenerations 
	private ArrayList<BGGeneration> genelist = new ArrayList<BGGeneration>();
	private ArrayList<BGRule> rulelist = new ArrayList<BGRule>();
	
	// Constructor
	public BGGenerationSet(Integer number, Double radians) {
		this.number = number;
		this.radians = radians;
	}
	
	// Getters and setters
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public Double getRadians() {
		return radians;
	}

	public void setRadians(Double radians) {
		this.radians = radians;
	}

	public ArrayList<BGGeneration> getGenelist() {
		return genelist;
	}

	public void setGenelist(ArrayList<BGGeneration> genelist) {
		this.genelist = genelist;
	}
	
	public ArrayList<BGRule> getRulelist() {
		return rulelist;
	}

	public void setRulelist(ArrayList<BGRule> rulelist) {
		this.rulelist = rulelist;
	}

	// Initialization of BGGeneration instances
	public void createGene() {
		for (int i = 1; i < number+1; i++) {
			genelist.add(new BGGeneration(i));
		}
	}
	
	// Initialization of BGRule instances
	public void createGeneSet() {
		for (BGGeneration bg: genelist) {
			rulelist.add(new BGRule(bg, radians));
			// sleep
		}
	}
	
/*	// Display all stems
	public void display() {
		for (BGRule rule : rulelist) {
			rule.growByGene();
			
			// Formatted display
			System.out.println("GenerationSet	" + String.format("Generation:%1$2d  Relative Angle:%2$2.2f", rulegenelist.get(rule).getLayer(), rule.getRadians()));
			System.out.println("------------------------------------------------------------------------------------------------------");
//			rulelist.get(rule).display(rulelist.get(rule).getRoot());
			
			System.out.println("******************************************************************************************************");
		}
	}*/
	
	// Complete initialization and display
	@Override
	public void run() {
		createGene();
		createGeneSet();
		for (BGRule rule: rulelist) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rule.growByGene();
//			BGApplication.refresh();
			System.out.println(rule.stemList.size());
			System.out.println("create geneset");
		}
//		display();
	}
	
	// Test
//    public static void main(String[] args) throws java.io.IOException {
//		// Input Instruction
//		char inChar = ' ';
//		System.out.println("*****Please Enter the GenerationSet Number*****");
//		inChar = (char) System.in.read();
//		
//		// Read '\r' and '\n' and do nothing
//		System.in.read();
//		System.in.read();
//		
//		// Create instances
////		BGGenerationSet bs = new BGGenerationSet(Character.getNumericValue(inChar));
//		
////		// Write print context to file
////		FileOutputStream bos = null;
////		// Error dealing
////		try {
////			bos = new FileOutputStream("BGGenerationSet.txt");
////		} catch (FileNotFoundException e) {
////			e.printStackTrace();
////		}
////		System.setOut(new PrintStream(bos));
//		
//		// run
////		bs.run();
//	}
}