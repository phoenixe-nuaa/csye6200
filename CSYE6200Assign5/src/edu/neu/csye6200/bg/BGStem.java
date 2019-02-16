/**
 * 
 */
package edu.neu.csye6200.bg;

import java.util.ArrayList;

/**
 * @author Chen.JL
 *
 */
public class BGStem {
	// Position, length and direction
	private Double x;
	private Double y;
	private Double length;
	private Double direction;
	
	// Indicate the depth
	private Integer depth;
	
	// Unique id
	private static int idCounter = 0;
	private int id;
	
	// Store child stems
	private ArrayList<BGStem> childlist = new ArrayList<BGStem>();
	
	// Constructor
	BGStem (Double x, Double y, Double length, Double direction, Integer depth) {
		this.x = x;
		this.y = y;
		this.length = length;
		this.direction = direction;
		this.depth = depth;
		setId(idCounter++);
	}

	// Getters and setters
	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
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
	
	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public ArrayList<BGStem> getChildlist() {
		return childlist;
	}

	public void setChildlist(ArrayList<BGStem> childlist) {
		this.childlist = childlist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
