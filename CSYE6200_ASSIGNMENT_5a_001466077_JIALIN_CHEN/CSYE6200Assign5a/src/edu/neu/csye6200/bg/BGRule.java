/**
 * 
 */
package edu.neu.csye6200.bg;

import java.util.ArrayList;

/**
 * @author Chen.JL
 *
 */
public class BGRule {
	private BGGeneration gene;
	// Control the relative angles of each child stem
	private Double radians;

	// Constructor
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

	// Search the deepest stem and add child stems on them
	public void growByLayer(BGStem stem) {
//		// If root, add child stems
//		if (stem.getDepth() == 0) {
//			addChildStem(stem);
//		}		
		for (BGStem stems: stem.getChildlist()) {
			if (stems.getChildlist().isEmpty())
				addChildStem(stems);
			else
				growByLayer(stems);
		}
	}
	
	// process input generations and produce an output generation
	public void growByGene() {
		// Root grows first
		addChildStem(gene.getRoot());
		// Grow by layer
		for (int i = 1; i < gene.getLayer(); i++) {
			growByLayer(gene.getRoot());
		}
	}
	
	// Calculate the node coordinates
	public ArrayList<Double> branch(BGStem parent) {
		// Store the node coordinates
		ArrayList <Double> node = new ArrayList<Double>();
		node.add(parent.getLength() * Math.cos(parent.getDirection()) + parent.getX());
		node.add(parent.getLength() * Math.sin(parent.getDirection()) + parent.getY());
		return node;
	}
	
	// Add child stems f that originate at parent stems' tip 
	// in terms of number, relative angles and lengths
	public void addChildStem (BGStem stem) {
		ArrayList <Double> node = new ArrayList<Double>();
		node = branch(stem);
		stem.getChildlist().add(new BGStem(node.get(0), node.get(1), stem.getLength()*0.66, stem.getDirection() - radians, stem.getDepth() + 1));
		stem.getChildlist().add(new BGStem(node.get(0), node.get(1), stem.getLength()*0.66, stem.getDirection(), stem.getDepth() + 1));
		stem.getChildlist().add(new BGStem(node.get(0), node.get(1), stem.getLength()*0.66, stem.getDirection() + radians, stem.getDepth() + 1));
	}
	
}
