package edu.neu.csye6200.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import edu.neu.csye6200.bg.BGGenerationSet;
import edu.neu.csye6200.bg.BGRule;
import edu.neu.csye6200.bg.*;


public class BGPanel extends JPanel {
	private BGRule bg1;
	private BGRule bg2;
	private BGRule bg3;
	private RuleSet set;
	private BGGenerationSet generationSet = new BGGenerationSet();
	
	// constructor
	public BGPanel() {
		
		this.set = RuleSet.NULL;  //  make the panel clear at the beginning
		
		// Set the different rules
		// different rules means different radians and length and color, but their base stems share the same start point
		this.bg1  = new BGRule(470, 800, Math.toRadians(25), 5, Color.black);
		this.bg2  = new BGRule(470, 800, Math.toRadians(30), 6, Color.blue);
		this.bg3  = new BGRule(470, 800, Math.toRadians(20), 7, Color.lightGray);
		
	}
	
	
	public void setPaintStype(RuleSet set) {
		this.set = set;   // if you choose the rule, it will change the set of the panel.
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);	
		switch(this.set) {
			// when you chose the RULE1
			case RULE1:{
				this.drawRule1(g);
				break;
			}
			// when you chose the RULE2
			case RULE2:{
				this.drawRule2(g);
				break;
			}
			// when you chose the RULE3
			case RULE3:{
				this.drawRule3(g);
				break;
			}
			// when you chose the CLEAR
			case NULL:{
				break;
			}
		}
	}
	
	// methods to call the painting method in the BGRules for drawing their corresponding trees.
	// also print the information of generation set at the same time
	private void drawRule1(Graphics g) {
		this.bg1.paint(g);
		generationSet.buildSet(bg1, this.set);
		
	}
	private void drawRule2(Graphics g) {
		this.bg2.paint(g);
		generationSet.buildSet(bg2, this.set);
		
	}
	private void drawRule3(Graphics g) {
		this.bg3.paint(g);
		generationSet.buildSet(bg3, this.set);
	}

}