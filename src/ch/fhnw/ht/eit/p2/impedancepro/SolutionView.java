package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * The <code>SolutionView</code> class combines up to 4
 * <code>SolutionPanel</code>s.
 * 
 * @author Simon Zumbrunnen
 */
public class SolutionView extends JPanel {
	private static final long serialVersionUID = 1L;

	private SolutionPanel[] solutionPanels;
	private ImpedanceProController controller;

	public SolutionView(ImpedanceProController controller) {
		super();
		this.controller = controller;

		setBorder(BorderFactory.createTitledBorder("Anpass-Netzwerke"));
		setLayout(new GridLayout(1, 0));
	}

	public SolutionPanel[] getSolutionPanels() {
		return solutionPanels;
	}

	public void setSolutionPanels(SolutionPanel[] solutionPanels) {
		this.solutionPanels = solutionPanels;

		for (int i = 0; i < solutionPanels.length; i++) {
			removeAll();
			add(solutionPanels[i]);
			revalidate();
			repaint();
		}
	}

	public void update(ImpedanceProModel model) {
		MatchingNetwork[] matchingNetworks = model.getNetwork()
				.getMatchingNetworks();

		removeAll();

		Color[] colors = new Color[] { ImpedanceProView.LIGHT_BLUE,
				ImpedanceProView.LIGHT_GREEN, ImpedanceProView.LIGHT_RED,
				ImpedanceProView.LIGHT_YELLOW };

		for (int i = 0; i < matchingNetworks.length; i++) {
			if (matchingNetworks[i] != null) {
				SolutionPanel sp = new SolutionPanel(colors[i],
						matchingNetworks[i].getTopology(), controller);
				add(sp);
				sp.lbValue1.setText(matchingNetworks[i]
						.getElectricalComponents()[0].getValueString());
				sp.lbValue2.setText(matchingNetworks[i]
						.getElectricalComponents()[1].getValueString());
				sp.valuePanel
						.setVisible(controller.getView().propertiesView.monteCarloPanel.btnMonteCarlo
								.isSelected());
			}
		}

		revalidate();
	}
}
