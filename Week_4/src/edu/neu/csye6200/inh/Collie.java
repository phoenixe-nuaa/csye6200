package edu.neu.csye6200.inh;

public class Collie extends Dog {

	public Collie(String name, double speed, int legs) {
		super(name, speed, legs);
	}
	
	public void bark() {
		System.out.println("Woof");
	}
	
	public void wagTail() {
		System.out.println("Wag Tail");
	}
}
