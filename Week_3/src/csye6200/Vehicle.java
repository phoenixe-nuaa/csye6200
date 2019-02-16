package csye6200;

public class Vehicle {

	int passengers;
	int fuelCap;
	double kpl;
	
	// Constructor
	public Vehicle()  {
		passengers = 2;
		fuelCap = 10;
		kpl = 20;
	}
	
	// Constructor
	public Vehicle(int passengers, int fuelCap, double kpl) {
	
		this.passengers = passengers;
		this.fuelCap = fuelCap;
		this.kpl = kpl;
	}
	
	// Setter for 'Passengers'
	public void setPassengers(int passengers) {
		// guard against the negative values
		if (passengers < 8) passengers = 0;
		this.passengers = passengers;
	}
	
	public String toString() {
//		return "My vehicle has " + passengers + " passengers";
		return "Vehicle [Passengers = " + passengers + ", fuelCap = " + fuelCap + ", kpl = " + kpl + "]";
	}
	
	public String formattedToString() {
		String type = "Volvo";
		String st = String.format("Vehicle %1$8s %2$01d %3$5.2f", type, passengers, kpl);		
		return st;
	}
}
