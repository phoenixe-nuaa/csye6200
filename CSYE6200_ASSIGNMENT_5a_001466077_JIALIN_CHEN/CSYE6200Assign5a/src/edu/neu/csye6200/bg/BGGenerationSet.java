/**
 * 
 */
package edu.neu.csye6200.bg;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Chen.JL
 *
 */
public class BGGenerationSet {
	// Number of generations
	private Integer number;
	// Store BGRules and corresponding BGGenerations 
	private ArrayList<BGGeneration> genelist= new ArrayList<BGGeneration>();
	private HashMap<BGRule,BGGeneration> rulegenelist= new HashMap<BGRule,BGGeneration>();
	
	// Constructor
	public BGGenerationSet(Integer number) {
		this.number = number;
	}
	
	// Getters and setters
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public ArrayList<BGGeneration> getGenelist() {
		return genelist;
	}

	public void setGenelist(ArrayList<BGGeneration> genelist) {
		this.genelist = genelist;
	}

	public HashMap<BGRule, BGGeneration> getRulegenelist() {
		return rulegenelist;
	}

	public void setRulegenelist(HashMap<BGRule, BGGeneration> rulegenelist) {
		this.rulegenelist = rulegenelist;
	}

	// Initialization of BGGeneration instances
	public void createGene() {
		for (int i = 2; i < number+2; i++) {
			genelist.add(new BGGeneration(i));
		}
	}
	
	// Initialization of BGRule instances
	public void createGeneSet() {
		for (int i = 0; i < number; i++) {
			rulegenelist.put(new BGRule(genelist.get(i), Math.PI/(3*number)), genelist.get(i));
		}
	}
	
	// Complete initialization and display
	public void run() {
		createGene();
		createGeneSet();
		
		for (BGRule rule : rulegenelist.keySet()) {
			rule.growByGene();
		}
		// Formatted display
		for (BGGeneration gene : rulegenelist.values()) {
			System.out.println("GenerationSet	" + String.format("Layer:%1$2d  Relative Angle:%2$2.2f", gene.getLayer(), Math.PI/(3*number)));
			System.out.println("------------------------------------------------------------------------------------------------------");
			gene.display(gene.getRoot());
			System.out.println("******************************************************************************************************");
		}
	}
	
	// Test
	public static void main(String[] args) {
		// Create instances
		BGGenerationSet bs = new BGGenerationSet(2);
		// Write print context to file
		FileOutputStream bos = null;
		// Error dealing
		try {
			bos = new FileOutputStream("BGGenerationSet.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.setOut(new PrintStream(bos));
		bs.run();
	}	
}