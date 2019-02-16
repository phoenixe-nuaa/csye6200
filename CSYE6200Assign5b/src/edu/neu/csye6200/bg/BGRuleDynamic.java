/**
 * 
 */
package edu.neu.csye6200.bg;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/** 
 * BGRuleDynamic: process input BGGenerations and produce output generations
 * 2nd Version: Applicable for Dynamic Rule
 * @author Chen.JL
 */
public class BGRuleDynamic {
	
	// To store all stems
	public ArrayList<BGStem> stemList = new ArrayList<>();
	
	// Input generation
	private BGGeneration gene;
	
	// Control the relative angles of each child stem
	private Double radians;
	
	// Rule
	private Integer rule;
	
	
	/**
	 * Constructor
	 * @param generation input generations
	 * @param radians the radians associated with rule
	 * @param rule the rule applied to the input generation
	 */
	public BGRuleDynamic(BGGeneration gene, Double radians, Integer rule) {
		this.gene = gene;
		this.radians = radians;	
		this.rule = rule;
	}
	
	
	// Getters and Setters
	public BGGeneration getGene() {
		return gene;
	}

	
	public void setGene(BGGeneration gene) {
		this.gene = gene;
	}

	
	public Double getRadians() {
		return radians;
	}

	
	public void setRadians(Double radians) {
		this.radians = radians;
	}

	
	public ArrayList<BGStem> getStemList() {
		return stemList;
	}

	
	public void setStemList(ArrayList<BGStem> stemList) {
		this.stemList = stemList;
	}

	
	public Integer getRule() {
		return rule;
	}

	
	public void setRule(Integer number) {
		this.rule = number;
	}
	
	
	/**
	 * Search the deepest stem and add child stems on them for one layer
	 * @param stem the parent stem on which child stems grow
	 */
	public void growLayerDynamic(BGStem stem) {		
		
		// Search
		for (BGStem stems: stem.getChildlist()) {
			
			// if deepest stem, add child stems
			if (stems.getChildlist().isEmpty())
				addChildStemDynamic(stems);
			
			// else put into recursion
			else
				growLayerDynamic(stems);
		}
	}
	
	
	/**
	 * Calculate the node coordinates
	 * @param parent the parent stem on which child stems grow
	 * @return node the list of x, y coordinates
	 */
	public ArrayList<Double> branch(BGStem parent) {
		
		// Store the node coordinates
		ArrayList <Double> node = new ArrayList<Double>();
		
		// Calculate
		node.add(parent.getLength() * Math.cos(parent.getDirection()) + parent.getX());
		node.add(parent.getLength() * Math.sin(parent.getDirection()) + parent.getY());
		
		return node;
	}
	
	
	/**
	 * Add child stems to stemList
	 * @param stem the parent stem on which child stems grow
	 */
	public void addChildStemDynamic(BGStem stem) {
		
		// Add all stems to stemList
		for (BGStem stm: rule(stem).getChildlist()) {
			stemList.add(stm);
		}
	}
	
	
	/**
	 * Add child stems that originate at parent stems' tip in terms of relative angles and lengths
	 * @param stem the parent stem on which child stems grow
	 * @return stem the parent stem on which child stems grow 
	 */
	public BGStem rule(BGStem stem) {
		
		// Get node coordinates
		ArrayList <Double> node = new ArrayList<Double>();
		node = branch(stem);
		
		// add different child stems according to the rule
		switch(rule) {
		
		case 1:
			
			// Create child stem instances
			BGStem stm1 = new BGStem(node.get(0), node.get(1), stem.getLength()*0.66, stem.getDirection() - radians, stem.getDepth() + 1);
			BGStem stm2 = new BGStem(node.get(0), node.get(1), stem.getLength()*0.66, stem.getDirection() + radians, stem.getDepth() + 1);
			
			// Set corresponding rule
			stm1.setRule(1);
			stm2.setRule(1);
			
			// Add child stems to parent stem's childList
			stem.getChildlist().add(stm1);
			stem.getChildlist().add(stm2);
//			System.out.println("growing 2 child stems");
			break;
		
		case 2:
			
			BGStem stm3 = new BGStem(node.get(0), node.get(1), stem.getLength()*0.66, stem.getDirection() - radians, stem.getDepth() + 1);
			BGStem stm4= new BGStem(node.get(0), node.get(1), stem.getLength()*0.66, stem.getDirection(), stem.getDepth() + 1);
			BGStem stm5 = new BGStem(node.get(0), node.get(1), stem.getLength()*0.66, stem.getDirection() + radians, stem.getDepth() + 1);
			
			stm3.setRule(2);
			stm4.setRule(2);
			stm4.setRule(2);
			
			stem.getChildlist().add(stm3);
			stem.getChildlist().add(stm4);
			stem.getChildlist().add(stm5);
//			System.out.println("growing 3 child stems");
			break;
		
		case 3:
			
			BGStem stm6 = new BGStem(node.get(0), node.get(1), stem.getLength()*0.66, stem.getDirection() - radians, stem.getDepth() + 1);
			BGStem stm7 = new BGStem(node.get(0), node.get(1), stem.getLength()*0.66, stem.getDirection() - radians*2, stem.getDepth() + 1);
			BGStem stm8 = new BGStem(node.get(0), node.get(1), stem.getLength()*0.66, stem.getDirection() + radians, stem.getDepth() + 1);
			BGStem stm9 = new BGStem(node.get(0), node.get(1), stem.getLength()*0.66, stem.getDirection() + radians*2, stem.getDepth() + 1);
			
			stm6.setRule(3);
			stm7.setRule(3);
			stm8.setRule(3);
			stm9.setRule(3);
			
			stem.getChildlist().add(stm6);
			stem.getChildlist().add(stm7);
			stem.getChildlist().add(stm8);
			stem.getChildlist().add(stm9);
//			System.out.println("growing 4 child stems");
			break;
		}
		
		return stem;
	}
	
	
	/*	public void grow() {
		stemList.add(gene.getRoot());
		
		// Root grows first
		if (gene.getLayer() >= 1)
			addChildStemCustom(gene.getRoot());
	}*/
	
	/*	// store all child stems and grow root firstly
	public void growByGeneDynamic() {
		stemList.add(gene.getRoot());
		
		// Root grows first
		if (gene.getLayer() >= 1) {
			addChildStemDynamic(gene.getRoot());
		}
	}
	
	// grow by input number and layer
	public void growDynamic(Integer rule, Integer layer) {
		
		setRule(rule);
		
//		if (gene.getLayer() >= 1) {
//			addChildStemDynamic(gene.getRoot());
//		}
		
		for (int i = 0; i < layer; i++) {
			growByLayerDynamic(gene.getRoot());
		}
	}*/
	
}
