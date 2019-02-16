package edu.neu.csye6200.inh;

import java.util.ArrayList;
import java.util.HashMap;

public class DogTest {
	
	private Dog simpleDog;
	private Dog simpleDog2;
	private Collie simpleCollie;
	
	// An ArrayList class
	private ArrayList<Dog> dogList;
	// A HashMap class
	private HashMap<String,Dog> dogMap;
	
	public DogTest() {
	// Constructor
		double airTemp = Dog.airTemp;
		
		// Create an ArrayList instance
		dogList = new ArrayList<Dog>();
		// Create a HashMap instance
		dogMap = new HashMap<String,Dog>();
		
		simpleDog = new Dog("Fido", 10.0, 4);
		simpleDog2 = new Dog("Fido2", "9.1");
		simpleCollie = new Collie("Tramp", 12.0, 4);
	}
	
	private void run () {
		System.out.println("My simpleDog: " + simpleDog.getName());
		simpleDog.bark();
		System.out.println("My simpleDog ID is: " + simpleDog.getId());
		
		System.out.println("My simpleCollie: " + simpleCollie.getName());
		simpleCollie.bark();
		simpleCollie.wagTail();
		System.out.println("My simpleCollie ID is: " + simpleCollie.getId());
		
		dogList.add(simpleDog);
		dogList.add(simpleDog2);
		dogList.add(simpleCollie);
		
		dogMap.put(simpleDog.getName(), simpleDog);
		dogMap.put(simpleDog2.getName(), simpleDog2);
		dogMap.put(simpleCollie.getName(), simpleCollie);
		
		for (Dog dg: dogList) {
			System.out.println("Before: " + dg);
		}
		
		dogList.remove(1);
		
		for (Dog dg: dogList) {
			System.out.println("After: " + dg);
		}
		
		for (Dog dg: dogMap.values()) {
			System.out.println("Map: " + dg);
		}
		
		Dog bulletDog = dogMap.get("Tramp");
//		Dog bulletDog = dogMap.get("Bulllet");
		System.out.println("My dog is " + bulletDog);
	}
	
	public static void main (String[] args) {
		DogTest dt = new DogTest();
		dt.run();
	}
}
