package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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

		reflectionBorderPanel.add(reflectionPanel, BorderLayout.CENTER);
		monteCarloBorderPanel.add(monteCarloPanel, BorderLayout.CENTER);

		reflectionBorderPanel.setBorder(BorderFactory
				.createTitledBorder("Plot"));
		monteCarloBorderPanel.setBorder(BorderFactory
				.createTitledBorder("Monte-Carlo"));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.2;
		gbc.weighty = 1.0;
		add(reflectionBorderPanel, gbc);

		gbc.weightx = 0.8;
		add(monteCarloBorderPanel, gbc);
	}

}
