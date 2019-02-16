package neu.edu.csye6200.assign3;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Chen.JL
 *
 */
public class PlantTest {
	// Create plant, flowerplant, flower and stem instances
	private Plant plt1;
	private Plant plt2;
	private Plant plt3;
	private FlowerPlant flrplt1;
	private FlowerPlant flrplt2;
	private Stem stems[];
	private Flower flower1;
	private Flower flower2;
	
	// Store plant instances
	private HashMap<String,Plant> plantMap;
	// Store flower plant instances
	private HashMap<String,FlowerPlant> flowerplantMap;
	
	public HashMap<String, Plant> getPlantMap() {
		return plantMap;
	}

	public void setPlantMap(HashMap<String, Plant> plantMap) {
		this.plantMap = plantMap;
	}

	public HashMap<String, FlowerPlant> getFlowerplantMap() {
		return flowerplantMap;
	}

	public void setFlowerplantMap(HashMap<String, FlowerPlant> flowerplantMap) {
		this.flowerplantMap = flowerplantMap;
	}

	// Constructor
	public PlantTest(int x) {		
		plantMap = new HashMap<String,Plant>();	
		flowerplantMap = new HashMap<String,FlowerPlant>();	
	
		stems = new Stem[x];
		for (int i = 0; i < x; i++) {
			stems[i] = new Stem ((double)i,(double)i,(double)i,(double)i/10*Math.PI);
		}
	
		plt1 = new Plant("01","lavender",1,10,stems[0]);				
		plt2 = new Plant("02","maple",2,20,stems[10]);
		plt3 = new Plant();
		
		flower1 = new Flower("blue",6);
		flower2 = new Flower("violet",8);
		flrplt1 = new FlowerPlant("04","sunflower",4,40,stems[0],flower1);
		flrplt2 = new FlowerPlant("05","rose",5,50,stems[0],flower1);
		
		// Let child stems grow based on father stems
		stems[1].setX(0.0);
		stems[2].setX(0.0);
		stems[3].setX(0.0);
		stems[4].setX(stems[1].getLength()*Math.cos(stems[1].getDirection()) + stems[1].getX());
		stems[5].setX(stems[1].getLength()*Math.cos(stems[1].getDirection()) + stems[1].getX());
		stems[6].setX(stems[1].getLength()*Math.cos(stems[1].getDirection()) + stems[1].getX());
		stems[7].setX(stems[2].getLength()*Math.cos(stems[2].getDirection()) + stems[2].getX());
		stems[8].setX(stems[2].getLength()*Math.cos(stems[2].getDirection()) + stems[2].getX());
		stems[9].setX(stems[3].getLength()*Math.cos(stems[3].getDirection()) + stems[3].getX());
		stems[10].setX(0.0);		
		stems[1].setY(0.0);
		stems[2].setY(0.0);
		stems[3].setY(0.0);
		stems[4].setY(stems[1].getLength()*Math.sin(stems[1].getDirection()) + stems[1].getY());
		stems[5].setY(stems[1].getLength()*Math.sin(stems[1].getDirection()) + stems[1].getY());
		stems[6].setY(stems[1].getLength()*Math.sin(stems[1].getDirection()) + stems[1].getY());
		stems[7].setY(stems[2].getLength()*Math.sin(stems[2].getDirection()) + stems[2].getY());
		stems[8].setY(stems[2].getLength()*Math.sin(stems[2].getDirection()) + stems[2].getY());
		stems[9].setY(stems[3].getLength()*Math.sin(stems[3].getDirection()) + stems[3].getY());
		stems[10].setY(0.0);
		stems[10].setLength(0.0);
		stems[10].setDirection(0.0);
	}
	
	// Test in run method
	public void run() {		
		plantMap.put(plt1.getSpecimenID(), plt1);
		plantMap.put(plt2.getSpecimenID(), plt2);
		plantMap.put(plt3.getSpecimenID(), plt3);
		
		flowerplantMap.put(flrplt1.getSpecimenID(), flrplt1);
		flowerplantMap.put(flrplt2.getSpecimenID(), flrplt2);
		
		plt3.grow(3);
		flrplt1.grow(flower2);
		flrplt2.grow(flower2);
	
		// If a base stem is present,
		// Add child stems and child stems of the child stem.
		for (Plant plt: plantMap.values()) {
			if (plt.BasestemIsPresent()) {	
				plt.getBasestem().addStem(stems[1]);
				plt.getBasestem().addStem(stems[2]);
				plt.getBasestem().addStem(stems[3]);
				plt.getBasestem().getStem(1).addStem(stems[4]);
				plt.getBasestem().getStem(1).addStem(stems[5]);
				plt.getBasestem().getStem(1).addStem(stems[6]);
				plt.getBasestem().getStem(2).addStem(stems[7]);
				plt.getBasestem().getStem(2).addStem(stems[8]);
				plt.getBasestem().getStem(3).addStem(stems[9]);
			}
		}

		// Initial display
		System.out.println("My Plant(Before):"); 
		for (Plant plt: plantMap.values()) {
			ArrayList<Double> totalheightlist = new ArrayList<Double>();
			ArrayList<Double> totalwidthlist = new ArrayList<Double>();
			System.out.println("******************************************************************");
		    plt.print();
			System.out.println("------------------------------------------------------------------");
			System.out.println(String.format("BaseStem ID:%1$2d  X:%2$2.2f  Y:%3$2.2f  Length:%4$2.2f  Direction:%5$2.2f", 
					plt.getBasestem().getId(),plt.getBasestem().getX(),plt.getBasestem().getY(),plt.getBasestem().getLength(),plt.getBasestem().getDirection()));
			System.out.println("------------------------------------------------------------------");
			System.out.println("Number of Childstem: " + Integer.toString(plt.totalChildStem(plt.getBasestem().getStemMap())));
			// call show method to display all child stems
			plt.getBasestem().show(plt.getBasestem().getStemMap());
			System.out.println("------------------------------------------------------------------");
            // Calculate the total height and width
			System.out.println("Total Height: " + Double.toString(plt.totalheight(plt.getBasestem().getStemMap(),totalheightlist)));
            System.out.println("------------------------------------------------------------------");
            System.out.println("Total Width: " + Double.toString(plt.totalwidth(plt.getBasestem().getStemMap(),totalwidthlist)));
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
			ArrayList<Double> totalheightlist = new ArrayList<Double>();
			ArrayList<Double> totalwidthlist = new ArrayList<Double>();
			System.out.println("******************************************************************");
			plt.print();
			System.out.println("------------------------------------------------------------------");
			System.out.println(String.format("BaseStem ID:%1$2d  X:%2$2.2f  Y:%3$2.2f  Length:%4$2.2f  Direction:%5$2.2f", 
					plt.getBasestem().getId(), plt.getBasestem().getX(), plt.getBasestem().getY(), plt.getBasestem().getLength(), plt.getBasestem().getDirection()));
			System.out.println("------------------------------------------------------------------");
			System.out.println("Number of Childstem: " + Integer.toString(plt.totalChildStem(plt.getBasestem().getStemMap())));
			// call show method to display all child stems
			plt.getBasestem().show(plt.getBasestem().getStemMap());
			System.out.println("------------------------------------------------------------------");
            // Calculate the total height and width
			System.out.println("Total Height: " + Double.toString(plt.totalheight(plt.getBasestem().getStemMap(),totalheightlist)));
            System.out.println("------------------------------------------------------------------");
            System.out.println("Total Width: " + Double.toString(plt.totalwidth(plt.getBasestem().getStemMap(),totalwidthlist)));
			System.out.println();
		}
		
		// Print Flower Plant
		for (FlowerPlant flrplt: flowerplantMap.values()) {
		    flrplt.print();
            System.out.println();
		}
	}
	
	// Create the test instance and call run() to test 
	public static void main(String[] args) {
		PlantTest plant = new PlantTest(11);
		plant.run();
	}
}
