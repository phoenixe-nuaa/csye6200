package edu.neu.csye6200.serial;

import java.io.Serializable;

public class CarData implements Serializable, Comparable<CarData> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int iValue = 0;
	private double dValue1 = 0.1;
	private double dValue2 = 0.2;
	private String name;

	// Constructor
	public CarData(int iValue, double dValue1, double dValue2, String name) {
		this.iValue = iValue;	
		this.dValue1 = dValue1;
		this.dValue2 = dValue2;
		this.name = name;
	}

	public int getiValue() {
		return iValue;
	}

	public void setiValue(int iValue) {
		this.iValue = iValue;
	}

	public double getdValue1() {
		return dValue1;
	}

	public void setdValue1(double dValue1) {
		this.dValue1 = dValue1;
	}

	public double getdValue2() {
		return dValue2;
	}

	public void setdValue2(double dValue2) {
		this.dValue2 = dValue2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int compareTo(CarData cd) {
		System.out.println("Compare to");
		return name.compareTo(cd.getName());
	}
}
