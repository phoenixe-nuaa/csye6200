/**
 * 
 */
package edu.neu.csye6200.bg;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;

/**
 * BGGeneration: hold a full tree
 * @author Chen.JL
 */
public class BGGeneration {
	
	// A BGGeneration has a root and a layer
	private BGStem root;
	private Integer layer;
//	private Double radians = Math.PI/3;
	
	
	/**
	 * Constructor
	 * @param layer the layer of BGGeneration
	 */
	public BGGeneration(Integer layer) {
		
		this.root = new BGStem(0.0, 0.0, 300.0, Math.PI/2, 0);
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
	
	
	/** 
	 * Set display format based on layer
	 * @param num the number of layer
	 * @return the format with blanks 
	 */
	public String layer(Integer num) {
		
		String s = "";
		for (int i = 0; i< num; i++) {
			s += "	     ";
		}
		
		return s;
	}
	
	
	/**
	 * Display format for single stem
	 * @param stem the single stem for showing its attributes
	 */
	public void show(BGStem stem) {
		
		System.out.println(String.format(layer(stem.getDepth()) + "Stem ID:%1$2d  X:%2$2.2f  Y:%3$2.2f  Length:%4$2.2f  Direction:%5$2.2f", 
				           stem.getId(), stem.getX(), stem.getY(), stem.getLength(), stem.getDirection()));
	}
	
	
	/**
	 * Display all stems using recursion
	 * @param stem each stem to show
	 */
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
	

	/**
	 * Test
	 * @param args
	 */
/*	public static void main(String[] args) {
		
		// Create instances
		BGGeneration bg = new BGGeneration(3);
//		BGRule br = new BGRule(bg, Math.PI/3);
		BGRuleDynamic br = new BGRuleDynamic(bg, Math.PI/3, 1);
		
		
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
//		br.growByGeneCustom();
		
		// Dynamic Rule
		
		// Ver.1 Test
		br.setNumber(1);
		br.grow();
		for (int i = 0; i < bg.getLayer()-2; i++) {
			br.growByLayerDynamic(bg.getRoot());
		}
		br.setNumber(2);
		br.growByLayerDynamic(bg.getRoot());
		
		// Ver.2 Test
		br.growByGeneDynamic();
//		br.setNumber(2);
		br.growDynamic(1, bg.getLayer()-2);
		br.growDynamic(2, 1);
		System.out.println("stemList's size: " + br.getStemList().size());
		bg.display(bg.getRoot());
		
		// Ver.3 Test
		for (int i = 0; i < bg.getLayer() + 1; i++) {
			System.out.println(i);
			
			if (i == 0) {
				System.out.println("rule " + br.getRule());
				br.getStemList().add(bg.getRoot());
				System.out.println("create geneset: "+ br.getStemList().size());
			}
			
//			else if (i >= 1 && i <= bg.getLayer() - 2) {
//				System.out.println("rule " + br.getRule());
//				br.addChildStemDynamic(bg.getRoot());
//				System.out.println("create geneset: "+ br.getStemList().size());
//			}
//			
//			else {
//				br.setRule(2);
//				System.out.println("rule " + br.getRule());
//				br.growByLayerDynamic(bg.getRoot());
//				System.out.println("create geneset: "+ br.getStemList().size());
//			}
			
			else if (i == 1) {
				System.out.println("rule " + br.getRule());
				br.addChildStemDynamic(bg.getRoot());
				System.out.println("create geneset: "+ br.getStemList().size());
			}
			
			else if (i <= bg.getLayer() - 1) {
				System.out.println("rule " + br.getRule());
//				br.addChildStemDynamic(bg.getRoot());
				br.growLayerDynamic(bg.getRoot());
				System.out.println("create geneset: "+ br.getStemList().size());
			}
			
			else {
				br.setRule(3);
				System.out.println("rule " + br.getRule());
//				br.addChildStemDynamic(bg.getRoot());
				br.growLayerDynamic(bg.getRoot());
				System.out.println("create geneset: "+ br.getStemList().size());
			}
//			br.growByLayerDynamic(bg.getRoot());
			System.out.println("GenerationSet ");
			bg.display(bg.getRoot());
		}
		
//		System.out.println("stemList's size: " + br.getStemList().size());	
	}*/
}
