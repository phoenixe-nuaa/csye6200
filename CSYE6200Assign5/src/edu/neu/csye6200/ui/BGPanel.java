package edu.neu.csye6200.ui;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.lang.reflect.Array;

import javax.swing.JPanel;

import edu.neu.csye6200.bg.BGRule;
import edu.neu.csye6200.bg.BGStem;

/**
 * A canvas that ys the visualization of plant growth
 * @author JL.Chen
 */
public class BGPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(BGPanel.class.getName());
    private Color col = null;
    private long counter = 0L;
    
    public ArrayList<BGStem> stemList;
    
    /**
     * Canvas constructor
     */
	public BGPanel() {

		col = Color.WHITE;
		stemList = new ArrayList<BGStem>();
		this.setBackground(Color.BLACK);
	}
	
	/**
	 * The UI thread calls this method when the screen changes, or in response
	 * to a user initiated call to repaint();
	 */
	@Override
	public void paint(Graphics g) {
		// Our Added-on drawing
		drawBG(g);
    }
	
	@Override
	public void paintComponent(Graphics g) {
		// Our Added-on drawing
		System.out.println("paintComponent");
//		drawBG(g);
    }
	
	/**
	 * Draw the stems on bgPanel
	 * @param g
	 */
	public void drawBG(Graphics g) {
		log.info("Drawing BG " + counter++);
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = getSize();
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, size.width, size.height);
		
		g2d.setColor(Color.RED);
		g2d.drawString("BG 2D", 10, 15);
		
		System.out.println("11");
		super.paint(g);
		
		// Our Added-on drawing
		System.out.println("Executing paint");
		Graphics2D g2 = (Graphics2D) g;
		// Set the origin 
		g2.translate(500, 700);
		// Flip
		g2.scale(1, -1);
		
		// Paint
		if (stemList == null) {
			System.out.println("stemList is null");
			return;
		} 
		else {
			System.out.println("stemList is not null");
//			System.out.println(stemList.size());
			
			// Draw Stems according to coordinates
			for (BGStem stm: stemList) {
				
				double x1 = stm.getX();
				double x2 = stm.getLength() * Math.cos(stm.getDirection()) + stm.getX();
				double y1 = stm.getY();
				double y2 = stm.getLength() * Math.sin(stm.getDirection()) + stm.getY();
				
				Line2D line = new Line2D.Double(x1, y1, x2, y2);
				// Set stroke
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        g2.setStroke(new BasicStroke((float) (0.1f * stm.getLength())));
				g2.setColor(Color.WHITE);
				// Draw line
				g2.draw(line);
			}
		}
	}

/*	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.repaint();

	}*/

/*	*//**
	 * A convenience routine to set the color and draw a line
	 * @param g2d the 2D Graphics context
	 * @param startx the line start position on the x-Axis
	 * @param starty the line start position on the y-Axis
	 * @param endx the line end position on the x-Axis
	 * @param endy the line end position on the y-Axis
	 * @param color the line color
	 *//*
	private void paintLine(Graphics2D g2d, int startx, int starty, int endx, int endy, Color color) {
		g2d.setColor(color);
		g2d.drawLine(startx, starty, endx, endy);
	}*/
	
}
