package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * The <code>PropertiesView</code> combines a <code>MonteCarloPanel</code> and a
 * <code>ReflectionPanel</code>. All the configurations can be changed here.
 * 
 * @author Simon Zumbrunnen
 */
public class PropertiesView extends JPanel {
	private static final long serialVersionUID = 1L;

	public ReflectionPanel reflectionPanel;
	public MonteCarloPanel monteCarloPanel;

	public PropertiesView(ImpedanceProController controller) {
		setLayout(new GridBagLayout());

		reflectionPanel = new ReflectionPanel(controller);
		monteCarloPanel = new MonteCarloPanel(controller);

		JPanel reflectionBorderPanel = new JPanel(new BorderLayout());
		JPanel monteCarloBorderPanel = new JPanel(new BorderLayout());

		reflectionBorderPanel.add(reflectionPanel);
		monteCarloBorderPanel.add(monteCarloPanel);

		reflectionBorderPanel.setBorder(BorderFactory
				.createTitledBorder("Plot"));
		monteCarloBorderPanel.setBorder(BorderFactory
				.createTitledBorder("Monte-Carlo"));

		add(reflectionBorderPanel, new GridBagConstraints(GridBagConstraints.RELATIVE, // gridx
				GridBagConstraints.RELATIVE, // gridy
				1, // gridwidth
				1, // gridheigth
				0.2, // weightx
				1.0, // weighty
				GridBagConstraints.CENTER, // anchor
				GridBagConstraints.BOTH, // fill
				new Insets(0, 0, 0, 0), // insets
				0, // ipadx
				0 // ipady
				));

		add(monteCarloBorderPanel, new GridBagConstraints(GridBagConstraints.RELATIVE, // gridx
				GridBagConstraints.RELATIVE, // gridy
				1, // gridwidth
				1, // gridheigth
				0.8, // weightx
				1.0, // weighty
				GridBagConstraints.CENTER, // anchor
				GridBagConstraints.BOTH, // fill
				new Insets(0, 0, 0, 0), // insets
				0, // ipadx
				0 // ipady
				));
	}

}
