package edu.neu.csye6200.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	int ctr = 0;

	public void paint(Graphics g) {
		Graphics g2d = (Graphics2D) g;
		ctr++;
		
		Dimension size = getSize();
		
		// even
		if ((ctr % 2) == 0)
			g2d.setColor(Color.BLUE);
		
		// odd
		else
			g2d.setColor(Color.GREEN);
			
		// Fill the entire panel
		g2d.fillRect(0, 0, size.width, size.height);
		
		g2d.setColor(Color.RED);
		// x1, y1, x2, y2
		g2d.drawLine(0, 0, size.width, size.height);
		System.out.print("->");
	}
}
