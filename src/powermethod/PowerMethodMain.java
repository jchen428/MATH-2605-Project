package powermethod;
import java.util.ArrayList;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.awt.Shape;

import java.awt.Color;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;

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
		
		//Plot number 1
		XYDataset dataset1 = createDataset(determinants, traces, iterations);
		JFreeChart chart = ChartFactory.createScatterPlot("Determinants vs Traces - Coloring: Iterations","Determinants","traces", dataset1);
		XYPlot xyPlot = (XYPlot) chart.getPlot();
        XYItemRenderer renderer = xyPlot.getRenderer();
        renderer.setSeriesPaint(0,(Paint) Color.ORANGE);
	    renderer.setSeriesPaint(1,(Paint) Color.MAGENTA);
	    renderer.setSeriesPaint(2,(Paint) Color.GRAY);
	    renderer.setSeriesPaint(3,(Paint) Color.CYAN);
	    renderer.setSeriesPaint(4,(Paint) Color.BLUE);
        double size = 2;
        double delta = size / 2.0;
        Shape shape2 = new Ellipse2D.Double(-delta, -delta, size, size);
        renderer.setSeriesShape(0, shape2);
        renderer.setSeriesShape(1, shape2);
        renderer.setSeriesShape(2, shape2);
        renderer.setSeriesShape(3, shape2);
        renderer.setSeriesShape(4, shape2);
		ChartPanel cp = new ChartPanel(chart);
		JFrame frame = new JFrame();
		frame.add(cp);
	    frame.pack();
	    frame.setVisible(true);
	    
	    //Plot number 2
		XYDataset dataset2 = createDataset(determinants, traces, inverseIterations);
		JFreeChart chart2 = ChartFactory.createScatterPlot("Determinants vs Traces - Coloring: Inverse Iterations","Determinants","traces", dataset2);
	    XYPlot xyPlot2 = (XYPlot) chart2.getPlot();
	    System.out.println(xyPlot2.getDatasetCount());
	    XYItemRenderer renderer2 = xyPlot2.getRenderer();
	    renderer2.setSeriesPaint(0,(Paint) Color.ORANGE);
	    renderer2.setSeriesPaint(1,(Paint) Color.MAGENTA);
	    renderer2.setSeriesPaint(2,(Paint) Color.GRAY);
	    renderer2.setSeriesPaint(3,(Paint) Color.CYAN);
	    renderer2.setSeriesPaint(4,(Paint) Color.BLUE);
	    
	    double size2 = 2;
	    double delta2 = size / 2.0;
	    Shape shape3 = new Ellipse2D.Double(-delta2, -delta2, size2, size2);
	    renderer2.setSeriesShape(0, shape3);
	    renderer2.setSeriesShape(1, shape3);
	    renderer2.setSeriesShape(2, shape3);
	    renderer2.setSeriesShape(3, shape3);
	    renderer2.setSeriesShape(4, shape3);
		ChartPanel cp2 = new ChartPanel(chart2);
		JFrame frame2 = new JFrame();
		frame2.add(cp2);
	    frame2.pack();
	    frame2.setVisible(true);
		
	}
	
	
	
	public static XYDataset createDataset(ArrayList det, ArrayList traces, ArrayList iterations) {
        XYSeriesCollection result = new XYSeriesCollection();
        XYSeries seriesTo20 = new XYSeries("1 - 20 Iterations");
        XYSeries seriesTo40 = new XYSeries("21 - 40 Iterations");
        XYSeries seriesTo60 = new XYSeries("41 - 60 Iterations");
        XYSeries seriesTo80 = new XYSeries("61 - 80 Iterations");
        XYSeries seriesTo100 = new XYSeries("81 - 100 Iterations");
        for (int t = 0; t < 1000; t++) {
        	if ((int) iterations.get(t) < 21) {
        		seriesTo20.add((float) det.get(t), (float) traces.get(t));
        	} else if (((int) iterations.get(t) > 20) && (int) iterations.get(t) < 41) {
        		seriesTo40.add((float) det.get(t), (float) traces.get(t));
        	} else if (((int) iterations.get(t) > 40) && (int) iterations.get(t) < 61) {
        		seriesTo60.add((float) det.get(t), (float) traces.get(t));
        	} else if (((int) iterations.get(t) > 60) && (int) iterations.get(t) < 81) {
        		seriesTo80.add((float) det.get(t), (float) traces.get(t));
        	} else if ((int) iterations.get(t) > 80) {
        		seriesTo100.add((float) det.get(t), (float) traces.get(t));
        	}
        }
        result.addSeries(seriesTo20);
        result.addSeries(seriesTo40);
        result.addSeries(seriesTo60);
        result.addSeries(seriesTo80);
        result.addSeries(seriesTo100);
        return result;
	    }
}