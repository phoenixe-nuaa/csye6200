package edu.neu.csye6200.ui;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

/**
 * A sample canvas that draws a rainbow of lines
 * @author Yixuan Wang
 */

public class BGCanvas extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(BGCanvas.class.getName());
    private int lineSize = 20;
  
    private long counter = 0L;
    private BGGenerationSet bgset;
    public RuleSet ruleset;
    public Color col;
    public GrowStep step;
	
    /**
     * Cell AutCanvas constructor
     */
    public BGCanvas() {
  
    	this.col = Color.WHITE;
    	bgset = new BGGenerationSet();
    	step = new GrowStep(0, new ArrayList<Stem>());
    	this.setBackground(Color.BLACK);
    }
    

	/**
	 * The UI thread calls this method when the screen changes, or in response
	 * to a user initiated call to repaint();
	 */

	
	public void paint(Graphics g) {
		
		super.paint(g);
//		// Our Added-on drawing
		if(this.step.getStepNum() == 0) {
			drawWelcome(g);
			System.out.println("show welcome!!");
			return;
		}
		
		System.out.println("Starting Selection");
		if(ruleset == null) return;
		System.out.println("Now ruleset: " + ruleset);
		switch(ruleset) {
		// when you chose the RULE1
			case RULE1:{
				drawRule1(g);
//				System.out.println("step Number: " + step.getStepNum() + ", Collection size: " + step.getSsList().size());
				break;
			}
		// when you chose the RULE2
			case RULE2:{
				drawRule2(g);
				break;
			}
		// when you chose the RULE3
			case RULE3:{
				drawRule3(g);
				break;
			}
		// when you chose nothing but click the "start" button
			case NULL:{
				drawNochoose(g);
				break;
			}
			
			case BLANK:{
				drawClear(g);
				break;
			}
		}
		
//		notChoose(g);
    }
	

	
//	private void printStem() {
//		System.out.println("step Number: " + step.getStepNum() + ", Collection size: " + step.getSsList().size());
//	}



	/**
	 * Draw the CA graphics panel
	 * @param g
	 */
	
	// Create the first welcome Interface.
	// 当step为0的时候，显示欢迎界面
	public void drawWelcome(Graphics g) {
		log.info("Drawing Welcome Interface " + counter++);
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = getSize();
	
		int midX = size.width / 2 - 120;
		int midY = size.height / 2;
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Tahoma", Font.BOLD, 15));
		g2d.drawString("Welcome to check Yixuan's Assignment 5", midX - 10, midY - 30);
		g2d.drawString("Please select a rule and press 'Start' button to see the Plant Growing!", midX - 130, midY);

	}
	
	
	private void drawNochoose(Graphics g) {
		// TODO Auto-generated method stub
		log.info("Drawing Not Choosing Interface " + counter++);
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = getSize();
		int midX = size.width / 2 - 120;
		int midY = size.height / 2;
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Tahoma", Font.BOLD, 15));
		g2d.drawString("You have not chosen any rule, please select a rule again!", midX - 30, midY - 30);
		
	}

	private void drawRule1(Graphics g) {
		// TODO Auto-generated method stub
		log.info("Drawing RULE1" + counter++);
		System.out.println("current rule1 color : " + col);
		Graphics2D g2d = (Graphics2D) g;
		if(ruleset == ruleset.NULL) return;
		for(Stem stm: step.getSsList()) {
			paintLine(g2d, stm.getstartx(), stm.getstarty(), stm.getEndx(), stm.getEndy(), Color.WHITE);
		}

	}
	
	private void drawRule2(Graphics g) {
		// TODO Auto-generated method stub
		log.info("Drawing RULE2" + counter++);
		Graphics2D g2d = (Graphics2D) g;
		if(ruleset == ruleset.NULL) return;
		for(Stem stm: step.getSsList()) {
			paintLine(g2d, stm.getstartx(), stm.getstarty(), stm.getEndx(), stm.getEndy(), this.col);
		}


	}

	
	private void drawRule3(Graphics g) {
		// TODO Auto-generated method stub
			log.info("Drawing RULE3" + counter++);
			Graphics2D g2d = (Graphics2D) g;
			if(ruleset == ruleset.NULL) return;
			for(Stem stm: step.getSsList()) {
				paintLine(g2d, stm.getstartx(), stm.getstarty(), stm.getEndx(), stm.getEndy(), this.col);
			}

	}
	

	private void drawClear(Graphics g) {
		log.info("Drawing Clear Screen " + counter++);
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = getSize();
		int midX = size.width / 2 - 120;
		int midY = size.height / 2;
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Tahoma", Font.BOLD, 15));
		g2d.drawString("RESET successfully, please select another rule :) ", midX - 30, midY - 30);
	}
	
	/*
	 * A local routine to ensure that the color value is in the 0 to 255 range.
	 */
	private int validColor(int colorVal) {
		if (colorVal > 255)
			colorVal = 255;
		if (colorVal < 0)
			colorVal = 0;
		return colorVal;
	}
	

	/**
	 * A convenience routine to set the color and draw a line
	 * @param g2d the 2D Graphics context
	 * @param startx the line start position on the x-Axis
	 * @param starty the line start position on the y-Axis
	 * @param endx the line end position on the x-Axis
	 * @param endy the line end position on the y-Axis
	 * @param color the line color
	 */
	private void paintLine(Graphics2D g2d, double startx, double starty, double endx, double endy, Color col) {
		double length = Math.sqrt(Math.pow((endx - startx), 2) + Math.pow((endy - starty), 2));
		g2d.setColor(col);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke((float) (0.4f * length)));
		Line2D line = new Line2D.Double(startx, starty, endx, endy);

		g2d.draw(line);
	}


	
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("Sth changed");
		// TODO Auto-generated method stub
		if (arg1 instanceof GrowStep) {
			this.step = (GrowStep) arg1;
			this.repaint();
		}
		
	}
	
}
