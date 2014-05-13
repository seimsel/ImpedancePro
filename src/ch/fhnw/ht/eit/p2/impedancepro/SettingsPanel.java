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

import com.alee.extended.button.WebSwitch;

/**
 * The <code>ReturnLossPanel</code> class contains the settings for the graphs.
 * 
 * @author Simon Zumbrunnen
 */
public class SettingsPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	String[] returnLossStrings = { "SWR", "R" };

	public JComboBox<String> cbReturnLoss;
	public WebSwitch btnMonteCarlo;

	private JLabel lbReturnLoss;
	private JLabel lbMonteCarlo;
	private ImpedanceProController controller;

	public SettingsPanel(ImpedanceProController controller) {
		super();
		
		this.controller = controller;
		
		setLayout(new GridBagLayout());

		lbReturnLoss = new JLabel("Reflexion:");
		cbReturnLoss = new JComboBox<String>(returnLossStrings);

		cbReturnLoss.setFocusable(false);
		cbReturnLoss.addActionListener(this);
		
		btnMonteCarlo = new WebSwitch();
		btnMonteCarlo.setSelected(true, false);
		btnMonteCarlo.setFocusable(false);
		btnMonteCarlo.addActionListener(this);
		
		lbMonteCarlo = new JLabel("Monte-Carlo:");
		
		add(lbMonteCarlo, new GridBagConstraints(0, // gridx
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
		
		add(btnMonteCarlo, new GridBagConstraints(1, // gridx
				GridBagConstraints.RELATIVE, // gridy
				2, // gridwidth
				1, // gridheigth
				0.0, // weightx
				0.0, // weighty
				GridBagConstraints.WEST, // anchor
				GridBagConstraints.NONE, // fill
				new Insets(0, 0, 0, 0), // insets
				0, // ipadx
				0 // ipady
				));
		
		add(lbReturnLoss, new GridBagConstraints(0, // gridx
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
		
		add(cbReturnLoss, new GridBagConstraints(1, // gridx
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
	}

	public void actionPerformed(ActionEvent e) {
		controller.viewAction();
	}

	public void update(Observable obs, Object obj) {
	}
}
