package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import java.util.Observable;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReflectionPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	String[] reflectionStrings = { "SWR", "R" };
	String[] amplitudeStrings = { "dB", "Abs" };

	private JComboBox<String> cbReflection, cbAmplitude;
	private JLabel lbReflection, lbAmplitude;

	public ReflectionPanel() {
		setLayout(new GridBagLayout());
		
		lbReflection = new JLabel("Reflexion:");
		cbReflection = new JComboBox<String>(reflectionStrings);
		lbAmplitude = new JLabel("Amplitude:");
		cbAmplitude = new JComboBox<String>(amplitudeStrings);
		
		cbReflection.setFocusable(false);
		cbAmplitude.setFocusable(false);

		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridy = 0;
		add(lbReflection, gbc);
		add(cbReflection, gbc);

		gbc.gridy = 1;
		add(lbAmplitude, gbc);
		add(cbAmplitude, gbc);
	}

	public void actionPerformed(ActionEvent e) {
	}

	public void update(Observable obs, Object obj) {
	}
}
