package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYAreaRenderer;
import org.jfree.data.Range;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GraphPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public XYPlot plot;
	
	public GraphPanel(String xName, String yName) {
		super();
		
		setLayout(new BorderLayout());
		
		JFreeChart chart = ChartFactory.createXYLineChart(
				"",								//title
				xName,							//xAxisLabel
				yName,							//yAxisLabel
				null,							//dataset
				PlotOrientation.VERTICAL,		//orientation
				false,							//legend
				false,							//tooltips
				false							//urls
		);
		
		plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinePaint(Color.BLACK);
        plot.setOutlinePaint(null);
		
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPopupMenu(null);
        chartPanel.setDomainZoomable(false);
        chartPanel.setRangeZoomable(false);
        chartPanel.setMinimumDrawWidth(0); 
        chartPanel.setMinimumDrawHeight(0);
        
        chart.getXYPlot().getRenderer(0).setSeriesPaint(0, ImpedanceProView.BLUE);
        chart.getXYPlot().getRenderer(0).setSeriesPaint(1, ImpedanceProView.GREEN);
        chart.getXYPlot().getRenderer(0).setSeriesPaint(2, ImpedanceProView.RED);
        chart.getXYPlot().getRenderer(0).setSeriesPaint(3, ImpedanceProView.YELLOW);
        
		add(chartPanel);
	}
	
	public void setYieldGoal(double lowerLimit, double upperLimit, double h) {
		Double rangeLength = plot.getRangeAxis(0).getRange().getLength();
		XYAreaRenderer renderer = new XYAreaRenderer();
		XYSeries xySeries = new XYSeries("yield_goal");
		XYDataset dataset = new XYSeriesCollection(xySeries);
		ValueAxis axis = new NumberAxis();

		renderer.setSeriesPaint(0, ImpedanceProView.LIGHT_RED);
		
		xySeries.add(lowerLimit, rangeLength-h);
		xySeries.add(upperLimit, rangeLength-h);
		
		axis.setInverted(true);
		axis.setVisible(false);
		axis.setRange(new Range(0, rangeLength));
		
		plot.setRenderer(1, renderer);
		plot.setDataset(1, dataset);
		plot.setRangeAxis(1, axis);
		plot.mapDatasetToRangeAxis(1, 1);
	}
}