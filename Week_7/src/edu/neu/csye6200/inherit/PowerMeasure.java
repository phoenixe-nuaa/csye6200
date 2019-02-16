package edu.neu.csye6200.inherit;

public class PowerMeasure extends MeterA implements MeterI{
	// 1 Ampere
	protected double current = 1.0;
	// 12 Volts
	protected double voltage = 12.0;
	// Constructor
	public PowerMeasure() {
		System.out.println("PowerMeasure Constructor called");
	}
	// V = IR
	public double getResistivity() {
		return voltage/current;
	}
	
	@Override
	public double measure() {
		return voltage*current;
	}
	
	@Override
	public double calcProbeMeasure() {
		return 0;
	}
}
