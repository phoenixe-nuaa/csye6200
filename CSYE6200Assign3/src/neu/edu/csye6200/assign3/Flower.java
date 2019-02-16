package neu.edu.csye6200.assign3;

public class Flower {
	private String color;
	private Integer petalnumber;
	
	// Constructor
	public Flower(String color, Integer petalnumber) {
		this.color = color;
		this.petalnumber = petalnumber;
	}

	// Getters and setters
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getPetalnumber() {
		return petalnumber;
	}

	public void setPetalnumber(Integer petalnumber) {
		this.petalnumber = petalnumber;
	}
	
	// Print Flower instances
	public void print() {
		System.out.println(String.format("Flower Color:%1$10s  PetalNumber:%2$5s", color, petalnumber));			
	}			
}
