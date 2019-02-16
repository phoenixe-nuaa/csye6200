package neu.edu.csye6200.assign3;

import java.util.ArrayList;

public class FlowerPlant extends Plant{
	private Flower flower;
	
	// Container for flower plant to store flowers
	private ArrayList<Flower> flrlist = new ArrayList<Flower>();
	
	// Constructor
	public FlowerPlant() {
		super();
		flower = new Flower("tulip",10);
	}

	// Constructor
	public FlowerPlant(String id, String type, int height, int age, Stem basestem, Flower flower) {		
		super(id, type, height, age, basestem);
		this.flower = flower;
		flrlist.add(flower);
	}
		
	// Getters and Setters
	public Flower getFlower() {
		return flower;
	}

	public void setFlower(Flower flower) {
		this.flower = flower;
	}

	public void setFlrlist(ArrayList<Flower> flrlist) {
		this.flrlist = flrlist;
	}

	public ArrayList<Flower> getFlrlist() {
		return flrlist;
	}
	    
	// Override grow method from Plant class
	public void grow(Flower flower) {
		flrlist.add(flower);
	}
	
	// Print Flower Plant info, using 'super' to access parent class
	public void print() {
		System.out.println("******************************************************************");
		super.print();
		System.out.println("------------------------------------------------------------------");
		System.out.println(String.format("BaseStem ID:%1$2d  X:%2$2.2f  Y:%3$2.2f  Length:%4$2.2f  Direction:%5$2.2f", 
				super.getBasestem().getId(),super.getBasestem().getX(),super.getBasestem().getY(),
				super.getBasestem().getLength(),super.getBasestem().getDirection()));
		System.out.println("------------------------------------------------------------------");
		for (Flower flr:flrlist) {
			flr.print();
		}
	}
}
