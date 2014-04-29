package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PropertiesView extends JPanel {
	private static final long serialVersionUID = 1L;

	public ReflectionPanel reflectionPanel = new ReflectionPanel();
	public MonteCarloPanel monteCarloPanel = new MonteCarloPanel();
	
	public PropertiesView() {
		setLayout(new GridBagLayout());
		
		reflectionPanel = new ReflectionPanel();
		monteCarloPanel = new MonteCarloPanel();
		
		JPanel reflectionBorderPanel = new JPanel(new BorderLayout());
		JPanel monteCarloBorderPanel = new JPanel(new BorderLayout());
		
		reflectionBorderPanel.add(reflectionPanel, BorderLayout.CENTER);
		monteCarloBorderPanel.add(monteCarloPanel, BorderLayout.CENTER);
		
		reflectionBorderPanel.setBorder(BorderFactory.createTitledBorder("Plot"));
		monteCarloBorderPanel.setBorder(BorderFactory.createTitledBorder("Monte-Carlo"));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.2;
		gbc.weighty = 1.0;
		add(reflectionBorderPanel, gbc);
		
		gbc.weightx = 0.8;
		add(monteCarloBorderPanel, gbc);
	}

}
