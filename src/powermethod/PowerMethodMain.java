package powermethod;
import java.util.ArrayList;

import javafx.geometry.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel; 

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.ShapeUtilities;

import java.awt.Shape;

import java.awt.Color;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;

public class PowerMethodMain {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		int counter = 0;
		ArrayList<Float> traces = new ArrayList<>();
		ArrayList<Float> determinants = new ArrayList<>();
		ArrayList<Integer> iterations = new ArrayList<>();
		ArrayList<Integer> inverseIterations = new ArrayList<>();
		while (counter < 1000) {
			ArrayList list = PowerMethodConvergance.makeManyMatricies();
			if (list != null) {
				traces.add((Float) list.get(0));
				determinants.add((Float) list.get(1));
				iterations.add((Integer) list.get(2));
				inverseIterations.add((Integer) list.get(3));
				counter++;
			}
		}
		
		//Plot number 1
				// X axis is determinants, Y axis is traces
				XYDataset dataset1 = createDataset(determinants, traces);
				JFreeChart chart = ChartFactory.createScatterPlot("Determinants vs Traces - Coloring: Iterations","Determinants","traces", dataset1);
				XYPlot xyPlot = (XYPlot) chart.getPlot();
		        XYItemRenderer renderer = xyPlot.getRenderer();
		        renderer.setSeriesPaint(0,(Paint) Color.BLACK);
		        double size = 2;
		        double delta = size / 2.0;
		        Shape shape2 = new Ellipse2D.Double(-delta, -delta, size, size);
		        renderer.setSeriesShape(0, shape2);
				ChartPanel cp = new ChartPanel(chart);
				JFrame frame = new JFrame();
				frame.add(cp);
			    frame.pack();
			    frame.setVisible(true);
	    
	  //Plot number 2
		// X axis is determinants, Y axis is traces
			    
			  //Plot number 1
				// X axis is determinants, Y axis is traces
				XYDataset dataset2 = createDataset(determinants, traces);
				JFreeChart chart2 = ChartFactory.createScatterPlot("Determinants vs Traces - Coloring: Inverse Iterations","Determinants","traces", dataset2);
		        XYPlot xyPlot2 = (XYPlot) chart2.getPlot();
		        System.out.println(xyPlot2.getDatasetCount());
		        XYItemRenderer renderer2 = xyPlot2.getRenderer();
		        renderer2.setSeriesPaint(0,(Paint) Color.BLACK);
		        double size2 = 2;
		        double delta2 = size / 2.0;
		        Shape shape3 = new Ellipse2D.Double(-delta2, -delta2, size2, size2);
		        renderer2.setSeriesShape(0, shape3);
				ChartPanel cp2 = new ChartPanel(chart2);
				JFrame frame2 = new JFrame();
				frame2.add(cp2);
			    frame2.pack();
			    frame2.setVisible(true);
		
	}
	
	
	
	public static XYDataset createDataset(ArrayList det, ArrayList traces) {
	        XYSeriesCollection result = new XYSeriesCollection();
	        XYSeries series = new XYSeries("Plot 1");
	        for (int t = 0; t < 1000; t++) {
	            series.add((float) det.get(t), (float) traces.get(t));
	        }
	        result.addSeries(series);
	        return result;
	    }
}