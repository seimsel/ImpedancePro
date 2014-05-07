package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The <code>ReflectionPanel</code> class contains the settings for the graphs.
 * 
 * @author Simon Zumbrunnen
 */
public class ReflectionPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	String[] reflectionStrings = { "SWR", "R" };
	String[] amplitudeStrings = { "dB", "Abs" };

	public JComboBox<String> cbReflection, cbAmplitude;

	private JLabel lbReflection, lbAmplitude;
	private ImpedanceProController controller;

	public ReflectionPanel(ImpedanceProController controller) {
		super();
		
		this.controller = controller;
		
		setLayout(new GridBagLayout());

		lbReflection = new JLabel("Reflexion:");
		cbReflection = new JComboBox<String>(reflectionStrings);
		lbAmplitude = new JLabel("Amplitude:");
		cbAmplitude = new JComboBox<String>(amplitudeStrings);

		cbReflection.setFocusable(false);
		cbReflection.addActionListener(this);
		
		cbAmplitude.setFocusable(false);
		cbAmplitude.addActionListener(this);

		add(lbAmplitude, new GridBagConstraints(GridBagConstraints.RELATIVE, // gridx
				0, // gridy
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
		
		add(cbAmplitude, new GridBagConstraints(GridBagConstraints.RELATIVE, // gridx
				0, // gridy
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

		add(lbReflection, new GridBagConstraints(GridBagConstraints.RELATIVE, // gridx
				1, // gridy
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
		
		add(cbReflection, new GridBagConstraints(GridBagConstraints.RELATIVE, // gridx
				1, // gridy
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
	}

	public void actionPerformed(ActionEvent e) {
		controller.viewAction();
	}

	public void update(Observable obs, Object obj) {
	}
}
