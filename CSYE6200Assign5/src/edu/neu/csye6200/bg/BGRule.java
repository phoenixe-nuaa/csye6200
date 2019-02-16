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
 * @author Chen.JL
 *
 */
public class BGRule {
	// To store all the stems
	public ArrayList<BGStem> stemList = new ArrayList<>();
	
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

	public ArrayList<BGStem> getStemList() {
		return stemList;
	}

	public void setStemList(ArrayList<BGStem> stemList) {
		this.stemList = stemList;
	}

	// process input generations and produce an output generation
	public void growByGene() {
		// Root grows first
		addChildStem(gene.getRoot());
		// Add to stemList
		stemList.add(gene.getRoot());
		
		// Grow by layer
		for (int i = 1; i < gene.getLayer(); i++) {
			growByLayer(gene.getRoot());
		}
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
	
	// Calculate the node coordinates
	public ArrayList<Double> branch(BGStem parent) {
		// Store the node coordinates
		ArrayList <Double> node = new ArrayList<Double>();
		// Calculate
		node.add(parent.getLength() * Math.cos(parent.getDirection()) + parent.getX());
		node.add(parent.getLength() * Math.sin(parent.getDirection()) + parent.getY());
		
		return node;
	}
	
	// Add child stems that originate at parent stems' tip 
	// in terms of number, relative angles and lengths
	public void addChildStem (BGStem stem) {
		ArrayList <Double> node = new ArrayList<Double>();
		node = branch(stem);
		
		BGStem stm1 = new BGStem(node.get(0), node.get(1), stem.getLength()*0.66, stem.getDirection() - radians, stem.getDepth() + 1);
		BGStem stm2 = new BGStem(node.get(0), node.get(1), stem.getLength()*0.66, stem.getDirection(), stem.getDepth() + 1);
		BGStem stm3 = new BGStem(node.get(0), node.get(1), stem.getLength()*0.66, stem.getDirection() + radians, stem.getDepth() + 1);
		
		stem.getChildlist().add(stm1);
		stem.getChildlist().add(stm2);
		stem.getChildlist().add(stm3);
		
		// Add all stems to stemList
		stemList.add(stm1);
		stemList.add(stm2);
		stemList.add(stm3);
	}
}
