/**
 * 
 */
package edu.neu.csye6200.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.neu.csye6200.bg.BGGenerationSet;
import edu.neu.csye6200.bg.BGRule;
import edu.neu.csye6200.bg.BGStem;

/**
 * @author Chen.JL
 *
 */
public class BGApplication extends BGApp{

	// log
	private static Logger log = Logger.getLogger(BGApplication.class.getName());
	
	// Panels
	protected JPanel mainPanel = null;
	protected JPanel northPanel = null;
	protected BGPanel bgPanel;
	
	// Buttons
	protected JButton startBtn;
	protected JButton pauseresumeBtn;
	protected JButton stopBtn;
	protected JButton clearBtn;
	
	// ComboBox
	protected JComboBox<String> rule = null;
	
	// Input number of generations
    protected JLabel gn;
	protected JTextField geneNum;
	
	// Control flag
	protected boolean flag = true;
    
	protected Integer num;
	
	// Constructor
    public BGApplication() {
    	
    	frame.setSize(1000, 800);
    	frame.setTitle("BGApplication");
//    	menuMgr.createDefaultActions();
    	showUI();
    }
    
    
    // mainPanel
    @Override
    public JPanel getMainPanel() {
    	
    	mainPanel = new JPanel();
    	mainPanel.setLayout(new BorderLayout());
    	mainPanel.add(BorderLayout.NORTH, getNorthPanel());
    	
    	bgPanel = new BGPanel();
    	mainPanel.add(BorderLayout.CENTER, bgPanel);

    	return mainPanel;
    }

	
    // draw every generations on bgPanel
    public void startSim(BGGenerationSet bs) {
//    	bs.run();
    	System.out.println("bs Rule set size : " + bs.getRulelist().size());
    	for (BGRule rule: bs.getRulelist()) {
			System.out.println("pass stemList");
    		bgPanel.stemList = rule.getStemList();
//    		System.out.println("sl in panel size: " + bgPanel.stemList.size());
//    		bgPanel.repaint();
//    		startPanelTimerTask();
//    		System.out.println("rule stem size: " + rule.getStemList().size());
    		
    		/*Timer timer=new Timer();//实例化Timer类   
    		timer.schedule(new TimerTask(){   
    		public void run(){   
    			System.out.println("延时");   
    			this.cancel();}},3000);//五百毫秒  
*/    	}
//    	bgPanel.stemList = bs.getRulelist().get(bs.getRulelist().size()-1).getStemList();
//    	bgPanel.repaint();
   
    }
    
    // refresh panel
    public void startPanelTimerTask() {
    	Timer timer = new Timer();
    	timer.scheduleAtFixedRate(new TimerTask() {
    		
    		public void run() {
    			bgPanel.repaint();
    		}
    	}, 0, 1000);
    }
    
  /*  public static void refresh() {
    	bgPanel.repaint();
    }*/
    
    
//    // initialize GenerationSet
//    public BGGenerationSet initGeneSet(Integer num, Double radians) {
//    	BGGenerationSet bs = new BGGenerationSet(num, radians);
//    	Thread t = new Thread(bs);
//    	t.start();
//    	System.out.println("start thread");
//    	return bs;
//    }
	
    
    // Control Panel
	public JPanel getNorthPanel() {
    	
		northPanel = new JPanel();
    	northPanel.setLayout(new FlowLayout());
    	
    	// Add components
    	rule = new JComboBox<String>();
    	northPanel.add(rule);
    	rule.addItem("Rule 1");
    	rule.addItem("Rule 2");
    	rule.addItem("Rule 3");
    	
    	rule.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED) {
//					System.out.println("now choose: " + e.getItem());
					
					switch((String)(e.getItem())) {
					case "Rule 1": {
						System.out.println("choosing rule1");System.out.println("choosing rule1");
						BGGenerationSet bs1 = new BGGenerationSet(num, Math.PI/6);
//						startSim(bs1);
//				    	bgPanel.repaint();
						System.out.println("choosing rule1");
						System.out.println(e.getItem());
						break;
					}
					
					case "Rule 2": {
						BGGenerationSet bs2 = new BGGenerationSet(num, Math.PI/4);
//						startSim(bs2);
//				    	bgPanel.repaint();
						System.out.println(e.getItem());
						break;
					}
					
					case "Rule 3": {
						BGGenerationSet bs3 = new BGGenerationSet(num, Math.PI/3);
//						startSim(bs3);
//				    	bgPanel.repaint();
						System.out.println(e.getItem());
						break;
					}
					}
				}
			}
    		
    	});
    	
    	gn = new JLabel("Number of Generations");
    	northPanel.add(gn);
    	
    	geneNum = new JTextField(6);
    	northPanel.add(geneNum);
    	
//    	geneNum.addActionListener(new java.awt.event.ActionListener() {
//			
//    		@Override
//			public void actionPerformed(ActionEvent e) {
//				String s = geneNum.getText();
//				System.out.println(s);
//    		}
//    	
//    	});
    	
    	startBtn = new JButton("Start");
    	startBtn.addActionListener(this); // Allow the app to hear about button pushes
    	northPanel.add(startBtn);
    	
    	pauseresumeBtn = new JButton("Pause/Resume");
    	pauseresumeBtn.addActionListener(this);
    	northPanel.add(pauseresumeBtn);
    	
    	stopBtn = new JButton("Stop");
    	stopBtn.addActionListener(this);
    	northPanel.add(stopBtn);
    	
    	clearBtn = new JButton("Clear");
    	clearBtn.addActionListener(this);
    	northPanel.add(clearBtn);
    	
    	return northPanel;
    }

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		log.info("We received an ActionEvent " + e);
		
		if (e.getSource() == startBtn) {
			System.out.println("Start pressed");
			String s = geneNum.getText();
//			System.out.println(s);
			num = Integer.parseInt(s);
//			System.out.println(num);
//			initGeneSet(num, Math.PI/6);
//			startPanelTimerTask();
			BGGenerationSet bs1 = new BGGenerationSet(num, Math.PI/6);
//			for (BGRule r: bs1.getRulelist()) {
//				System.out.println(r.getStemList().size());
//			}
	    	Thread t = new Thread(bs1);
	    	t.start();
	    	System.out.println("start thread");
//	    	startSim(bs1);
//			System.out.println("start sim");
	    	bgPanel.repaint();
	    	bgPanel.repaint();
	    	bgPanel.repaint();
//	    	flag = true;
		}
		
		if (e.getSource() == pauseresumeBtn) {
			System.out.println("Pause/Resume pressed");
		}
		
		if (e.getSource() == stopBtn) {
			System.out.println("Stop pressed");
		}
		
		if (e.getSource() == clearBtn) {
			System.out.println("Clear pressed");
//			BGGenerationSet bs4 = null;
//	    	paintPanel(bs4);
	    	bgPanel.repaint();
//			flag = false;
		}
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
	 * @param args
	 */
	public static void main(String[] args) {
		BGApplication ba = new BGApplication();
		log.info("BGApplication Started");
	}

}

/**
*on-time 5
*early finish 5
*code 20
*abstract 5 
*sim 20
*growth 15 
*multi-rule 10 
*start/stop 5
*ui quality 10
*thread/comms 15
*visual quality 10
*bonus 10
*120
*/
