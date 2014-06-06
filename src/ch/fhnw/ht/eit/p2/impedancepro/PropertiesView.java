package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * <pre>
 * The <code>PropertiesView</code> combines a <code>MonteCarloPanel</code> and a
 * <code>ReturnLossPanel</code>. All the configurations can be changed here.
 * </pre>
 * 
 * @author Simon Zumbrunnen
 */
public class PropertiesView extends JPanel {
	private static final long serialVersionUID = 1L;

	public SettingsPanel settingsPanel;
	public MonteCarloPanel monteCarloPanel;

	public PropertiesView(ImpedanceProController controller) {
		setLayout(new GridBagLayout());

		settingsPanel = new SettingsPanel(controller);
		monteCarloPanel = new MonteCarloPanel(controller);

		JPanel returnLossBorderPanel = new JPanel(new BorderLayout());
		JPanel monteCarloBorderPanel = new JPanel(new BorderLayout());

		returnLossBorderPanel.add(settingsPanel);
		monteCarloBorderPanel.add(monteCarloPanel);

		returnLossBorderPanel.setBorder(BorderFactory
				.createTitledBorder("Einstellungen"));
		monteCarloBorderPanel.setBorder(BorderFactory
				.createTitledBorder("Monte-Carlo"));
		
		add(monteCarloBorderPanel, new GridBagConstraints(0, // gridx
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
		
		add(returnLossBorderPanel, new GridBagConstraints(0, // gridx
				GridBagConstraints.RELATIVE, // gridy
				1, // gridwidth
				1, // gridheigth
				1.0, // weightx
				0.0, // weighty
				GridBagConstraints.CENTER, // anchor
				GridBagConstraints.BOTH, // fill
				new Insets(0, 0, 0, 0), // insets
				0, // ipadx
				0 // ipady
				));
	}

}
