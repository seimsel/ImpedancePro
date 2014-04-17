package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GraphPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public GraphPanel(Double[] xData, Double[] yData, String xName, String yName) {
		super();
		
		setLayout(new BorderLayout());
		
        final XYSeries series = new XYSeries("");
        
        if(xData.length == yData.length) {
            for(int i=0;i<xData.length;i++) {
            	series.add(xData[i], yData[i]);
            }
        } else {
        	System.out.println("X/Y-Size of Graph-Data doesn't match");
        }
        
        XYSeriesCollection data = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
            "",
            xName, 
            yName, 
            data,
            PlotOrientation.VERTICAL,
            false,
            true,
            false
        );
        
        XYPlot plot = (XYPlot)chart.getPlot();
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
        
        add(chartPanel, BorderLayout.CENTER);
	}
}