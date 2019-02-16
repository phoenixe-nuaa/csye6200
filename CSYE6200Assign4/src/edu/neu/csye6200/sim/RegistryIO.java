package edu.neu.csye6200.sim;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class RegistryIO {
	private static Logger log = Logger.getLogger(RegistryIO.class.getName());
	// For Error Testing
//	private String base = "src/edu/neu/csye6200/assign4/";
	private String base = "src/edu/neu/csye6200/sim/";
	// To store plants loaded from disk
	private HashMap<String,Plant> plantMap = new HashMap<String,Plant>();
	private HashMap<String,FlowerPlant> flowerplantMap = new HashMap<String,FlowerPlant>();
	// To store flower plants loaded from disk
	private String filename;
	private FileWriter writer1;
	private FileWriter writer2;
	private FileReader reader1;
	private FileReader reader2;
	private FileHandler fh;
	
	// Constructor
	public RegistryIO(String filename) {
		try {
			// Send log messages to disk
			fh = new FileHandler("C:\\Users\\Chen.JL\\Dropbox\\csye6200\\CSYE6200Assign4\\src\\edu\\neu\\csye6200\\sim\\LogFile_RegistrIO.log");
		} catch (SecurityException | IOException e1) {
			e1.printStackTrace();
			log.severe("Error");
		}
		// Configure the logger with handler and formatter
		log.addHandler(fh);
		SimpleFormatter formatter = new SimpleFormatter();
		fh.setFormatter(formatter);
		// Log information messages for class creation
		log.info("Constructing a RegistryIO instance");
	
		try {
//		writer = new FileWriter(base + filename + ".txt", true);
		writer1 = new FileWriter(base + filename + "_plant.txt");
		writer2 = new FileWriter(base + filename + "_flowerplant.txt");
		reader1 = new FileReader(base + filename + "_plant.txt");
		reader2 = new FileReader(base + filename + "_flowerplant.txt");
		
		// File can't be found
		} catch (FileNotFoundException e) {
	    e.printStackTrace();
		log.severe("Can't find the file");
		// All other IO problems
		} catch (IOException e) {
		e.printStackTrace();
		log.severe("IO error");
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
		String st;
		try {
			if (plt instanceof FlowerPlant) {
				String str = "";
				for (Flower flr: ((FlowerPlant) plt).getFlrlist()) {
					str += flr.getColor() + "," + flr.getPetalnumber() + ",";
				}
				st = plt.getTypeName() + "," + plt.getSpecimenID() + "," + String.valueOf(plt.getAge()) + "," + 
						plt.getHeight() + "," + plt.getBasestem().getId() + "," + 
						plt.getBasestem().getX() + "," + plt.getBasestem().getY() + "," + 
						plt.getBasestem().getLength() + "," + plt.getBasestem().getDirection() + "," + str;				
				writer2.write(st);
				writer2.write("\r\n");
			}
			else {
				st = plt.getTypeName() + "," + plt.getSpecimenID() + "," + plt.getAge() + "," + plt.getHeight() + "," + 
						String.valueOf(plt.getBasestem().getId()) + "," + String.valueOf(plt.getBasestem().getX()) + "," + 
						String.valueOf(plt.getBasestem().getY()) + "," + String.valueOf(plt.getBasestem().getLength()) + "," + 
						String.valueOf(plt.getBasestem().getDirection());
				writer1.write(st);
				writer1.write("\r\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.severe("Error");
		} catch (IOException e) {
			e.printStackTrace();
			log.severe("Error");
		}
	}
		
	// Implement save method functionality that writes all Plant data to a file
	private void saveplant(HashMap<String,Plant>plantMap, String filename) {
			try {
				for (Plant plt: plantMap.values()) {
					this.saveplant(plt);
				}
				writer1.close();
				writer2.close();
			} catch (IOException e) {
				e.printStackTrace();
				log.severe("Error");
			}
			log.info("Save Successfully");
	}	
	
	// Load all Plants to disk
	private void loadplant(HashMap<String,Plant>plantMap,String filename) {
		try {
			BufferedReader buffreader = new BufferedReader(reader1);
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
				    plant.print();
				    System.out.println(String.format("BaseStem ID:%1$2d  X:%2$2.2f  Y:%3$2.2f  Length:%4$2.2f  Direction:%5$2.2f", 
						plant.getBasestem().getId(), plant.getBasestem().getX(), plant.getBasestem().getY(), 
						plant.getBasestem().getLength(), plant.getBasestem().getDirection()));
				        plant.getBasestem().show(plant.getBasestem().getStemMap());
			    }
			log.info("Plants Load Successfully");
			buffreader.close();
			reader1.close();
			} catch (FileNotFoundException e) {
			     e.printStackTrace();
			     log.severe("Error");
		    } catch (IOException e) {
			     e.printStackTrace();
			     log.severe("Error");
		}
	}
	
	// Load all FlowerPlants to disk
	private void loadflowerplant(HashMap<String,FlowerPlant>plantMap,String filename) {
		try {
			BufferedReader buffreader = new BufferedReader(reader2);
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
			    Flower flr1 = new Flower(fileContextArr[9],Integer.parseInt(fileContextArr[10]));
			    Flower flr2 = new Flower(fileContextArr[11],Integer.parseInt(fileContextArr[12]));
			    plt.getFlrlist().add(flr1);
			    plt.getFlrlist().add(flr2);
				flowerplantMap.put(fileContextArr[1], plt);
			}
			
			for (FlowerPlant plant: flowerplantMap.values()) {
				plant.print();
				System.out.println("------------------------------------------------------------------");
				System.out.println(String.format("BaseStem ID:%1$2d  X:%2$2.2f  Y:%3$2.2f  Length:%4$2.2f  Direction:%5$2.2f", 
						plant.getBasestem().getId(), plant.getBasestem().getX(), plant.getBasestem().getY(), 
						plant.getBasestem().getLength(), plant.getBasestem().getDirection()));
				        plant.getBasestem().show(plant.getBasestem().getStemMap());
			}
			buffreader.close();
			reader2.close();
			log.info("FlowerPlants Load Successfully");
			} catch (FileNotFoundException e) {
			     e.printStackTrace();
			     log.severe("Error");
		    } catch (IOException e) {
			     e.printStackTrace();
			     log.severe("Error");
		}
	}
	
	// Test
	public static void main(String[] args) {
		RegistryIO io = new RegistryIO("data");
		PlantTest plant = new PlantTest(11);
		plant.run();
		io.saveplant(plant.getPlantMap(),io.getFilename());
		io.loadplant(io.plantMap,io.getFilename());
		io.loadflowerplant(io.flowerplantMap,io.getFilename());
	}
}