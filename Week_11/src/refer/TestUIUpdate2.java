package refer;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 

/** * �¼����������UI��ˢ�� * 
 * @author Bruce * 
 * @version 1.0 */ 
public class TestUIUpdate2 { 
	public TestUIUpdate2() { 
		TestUIUpdate2Frame frame = new TestUIUpdate2Frame(); 
		frame.pack(); 
		frame.setVisible(true); 
	} 

	public static void main(String[] args) { 
		new TestUIUpdate2(); 
	} 
} 

class TestUIUpdate2Frame extends JFrame {
	JTextPane pane = new JTextPane(); 
	JButton button = new JButton("action..."); 
	TestUIUpdate2Frame() { 
		init(); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		button.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				new Thread() { 
					public void run() { 
						try { 
							showMessage("step one..."); 
							System.out.println("thread1 start");
							Thread.sleep(500); 
							showMessage("\nstep two..."); 	
							System.out.println("thread1 ing");
							Thread.sleep(500); 
							showMessage("\nfinished."); 
							System.out.println("thread1 finish");
							Thread.sleep(500); 
						} 
						catch (InterruptedException ie) { 
							//ignored 
						} 
					} 
				}.start();

			} 
		}); 
	} 
	
	
	private void showMessage (final String msg) { 
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				pane.setText(pane.getText() + msg); 
				System.out.println("this is another thread");
			} 
		}); 
	} 

	private void init() { 
		pane.setPreferredSize(new Dimension(300, 200)); 
		Container content = getContentPane(); 
		content.setLayout(new BorderLayout()); 
		content.add(pane, BorderLayout.CENTER); 
		content.add(button, BorderLayout.SOUTH); 
	}	 
	} 
//���Ѱ�ť��action�¼�����һ���߳��Ȼ����SwingUtilities.invokeLater����