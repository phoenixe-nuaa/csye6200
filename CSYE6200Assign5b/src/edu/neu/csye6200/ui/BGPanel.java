package edu.neu.csye6200.ui;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
import edu.neu.csye6200.bg.BGStem;

/**
 * BGPanel: paint growth simulation for each generation
 * @author JL.Chen
 */
public class BGPanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	// log
	private Logger log = Logger.getLogger(BGPanel.class.getName());
	// counter of drawBG
    private long counter = 0L;
    
    // flags
    private boolean clear = false;
    private boolean customflag;
    
    // Stores all child stems
    public ArrayList<BGStem> stemList;
    
    
    // Getters and setters
    public boolean isClear() {
		return clear;
	}


	public void setClear(boolean clear) {
		this.clear = clear;
	}


	public ArrayList<BGStem> getStemList() {
		return stemList;
	}


	public void setStemList(ArrayList<BGStem> stemList) {
		this.stemList = stemList;
	}
	

	public boolean isCustomflag() {
		return customflag;
	}


	public void setCustomflag(boolean customflag) {
		this.customflag = customflag;
	}


	/**
     * Constructor
     */
	public BGPanel() {

		this.stemList = new ArrayList<BGStem>();
		this.setBackground(Color.BLACK);
	}
	
	
	/**
	 * Update when Observable BGGenerationSetDynamic grows by generation with passing stemList
	 * @param bs Observable BGGenerationSetDynamic
	 * @param change refers to stemList
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable bs, Object change) {
		    
		// Refresh based on Observation
		stemList = (ArrayList<BGStem>) change;
		System.out.println("update " + stemList.size());

		// repaint
		BGApplication.refresh();
	}
	
	
	/**
	 * The UI thread calls this method when the screen changes, or in response
	 * to a user initiated call to repaint();
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		// Our Added-on drawing
		if (!clear) {
			draw(g);
			drawBG(g);
//			System.out.println("calling paint method");
		}
		else {
			draw(g);			
		}
    }
	
	
	/**
	 * Background setting
	 * @param g
	 */
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = getSize();
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, size.width, size.height);
		
		g2d.setColor(Color.RED);
		g2d.setFont(new Font("Times Roman", Font.BOLD, 20));
		g2d.drawString("BG Simulation", 10, 30);
		g2d.drawString("by Jialin Chen", 10, 60);
	}
	
	
	/**
	 * Draw the stemList of each generation on bgPanel
	 * @param g
	 */
	public void drawBG(Graphics g) {
		
		// log
		log.info("Drawing BG " + counter++);
		Graphics2D g2 = (Graphics2D) g;
		
		// Set the origin 
		g2.translate(620, 900);
		
		// Paint
		if (stemList == null) {
			
			System.out.println("stemList is null");
			return;
		} 
		else {
			
			System.out.println("stemList is not null");
			
			// Display of current generation
			if (stemList.size() == 0) System.out.println(stemList.size());
			else g2.drawString(String.valueOf("Current Generation: " + stemList.get(stemList.size()-1).getDepth()), 380, 0);
			
			// Flip
			g2.scale(1, -1);
			
			// Draw stems according to coordinates
			for (BGStem stm: stemList) {
				
//				if (stm.getRule() == null) {
//					System.out.println("No rule choosed");
//				}
//				else {
				if (stm.getRule() != null) {
					
					// Different color according to the rule
					switch(stm.getRule()) {
					
					case 1:
						g2.setColor(Color.yellow);
//						System.out.println("Rule 1 Red");
						break;
					
					case 2:
						g2.setColor(Color.red);
//						System.out.println("Rule 2 Yellow");
						break;
					
					case 3:
						g2.setColor(Color.green);
//						System.out.println("Rule 3 Blue");
						break;
					}
				}
				paintLine(g2, stm);
			}
		}
	}
	

	/**
	 * A convenience routine to draw a line
	 * @param g2 the 2D Graphics context
	 * @param stem the stem to be painted
	 */
	private void paintLine(Graphics2D g2, BGStem stm) {
		
		// Calculate the start x, end x, start y, end y
		double x1 = stm.getX();
		double x2 = stm.getLength() * Math.cos(stm.getDirection()) + stm.getX();
		double y1 = stm.getY();
		double y2 = stm.getLength() * Math.sin(stm.getDirection()) + stm.getY();
		
		// Generate the line
		Line2D line = new Line2D.Double(x1, y1, x2, y2);
		
		// Change according to the customFalg
		if (customflag) {
			
			// Set stroke
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setStroke(new BasicStroke((float) (0.1f * stm.getLength())));
		}
//		g2.setColor(Color.WHITE);
		
		// Draw line
		g2.draw(line);
	}	
}
