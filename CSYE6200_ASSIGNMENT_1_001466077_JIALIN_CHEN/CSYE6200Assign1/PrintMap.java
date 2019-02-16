import java.awt.Color;  
import java.awt.Font;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.axis.NumberAxis;  
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.chart.plot.XYPlot;  
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;  
import org.jfree.chart.title.TextTitle;  
import org.jfree.data.xy.XYSeries;  
import org.jfree.data.xy.XYSeriesCollection;  
import org.jfree.ui.HorizontalAlignment;  
import org.jfree.ui.RectangleEdge;  
  
public class PrintMap {  
  
    XYSeriesCollection dataset1;  
    XYSeriesCollection dataset2;  
    XYSeriesCollection dataset3;  
    JFreeChart chart;  
    XYPlot plot;

    private ArrayList <Student> stuList;

    //Constructor
    public PrintMap (ArrayList <Student> stuList) {    	
    	
    	this.stuList = stuList;
	}
      
    //Plot data  
    public void myplot() {
    	
    	//Plot All-grade-level data
        XYSeries series1 = new XYSeries("All Grade Levels");
        for(Student stu:stuList)   {
//        System.out.println(stuList.get(i));
        series1.add(stu.getX(), stu.getY());
        }
        
        //Plot Elementary-grade-level data
        XYSeries series2 = new XYSeries("Elementary(1-6)");  
        for(Student stu:stuList)   {
        	if (stu.getGrade() >= 1 && stu.getGrade() <= 6) {
            series2.add(stu.getX(), stu.getY());
        	}
          } 
  
        //Plot Middle-grade-level data
        XYSeries series3 = new XYSeries("Middle(7-10)");
        for(Student stu:stuList)   {
        	if (stu.getGrade() >= 7 && stu.getGrade() <= 10) {
            series3.add(stu.getX(), stu.getY());
        	}
          }
  
        dataset1 = new XYSeriesCollection();  
        dataset2 = new XYSeriesCollection();  
        dataset3 = new XYSeriesCollection();  
          
        dataset1.addSeries(series1);  
        dataset2.addSeries(series2);  
        dataset3.addSeries(series3);  
  
        //Set Chart
        chart = ChartFactory.createXYLineChart("2D-Map of schoolbus stops", null,  
                null, dataset1, PlotOrientation.VERTICAL, true, true,  
                false);  
  
        //Plot
        plot = chart.getXYPlot();  
  
                      
//        NumberAxis axis2 = new NumberAxis();  
//            
//        // -- 修改第2个Y轴的显示效果  
//        axis2.setAxisLinePaint(Color.BLUE);  
//        axis2.setLabelPaint(Color.BLUE);  
//        axis2.setTickLabelPaint(Color.BLUE); 
        NumberAxis axis3 = new NumberAxis();  
          
        plot.setRangeAxis(1, axis3);  
        plot.setDataset(1, dataset2);  
        plot.mapDatasetToRangeAxis(1, 1);  
             
        // -- 修改第2条曲线显示效果  
        XYLineAndShapeRenderer render2 =  new XYLineAndShapeRenderer();   
        render2.setSeriesPaint(0, Color.BLUE);  
        plot.setRenderer(1, render2);  
          
          
        // 添加第3个Y轴  
//        NumberAxis axis3 = new NumberAxis();  
//          
//        axis3.setAxisLinePaint(Color.GREEN);  
//        axis3.setLabelPaint(Color.GREEN);  
//        axis3.setTickLabelPaint(Color.GREEN);  
          
        plot.setRangeAxis(2, axis3);  
        plot.setDataset(2, dataset3);  
        plot.mapDatasetToRangeAxis(2, 2);  
          
        XYLineAndShapeRenderer render3 =  new XYLineAndShapeRenderer();   
        render3.setSeriesPaint(0, Color.GREEN);  
        plot.setRenderer(2, render3);
                  
        TextTitle copyright = new TextTitle("Author @Jialin Chen");  
        copyright.setPosition(RectangleEdge.BOTTOM);  
        copyright.setHorizontalAlignment(HorizontalAlignment.RIGHT);  
        copyright.setFont(new Font("Times New Roman", 12, 12));  
        chart.addSubtitle(copyright);  
        chart.getLegend().setItemFont(new Font("Times New Roman", 12, 12));  
    }  
  
}  