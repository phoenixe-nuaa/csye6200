/**
 * 
 */

/**
 * @author Chen.JL
 *
 */
public class PlantTest {
	
	public static void main (String[] args) {
		
		// make plant instances
		Plant plt1 = new Plant();
		Plant plt2 = new Plant("02","maple",2,20);
		Plant plt3 = new Plant("03","peachtree",3,30);
		plt3.grow(3);
		Plant plt4 = new Plant("04","pine",4,40);
		plt4.grow(4);
		Plant plt5 = new Plant("05","appletree",5,50);
		plt5.grow(5);
		
		
		// print
		System.out.println(plt1.print());
		System.out.println(plt2.print());
		System.out.println(plt3.print());
		System.out.println(plt4.print());
		System.out.println(plt5.print());
	}

}
