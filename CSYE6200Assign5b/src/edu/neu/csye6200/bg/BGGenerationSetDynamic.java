/**
 * 
 */
package edu.neu.csye6200.bg;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Observable;

/** 
 * BGGenerationSetDynamic: Stores one BGGeneration with corresponding rule
 * 2nd Version: Applicable for Dynamic Rule
 * @author Chen.JL
 */
public class BGGenerationSetDynamic extends Observable implements Runnable {
	
	// Attributes of the GenerationSet
	private Integer number;
	private Double radians;
	private Integer rule;
	
	// BGRuleDynamic and BGGeneration instance
	private BGGeneration bg;
	private BGRuleDynamic br;
	
	// Button control flag
	private boolean runflag = true;
	private boolean pauseflag = false;	
	
	
	/**
	 * Constructor
	 * @param number the number of generations
	 * @param radians the radians associated with rule
	 * @param rule the rule applied to BGGeneration
	 * */
	public BGGenerationSetDynamic(Integer number, Double radians, Integer rule) {
		// initialize
		this.number = number;
		this.radians = radians;
		this.rule = rule;
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
	

	public Integer getRule() {
		return rule;
	}


	public void setRule(Integer rule) {
		this.rule = rule;
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
	 * Complete initialization and grow each generations with delay
	 * */
	@Override
	public void run() {
		
		// initialize
		bg = new BGGeneration(number);
		br = new BGRuleDynamic(bg, radians, rule);
		
		// dynamic grow		
		while(runflag) {
			
			for (int i = 0; i < bg.getLayer() + 1; i++) {
				
				try {
						// Delay 1s
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
							// Sleep
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					// Set the applied rule
					br.setRule(rule);
					
					// Root
					if (i == 0) {
						br.getStemList().add(bg.getRoot());
						bg.getRoot().setRule(rule);
					}
					
					// Root grows firstly
					else if (i == 1) {
						br.addChildStemDynamic(bg.getRoot());
					}
					
					// Grow following generations
					else {
						br.growLayerDynamic(bg.getRoot());
					}
					
					// Print some info
					System.out.println("Rule: " + br.getRule());
					System.out.println("create geneset: "+ br.getStemList().size());
					
//					bg.display(bg.getRoot());
					
					// Pass the stemList to BGPanel when one generation is generated
					setChanged();
					notifyObservers(br.getStemList());
			}
			
			// Complete Growth
			runflag = false;
		}
	}
	
	/**
	 * Display all stems
	 */
/*	public void display() {
		
		// Formatted display
		System.out.println("GenerationSet");
		System.out.println("------------------------------------------------------------------------------------------------------");
		bg.display(bg.getRoot());
		System.out.println("******************************************************************************************************");
	}*/
	
	
	/**
	 * Test
	 * @param args
	 * @throws java.io.IOException
	 */
/*    public static void main(String[] args) throws java.io.IOException {
		
		// Input Instruction
		char inChar = ' ';
		System.out.println("*****Please Enter the GenerationSet Number*****");
		inChar = (char) System.in.read();
		
		// Read '\r' and '\n' and do nothing
		System.in.read();
		System.in.read();
		
		// Create instances
		BGGenerationSetDynamic bs = new BGGenerationSetDynamic(Character.getNumericValue(inChar), Math.PI/3, 2);
		
//		// Write print context to file
//		FileOutputStream bos = null;
//		// Error dealing
//		try {
//			bos = new FileOutputStream("BGGenerationSet.txt");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		System.setOut(new PrintStream(bos));
		
		// Dynamic Rule
		bs.run();		
	}*/
}