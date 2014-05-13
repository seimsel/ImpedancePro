package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The <code>GraphView</code> combines two <code>GraphPanel</code>s.
 * 
 * @author Simon Zumbrunnen
 */
public class GraphView extends JPanel {
	private static final long serialVersionUID = 1L;

	public GraphPanel amplitudeGraph, returnLossGraph;
	private JLabel lbInfo;

	public GraphView() {
		super();

		lbInfo = new JLabel("Berechnung des Plots nicht m�glich");
		lbInfo.setVisible(false);
		
		JPanel returnLossGraphBorderPanel = new JPanel(new GridBagLayout());

		setLayout(new GridLayout(1, 0));

		returnLossGraph = new GraphPanel("Frequenz", "Reflexion");

		returnLossGraphBorderPanel.setBorder(BorderFactory
				.createTitledBorder("Reflexion"));

		returnLossGraphBorderPanel.add(returnLossGraph, new GridBagConstraints(GridBagConstraints.RELATIVE, // gridx
				GridBagConstraints.RELATIVE, // gridy
				1, // gridwidth
				1, // gridheigth
				1.0, // weightx
				1.0, // weighty
				GridBagConstraints.CENTER, // anchor
				GridBagConstraints.BOTH, // fill
				new Insets(0, 0, 0, 0), // insets
				0, // ipadx
				0 // ipady
				));
		
		returnLossGraphBorderPanel.add(lbInfo, new GridBagConstraints(GridBagConstraints.RELATIVE, // gridx
				GridBagConstraints.RELATIVE, // gridy
				1, // gridwidth
				1, // gridheigth
				0.0, // weightx
				0.0, // weighty
				GridBagConstraints.CENTER, // anchor
				GridBagConstraints.NONE, // fill
				new Insets(0, 0, 0, 0), // insets
				0, // ipadx
				0 // ipady
				));

		add(returnLossGraphBorderPanel);
	}
	
	public void update(ImpedanceProModel model) {
		if(model.getNetwork().getReturnLossData() != null) {
			returnLossGraph.plot.setDataset(model.getNetwork().getReturnLossData());
		}
	}
	
	public void setEnabled(boolean enabled) {
		returnLossGraph.setVisible(enabled);
		lbInfo.setVisible(!enabled);
	}
}
