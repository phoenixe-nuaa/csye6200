package edu.neu.csye6200.inherit;

import java.util.ArrayList;
import java.util.logging.Logger;

/*
 * Example of a Singleton Class
 * @author JL.Chen
*/
public class MeterManager {
	private static Logger log = Logger.getLogger(MeterManager.class.getName());
	
	// The single copy
	private static MeterManager instance = null;
	private ArrayList<MeterI> meterlist = new ArrayList<MeterI>();
	
	// Can't be built externally to class
	private MeterManager() {
		log.info("Constructing a MeterManager instacne");
	}
	
	public static MeterManager instance() {
		// Build if needed
		if (instance == null) instance = new MeterManager();
		
		// Return the single copy
		return (instance);
	}

	/*
	 * Add a meter to our manager
	 * @param meter 
	 */
	public void add(MeterI meter) {
		meterlist.add(meter);
		log.warning("Added a meter");
	}
	
	public void displayMeter() {
		for (MeterI meter: meterlist) {
			String fTxt = String.format("Measure: %1$8f %2$8f", meter.measure(),meter.getResistivity());
			System.out.println(fTxt);
		}
	}

}
