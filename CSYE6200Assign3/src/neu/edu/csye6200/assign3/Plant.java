package neu.edu.csye6200.assign3;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author Chen.JL
 *
 */
public class Plant {
	// Variables of the plant
	protected String  specimenID;
	protected String  TypeName;
	protected Integer height;
	protected Integer age;
	// Each plant has a base stem
	protected Stem basestem;

	// Constructor with default values
	public Plant() {
		height = 3;
		age = 30;
		specimenID = "03";
		TypeName   = "peachtree";
		basestem =  new Stem(0.0, 0.0, 0.0, 0.0);
	}
	
	// Constructor with parameters
	public Plant(String id, String type, int height, int age, Stem basestem) {		
		this.specimenID = id;
		this.TypeName = type;
		this.height = height;
		this.age = age;
		this.basestem = basestem;
	}
	
	// Getters and setters
	public String getSpecimenID() {
		return specimenID;
	}

	public void setSpecimenID(String specimenID) {
		this.specimenID = specimenID;
	}

	public String getTypeName() {
		return TypeName;
	}

	public void setTypeName(String typeName) {
		TypeName = typeName;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	public Stem getBasestem() {
		return basestem;
	}

	public void setBasestem(Stem basestem) {
		this.basestem = basestem;
	}

	// Method to determine if a base stem is present
	public boolean BasestemIsPresent() {
		if (basestem == null) return false;
		else return true;
	}
	
	// Method to calculate and return the total number of child stems
	public Integer totalChildStem(HashMap<Integer,Stem> stemMap) {
		int counter = 0;
		for (Stem stem: stemMap.values()) {
			counter++;
			// Call listChindernStem() in Stem class
			counter+=stem.listChildernStem(stem.getStemMap());
		}
		return counter;
	}
	
	// Grow
	public void grow(int year) {
		for (int i = 0; i < year; i++) {
			height+=10;
			age++;
		}
	}
	
	// Method to calculate the total height
	public Double totalheight(HashMap<Integer,Stem> stemMap, ArrayList<Double> totalheightlist) {
		for (Stem stem: stemMap.values()) {
			if (stem.ChildrenStemIsEmpty(stem.getStemMap())) {
				totalheightlist.add(stem.getY()+stem.getLength()*Math.sin(stem.getDirection()));
			}
			else totalheight(stem.getStemMap(), totalheightlist);
		}
		return Collections.max(totalheightlist);
	}
	
	// Method to calculate the total width
	public Double totalwidth(HashMap<Integer,Stem> stemMap, ArrayList<Double> totalwidthlist) {
		for (Stem stem: stemMap.values()) {
			if (stem.ChildrenStemIsEmpty(stem.getStemMap())) {
				totalwidthlist.add(stem.getX()+stem.getLength()*Math.cos(stem.getDirection()));
			}
			else totalwidth(stem.getStemMap(), totalwidthlist);
		}
		return Collections.max(totalwidthlist)-Collections.min(totalwidthlist);
	}
	
	// Display of plants
	public void print() {
		System.out.println(String.format("Plant TypeName:%1$10s  Specimen ID:%2$5s  Age:%3$3d  Height:%4$2d", 
				                         TypeName, specimenID, age, height));
	}
}
