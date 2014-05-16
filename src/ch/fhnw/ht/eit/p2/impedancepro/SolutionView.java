package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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

	public SolutionView(ImpedanceProController controller) {
		super();

		setBorder(BorderFactory.createTitledBorder("Anpass-Netzwerke"));
		setLayout(new GridBagLayout());

		Color[] colors = new Color[] { ImpedanceProView.LIGHT_BLUE,
				ImpedanceProView.LIGHT_GREEN, ImpedanceProView.LIGHT_RED,
				ImpedanceProView.LIGHT_YELLOW };

		SolutionPanel[] solutionPanels = new SolutionPanel[4];
		for (int i = 0; i < 4; i++) {
			solutionPanels[i] = new SolutionPanel(colors[i],
					MatchingNetwork.NONE, controller);
			add(solutionPanels[i], new GridBagConstraints(
					GridBagConstraints.RELATIVE, // gridx
					0, // gridy
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
		}
		setSolutionPanels(solutionPanels);
	}

	public void setSolutionPanels(SolutionPanel[] solutionPanels) {
		this.solutionPanels = solutionPanels;
	}

	public SolutionPanel[] getSolutionPanels() {
		return solutionPanels;
	}

	public void update(ImpedanceProModel model) {
		MatchingNetwork[] matchingNetworks = model.getNetwork()
				.getMatchingNetworks();
		SolutionPanel[] solutionPanels = getSolutionPanels();

		for (int i = 0; i < 4; i++) {
			solutionPanels[i].setVisible(false);
		}

		for (int i = 0; i < matchingNetworks.length; i++) {
			solutionPanels[i].setVisible(true);
			
			if(matchingNetworks[i]
					.getElectricalComponents()[0].getValue() > 0) {
				solutionPanels[i].lbValue1
				.setText(EngineeringUtil.convert(matchingNetworks[i]
						.getElectricalComponents()[0].getValue(), 3));
			} else {
				solutionPanels[i].lbValue1
				.setText(" ");
			}
			
			if(matchingNetworks[i]
					.getElectricalComponents()[1].getValue() > 0) {
				solutionPanels[i].lbValue2
				.setText(EngineeringUtil.convert(matchingNetworks[i]
						.getElectricalComponents()[1].getValue(), 3));
			} else {
				solutionPanels[i].lbValue2
				.setText(" ");
			}
			
			solutionPanels[i].setTopology(matchingNetworks[i].getTopology());

			if (model.getNetwork().getMonteCarloResults() != null) {
				solutionPanels[i].valuePanel.lbMonteCarlo.setText(String
						.valueOf(Math.round(model.getNetwork()
								.getMonteCarloResults()[i])) + "%");
			}
		}
	}
}
