import java.util.HashMap;

import javax.swing.plaf.synth.SynthSpinnerUI;

/**
 * 
 */

/**
 * @author Chen.JL
 *
 */
public class Stem{
	// Add a starting location (i.e. X, Y), a length, and a direction (in radians)
	private Integer x;
	private Integer y;
	private Double length;
	private Double direction;
	// Generate unique ID for each stem instance
	private static int idCounter = 0;
	private int id;
	
	// Private container for holding child stems
	private HashMap<Integer,Stem> stemMap = new HashMap<Integer,Stem>();

	// Constructor
	Stem(Integer x, Integer y, Double length, Double direction) {
		this.x = x;
		this.y = y;
		this.length = length;
		this.direction = direction;
		setId(idCounter++);
	}
	
	// Getters and setters
	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getDirection() {
		return direction;
	}

	public void setDirection(Double direction) {
		this.direction = direction;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public HashMap<Integer,Stem> getStemMap() {
		return stemMap;
	}

	public void setStemMap(HashMap<Integer, Stem> stemMap) {
		this.stemMap = stemMap;
	}
	public void addStem(HashMap<Integer,Stem> stemMap, Stem stem)  {
		stemMap.put(stem.getId(),stem);
	}
	
	// Method that supports adding stems
	public void addStem(Stem stem)  {
		stemMap.put(stem.getId(),stem);
//		System.out.println("add");
	}
	
	// Method that supports getting stems
	public Stem getStem(int id)  {
//		System.out.println("get");
		return stemMap.get(id);
	}
	
	// Method that supports removing stems
	public void removeStem(int id)  {
		stemMap.remove(id);
	}
	
	// Method to list the number of child stems
	public Integer listChildernStem(HashMap<Integer,Stem> stemMap)  {
		int counter = 0;
		for (Stem stem: stemMap.values()) {
//			counter++;
			if (!stem.ChildrenStemIsEmpty(stem.getStemMap())) {
				listChildernStem(stem.getStemMap());
			}
			else counter++;
		}
		return counter++;
	}
	
	// Method to determine if the child list is empty
	public boolean ChildrenStemIsEmpty(HashMap<Integer,Stem> stemMap)  {
		return stemMap.isEmpty();
	}
	
	// Method to loop through all child stems and display
	public void show(HashMap<Integer,Stem> stemMap)  {
		for (Stem stem: stemMap.values()) {
			// If child stems are not empty, then print
			if (!stem.ChildrenStemIsEmpty(stem.getStemMap())) {
				System.out.println("------------------------------------------------------------------");
				System.out.println(String.format("  FatherStem ID:%1$2d  X:%2$2d  Y:%3$2d  Length:%4$2.2f  Direction:%5$2.2f", 
						stem.getId(), stem.getX(), stem.getY(), stem.getLength(), stem.getDirection()));
				System.out.println("------------------------------------------------------------------");
				show(stem.getStemMap());
			}
			else {
				System.out.println(String.format("    ChildStem ID:%1$2d  X:%2$2d  Y:%3$2d  Length:%4$2.2f  Direction:%5$2.2f", 
						stem.getId(), stem.getX(), stem.getY(), stem.getLength(), stem.getDirection()));			
			}			
		}
	}
}




