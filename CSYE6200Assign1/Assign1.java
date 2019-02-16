import java.util.ArrayList;
import javax.swing.SwingUtilities;

/**
* A starter file for implementing CSYE 6200 Assignment 1
* Filename: Assign1.java
* NUID: 001466077
* @author Jialin Chen
*/

public class Assign1 {

	/**
	 * The main entry point... your program starts here
	 * @param args command line arguments
	 */	
	
	public static void main(String[] args) throws java.io.IOException {
		
		char inChar = ' ';
		
		// Student data
		String accounts[][] = {  {"Rachel",   "500.1",     "451.45",    "2"}, 
				                 {"Ross",     "293.23",    "120.45",    "7"},  
				                 {"Monica",   "423.3",     "400.3",     "4"}, 
				                 {"Chandler", "628.4",     "282.67",    "3"},
				                 {"Phoebe",   "345.25",    "84.4",      "1"},
				                 {"Joey",     "123.4",     "345.3",     "8"},
				                 {"Robin",    "523.6",     "500.45",    "3"}, 
				                 {"Lily",     "264.43",    "323.45",    "6"},  
				                 {"Marshall", "523.3",     "290.3",     "7"}, 
				                 {"Ted",      "328.4",     "584.6",     "3"},
				                 {"Barney",   "545.35",    "233.4",     "5"},
				                 {"Spongebob","673.4",     "666.3",     "9"}
				              };
		
		// Read student data
		ArrayList<Student> stuList = new ArrayList<>();
		
		for(int i = 0; i < accounts.length; i++) {
			Student stu = new Student(accounts[i][0], Double.valueOf(accounts[i][1]), Double.valueOf(accounts[i][2]), Integer.valueOf(accounts[i][3]));
			stuList.add(stu);
		}
		
//		// test-Print
// 		System.out.println("****Print Student Info****");
//  		  
// 		  for(Student stu : stuList) {
//			stu.show();
//		  }
		 
		// Construct an ArrayList to read commands
		ArrayList<Character> comList = new ArrayList<>();		
		
		do {
			  System.out.println("*****Enter choice 'c' to get the number of students in each grade level, then press <enter>*****");
			  System.out.println("*****Enter choice 'p' to get the 2D-map of the stops, then press <enter>*****");
			  System.out.println("*****Enter choice 'h' to get the last three commands(when commands are more than three), then press <enter>*****");
			  System.out.println("*****Enter choice 'q' to quit, then press <enter>*****");
					   	  
			  inChar = (char) System.in.read();
			  			  
			  // Read '\r' and '\n' and do nothing
			  System.in.read();
			  System.in.read();			  
			  
		   	  // Read commands
			  comList.add(inChar);
	          
		   	  // React to input and take appropriate action
	   		  switch (inChar)  {
	   		  
		   	  // Calculate how many students are in each grade level and list the results
	   		  case 'c':
		   		  System.out.println("*****Calculate Student Info****");
		   		  
		   		  int count_elementery = 0;
		   		  int count_middle = 0;
		   		  
		 		  for(Student stu : stuList) {
		 				if ( stu.getGrade() >= 1 && stu.getGrade() <= 6)
		 					count_elementery++;
		 
		 				else if (stu.getGrade() >= 7 && stu.getGrade() <= 9)
		 					count_middle++;
		 					
		 			  }
		 		  
		 		  System.out.println("Number of students in Elementery Grade Level(1-6): " + count_elementery);
		 		  System.out.println("Number of students in Middle Grade Level(7-9): " + count_middle);
		 		  
		   		  break;
		   		   		  
		      // Print a region map (2D) that shows where a bus would need to stop for student pick-up/drop-off. 
              // Allow custom map displays by grade level (i.e. Elementary (1-6), Middle (7-8), or All).
		   	  case 'p':
		   		  System.out.println("****Print 2D Region Map****");
		   			
		   		    //Generate 2D-Map
		   	    SwingUtilities.invokeLater(() -> {
		   	    	
//		   	    	ScatterPlot obj = new ScatterPlot(stuList); 
//		   	    	obj.myplot();
		   		    ScatterPlot2 obj = new ScatterPlot2(stuList);  
		   		    obj.main();
		   	        obj.setSize(800, 400);
		   	        obj.setLocationRelativeTo(null);
//		   	        obj.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		   	    });
	 			  
		   		  break;		   	  
		   		  
		   	  // Print a history of the last three commands
		      case 'h':
		   		  System.out.println("****Print Command History*****");			   	  
			   	  System.out.println("Last Three Command History:"+ comList.get(comList.size()-2) + "," + comList.get(comList.size()-3) + "," + comList.get(comList.size()-4));
			   	  
		   		  break;
		   		  		      
	        }
		}  
		   
		   // Exit on quit
		   while (inChar != 'q');		
		   System.out.println("Quiting...");
		   		   
	    } 	
}
