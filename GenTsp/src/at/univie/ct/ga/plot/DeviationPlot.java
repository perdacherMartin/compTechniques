package at.univie.ct.ga.plot;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class DeviationPlot extends ApplicationFrame {
	
	private static final long serialVersionUID = -2329199783000692330L;

	public DeviationPlot(XYDataset dataset){
		super("Evolution");
		
		JFreeChart chart = ChartFactory.createXYLineChart("Evolution2", 
							"generations", 
							"distance to opt.", 
							dataset, 
							PlotOrientation.VERTICAL, 
							true, 
							false, 
							false
				);
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesLinesVisible(1, false);
        renderer.setSeriesShapesVisible(1, true);    
        renderer.setSeriesLinesVisible(2, true);
        renderer.setSeriesShapesVisible(2, false);
        plot.setRenderer(renderer);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
        setContentPane(chartPanel);
        
        
        // reset size
        this.pack();
        RefineryUtilities.centerFrameOnScreen(this);
        this.setVisible(true);
	}
	
//    private static XYDataset createSampleDataset() {
//        XYSeries series1 = new XYSeries("Series 1");
//        series1.add(1.0, 3.3);
//        series1.add(2.0, 4.4);
//        series1.add(3.0, 1.7);
//        XYSeries series2 = new XYSeries("Series 2");
//        series2.add(1.0, 7.3);
//        series2.add(2.0, 6.8);
//        series2.add(3.0, 9.6);
//        series2.add(4.0, 5.6);
//        XYSeriesCollection dataset = new XYSeriesCollection();
//        dataset.addSeries(series1);
//        dataset.addSeries(series2);
//        return dataset;
//    }
//
//	
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		final DeviationPlot plot = new DeviationPlot(DeviationPlot.createSampleDataset());
//
//        plot.pack();
//        RefineryUtilities.centerFrameOnScreen(plot);
//        plot.setVisible(true);
//	}

}
