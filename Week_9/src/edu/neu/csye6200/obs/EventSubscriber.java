package edu.neu.csye6200.obs;

import java.util.Observable;
import java.util.Observer;

public class EventSubscriber implements Observer{

	@Override
	public void update(Observable src, Object msg) {
		// React to the update
		System.out.println("ES received update from: " + src);
		System.out.println("                message: " + msg);
	}
}