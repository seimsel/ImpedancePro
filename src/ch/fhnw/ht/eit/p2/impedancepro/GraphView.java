package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GraphView extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public GraphPanel amplitudeGraph;
	public GraphPanel reflectionGraph;
	
	public GraphView() {
		super();
		setLayout(new GridLayout(1, 0));
				
		amplitudeGraph = new GraphPanel("Frequenz", "Amplitude");
		reflectionGraph = new GraphPanel("Frequenz", "Reflexion");
		
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
