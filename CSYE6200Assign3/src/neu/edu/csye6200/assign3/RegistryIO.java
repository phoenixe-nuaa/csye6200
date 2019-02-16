package neu.edu.csye6200.assign3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class RegistryIO {
	private String base = "src/neu/edu/csye6200/assign3/";
	// To store plants loaded from disk
	private HashMap<String,Plant> plantMap = new HashMap<String,Plant>();
	// To store flower plants loaded from disk
	private HashMap<String,FlowerPlant> flowerplantMap = new HashMap<String,FlowerPlant>();
	private String filename;
	private FileWriter writer;
	private FileReader reader;
	
	// Constructor
	public RegistryIO(String filename) {
	try {
//		writer = new FileWriter(base + filename + ".txt", true);
		writer = new FileWriter(base + filename + ".txt");
		reader = new FileReader(base + filename + ".txt");
		// File can't be found
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
		// All other IO problems
	} catch (IOException e) {
		e.printStackTrace();
	}
}
	// Getters and setters
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		System.out.println(filename);
		this.filename = filename;
	}
	
	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}
	
	// Private save method for writing a single Plant to an open file
	private void saveplant(Plant plt) {
		try {
		    String st = plt.getTypeName() + "," + plt.getSpecimenID() + "," + plt.getAge() + "," + plt.getHeight() + "," + 
				   String.valueOf(plt.getBasestem().getId()) + "," + String.valueOf(plt.getBasestem().getX()) + "," + 
				   String.valueOf(plt.getBasestem().getY()) + "," + String.valueOf(plt.getBasestem().getLength()) + "," + 
				   String.valueOf(plt.getBasestem().getDirection());
		    writer.write(st);
		    writer.write("\r\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Private save method for writing a single FlowerPlant to an open file
	private void saveflowerplant(FlowerPlant plt) {
		try {
		    String st = plt.getTypeName() + "," + plt.getSpecimenID() + "," + String.valueOf(plt.getAge()) + "," + String.valueOf(plt.getHeight()) + "," + 
		           String.valueOf(plt.getBasestem().getId()) + "," + String.valueOf(plt.getBasestem().getX()) + "," + 
				   String.valueOf(plt.getBasestem().getY()) + "," + String.valueOf(plt.getBasestem().getLength()) + "," + 
				   String.valueOf(plt.getBasestem().getDirection()) + "," + plt.getFlower().getColor() + "," + String.valueOf(plt.getFlower().getPetalnumber());
		    writer.write(st);
		    writer.write("\r\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Implement save method functionality that writes all Plant data to a file
	private void saveallplant(HashMap<String,Plant>plantMap, String filename) {
			try {
				for (Plant plt: plantMap.values()) {
					this.saveplant(plt);
				}
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Save Successfully");
	}
	
	// Implement save method functionality that writes all FlowerPlant data to a file
	private void saveallflowerplant(HashMap<String,FlowerPlant> flowerplantMap, String filename) {
		try {
			for (FlowerPlant plt: flowerplantMap.values()) {
				this.saveflowerplant(plt);
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Save Successfully");
		System.out.println();
	}
	
	// Load all Plants to disk
	private void loadplant(HashMap<String,Plant>plantMap,String filename) {
		try {
			BufferedReader buffreader = new BufferedReader(reader);
            String str;
			while((str = buffreader.readLine()) != null) {
				Plant plt = new Plant();
				System.out.println(str);
	            String[] fileContextArr = str.split(",");
//	            System.out.println("length:" + textArr.length);
	            plt.setTypeName(fileContextArr[0]);
			    plt.setSpecimenID(fileContextArr[1]);
			    plt.setAge(Integer.parseInt(fileContextArr[2]));
			    plt.setHeight(Integer.parseInt(fileContextArr[3]));
			    plt.getBasestem().setId(Integer.parseInt(fileContextArr[4]));
			    plt.getBasestem().setX(Double.valueOf(fileContextArr[5]));
			    plt.getBasestem().setY(Double.valueOf(fileContextArr[6]));
			    plt.getBasestem().setLength(Double.valueOf(fileContextArr[7]));
			    plt.getBasestem().setDirection(Double.valueOf(fileContextArr[8]));
				plantMap.put(fileContextArr[1], plt);
			}
			for (Plant plant: plantMap.values()) {
				System.out.println("******************************************************************");
				plant.print();
				System.out.println(String.format("BaseStem ID:%1$2d  X:%2$2.2f  Y:%3$2.2f  Length:%4$2.2f  Direction:%5$2.2f", 
						           plant.getBasestem().getId(), plant.getBasestem().getX(), plant.getBasestem().getY(), 
						           plant.getBasestem().getLength(), plant.getBasestem().getDirection()));
				plant.getBasestem().show(plant.getBasestem().getStemMap());
			}
			System.out.println();			
			System.out.println("Load Successfully");
			System.out.println();
			buffreader.close();
			reader.close();
			} catch (FileNotFoundException e) {
			e.printStackTrace();
		    } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Load all FlowerPlants to disk
		private void loadflowerplant(HashMap<String,FlowerPlant>flowerplantMap,String filename) {
			try {
				BufferedReader buffreader = new BufferedReader(reader);
	            String str;
				while((str = buffreader.readLine()) != null) {
					FlowerPlant plt = new FlowerPlant();
					System.out.println(str);
		            String[] fileContextArr = str.split(",");
		            plt.setTypeName(fileContextArr[0]);
				    plt.setSpecimenID(fileContextArr[1]);
				    plt.setAge(Integer.parseInt(fileContextArr[2]));
				    plt.setHeight(Integer.parseInt(fileContextArr[3]));
				    plt.getBasestem().setId(Integer.parseInt(fileContextArr[4]));
				    plt.getBasestem().setX(Double.valueOf(fileContextArr[5]));
				    plt.getBasestem().setY(Double.valueOf(fileContextArr[6])); 
				    plt.getBasestem().setLength(Double.valueOf(fileContextArr[7]));
				    plt.getBasestem().setDirection(Double.valueOf(fileContextArr[8]));
				    plt.getFlower().setColor(fileContextArr[9]);
				    plt.getFlower().setPetalnumber(Integer.parseInt(fileContextArr[10]));
					flowerplantMap.put(fileContextArr[1], plt);
				}
				for (FlowerPlant plant: flowerplantMap.values()) {
					plant.print();
				}
				System.out.println("Load Successfully");
				System.out.println();
				buffreader.close();
				reader.close();
				} catch (FileNotFoundException e) {
				e.printStackTrace();
			    } catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	// Test
	public static void main(String[] args) {
		RegistryIO io1 = new RegistryIO("plantdata");
		RegistryIO io2 = new RegistryIO("flowerplantdata");
		PlantTest plant = new PlantTest(11);
		plant.run();
		io1.saveallplant(plant.getPlantMap(),io1.getFilename());
		io2.saveallflowerplant(plant.getFlowerplantMap(),io2.getFilename());		
		io1.loadplant(io1.plantMap,io1.getFilename());
		io2.loadflowerplant(io2.flowerplantMap,io2.getFilename());
	}
}