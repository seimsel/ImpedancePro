package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GraphView extends JPanel {
	private static final long serialVersionUID = 1L;

	public GraphView() {
		super();
		setLayout(new GridLayout(1, 0));
		
		Double[] amplitudeXValues = new Double[]{0.0, 1.0, 2.0, 3.0};
		Double[] amplitudeYValues = new Double[]{0.0, 1.0, 2.0, 3.0};
		Double[] reflectionXValues = new Double[]{0.0, 1.0, 2.0, 3.0};
		Double[] reflectilYValues = new Double[]{0.0, 1.0, 2.0, 3.0};
		
		GraphPanel amplitudeGraph = new GraphPanel(amplitudeXValues, amplitudeYValues, "Frequenz", "Amplitude");
		GraphPanel reflectionGraph = new GraphPanel(reflectionXValues, reflectilYValues, "Frequenz", "Reflexion");
		
		JPanel amplitudeGraphBorderPanel = new JPanel(new BorderLayout());
		JPanel reflectionGraphBorderPanel = new JPanel(new BorderLayout());
		
		amplitudeGraphBorderPanel.setBorder(BorderFactory.createTitledBorder("Amplitude"));
		reflectionGraphBorderPanel.setBorder(BorderFactory.createTitledBorder("Reflexion"));
		
		amplitudeGraphBorderPanel.add(amplitudeGraph, BorderLayout.CENTER);
		reflectionGraphBorderPanel.add(reflectionGraph, BorderLayout.CENTER);
		
		add(amplitudeGraphBorderPanel);
		add(reflectionGraphBorderPanel);
	}
	
}
