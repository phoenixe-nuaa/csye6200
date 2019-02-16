package edu.neu.csye6200.inherit;

public class InheritTest {
	// Constructor
	public InheritTest() {
		
	}
	
	public void run() {
		PowerMeasure pm0 = new PowerMeasure();
		System.out.println("PowerMeasure Resistivity:" + pm0.getResistivity());

		AltPowerMeasure apm0 = new AltPowerMeasure();
		System.out.println("PowerMeasure Resistivity:" + apm0.getResistivity());
		
		displayMeterMeasure(apm0);// It's a MeterI
		// static call to create an instance of MeterManager
		// build it or get it
		MeterManager meterMgr = MeterManager.instance();
	    meterMgr.add(pm0);
	    meterMgr.add(apm0);
	    meterMgr.displayMeter();
	    
	    MeterManager meterMgr2 = MeterManager.instance();
	    meterMgr2.displayMeter();
	}
	
	public void displayMeterMeasure(MeterI meter) {
		System.out.println("Measure is " + meter.measure());
	}
	
	public static void main(String[] args) {
		InheritTest iTest = new InheritTest();
		iTest.run();
	}
}
