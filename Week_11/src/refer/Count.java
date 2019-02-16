package refer;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JTextField;
 
/**
 * 将显示数值的任务放在一个单独的线程中。
 * */
public class Count extends JApplet {
	private class SeparateSubTask extends Thread{
		private int count = 0;
		private boolean runFlag = true;
		
		SeparateSubTask(){
			start();
		}
		
		void invertFlag(){
			runFlag = ! runFlag;
		}
		
		public void run(){
			while(true){
				try{
					sleep(100);
				}
				catch(InterruptedException e){
					System.err.println("Interrupted");
				}
				if(runFlag)
					t.setText(Integer.toString(count++));
			}
		}		
	}
	
	private SeparateSubTask sp = null;
	private JTextField t = new JTextField(10);
	
	private JButton start = new JButton("开  始");
	private JButton onOff = new JButton("暂  停");
	
	class StartL implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			if(sp == null){
				sp = new SeparateSubTask();
			}
		}
	}
	
	class OnOffL implements ActionListener{
 
		@Override
		public void actionPerformed(ActionEvent e) {
			if(sp != null){
				sp.invertFlag();
			}			
		}			
	}
	
	public void init(){
		Container cp = getContentPane();
		
		cp.setLayout(new FlowLayout());
		cp.add(t);
		
		start.addActionListener(new StartL());
		cp.add(start);
		
		onOff.addActionListener(new OnOffL());
		cp.add(onOff);			
	}
 
}

