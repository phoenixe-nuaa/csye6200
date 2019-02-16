package edu.neu.csye6200.sim;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class PlantRoster {
	private static Logger log = Logger.getLogger(RegistryIO.class.getName());
	private static PlantRoster instance = null;
	// To store sorted plants
	public ArrayList<Plant> plantlist_n = new ArrayList<Plant>();
	public Integer items[] = new Integer[5];
	private FileHandler fh;
	
	// Singleton Pattern
	public static PlantRoster instance() {
		// Build if needed
		if (instance == null) instance = new PlantRoster();
		// Return the single copy
		return (instance);
	}
	
	public PlantRoster() {
		try {
			// Send log messages to disk
			fh = new FileHandler("C:\\Users\\Chen.JL\\Dropbox\\csye6200\\CSYE6200Assign4\\src\\edu\\neu\\csye6200\\sim\\LogFile_PltRoster.log");
		} catch (SecurityException | IOException e1) {
			e1.printStackTrace();
			log.severe("Error");
		}
		// Configure the logger with handler and formatter
		log.addHandler(fh);
		SimpleFormatter formatter = new SimpleFormatter();
		fh.setFormatter(formatter);
		// Log information messages for class creation
		log.info("Constructing a PlantRoster instance");
	}
	
	// Call quicksort
	public static void QR(Integer items[]) {
		quicksort(items, 0, items.length-1);
		log.info("Quicksort completed");
	}
	
	// Quicksort method
	public static void quicksort(Integer items[], int left, int right) {	
	    int i, j;
	    int x, temp;
	    i = left; j = right;
	    x = items[(left+right)/2];
	do {
	    while ((items[i] < x) && (i < right)) i++;
	    while ((x < items[j]) && (j > left)) j--;
	    if (i <= j) {
	       temp = items[i];
	       items[i] = items[j];
	       items[j] = temp;
	       i++; j--;
	    }
	   } while(i <= j);
	   if (left < j) quicksort(items, left, j);
	   if (i < right) quicksort(items, i, right);
	}

	// Display the plants
	public void displayPlant(ArrayList<Plant> plantlist) {
		for (Plant plt: plantlist) {
			plt.print();
		}
		System.out.println();
	}
	
    public void test(ArrayList<Plant> plantlist) {
		
		for (int i = 0 ; i < 5; i++) {
			items[i] = Integer.valueOf(plantlist.get(i).getSpecimenID());
//			System.out.println(items[i]);
		}
		
		QR(items);
		
		// Sorted plant list
		for (int i = 0 ; i < 5; i++) {
//			System.out.println(items[i]);
			for (Plant plt: plantlist) {
				if (Integer.parseInt(plt.getSpecimenID()) == items[i])  
					plantlist_n.add(plt);
			}
		}
    }
	
    // Test
	public static void main(String[] args) {
		PlantRoster pltr = PlantRoster.instance();
		PlantTest plant = new PlantTest(11);
		pltr.test(plant.getPlantlist());
		System.out.println("Before sorting:");
		pltr.displayPlant(plant.getPlantlist());
		System.out.println("After sorting:");
		pltr.displayPlant(pltr.plantlist_n);
	}
}
