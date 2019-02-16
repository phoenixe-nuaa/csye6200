/**
 * 
 */
package edu.neu.csye6200.bg;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;

/**
 * @author Chen.JL
 *
 */
public class BGGeneration {
	private BGStem root;
	private Integer layer;
//	private Double radians = Math.PI/3;
	
	// Constructor
	public BGGeneration(Integer layer) {
		this.root = new BGStem(0.0, 0.0, 200.0, Math.PI/2, 0);
		this.layer = layer;
	}
	
	// Getters and Setters
	public BGStem getRoot() {
		return root;
	}

	public void setRoot(BGStem root) {
		this.root = root;
	}

	public Integer getLayer() {
		return layer;
	}

	public void setLayer(Integer layer) {
		this.layer = layer;
	}
	
/*	// Grow automatically and display
	public void grow(BGStem stem) {
		if (stem.getDepth() == 0)
			show(stem);
		if (stem.getDepth() < layer) {
			addChildStem(stem);
			for (BGStem stems: stem.getChildlist()) {
				System.out.println("------------------------------------------------------------------------------------------------------");
				show(stems);
				grow(stems);
			}
		}
	}
	
	// Calculate the node coordinates
	public ArrayList<Double> branch(BGStem parent) {
		// Store node coordinates
		ArrayList <Double> node = new ArrayList<Double>();
		node.add(parent.getLength() * Math.cos(parent.getDirection()) + parent.getX());
		node.add(parent.getLength() * Math.sin(parent.getDirection()) + parent.getY());
		return node;
	}
	
	// Add child stems for the parent stems
	public void addChildStem (BGStem stem) {
		ArrayList <Double> node = branch(stem);
		stem.getChildlist().add(new BGStem(node.get(0), node.get(1), stem.getLength()*0.66, stem.getDirection() - radians, stem.getDepth() + 1));
		stem.getChildlist().add(new BGStem(node.get(0), node.get(1), stem.getLength()*0.66, stem.getDirection(), stem.getDepth() + 1));
		stem.getChildlist().add(new BGStem(node.get(0), node.get(1), stem.getLength()*0.66, stem.getDirection() + radians, stem.getDepth() + 1));
	}*/
	
	// Set display format based on depth
	public String layer(Integer num) {
		String s = "";
		for (int i = 0; i< num; i++) {
			s += "	     ";
		}
		return s;
	}
	
	// Display format for single stem
	public void show(BGStem stem) {
		System.out.println(String.format(layer(stem.getDepth()) + "Stem ID:%1$2d  X:%2$2.2f  Y:%3$2.2f  Length:%4$2.2f  Direction:%5$2.2f", 
				stem.getId(), stem.getX(), stem.getY(), stem.getLength(), stem.getDirection()));
	}
	
	// Display all stems
	public void display(BGStem stem) {
		// display root
		if (stem.getDepth() == 0)
			show(stem);
		
		// display child stems
		if (stem.getDepth() < layer) {
			for (BGStem stems: stem.getChildlist()) {
				System.out.println("------------------------------------------------------------------------------------------------------");
				show(stems);
				display(stems);
			}
		}
	}
	
	// Test
	public static void main(String[] args) {
		// Create instances
		BGGeneration bg = new BGGeneration(3);
		BGRule br = new BGRule(bg, Math.PI/3);
		
//		// Write print context to file
//		FileOutputStream bos = null;
//		// Error dealing
//		try {
//			bos = new FileOutputStream("BGGeneration.txt");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		System.setOut(new PrintStream(bos));
		
		// run
		br.growByGene();
		bg.display(bg.getRoot());
	}	
}
