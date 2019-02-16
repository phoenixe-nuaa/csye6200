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
 * BGRule: process input BGGenerations and produce output generations according to the layer of generations
 * 1st Version: Not Applicable for Dynamic Rule
 * @author Chen.JL
 */
public class BGRule {
	
	// To store all stems
	public ArrayList<BGStem> stemList = new ArrayList<>();
	
	// Input generation
	private BGGeneration gene;
	
	// Control the relative angles of each child stem
	private Double radians;
	
	
	/**
	 * Constructor
	 * @param generation input generations
	 * @param radians the radians associated with rule
	 */
	public BGRule(BGGeneration gene, Double radians) {
		this.gene = gene;
		this.radians = radians;	
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

	
	/**
	 * Let the generation grows by given layer
	 */
	public void growGeneCustom() {
		
		// add root to stemList
		stemList.add(gene.getRoot());
		
		// Root grows first
		if (gene.getLayer() == 1) {
			addChildStemCustom(gene.getRoot());
		}
		
		// Grow by layer
		if (gene.getLayer() > 1) {
			
			addChildStemCustom(gene.getRoot());
			
			for (int i = 0; i < gene.getLayer()-1; i++) {
				growLayerCustom(gene.getRoot());
			}
		}
	}
	
	
	/**
	 * Search the deepest stem and add child stems on them for one layer
	 * @param stem the parent stem on which child stems grow
	 */
	public void growLayerCustom(BGStem stem) {		
		
		// Search
		for (BGStem stems: stem.getChildlist()) {
			
			// if deepest stem, add child stems
			if (stems.getChildlist().isEmpty())
				addChildStemCustom(stems);
			
			// else put into recursion
			else
				growLayerCustom(stems);
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
	 * Add child stems that originate at parent stems' tip in terms of relative angles and lengths
	 * @param stem the parent stem on which child stems grow
	 */
	public void addChildStemCustom (BGStem stem) {
		
		// Get node coordinates
		ArrayList <Double> node = new ArrayList<Double>();
		node = branch(stem);
		
		// Create child stem instances
		BGStem stm1 = new BGStem(node.get(0), node.get(1), stem.getLength()*0.66, stem.getDirection() - radians, stem.getDepth() + 1);
		BGStem stm2 = new BGStem(node.get(0), node.get(1), stem.getLength()*0.66, stem.getDirection(), stem.getDepth() + 1);
		BGStem stm3 = new BGStem(node.get(0), node.get(1), stem.getLength()*0.66, stem.getDirection() + radians, stem.getDepth() + 1);
		
		// Add child stems to parent stem's childList
		stem.getChildlist().add(stm1);
		stem.getChildlist().add(stm2);
		stem.getChildlist().add(stm3);
		
		// Add all stems to stemList
		stemList.add(stm1);
		stemList.add(stm2);
		stemList.add(stm3);
	}
}
