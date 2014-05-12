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

	public GraphPanel amplitudeGraph, returnLossGraph;

	public GraphView() {
		super();

		JPanel returnLossGraphBorderPanel = new JPanel(new BorderLayout());

		setLayout(new GridLayout(1, 0));

		returnLossGraph = new GraphPanel("Frequenz", "Reflexion");

		returnLossGraphBorderPanel.setBorder(BorderFactory
				.createTitledBorder("Reflexion"));

		returnLossGraphBorderPanel.add(returnLossGraph);

		add(returnLossGraphBorderPanel);
	}
	
	public void update(ImpedanceProModel model) {
		if(model.getNetwork().getReturnLossData() != null) {
			returnLossGraph.plot.setDataset(model.getNetwork().getReturnLossData());
		}
	}
}
