package edu.neu.csye6200.obs;

import java.util.Observable;

public class EventPublisher extends Observable{
	
	// Set true to stop the run method
	private boolean done = false;
	// Count how many times we loop around
	private int ctr = 0;
	// Constructor
	public EventPublisher() {
		System.out.println("EventPubliher - We are here");
	}
	
	// An event has occurred, so notify the subscribers
	public void doAction() {
		// Indicate that a change has happened
		setChanged();
		notifyObservers(new String("A message " + ctr));
	}
	
	// Do a lot of messaging
	public void run() {
		while (!done) {
			doAction();
			if (ctr++ > 10)
				done = true;
		}
	}
	
	public static void main(String [] args) {
		// We have a publisher
		EventPublisher ep = new EventPublisher();
		// We have a subscriber
		EventSubscriber es = new EventSubscriber();
		// Make the subscription
		ep.addObserver(es);
		ep.run();
	}
}
