package edu.neu.csye6200.inh;

public class Dog {
	static private int idCounter = 0;
	static public double airTemp = 39.0;
	private int id;
	private int legs;
	private double speed;
	private String name = "";
	
	public Dog(String name, double speed, int legs) {
		this.name = name;
		this.speed = speed;
		this.legs = legs;
		// Create a unique ID value
		id = idCounter++;
	}
	
	public Dog(String name, String speed) {
		this.name = name;
		// a static method call
		this.speed = Double.valueOf(speed);
		this.legs = 4;
		// Create a unique ID value
		id = idCounter++;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public int getLegs() {
		return legs;
	}

	public void setLegs(int legs) {
		// Don't allow negative legs
		if (legs < 0) legs = 0;  
		this.legs = legs;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void bark() {
		System.out.println("Bark");
	}
	
	public String toString() {
		return String.format("Dog> %1$12s %2$5.2f %3$01d", getName(), getSpeed(), getLegs());
	}

}
