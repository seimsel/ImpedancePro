package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * The <code>GraphView</code> combines two <code>GraphPanel</code>s.
 * 
 * @author Simon Zumbrunnen
 */
public class GraphView extends JPanel {
	private static final long serialVersionUID = 1L;

	public GraphPanel amplitudeGraph, reflectionGraph;

	public GraphView() {
		super();

		JPanel amplitudeGraphBorderPanel = new JPanel(new BorderLayout());
		JPanel reflectionGraphBorderPanel = new JPanel(new BorderLayout());

		setLayout(new GridLayout(1, 0));

		amplitudeGraph = new GraphPanel("Frequenz", "Amplitude");
		reflectionGraph = new GraphPanel("Frequenz", "Reflexion");

		amplitudeGraphBorderPanel.setBorder(BorderFactory
				.createTitledBorder("Amplitude"));
		reflectionGraphBorderPanel.setBorder(BorderFactory
				.createTitledBorder("Reflexion"));

		amplitudeGraphBorderPanel.add(amplitudeGraph);
		reflectionGraphBorderPanel.add(reflectionGraph);

		add(amplitudeGraphBorderPanel);
		add(reflectionGraphBorderPanel);
	}
}
