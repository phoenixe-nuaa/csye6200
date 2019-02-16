package edu.neu.csye6200.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.java.dev.designgridlayout.DesignGridLayout;

public class MyAppUI implements ActionListener {
	private Logger log = Logger.getLogger(MyAppUI.class.getName());
	private JFrame frame;
	
	private JPanel drawPanel;
	private JPanel mainPanel;
	
	private JButton startBtn;
	private JButton endBtn;
	
	// a name input field
	private JTextField nameTF = new JTextField();
	// a ID input field
	private JTextField idTF = new JTextField();
	
	// Constructor
	public MyAppUI() {
		log.info("App Started");
		initGUI();
	}
	
	// Initialize the GUI
	private void initGUI() {
		frame = new JFrame();
		frame.setTitle("MyAppUI");
		// Set the size to something reasonable
		frame.setSize(400, 300);
		// If we press the close button, exit
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLayout(new BorderLayout());
		// Button on top
		frame.add(getMainPanel(), BorderLayout.NORTH);
		
		// Drawing in the center
		frame.add(getDrawPanel(), BorderLayout.CENTER);
		
		frame.setVisible(true);
	}
	
	public JPanel getDrawPanel() {
		drawPanel = new MyPanel();
		return drawPanel;
	}
	
	// Create a Panel that contains control buttons
	public JPanel getMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setVisible(true);
		
		// Flow from left to right
//		mainPanel.setLayout(new FlowLayout());
		DesignGridLayout pPlayout = new DesignGridLayout(mainPanel);
		
		// Create Button instances
		startBtn = new JButton("Start");
		endBtn = new JButton("Stop");
		
		// Anonymous Inner
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Do start operation - Anonymous");
				// ask for a panel redraw
				drawPanel.repaint();
			}
		});	
		endBtn.addActionListener(this);
		
		nameTF.setText("Mark");
		
		pPlayout.row().grid(new JLabel("Name")).add(nameTF);
		pPlayout.row().grid(new JLabel("ID")).add(idTF);
		pPlayout.emptyRow();
		pPlayout.row().center().add(startBtn, endBtn);
		
		// Add them to the panel
//		mainPanel.add(startBtn);
//		mainPanel.add(endBtn);
		mainPanel.setBackground(Color.black);
		return mainPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		log.info("We receive an ActionEvnet " + e);
		
		if (e.getSource() == startBtn) {
			System.out.println("Do start operation");
		}
		
		if (e.getSource() == endBtn) {
			System.out.println("Do stop operation");
		}
	}
	
	public static void main(String[] args) {
		MyAppUI myapp = new MyAppUI();
		System.out.println("MyAppUI is exiting");
	}
}
