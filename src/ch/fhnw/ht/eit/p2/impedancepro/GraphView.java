package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * <pre>
 * The <code>GraphView</code> holds the <code>GraphPanel</code> for the return
 * loss plot.
 * </pre>
 * 
 * @author Simon Zumbrunnen
 */
public class GraphView extends JPanel {
	private static final long serialVersionUID = 1L;

	public GraphPanel returnLossGraph;
	private JLabel lbInfo;

	private ImpedanceProController controller;

	/**
	 * <pre>
	 * Creates a <code>GraphPanel</code> and configures it.
	 * </pre>
	 * 
	 * @param controller
	 *            The controller needs to be able to change graph settings.
	 */
	public GraphView(ImpedanceProController controller) {
		super();
		this.controller = controller;

		lbInfo = new JLabel("Berechnung des Plots nicht mšglich");
		lbInfo.setVisible(false);

		JPanel returnLossGraphBorderPanel = new JPanel(new GridBagLayout());

		setLayout(new GridLayout(1, 0));

		returnLossGraph = new GraphPanel("Frequenz / Hz", "", controller);

		returnLossGraphBorderPanel.setBorder(BorderFactory
				.createTitledBorder("Reflexion"));

		returnLossGraphBorderPanel.add(returnLossGraph, new GridBagConstraints(
				GridBagConstraints.RELATIVE, // gridx
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

		returnLossGraphBorderPanel.add(lbInfo, new GridBagConstraints(
				GridBagConstraints.RELATIVE, // gridx
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

	/**
	 * <pre>
	 * Updates the graph.
	 * </pre>
	 * 
	 * @param model
	 */
	public void update(ImpedanceProModel model) {
		if (controller.getGraphType() == 0) {
			if (model.getNetwork().getReturnLossData() != null) {
				returnLossGraph.plot.setDataset(model.getNetwork()
						.getReturnLossData());
			}
		} else {
			if (model.getNetwork().getSwrData() != null) {
				returnLossGraph.plot
						.setDataset(model.getNetwork().getSwrData());
			}
		}
		if (controller.isMonteCarloDisplayed()
				&& controller.getGraphType() == 0) {
			returnLossGraph.setYieldGoal(model.getLowerFrequency(),
					model.getUpperFrequency(), model.getH());
		} else {
			returnLossGraph.removeYieldGoal();
		}
	}

	/**
	 * <pre>
	 * Enables or disables the <code>GraphView</code>
	 * </pre>
	 */
	public void setEnabled(boolean enabled) {
		returnLossGraph.setVisible(enabled);
		lbInfo.setVisible(!enabled);
	}
}
