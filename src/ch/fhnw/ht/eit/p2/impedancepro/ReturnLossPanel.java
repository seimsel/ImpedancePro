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
 * The <code>ReturnLossPanel</code> class contains the settings for the graphs.
 * 
 * @author Simon Zumbrunnen
 */
public class ReturnLossPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	String[] returnLossStrings = { "SWR", "R" };

	public JComboBox<String> cbReturnLoss;

	private JLabel lbReturnLoss;
	private ImpedanceProController controller;

	public ReturnLossPanel(ImpedanceProController controller) {
		super();
		
		this.controller = controller;
		
		setLayout(new GridBagLayout());

		lbReturnLoss = new JLabel("Reflexion:");
		cbReturnLoss = new JComboBox<String>(returnLossStrings);

		cbReturnLoss.setFocusable(false);
		cbReturnLoss.addActionListener(this);

		add(lbReturnLoss, new GridBagConstraints(GridBagConstraints.RELATIVE, // gridx
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
		
		add(cbReturnLoss, new GridBagConstraints(GridBagConstraints.RELATIVE, // gridx
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
