/**
 * 
 */
package edu.neu.csye6200.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import edu.neu.csye6200.bg.BGGenerationSet;
import edu.neu.csye6200.bg.BGGenerationSetDynamic;

/** 
 * BGApplication: The application of Growth Simulation, extends from BGApp
 * @author Chen.JL
 */
public class BGApplication extends BGApp{

	// log
	private static Logger log = Logger.getLogger(BGApplication.class.getName());
	
	// Panels
	protected static JPanel mainPanel = null;
	protected JPanel northPanel = null;
	protected static BGPanel bgPanel;
	
	// Control Buttons
	protected JButton startBtn;
	protected JButton pauseresumeBtn;
	protected JButton stopBtn;
	protected JButton clearBtn;
	protected JButton helpBtn;
	
	// Custom Rule or Dynamic Rule
	protected JRadioButton dynamicRule;
	protected JRadioButton customRule;
	
	// To Apply a new rule
	protected JButton newruleBtn;
	
	// Current Rule
	protected Integer currentRule;
	
	// Rule Selection
	protected JLabel rl;
	protected JComboBox<String> rule;
	
	// Input number of generations
    protected JLabel gn;
	protected JTextField geneNum;
	
	// number of generations
	protected Integer num;
	
	// geneSet
	protected BGGenerationSet bs;
	protected BGGenerationSetDynamic bsd;
	
	/**
     * Constructor
     */
    public BGApplication() {
    	
    	// Setting
    	frame.setSize(1300, 1000);
    	frame.setTitle("BGApplication");
//    	menuMgr.createDefaultActions();
    	showUI();
    }
    
    
    /**
     * Get mainPanel
     * @return mainPanel the main panel
     */
    @Override
    public JPanel getMainPanel() {
    	
    	// Setting
    	mainPanel = new JPanel();
    	mainPanel.setLayout(new BorderLayout());
    	
    	// Control and everything on NorthPanel
    	mainPanel.add(BorderLayout.NORTH, getNorthPanel());
    	
    	// bgPanel for growth simulation
    	bgPanel = new BGPanel();
    	mainPanel.add(BorderLayout.CENTER, bgPanel);

    	return mainPanel;
    }

	
    /**
     * Get northPanel
     * @return northPanel the panel on which are all the control buttons
     */
	public JPanel getNorthPanel() {
    	
		// Setting
		northPanel = new JPanel();
    	northPanel.setLayout(new FlowLayout());
    	
    	// Modal selection buttons
    	customRule = new JRadioButton("Custom Rule");
    	dynamicRule = new JRadioButton("Dynamic Rule");
    	
    	// Only one of the two radio buttons can be enabled
    	ButtonGroup group = new ButtonGroup();
    	group.add(customRule);
    	group.add(dynamicRule);

    	// Add to NorthPanel
    	northPanel.add(customRule);
    	northPanel.add(dynamicRule);
    	
    	// If we select the custom rule
    	customRule.addItemListener(new ItemListener() {
			
    		@Override
    		public void itemStateChanged(ItemEvent e) {
				
    			// Disable the 'Apply' button 
				newruleBtn.setEnabled(false);
				// Set customFlag
				bgPanel.setCustomflag(true);
			}
    	});
    	
    	// If we select the dynamic rule
    	dynamicRule.addItemListener(new ItemListener() {
			
    		@Override
			public void itemStateChanged(ItemEvent e) {
				
    			// Enable the 'Apply' button
				newruleBtn.setEnabled(true);
				// Set customFlag
				bgPanel.setCustomflag(false);
			}
    	});
    	
    	// Add components
    	gn = new JLabel("Please enter the number of Generations");
    	northPanel.add(gn);
    	
    	geneNum = new JTextField(3);
    	northPanel.add(geneNum);
    	
    	rl = new JLabel("Please select a rule:");
    	northPanel.add(rl);
    	
    	rule = new JComboBox<String>();
    	northPanel.add(rule);
    	rule.addItem("...");
    	rule.addItem("Rule 1");
    	rule.addItem("Rule 2");
    	rule.addItem("Rule 3");
    	
    	// When choosing rules
    	rule.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED) {
					
					// Change the currentRule according to rule selections
					switch((String)(e.getItem())) {
					case "Rule 1": {
						currentRule = 1;
						System.out.println("Choosing rule 1");
						break;
					}
					
					case "Rule 2": {
						currentRule = 2;
						System.out.println("Choosing rule 2");
						break;
					}
					
					case "Rule 3": {
						currentRule = 3;
						System.out.println("Choosing rule 3");
						break;
					}
					}
				}
			}
    		
    	});
    	
    	// Create the button to apply a new rule and add to the panel
    	newruleBtn = new JButton("Apply new rule");
    	northPanel.add(newruleBtn);
    	
    	// When we press the button, change the rule of BGGeneration growth
    	newruleBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {			
				
				// Change the rule of growth
				bsd.setRule(currentRule);
				System.out.println("change to Rule: " + currentRule);
			}
    		
    	});
    	
    	// Create process control buttons and add them to the panel 
    	startBtn = new JButton("Start");
    	pauseresumeBtn = new JButton("Pause/Resume");
    	stopBtn = new JButton("Stop");
    	helpBtn = new JButton("Help");
    	
    	northPanel.add(startBtn);
    	northPanel.add(pauseresumeBtn);
    	northPanel.add(stopBtn);
    	northPanel.add(helpBtn);
    	
    	// Allow the application to hear about button pushes
    	startBtn.addActionListener(this); 
    	pauseresumeBtn.addActionListener(this);
    	stopBtn.addActionListener(this);    	
    	helpBtn.addActionListener(this);    	
    	
    	// Tried to add a Clear Button
    	// Seems unnecessary, combines the "Clear" function with the stop button
/*    	clearBtn = new JButton("Clear");
    	clearBtn.addActionListener(this);
    	northPanel.add(clearBtn);*/
    	
    	return northPanel;
    }

	
	/**
	 * Start Simulation with input number of generation and rule
	 * @param rule the chosen rule
	 */
	public void startSim(Integer rule) {
		
		// Initialize
//		bs.setRunflag(true);
		num = Integer.valueOf(geneNum.getText());
		bsd = new BGGenerationSetDynamic(num, Math.PI/5, rule);
		
		// Set runFlag to true
		// Meaning that start growth
		bsd.setRunflag(true);
		
		// Start thread
//    	Thread t = new Thread(bs);
		Thread t = new Thread(bsd);
		t.start();
		
		// Add bgPanel as an observer
//    	bs.addObserver(bgPanel);
		bsd.addObserver(bgPanel);
		System.out.println("start sim");
	} 

	
	/**
	 * Refresh bgPanel by calling the repaint method
	 */
	public static void refresh() {
//    	System.out.println("refresh");
		bgPanel.repaint();
	}
	
	
	/**Reacting to different events
	 * @param e the action event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		log.info("We received an ActionEvent " + e);
		
		// If we press the start button
		if (e.getSource() == startBtn) {
			
			System.out.println("Start pressed");

			// Start growth simulation with chosen rule
			startSim(currentRule);
			
			// Set the panel's clearFlag to true
			// Meaning that no need to clear the panel, at the beginning :)
			bgPanel.setClear(false);
			
			// Enable buttons
			startBtn.setEnabled(false);
			stopBtn.setEnabled(true);
			pauseresumeBtn.setEnabled(true);
		}
		
		// If we press the pause or resume button
		if (e.getSource() == pauseresumeBtn) {
			
			System.out.println("Pause/Resume pressed");
			
			// Flip the boolean status of the button
			// To conduct the "pause" or "resume" function
//			bs.invert();
			bsd.invert();
		}
		
		// If we press the stop button
		if (e.getSource() == stopBtn) {
			
			System.out.println("Stop pressed");
			
			// Set the panel's runFlag to false
		    // Meaning that the growth of BGGenerationSet STOPs.
//			bs.setRunflag(false);
			bsd.setRunflag(false);
			
			// Enable buttons
			startBtn.setEnabled(true);
			stopBtn.setEnabled(false);
			pauseresumeBtn.setEnabled(false);
			
			// Reset the input and rule selection to default values.
			// Ready for a new Growth Simulation.
			geneNum.setText("");
			rule.setSelectedIndex(0);
			
			// Set the panel's clearFlag to true
			// To clear the panel, ready for a new Growth Simulation now.
			bgPanel.setClear(true);
			refresh();
		}
		
		// If we press the help button
		if (e.getSource() == helpBtn) {
			
			final JDialog dialog = new JDialog(frame,"Help");
			final JLabel help = new JLabel();
//		    final JButton closeBtn = new JButton("Close");
		    
		    // Help dialog setting
		    dialog.setSize(820,600);
		    dialog.setLocation(400, 300);
		    dialog.setLayout(new FlowLayout());
			
		    // Set Modal to false 
			dialog.setModal(false);
    		if(dialog.getComponents().length == 1){
    			dialog.add(help);
    		}
    		
    		// Add Help Content
    		help.setText("<html> <h2 style=text-align:center> Instruction </h2> This BGApplication has two modals: Custom Rule and Dynamic Rule."
    				+ "<br/> Under modal Custom Rule the plant grows with consistent rule, while under modal Dynamic Rule the plant can grow with changeable rule."
    				+ "<br/> <h3>To start Growth Simulation: </h3>"
    				+ " - Select the modal"
    				+ "<br/> - Enter the number of generations"
    				+ "<br/> - Select the rule"
    				+ "<br/> - Click 'Start' button"
    				+ "<h3> During the process of Growth Simulation under modal Custom Rule: </h3>"
    				+ " - Click 'Pause/Resume' button to pause or resume"
    				+ "<br/> - Click 'Stop' button for an early termination and reset all"
    				+ "<h3> During the process of Growth Simulation under modal Dynamic Rule: </h3>"
    				+ " - Click 'Pause/Resume' button to pause"
    				+ "<br/> - Select a new Rule"
    				+ "<br/> - Click 'Apply new rule' button to apply the new rule"
    				+ "<br/> - Click 'Pause/Resume' button to resume"
    				+ "<br/> - Click 'Stop' button for an early termination and reset all"
    				+ "<h3> To generate a pretty flower, I recommend the following combination :) </h3>"
    				+ " - Enter the number of generations as 10"
    				+ "<br/> - Select 'Rule 3' for Generation 0-2"
    				+ "<br/> - Select 'Rule 2' for Generation 3-8"
    				+ "<br/> - Select 'Rule 1' for Generation 9-10"
    				+ "</html>");
    		
    		// Add close button
//    		dialog.add(closeBtn);
    		dialog.setVisible(true);
    		
/*    		// Close the dialog
    		closeBtn.addActionListener(new ActionListener(){
    			public void actionPerformed(ActionEvent e){
    				dialog.dispose();
    			}
    		});	*/		
		}
		
		// Tried to add a Clear Button
    	// Seems unnecessary, combines the "Clear" function with stop button
		/*if (e.getSource() == clearBtn) {
			System.out.println("Clear pressed");
			
			bgPanel.setClear(true);
			refresh();
			
			clearBtn.setEnabled(false);
		}*/
	}

	
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	
	/**
	 * The main method to start the BGApplication where the MAGIC begins.
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Create the instance
		BGApplication ba = new BGApplication();
		log.info("BGApplication Started");
	}
}
