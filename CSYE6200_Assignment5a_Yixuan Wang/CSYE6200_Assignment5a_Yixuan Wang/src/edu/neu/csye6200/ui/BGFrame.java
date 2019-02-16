package edu.neu.csye6200.ui;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import edu.neu.csye6200.bg.*;


public class BGFrame extends JFrame implements ActionListener {
	private JMenuBar menuBar;
	private JMenu BGMenu;
	private JMenuItem rule1;
	private JMenuItem rule2;
	private JMenuItem rule3;
	private JMenuItem clear;
	private RuleSet set;
	private BGPanel bgPanel = new BGPanel();
	
	
	// constructor
	public BGFrame() {
		this.setTitle("PlANT GROW");
		this.setSize(1000, 1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.createMenu();
        this.setJMenuBar(this.menuBar);
        this.add(this.bgPanel, "Center");
        this.setVisible(true);
		
	}
	
	// create menu and buttons for choosing rules
	// initialize these buttons
	private void createMenu() {
		this.menuBar = new JMenuBar();
		this.BGMenu = new JMenu("RULE");
		this.rule1 = new JMenuItem("RULE1");
		this.rule1.addActionListener(this);
		this.rule2 = new JMenuItem("RULE2");
		this.rule2.addActionListener(this);
		this.rule3 = new JMenuItem("RULE3");
		this.rule3.addActionListener(this);
		this.clear = new JMenuItem("CLEAR");
		this.clear.addActionListener(this);
		this.menuBar.add(this.BGMenu);
		this.BGMenu.add(this.rule1);
		this.BGMenu.add(this.rule2);
		this.BGMenu.add(this.rule3);
		this.BGMenu.add(this.clear);
		
	}
	
	// when you chose the RULE1
	private void drawRule1() {
		this.set = RuleSet.RULE1;
		this.bgPanel.setPaintStype(this.set);
		this.repaint();
	}
	
	// when you chose the RULE 2
	private void drawRule2() {
		this.set = RuleSet.RULE2;
		this.bgPanel.setPaintStype(this.set);
		this.repaint();
	}
	
	// when you chose the RULE3
	private void drawRule3() {
		this.set = RuleSet.RULE3;
		this.bgPanel.setPaintStype(this.set);
		this.repaint();
	}
	
	// when you click the button "CLEAR"
	private void drawClear() {
		this.set = RuleSet.NULL;
		this.bgPanel.setPaintStype(this.set);
		this.repaint();
	}
	

	// method to execute the corresponding action when you clicked button
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.clear) {
			this.drawClear();
		}
		if(e.getSource() == this.rule1) {
			this.drawRule1();
		}
		if(e.getSource() == this.rule2) {
			this.drawRule2();
		}
		if(e.getSource() == this.rule3) {
			this.drawRule3();
		}
	}
	
	
	public static void main(String[] args) {
		new BGFrame();

	}

	
}
