package edu.neu.csye6200.timer;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {
	private Timer timer;
	
	// Constructor
	public TimerDemo() {
		timer = new Timer();
		// task, delay time, periodic delay
		timer.scheduleAtFixedRate(new RemindTask(), 3000L, 1000L);
	}
	
	// A TimerTask Class
	class RemindTask extends TimerTask {
		private int ctr = 0;
		
		@Override
		public void run() {
			System.out.println("Timer alert " + ctr++);
			// Ring the bell
			Toolkit.getDefaultToolkit().beep();
			if (ctr > 5)
				timer.cancel();
		}
	}
	
	public static void main(String[] args) {
		new TimerDemo();
	}
}