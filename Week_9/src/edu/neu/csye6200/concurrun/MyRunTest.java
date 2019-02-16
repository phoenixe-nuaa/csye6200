package edu.neu.csye6200.concurrun;

public class MyRunTest {
	
	MyRunnable myRunA = null;
	Thread threadA = null;
	MyRunnable myRunB = null;
	Thread threadB = null;
	
	public MyRunTest() {
		// Create a Runnable
		myRunA = new MyRunnable("A");
		// Create a thread
		threadA = new Thread(myRunA);
		
		// Create a Runnable
		myRunB = new MyRunnable("B");
		// Create a thread
		threadB = new Thread(myRunB);
	}
	
	public void run() {
		// Start the thread
		threadA.start();
		threadB.start();
	}

	public static void main(String[] args) {
		MyRunTest mrtest = new MyRunTest();
		mrtest.run();
	}
}
