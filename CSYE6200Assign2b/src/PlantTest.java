import java.util.HashMap;

/**
 * 
 */

/**
 * @author Chen.JL
 *
 */
public class PlantTest {

	/**
	 * @param args
	 */
	// Create plant and stem instances
	private Plant plt1;
	private Plant plt2;
	private Plant plt3;	
	private Stem stems[];
	
	// Store plant instances
	private HashMap<String,Plant> plantMap;
	
	// Constructor
	public PlantTest(int x) {		
		plantMap = new HashMap<String,Plant>();	
	
		stems = new Stem[x];
		for (int i = 0; i < x; i++) {
//			System.out.println(i);		
			stems[i] = new Stem (i, i, (double)i, (double)i/10);
		}		
	
		plt1 = new Plant("01","lavender",1,10,stems[0]);				
		plt2 = new Plant("02","maple",2,20,stems[10]);
		plt3 = new Plant();
		plt3.grow(3);						
	}
	
	// Test in run method
	private void run() {		
		plantMap.put(plt1.getSpecimenID(), plt1);
		plantMap.put(plt2.getSpecimenID(), plt2);
		plantMap.put(plt3.getSpecimenID(), plt3);
	
		// Is a base stem is present,
		// Add child stems and child stems of the child stem.
		for (Plant plt: plantMap.values()) {
			if (plt.BasestemIsPresent()) {	
				plt.getBasestem().addStem(stems[1]);
				plt.getBasestem().addStem(stems[2]);
				plt.getBasestem().addStem(stems[3]);
				plt.getBasestem().getStem(1).addStem(stems[4]);
//				plt.getBasestem().getStem(1).addStem(plt1.getBasestem().getStem(1).getStemMap(),stem4);
				plt.getBasestem().getStem(1).addStem(stems[5]);
//				plt.getBasestem().getStem(1).getStemMap().get(4).addStem(stem5);
				plt.getBasestem().getStem(1).addStem(stems[6]);
				plt.getBasestem().getStem(2).addStem(stems[7]);
				plt.getBasestem().getStem(2).addStem(stems[8]);
				plt.getBasestem().getStem(3).addStem(stems[9]);
			}
		}
		
		// Initial display
		System.out.println("My Plant(Before):"); 
		for (Plant plt: plantMap.values()) {
			System.out.println("******************************************************************");
			System.out.println(plt.print());
			System.out.println("------------------------------------------------------------------");
			System.out.println(String.format("BaseStem ID:%1$2d  X:%2$2d  Y:%3$2d  Length:%4$2.2f  Direction:%5$2.2f", 
					plt.getBasestem().getId(),plt.getBasestem().getX(),plt.getBasestem().getY(),plt.getBasestem().getLength(),plt.getBasestem().getDirection()));
			System.out.println("------------------------------------------------------------------");
			System.out.println("Number of Childstem: " + Integer.toString(plt.totalChildStem(plt.getBasestem().getStemMap())));
			// call show method to display all child stems
			plt.getBasestem().show(plt.getBasestem().getStemMap());
			System.out.println();
		}
		
		// Call removeStem()
		plt1.getBasestem().removeStem(1);
		plt2.getBasestem().removeStem(2);
		plt3.getBasestem().removeStem(3);		

		// Print results after removing some child stems
		System.out.println();
		System.out.println("My Plant(After):");
		for (Plant plt: plantMap.values()) {
			System.out.println("******************************************************************");
			System.out.println(plt.print());
			System.out.println("------------------------------------------------------------------");
			System.out.println(String.format("BaseStem ID:%1$2d  X:%2$2d  Y:%3$2d  Length:%4$2.2f  Direction:%5$2.2f", 
					plt.getBasestem().getId(), plt.getBasestem().getX(), plt.getBasestem().getY(), plt.getBasestem().getLength(), plt.getBasestem().getDirection()));
			System.out.println("------------------------------------------------------------------");
			System.out.println("Number of Childstem: " + Integer.toString(plt.totalChildStem(plt.getBasestem().getStemMap())));
			// call show method to display all child stems
			plt.getBasestem().show(plt.getBasestem().getStemMap());
			System.out.println();
		}		
	}
	
	// Create the test instance and call run() to test 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PlantTest plant = new PlantTest(11);
		plant.run();
	}
}
