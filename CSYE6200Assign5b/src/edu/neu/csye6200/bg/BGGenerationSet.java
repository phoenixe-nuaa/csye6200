/**
 * 
 */
package edu.neu.csye6200.bg;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Observable;

/** 
 * BGGenerationSet: stores each generation with corresponding rule
 * 1st Version: Not Applicable for Dynamic Rule
 * @author Chen.JL
 */
public class BGGenerationSet extends Observable implements Runnable {
	
	// Attributes of GenerationSet
	private Integer number;
	private Double radians;
	
	// Store BGRules and corresponding BGGenerations 
	private ArrayList<BGGeneration> genelist = new ArrayList<BGGeneration>();
	private ArrayList<BGRule> rulelist = new ArrayList<BGRule>();
	
	// Button control flag
	private boolean runflag = true;
	private boolean pauseflag = false;
	
	
	/**
	 * Constructor
	 * @param number the number of generations
	 * @param radians the radians associated with rule
	 * */
	public BGGenerationSet(Integer number, Double radians) {
		// initialize
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
	

	public boolean isRunflag() {
		return runflag;
	}


	public void setRunflag(boolean runflag) {
		this.runflag = runflag;
	}


	public boolean isPauseflag() {
		return pauseflag;
	}
	
	
	public void setPauseflag(boolean value) {
		pauseflag = value;
	}
	
	public void invert() {
		pauseflag = !pauseflag;
	}

	
	/**
	 * Initialization of BGGeneration instances
	 */
	public void createGene() {
		for (int i = 0; i < number + 1; i++) {
			genelist.add(new BGGeneration(i));
		}
	}
	
	
	/**
	 * Initialization of BGRule instances
	 */
	public void createGeneSet() {
		for (BGGeneration bg: genelist) {
			rulelist.add(new BGRule(bg, radians));
		}
	}
	
	
	/**
	 * Display all stems
	 */
	public void display() {
		
//		for (BGRule rule : rulelist) {
//			rule.growByGene();
//		}
		
		for (BGGeneration bg: genelist) {
			// Formatted display
			System.out.println("GenerationSet");
			System.out.println("------------------------------------------------------------------------------------------------------");
			bg.display(bg.getRoot());
			System.out.println("******************************************************************************************************");
		}
	}
	
	
	/**
	 * Complete initialization and display
	 */
	@Override
	public void run() {
		
		// initialize
		createGene();
		createGeneSet();
		
		while(runflag) {
			
			for (BGRule rule: rulelist) {
					
				try {
						// Delay
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					// Stop
					if (runflag == false) {
						// Stop drawing
						break;
					}
					
					// Pause
					while (pauseflag) {
						try {
							// sleep
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					// Grow
					rule.growGeneCustom();
					System.out.println("create geneset " + rule.stemList.size());
					
					// Notify and pass the stemList to BGPanel
					setChanged();
					notifyObservers(rule.getStemList());
			}
			
			// Complete Growth
			runflag = false;
		}
	}
	
	
/*	// Test
    public static void main(String[] args) throws java.io.IOException {
		// Input Instruction
		char inChar = ' ';
		System.out.println("*****Please Enter the GenerationSet Number*****");
		inChar = (char) System.in.read();
		
		// Read '\r' and '\n' and do nothing
		System.in.read();
		System.in.read();
		
		// Create instances
		BGGenerationSet bs = new BGGenerationSet(Character.getNumericValue(inChar), Math.PI/3);
		
//		// Write print context to file
//		FileOutputStream bos = null;
//		// Error dealing
//		try {
//			bos = new FileOutputStream("BGGenerationSet.txt");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		System.setOut(new PrintStream(bos));
		
		// run
		bs.run();
		bs.display();
	}*/
}