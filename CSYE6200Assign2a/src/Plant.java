/**
  Write a Plant and a PlantTest class ( Plant.java  and  PlantTest.java )
• To the Plant class
• Add values for Type Name and specimen ID
• Add a constructor that sets the Type Name and specimen ID , along with the other needed instance variables
• Add values for plant age (you choose units) and length
• Add a method to  grow the plant (i.e. make it longer and older)
• In the PlantTest program, use the ‘new’ operation with your Plant constructor to generate  five instances of different Plant objects.
• Add a method to print an attractive display of the Plant data including the type name, specimen ID, age and length
• Have your test code print the contents of all Plant instances.
• Submit your source code Blackboard as.java files. Include a copy of your program’s output captured in a text file. 
 
 */

/**
 * @author Chen.JL
 *
 */
public class Plant {
	
	private String  specimenID;
	private String  TypeName;
	private Integer height;
	private Integer age;
	
	// Constructor
	public Plant() {
		height = 1;
		age = 10;
		specimenID = "01";
		TypeName   = "lavender";
	}
	
	// constructor
	public Plant(String id, String type, int height, int age) {		
		this.specimenID = id;
		this.TypeName = type;
		this.height = height;
		this.age = age;
	}
	
	// grow
	public void grow(int year) {
		for (int i = 0; i < year; i++) {
			height+=10;
			age++;
		}
	}
	
	// display of plant instances
	public String print() {
		String st = String.format("Plant TypeName:%1$10s  Specimen ID:%2$5s  Age:%3$3d  Height:%4$2d", TypeName, specimenID, age, height);
		return st;
	}

}
