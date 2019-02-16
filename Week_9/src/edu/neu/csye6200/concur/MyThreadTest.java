package edu.neu.csye6200.concur;

public class MyThreadTest {
	
	private MyThread threadA = null;
	// A group of threads
	private MyThread[] threads = new MyThread[10];
	
	// Constructor
	public MyThreadTest() {
		threadA = new MyThread("A");
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new MyThread("Thread" + i);
		}
	}
	
	
	public void run() {
		// Launch our thread - Lift off!
		threadA.start();
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
	}


	public static void main(String[] args) {
		MyThreadTest mtt = new MyThreadTest();
		mtt.run();
		System.out.println("We are done with main!!!");
	}

}
