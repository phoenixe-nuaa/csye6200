package edu.neu.csye6200.concur;

public class MyThread extends Thread {
	// We are not done initially
	public boolean done = false;
	// How many times around the loop?
	private int ctr = 0;
	
	public MyThread(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		while (!done) {
			// Do important time consuming work
			doWork();
			// Count how many times around the loop
			ctr++;
			System.out.println("Thread: " + this.getName() + "- loop: " + ctr);
			if (ctr > 1000)
				done = true;
		}
	}
	
	public void doWork() {
		for (int i = 0; i < 100000; i++) {
			double val = Math.exp((double)i);
		}
	}
}
