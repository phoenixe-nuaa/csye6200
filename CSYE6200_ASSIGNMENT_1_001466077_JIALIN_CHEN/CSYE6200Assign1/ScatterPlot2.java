/**
* A file for implementing CSYE 6200 Assignment 1
* Filename: ScatterPlot2.java
* NUID: 001466077
* @author Jialin Chen
*/

// Optimized version of Print 2D-Map

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

public class ScatterPlot2 extends JFrame{ 
	
    private ArrayList <Student> stuList;

    
    // Constructor
    public ScatterPlot2 (ArrayList <Student> stuList) {    	    	
    	this.stuList = stuList;
	}
	
    
	// Plot 
    public void myplot(XYDataset dataset, ArrayList <XYTextAnnotation> textList) {
		 	        
	        // Create chart
	        JFreeChart chart = ChartFactory.createScatterPlot(
	            "2D-Map of schoolbus stops", 
	            "distance(m)", "distance(m)", dataset);
	        
	        // Changes background color
	        XYPlot plot = (XYPlot)chart.getPlot();
	        plot.setBackgroundPaint(new Color(255,228,196));	        
	       
	        // Create Panel
	        ChartPanel panel = new ChartPanel(chart);
	        setContentPane(panel);
	        
	        // Enable Frame
	        ChartFrame frame = new ChartFrame("2D-Map", chart);
	        frame.pack();
	        RefineryUtilities.centerFrameOnScreen(frame);
	        frame.setVisible(true);

		    // Annotation
	        for (XYTextAnnotation text: textList)   {
		    	plot.addAnnotation(text);
			}	      	        
	 }

    
	 // Create Data Set
	 private Map <String,Object> createDataset (String type, int highNum, int lowNum ) {	 
		    
		 // Use map to store data
		 XYSeriesCollection dataset = new XYSeriesCollection();
		 ArrayList <XYTextAnnotation> textList = new ArrayList<>();
		 Map <String,Object> dataMap= new HashMap<>();
		 XYSeries series = new XYSeries(type);
		    
		 // Data Processing   
		 for(Student stu:stuList)  {
			 if (stu.getGrade() >= lowNum && stu.getGrade() <= highNum) {
	        	XYTextAnnotation text = new XYTextAnnotation(stu.getName() + "(" + Double.toString(stu.getX()) + "," + Double.toString(stu.getY()) + ")",stu.getX(),stu.getY());
			    textList.add(text);
	            series.add(stu.getX(), stu.getY());
	        	}
	          }
		 
		 // Put data in a map
		 dataset.addSeries(series);
	     dataMap.put("dataset", dataset);
	     dataMap.put("textList", textList);
	     return dataMap;
	 }

	 
	@SuppressWarnings("unchecked")
	public void main() {
		
		 // Read Data
		 Map<String,Object> dataMap1 = this.createDataset("All Grade Levels", 10, 1);
		 Map<String,Object> dataMap2 = this.createDataset("Elementary(1-6)", 6, 1);
		 Map<String,Object> dataMap3 = this.createDataset("Middle(7-10)", 10, 7);
		 this.myplot((XYDataset)dataMap1.get("dataset"),(ArrayList <XYTextAnnotation>)dataMap1.get("textList"));
		 this.myplot((XYDataset)dataMap2.get("dataset"),(ArrayList <XYTextAnnotation>)dataMap2.get("textList"));
		 this.myplot((XYDataset)dataMap3.get("dataset"),(ArrayList <XYTextAnnotation>)dataMap3.get("textList"));
		 
	 }	 
}
	 
