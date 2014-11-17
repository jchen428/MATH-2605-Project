package powermethod;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ValueAxis;

import org.jfree.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.DefaultPolarItemRenderer;
import org.jfree.chart.renderer.PolarItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.sun.javafx.geom.Rectangle;

public class PowerMethodMain {
	
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

		
		XYDataset dataset = createDataset(determinants, traces);
		JFreeChart chart = ChartFactory.createScatterPlot("Scatter Plot","Year","Paddy Production", dataset);
		chart.draw();
		
	}
	
	//Plot number 1
			// X axis is determinants, Y axis is traces
	
	public static XYDataset createDataset(ArrayList det, ArrayList traces) {
	        XYSeriesCollection result = new XYSeriesCollection();
	        XYSeries series = new XYSeries("Plot 1");
	        for (int t = 0; t <= 100; t++) {
	            series.add((float) det.get(t), (float) traces.get(t));
	        }
	        result.addSeries(series);
	        return result;
	    }
}