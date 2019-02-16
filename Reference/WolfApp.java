package edu.neu.csye6200.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A Test application for the Wolframe Biological Growth application
 * 
 * @author Yixuan Wang
 */

public class WolfApp extends BGApp {
	
	private static Logger log = Logger.getLogger(WolfApp.class.getName());
	
	protected static JPanel mainPanel = null;
	protected JPanel northPanel = null;
	protected JButton startBtn;
	protected JButton stopBtn;
	protected JButton resetBtn;
	protected JButton helpBtn;
	protected JButton pauseBtn;
	private BGCanvas bgPanel;
	private JComboBox<String> ruleBox;
	private JTextField maxGnt;
	private JLabel maxGntLabel;
	private JLabel ruleLabel;
	private JLabel speedLabel;
	private JSlider speedSlider;
	
	BGGenerationSet bgset = null;
	private Thread thread = null;
	private boolean done = false;
	private int maxGntNum = 0;
	/**
	 * Sample app constructor
	 */
	public WolfApp() {
		System.out.println("wolfApp");
		frame.setSize(1200, 1000);
		frame.setTitle("PLANG GROW - Yixuan Wang");
		
		menuMgr.createDefaultActions();
		
		
		showUI();
	}
	
	
	/**
	 * Create a main panel that will hold the bulk of our application display
	 */
	@Override
	public JPanel getMainPanel() {
		// TODO Auto-generated method stub
//		System.out.println("getmainPanel");
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(BorderLayout.NORTH, getNorthPanel());
		
		// set canvas
		bgPanel = new BGCanvas();
		mainPanel.add(BorderLayout.CENTER, bgPanel);
		System.out.println("add mainpanel");
		
//		mainPanel.revalidate();
//		mainPanel.repaint();
//		mainPanel.setVisible(true);
//		mainPanel.removeAll();
		
		return mainPanel;
		
	}
	
	/**
	 * Create a top(North) panel that will hold control buttons
	 * 
	 * @return
	 */
	public JPanel getNorthPanel() {
		
		// Create the North Panel
		northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout());
		northPanel.setBackground(Color.WHITE);
		
		
		// Create Buttons
		startBtn = new JButton("Start");
		startBtn.addActionListener(this);
//		startBtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("Do START operation - Anonymous");
//				// ask for a panel redraw
//				startSim();
//			}
//		});	
		
		stopBtn = new JButton("Stop");
		stopBtn.addActionListener(this);
//		stopBtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("Do STOP operation - Anonymous");
//				// ask for a panel redraw
//				
//			}
//		});	

		
		resetBtn = new JButton("Reset");
		resetBtn.addActionListener(this);
//		resetBtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("Do RESET operation - Anonymous");
//				// ask for a panel redraw
//				
//			}
//		});	
		
		pauseBtn = new JButton("Pause");
		pauseBtn.addActionListener(this);
//		pauseBtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("Do PAUSE operation - Anonymous");
//				// ask for a panel redraw
//				
//			}
//		});	
		
		helpBtn = new JButton("Help");
		helpBtn.addActionListener(this);
//		helpBtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("Do HELP operation - Anonymous");
//				// ask for a panel redraw
//				
//			}
//		});	
		
		// Create Rule ComboBox
		ruleBox = new JComboBox<String>();
//		ruleBox.setModel(new DefaultComboBoxModel<>(new String[] { "Select Rule", "Rule1", "Rule2", "Rule3", "Rule4" }));
//        ruleBox.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent evt) {
//                ruleBoxActionPerformed(evt);
//            }
//        });
		
		ruleBox.addItem("Select a rule");
		ruleBox.addItem("RULE1");
		ruleBox.addItem("RULE2");
		ruleBox.addItem("RULE3");
		ruleBox.addItemListener(this);
		ruleBox.setEnabled(true);
		ruleBox.addActionListener(this);
		
		// Create text fields and labels
		maxGntLabel = new JLabel("Max Generation:");
		ruleLabel = new JLabel("Rule:");
		speedLabel = new JLabel("Speed:");
		maxGnt = new JTextField(10);
//		maxGnt.setText("5");
		
//		ruleLabel = new JLabel();
//		maxGntLabel = new JLabel();
//		speedLabel = new JLabel();
//		maxGntLabel.setText("Max Generation:");
//        ruleLabel.setText("Rule:");
//        speedLabel.setText("Speed:");
		
		// Create speed slider
		speedSlider = new JSlider(0, 10, 5);
		speedSlider.setMajorTickSpacing(5);
		speedSlider.setMinorTickSpacing(1);
		speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
		speedSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                System.out.println("speed: " + speedSlider.getValue());
            }
        });
		
		

		// Add all components on the panel
		northPanel.add(ruleLabel, BorderLayout.SOUTH);
		northPanel.add(ruleBox, BorderLayout.SOUTH);
		northPanel.add(maxGntLabel, BorderLayout.SOUTH);
		northPanel.add(maxGnt, BorderLayout.SOUTH);
		northPanel.add(speedLabel, BorderLayout.SOUTH);
		northPanel.add(speedSlider, BorderLayout.SOUTH);
		northPanel.add(startBtn);
		northPanel.add(stopBtn);
		northPanel.add(resetBtn);
		northPanel.add(pauseBtn);
		northPanel.add(helpBtn);
		
		northPanel.revalidate();
		
		return northPanel;
		
		
		
	}
	

	
	
	
	private void startPaint() {
		
		if(maxGnt.getText().equals("")) {
			System.out.println("gnt is null now");
			maxGnt.setText("5"); 
//			System.out.println(maxGnt.getText());// 如果没有输入 默认为5代
		}
		
//		System.out.println("start painting");
		
		if(ruleBox.getSelectedItem().toString() == "Select a rule") {
			bgPanel.ruleset = RuleSet.NULL;
			bgPanel.repaint();
		}
		
		if(bgPanel.ruleset != RuleSet.NULL) {
			if(bgset == null) {
				startObs(); 
				long duration = (long) (speedSlider.getValue() * 100);
				bgset.maxGntNum = Integer.parseInt(maxGnt.getText());
				bgset.setDuration(duration);
				if(thread == null) {
//					System.out.println("wolfApp startpainting thread is null");
					new Thread() { 
						public void run() { 
							bgset.run();
						}
					}.start();
				}
			} 
		}
		
		if(bgset != null && bgset.isPause() == true) {
			bgset.setDone(false);
			bgset.setPause(false);
			System.out.println("stop is false now");
		}
		stopBtn.setEnabled(true);
		pauseBtn.setEnabled(true);
		startBtn.setEnabled(false);
	}
	
	
//	private void startSim() {
//		startObs();
//		System.out.println(bgPanel.ruleset);
////		bgset.grow(bgPanel.ruleset);
////		bgset.showStem();
//	}
	
	
	
	private void stopPaint() {
		if(bgset == null) return;
		bgset.setDone(true);
		System.out.println("sys stop~~");
		stopBtn.setEnabled(false);
		pauseBtn.setEnabled(false);
		startBtn.setEnabled(false);
		resetBtn.setEnabled(true);
		maxGnt.setEnabled(false);
		speedSlider.setEnabled(false);
		
	}
	
	private void pausePaint() {
		if(bgset == null) return;
		bgset.setPause(true);
//		if(thread == null) System.out.println("thread null!!");
		System.out.println("sys pause~~");
		stopBtn.setEnabled(false);
		pauseBtn.setEnabled(false);
		startBtn.setEnabled(true);
		resetBtn.setEnabled(true);
		maxGnt.setEnabled(true);
		speedSlider.setEnabled(true);
	}
	
	
	private void resetPaint() {

		bgset = null;
		thread = null;
		bgPanel.ruleset = RuleSet.BLANK;
		bgPanel.repaint();
		System.out.println("sys reset~~");
		stopBtn.setEnabled(true);
		pauseBtn.setEnabled(true);
		startBtn.setEnabled(true);
		maxGnt.setEnabled(true);
		speedSlider.setEnabled(true);

		
	}
	
	private void showHelpInfo() {
//		WolfApp wapp = new WolfApp();
		AppHelp help = new AppHelp();
	}
	
	
	private void startObs() {
		// TODO Auto-generated method stub
		System.out.println("wolfApp start observing the Plant Growing");
		bgset = new BGGenerationSet();
		bgset.addObserver(bgPanel);
		bgset.ruleset = bgPanel.ruleset;
		bgset.setPause(false);
		System.out.println("start observing bgset of " + bgPanel.ruleset);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		log.info("We received an ActionEvent " + e);
		if (e.getSource() == startBtn) {
			System.out.println("Start pressed");
			log.info("We received an ActionEvent " + e);
			startPaint();
		} else if (e.getSource() == stopBtn) {
			System.out.println("Stop pressed");
			stopPaint();
			log.info("We received an ActionEvent " + e);
		} else if (e.getSource() == resetBtn) {
			System.out.println("Reset pressed");
			log.info("We received an ActionEvent " + e);
			resetPaint();
		} else if(e.getSource() == pauseBtn) {
			System.out.println("Pause pressed");
			pausePaint();
			log.info("We received an ActionEvent " + e);
		} else if(e.getSource() == helpBtn) {
			System.out.println("Help pressed");
			showHelpInfo();
			log.info("We received an ActionEvent " + e);
		}
		
	}
	
	
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == ruleBox) {
			if(e.getItem().toString() == "RULE1") {
				System.out.println("CHOOSE 1");
				bgPanel.ruleset= RuleSet.RULE1;
				bgPanel.col = Color.white;
			}else if(e.getItem().toString() == "RULE2") {
				System.out.println("CHOOSE 2");
				bgPanel.ruleset = RuleSet.RULE2;
				bgPanel.col = Color.white;
			}else if(e.getItem().toString() == "RULE3"){
				System.out.println("CHOOSE 3");
				bgPanel.ruleset = RuleSet.RULE3;
				bgPanel.col = Color.white;
			}else if(e.getItem().toString() == "Select a rule"){
				System.out.println("No choosing now");
				bgPanel.ruleset = RuleSet.NULL;
//				bgPanel.repaint();
			}
		}
	}
	

	
	@Override
	public void windowOpened(WindowEvent e) {
		log.info("Window opened");
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		log.info("Window closing");
	}

	@Override
	public void windowClosed(WindowEvent e) {
		log.info("Window closed");
	}

	@Override
	public void windowIconified(WindowEvent e) {
		log.info("Window iconified");
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		log.info("Window deiconified");
	}

	@Override
	public void windowActivated(WindowEvent e) {
		log.info("Window activated");
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		log.info("Window deactivated");
	}

	
	public static void main(String[] args) {
		WolfApp wapp = new WolfApp();
		log.info("WolfApp started");

	}

}
