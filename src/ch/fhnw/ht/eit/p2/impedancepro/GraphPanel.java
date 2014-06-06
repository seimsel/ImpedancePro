package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYAreaRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.Range;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * The <code>GraphPanel</code> class is able to show a <code>JFreeChart</code>
 * by setting the dataset of the <code>public XYPlot plot</code>. It is also
 * possible to display a yield goal.
 * 
 * @author Simon Zumbrunnen
 */
public class GraphPanel extends JPanel implements MouseWheelListener {
	private static final long serialVersionUID = 1L;

	private ImpedanceProController controller;
	
	public XYPlot plot;
	public NumberAxis axis;
	
	/**
	 * Creates a <code>JFreeChart</code> and configures it.
	 * 
	 * @param xName
	 *            The name of the x-axis
	 * @param yName
	 *            The name of the y-axis
	 */
	public GraphPanel(String xName, String yName, ImpedanceProController controller) {
		super();
		this.controller = controller;
		
		setLayout(new BorderLayout());

		JFreeChart chart = ChartFactory.createXYLineChart("", // title
				xName, // xAxisLabel
				yName, // yAxisLabel
				null, // dataset
				PlotOrientation.VERTICAL, // orientation
				false, // legend
				false, // tooltips
				false // urls
				);

		plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);
		plot.setDomainGridlinePaint(Color.BLACK);
		plot.setOutlinePaint(null);
		
		axis = (NumberAxis) plot.getDomainAxis();
		axis.setNumberFormatOverride(new DecimalFormat("###E0"));
		axis.setLabelFont(getFont());
		plot.setDomainAxis(axis);
		
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPopupMenu(null);
		chartPanel.setDomainZoomable(false);
		chartPanel.setRangeZoomable(false);
		chartPanel.setMinimumDrawWidth(0);
		chartPanel.setMinimumDrawHeight(0);
		chartPanel.addMouseWheelListener(this);

		XYItemRenderer renderer = chart.getXYPlot().getRenderer(0);
		renderer.setSeriesPaint(0, ImpedanceProView.BLUE);
		renderer.setSeriesPaint(1, ImpedanceProView.GREEN);
		renderer.setSeriesPaint(2, ImpedanceProView.RED);
		renderer.setSeriesPaint(3, ImpedanceProView.YELLOW);

		add(chartPanel);
	}

	/**
	 * Adds a yield goal to the plot.
	 * 
	 * @param lowerLimit
	 *            Lower limit of the yield goal
	 * @param upperLimit
	 *            Upper limit of the yield goal
	 * @param h
	 *            Height of the yield goal relative to the range minimum
	 */
	public void setYieldGoal(double lowerLimit, double upperLimit, double h) {
		Double rangeLength = plot.getRangeAxis(0).getRange().getLength();
		XYAreaRenderer renderer = new XYAreaRenderer();
		XYSeries xySeries = new XYSeries("yield_goal");
		XYDataset dataset = new XYSeriesCollection(xySeries);
		ValueAxis axis = new NumberAxis();

		renderer.setSeriesPaint(0, ImpedanceProView.LIGHT_RED);

		xySeries.add(lowerLimit, rangeLength - h);
		xySeries.add(upperLimit, rangeLength - h);

		axis.setInverted(true);
		axis.setVisible(false);
		axis.setRange(new Range(0, rangeLength));

		plot.setRenderer(1, renderer);
		plot.setDataset(1, dataset);
		plot.setRangeAxis(1, axis);
		plot.mapDatasetToRangeAxis(1, 1);
	}
	
	public void removeYieldGoal() {
		plot.setDataset(1, null);
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		controller.setYieldGoalSpan(e.getWheelRotation());
	}
}