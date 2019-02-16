package csye6200;

public class VehicleTest {

	public static void main(String[] args) {
		// make a Vehicle instance
		Vehicle vel = new Vehicle();
		
		// using dot operator to set values
		vel.passengers = -1;
		
		vel.setPassengers(-1);
		
		System.out.println("vel passengers is:" + vel.passengers);
		
		// make a second Vehicle instance
		Vehicle vel2 = new Vehicle();
		Vehicle vel3 = new Vehicle(4,30,3.5);
		
		System.out.println("Vehicle instance vel3 is:"+ vel3.formattedToString());
		
		System.out.println(vel.formattedToString());
		System.out.println(vel2.formattedToString());
		System.out.println(vel3.formattedToString());
		
//		vel3.toString();
		
		// point to the same location
//		vel2 = vel;
//		System.out.println(vel2.passengers);
	}

}
