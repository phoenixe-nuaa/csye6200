/**
* A file for implementing CSYE 6200 Assignment 1
* Filename: ScatterPlot.java
* NUID: 001466077
* @author Jialin Chen
*/

// First Version of print 2D-Map

import java.awt.Color;
import java.util.ArrayList;
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

public class ScatterPlot extends JFrame{ 
	
    private ArrayList <Student> stuList;

    // Constructor
    public ScatterPlot (ArrayList <Student> stuList) {    	    	
    	this.stuList = stuList;
	}
	
	// Plot 
    public void myplot() {
		 	        
	        XYDataset dataset = createDataset();

	        // Create chart
	        JFreeChart chart = ChartFactory.createScatterPlot(
	            "2D-Map of schoolbus stops", 
	            "distance(m)", "distance(m)", dataset);
	        
	        // Change background color
	        XYPlot plot = (XYPlot)chart.getPlot();
	        plot.setBackgroundPaint(new Color(255,228,196));
	        	       
	        // Create Panel
	        ChartPanel panel = new ChartPanel(chart);
	        setContentPane(panel);
	        
	        // Create Frame
	        ChartFrame frame = new ChartFrame("2D-Map", chart);
	        frame.pack();
	        RefineryUtilities.centerFrameOnScreen(frame);
	        frame.setVisible(true);
	        		    
		    // Add Annotation
	        ArrayList <XYTextAnnotation> textList = new ArrayList<>();
		    
		    for (Student stu:stuList)   {
		        XYTextAnnotation text = new XYTextAnnotation(stu.getName() + "(" + Double.toString(stu.getX()) + "," + Double.toString(stu.getY()) + ")",stu.getX(),stu.getY());
		    	textList.add(text);
			}
		    
		    for (XYTextAnnotation text: textList)   {
			    plot.addAnnotation(text);
			}	      	        
	 }

	 // Create Data Set
	 private XYDataset createDataset () {
		 
		    XYSeriesCollection dataset = new XYSeriesCollection();
	        
//		    XYSeries series1 = new XYSeries("All Grade Levels");
//	        for (Student stu:stuList)   {
////	        System.out.println(stuList.get(i));
//	        series1.add(stu.getX(), stu.getY());
//	        }
	        
	        // Plot Elementary-grade-level data
	        XYSeries series2 = new XYSeries("Elementary(1-6)");  
	        for(Student stu:stuList)   {
	        	if (stu.getGrade() >= 1 && stu.getGrade() <= 6) {
	            series2.add(stu.getX(), stu.getY());
	        	}
	          } 
	  
	        // Plot Middle-grade-level data
	        XYSeries series3 = new XYSeries("Middle(7-10)");
	        for(Student stu:stuList)   {
	        	if (stu.getGrade() >= 7 && stu.getGrade() <= 10) {
	            series3.add(stu.getX(), stu.getY());
	        	}
	          } 
	          
//	        dataset.addSeries(series1);
	        dataset.addSeries(series2);
	        dataset.addSeries(series3);
	        
	        return dataset;
	 }	 
}

//Question: How to write concise and readable comments?
	 
