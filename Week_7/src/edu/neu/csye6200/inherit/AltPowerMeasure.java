package edu.neu.csye6200.inherit;

public class AltPowerMeasure extends PowerMeasure{
	public AltPowerMeasure() {
		System.out.println("AltPowerMeasure constructor called");
	}
	
	@Override
	public double getResistivity() {
		return super.getResistivity() * 2;
//		return (voltage/current) * 2;
	}
}
