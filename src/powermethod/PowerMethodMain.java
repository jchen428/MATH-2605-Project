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
        double size = 1;
        double delta = size / 2.0;
        Shape shape2 = new Ellipse2D.Double(-delta, -delta, size, size);
        renderer.setSeriesShape(0, shape2);
        renderer.setSeriesShape(1, shape2);
        renderer.setSeriesShape(2, shape2);
        renderer.setSeriesShape(3, shape2);
        renderer.setSeriesShape(4, shape2);
        renderer.setSeriesShape(5, shape2);
        renderer.setSeriesShape(6, shape2);
        renderer.setSeriesShape(7, shape2);
        renderer.setSeriesShape(8, shape2);
        renderer.setSeriesShape(9, shape2);
		ChartPanel cp = new ChartPanel(chart);
		JFrame frame = new JFrame();
		frame.add(cp);
	    frame.pack();
	    frame.setVisible(true);
	    
	    //Plot number 2
		XYDataset dataset2 = createDataset(determinants, traces, inverseIterations);
		JFreeChart chart2 = ChartFactory.createScatterPlot("Determinants vs Traces - Coloring: Inverse Iterations","Determinants","traces", dataset2);
	    XYPlot xyPlot2 = (XYPlot) chart2.getPlot();
	    XYItemRenderer renderer2 = xyPlot2.getRenderer();
	    
	    double size2 = 1;
	    double delta2 = size / 2.0;
	    Shape shape3 = new Ellipse2D.Double(-delta2, -delta2, size2, size2);
	    renderer2.setSeriesShape(0, shape3);
	    renderer2.setSeriesShape(1, shape3);
	    renderer2.setSeriesShape(2, shape3);
	    renderer2.setSeriesShape(3, shape3);
	    renderer2.setSeriesShape(4, shape3);
	    renderer2.setSeriesShape(5, shape3);
	    renderer2.setSeriesShape(6, shape3);
	    renderer2.setSeriesShape(7, shape3);
	    renderer2.setSeriesShape(8, shape3);
	    renderer2.setSeriesShape(9, shape3);
		ChartPanel cp2 = new ChartPanel(chart2);
		JFrame frame2 = new JFrame();
		frame2.add(cp2);
	    frame2.pack();
	    frame2.setVisible(true);
		
	}
	
	
	
	public static XYDataset createDataset(ArrayList det, ArrayList traces, ArrayList iterations) {
        XYSeriesCollection result = new XYSeriesCollection();
        XYSeries seriesTo10 = new XYSeries("1 - 10 Iterations");
        XYSeries seriesTo20 = new XYSeries("11 - 20 Iterations");
        XYSeries seriesTo30 = new XYSeries("21 - 30 Iterations");
        XYSeries seriesTo40 = new XYSeries("31 - 40 Iterations");
        XYSeries seriesTo50 = new XYSeries("41 - 50 Iterations");
        XYSeries seriesTo60 = new XYSeries("51 - 60 Iterations");
        XYSeries seriesTo70 = new XYSeries("61 - 70 Iterations");
        XYSeries seriesTo80 = new XYSeries("71 - 80 Iterations");
        XYSeries seriesTo90 = new XYSeries("81 - 90 Iterations");
        XYSeries seriesTo100 = new XYSeries("91 - 100 Iterations");
        for (int t = 0; t < 1000; t++) {
        	int i = (int) iterations.get(t);
        	if (i <= 10) {
        		seriesTo10.add((float) det.get(t), (float) traces.get(t));
        	} else if ( i > 10 && i <= 20) {
        		seriesTo20.add((float) det.get(t), (float) traces.get(t));
        	} else if ( i > 20 && i <= 30) {
        		seriesTo30.add((float) det.get(t), (float) traces.get(t));
        	} else if ( i > 30 && i <= 40) {
        		seriesTo40.add((float) det.get(t), (float) traces.get(t));
        	} else if ( i > 40 && i <= 50) {
        		seriesTo50.add((float) det.get(t), (float) traces.get(t));
        	} else if ( i > 50 && i <= 60) {
        		seriesTo60.add((float) det.get(t), (float) traces.get(t));
        	} else if ( i > 60 && i <= 70) {
        		seriesTo70.add((float) det.get(t), (float) traces.get(t));
        	} else if ( i > 70 && i <= 80) {
        		seriesTo80.add((float) det.get(t), (float) traces.get(t));
        	} else if ( i > 80 && i <= 90) {
        		seriesTo90.add((float) det.get(t), (float) traces.get(t));
        	} else if ( i > 90) {
        		seriesTo100.add((float) det.get(t), (float) traces.get(t));
        	}
        }
        result.addSeries(seriesTo10);
        result.addSeries(seriesTo20);
        result.addSeries(seriesTo30);
        result.addSeries(seriesTo40);
        result.addSeries(seriesTo50);
        result.addSeries(seriesTo60);
        result.addSeries(seriesTo70);
        result.addSeries(seriesTo80);
        result.addSeries(seriesTo90);
        result.addSeries(seriesTo100);
        return result;
	    }
}