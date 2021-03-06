/**
 * 
 */
package csye6200;

import java.io.IOException;

/**
 * @author Chen.JL
 *
 */
public class SwitchDemo {
	
	/**
	 * @param args
	 * @throws IOException 
	 */
public static void main(String[] args) throws IOException {
	// X Y grade level	
	int student[][] = {
			{3,2,5}, 
			{3,1,5},
			{1,2,1},
			{1,3,2},
			{2,4,8},
			{4,3,1},
			{3,4,5},
			{1,3,9},
			{1,2,8},
			{2,4,6},			
			// more students...
	};
		
	int gradeTotals[] = new int[10];	
	// index value 0 to 4
	int elemmap[][] = new int[5][5];
	// middle
	int middlemap[][] = new int[5][5];	
	// all
	int allmap[][] = new int[10][10];
	// read student data
	
	for (int i = 0; i < student.length; i++) {
		
		int x = student[i][0];
		int y = student[i][1];
		int grade = student[i][2];
		
//		System.out.println("X:" + x + "," + "Y:" + y + "," + "grade:" + grade);		
		gradeTotals[grade] = gradeTotals[grade] + 1;
		
		// Count total of students at a region
		allmap[y][x] = allmap[y][x] + 1;		
		if (grade < 7) elemmap[y][x] = elemmap[y][x] + 1;
		else middlemap[y][x] = middlemap[y][x] + 1;
		
	}
	
	char hist[] = {'*', '*', '*', '*'};		
	char inChar = ' ';
	
	System.out.println("Enter choice(1,2,3,4,5) or 'q' to quit, then press <enter>");
	
	do {
		inChar = (char) System.in.read();	
		if (inChar == '\r') continue;  // new line
		if (inChar == '\n') continue;
		
		switch (inChar) {
		case '1':
			System.out.println("You selected option 1");			
			for (int i = 1; i < 9; i++ ) {
				System.out.println("Grade " + i + " students: " + gradeTotals[i]);
			}
			break;
		
		case '2':
			System.out.println("You selected option 2 - Elem Map");
			printMap("Elementary School Stops", elemmap);
			break;
			
		case '3':
			System.out.println("You selected option 3 - Middle Map");
			printMap("Middle School Stops", middlemap);
			break;
			
		case '4':
			System.out.println("You selected option 4 - All Student Map");
			printMap("All School Stops", allmap);
			break;
			
		case '5':
			System.out.println("Command history");
			System.out.println(hist[3] + "," + hist[2] + "," + hist[1] + "," + hist[0]);
			break;
			
		case 'q':
		case '\r':  // Windows \r\n
			// Do nothing
			break;
			
		default:
			System.out.println("Invalid selection");
			break;
		   }
		
		   // Save history
		   hist[3] = hist[2];
		   hist[2] = hist[1];		   
		   hist[1] = hist[0];
		   hist[0] = inChar;
		}
		while (inChar != 'q'); // Exit on quit (i.e., 'q')		
		System.out.println("Quitting......");
	}

// Print a map     
static void printMap (String mapName, int map[][]) {
    	 
    	 for (int y = 0; y < map.length; y++)  {   		 
    		 for (int x = 0; x < 5; x++)  {    			 
    			 int value = map[y][x];
//    			 if (value > 0 ) System.out.println("*");
//    			 else System.out.println("-");
    			 System.out.print((value > 0)? '*' : '-');
    		 }
    		 System.out.println("");
    	 }
     } 
}
