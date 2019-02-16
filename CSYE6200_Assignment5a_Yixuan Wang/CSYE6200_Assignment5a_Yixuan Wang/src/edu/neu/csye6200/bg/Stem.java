package edu.neu.csye6200.bg;

import java.util.ArrayList;
import java.util.Random;



public class Stem {
	// Add a starting location (i.e. X, Y), a length, and a direction (in radians)
	private double radians;
	private double length;
	private double X;
	private double Y;
	private int id;
	static private int idCounter = 0;
	private ArrayList<Stem> childStemList;  // Add a private container for holding child stem instances
	
	
	// Constructor
	public Stem(double radians, double length) {
		this.radians = radians;
		this.length = length;
//		X = 0;
//		Y = 0;
		id = idCounter++;
		childStemList = new ArrayList<Stem>();
	}
	
	
	public Stem(double radians, double length, double X, double Y) {
		this.radians = radians;
		this.length = length;
		this.X = X;
		this.Y = Y;
		childStemList = new ArrayList<Stem>();
		id  = idCounter++;  // Create a unique ID value
	}
	
	
	// Get and Set method
	public double getRadians() {
		return radians;
	}

	public void setRadians(double radians) {
		this.radians = radians;
	}

	public double getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getY() {
		return Y;
	}

	public void setY(double y) {
		Y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public ArrayList<Stem> getChildStemList() {
		return childStemList;
	}

	public void setChildStemList(ArrayList<Stem> childStemList) {
		this.childStemList = childStemList; 
	}
	
	
	// Add public convenience methods that support adding, getting, and removing stem instances
	public void addStem(Stem st) {
		double l1 = this.length;
		double x1 = this.X;
		double y1 = this.Y;
		double x2 = l1 * Math.cos(this.radians) + x1;
	    double y2 = l1 * Math.sin(this.radians) + y1;
        st.setX(x2);
        st.setY(y2);
		childStemList.add(st);
	}
	
	public void removeStem(Stem childStem) {
		childStemList.remove(childStem);
	}
	
	public void gettingStem(int id) {
		for(Stem cs: childStemList) {
			if(cs.getId() == id) {
			System.out.println("The stem is: " + cs);
			}
		}
	}
	
	
//	// A method to list the number of children 
//	public static int listnumber(ArrayList<Stem> childStemList) {
//		int sum = 1;
//		for(Stem cs : childStemList) {
//			if(cs.getChildStemList().size() != 0 ) {
//				sum = sum + listnumber(cs.getChildStemList());
//			}
//			else {
//				sum = sum + 1;
//			} 
//		}
//		return sum;
//	}
//	
//	
//	
//	public static void printNumber(ArrayList<Stem> childStemList) {
//		System.out.println("The number of child stems is: " + listnumber(childStemList));
//	}
//	
//	
//	// A method to determine if the list is empty
//	public static void ifEmpty(ArrayList<Stem> childStemList) {
//		if(childStemList.size() == 0) {
//			System.out.println("The child stems list is empty. There is no child stem.");
//		}
//		else{
//			System.out.println("The child stems list isn't empty.");
//		}
//	}
//	
//
//	// A method that loops through all child stems in class, and prints the contents (add an attractive header) 
//	public static void printStem(ArrayList<Stem> childStemList){
//		for(Stem cs: childStemList) {
//			System.out.println(cs);
//		}	
//	}
//
	public String toString() {
		return String.format("Child Stem> ID:%1$01d   Radians:%2$01.2f   Length:%3$01.2f   X: %4$01.2f   Y: %5$01.2f", getId(), getRadians(), getLength(), getX(), getY());
	}
	
	public String printBaseString() {
		return String.format("Base Stem> ID:%1$01d   Radians:%2$01.2f   Length:%3$01.2f   X: %4$01.2f   Y: %5$01.2f", getId(), getRadians(), getLength(), getX(), getY());
	}
}

