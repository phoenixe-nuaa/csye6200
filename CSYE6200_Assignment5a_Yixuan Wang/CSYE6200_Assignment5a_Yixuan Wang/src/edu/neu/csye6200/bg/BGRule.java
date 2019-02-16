package edu.neu.csye6200.bg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class BGRule {
	
	private double x;
	private double y;
	private double radians;
	private double length;
	private Color color = new Color(43, 77, 219);
	private Stem baseStem;
	private Stem childStem;
	private double deviation;
	public ArrayList<Stem> stemList;
	
	
	// constructor
	public BGRule(double x, double y, double deviation, double length, Color color) {
		this.x = x;
		this.y = y;
		this.deviation = deviation;
		this.radians = Math.toRadians(270);
		this.length = length;
		this.color = color;
		this.stemList  = new ArrayList<Stem>();  // stem list to store all stems (child & base) when you chose a rule
		
	}
	
	
	// method to paint the tree
	public void paint(Graphics g) {
		g.setColor(this.color);
		this.display(g, this.x, this.y, this.radians, this.length);
//		printChildStems();
		
	}
	
	
	// method to produce stems successively and draw them
	private void display(Graphics g, double x1, double y1, double radians, double length) {
		if(length > 0) {
			
			// calculate the new start point of new child stem
			double x2 = x1 + length * Math.cos(radians) * 20.0;
			double y2 = y1 + length * Math.sin(radians) * 20.0; 

			double deviation = this.deviation;
	
			// construct a pen to draw the line between two points
			Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2.setStroke(new BasicStroke((float) (0.5f * length)));
	        Line2D line = new Line2D.Double(x1, y1, x2, y2);
			g2.draw(line);
			
			// construct the child stem instance
			childStem = new Stem(this.deviation, length, x1, y1);
			
			// use stemList to store all stems (including base stem and child stems)
			stemList.add(childStem);
			
			// choose the base stem, and construct the child stem list of this base stem, add child stems into the list.
			if(x1 == this.x && y1 == this.y) {
				baseStem = childStem;
			} 
			else {
				baseStem.getChildStemList().add(childStem);
			}
			
			// recursion for grow stems successively
			display(g, x2, y2, radians + deviation, length - 1);
			display(g, x2, y2, radians - deviation, length - 1);
		}
	}

	
//	print child stems of the base stem of the specific rule
	public void printChildStems() {
		System.out.println("==============================================================================");
		System.out.println("                        Child Stems List");
		System.out.println("==============================================================================");
	
		System.out.println(baseStem.printBaseString());
		for(Stem st: baseStem.getChildStemList()) {
			System.out.println(st.toString());
		}
	}


	// get and set methods
	public ArrayList<Stem> getStemList() {
		return stemList;
	}



	public void setStemList(ArrayList<Stem> stemList) {
		this.stemList = stemList;
	}



	public double getX() {
		return x;
	}



	public void setX(double x) {
		this.x = x;
	}



	public double getY() {
		return y;
	}



	public void setY(double y) {
		this.y = y;
	} 
	
	
	
	
}
