package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.alee.extended.image.DisplayType;
import com.alee.extended.image.WebImage;

/**
 * The <code>MonteCarloPanel</code> class contains all the inputs for the monte
 * carlo simulation.
 * 
 * @author Simon Zumbrunnen
 */
public class MonteCarloPanel extends JPanel implements ActionListener,
		FocusListener {
	private static final long serialVersionUID = 1L;

	public JEngineeringTextField tfN, tfFu, tfFo, tfH;

	private JLabel lbN, lbFu, lbFo, lbH;

	private ImpedanceProController controller;

	public MonteCarloPanel(ImpedanceProController controller) {
		setLayout(new GridBagLayout());

		this.controller = controller;

		lbN = new JLabel("n:");
		lbFu = new JLabel("<html><i>f</i>g<sub>u</sub>: </html>");
		lbFo = new JLabel("<html><i>f</i>g<sub>o</sub>: </html>");
		lbH = new JLabel("h:");

		tfN = new JEngineeringTextField(4);
		tfFu = new JEngineeringTextField(4);
		tfFo = new JEngineeringTextField(4);
		tfH = new JEngineeringTextField(4);

		tfN.addFocusListener(this);
		tfFu.addFocusListener(this);
		tfFo.addFocusListener(this);
		tfH.addFocusListener(this);

		tfN.addActionListener(this);
		tfFu.addActionListener(this);
		tfFo.addActionListener(this);
		tfH.addActionListener(this);

		add(lbFo, new GridBagConstraints(0, // gridx
				1, // gridy
				1, // gridwidth
				1, // gridheigth
				0.0, // weightx
				0.0, // weighty
				GridBagConstraints.EAST, // anchor
				GridBagConstraints.NONE, // fill
				new Insets(0, 0, 0, 0), // insets
				10, // ipadx
				0 // ipady
				));

		add(tfFo, new GridBagConstraints(1, // gridx
				1, // gridy
				1, // gridwidth
				1, // gridheigth
				0.0, // weightx
				0.0, // weighty
				GridBagConstraints.EAST, // anchor
				GridBagConstraints.NONE, // fill
				new Insets(0, 0, 0, 0), // insets
				tfFo.getPreferredSize().width, // ipadx
				0 // ipady
				));

		add(lbFu, new GridBagConstraints(0, // gridx
				2, // gridy
				1, // gridwidth
				1, // gridheigth
				0.0, // weightx
				0.0, // weighty
				GridBagConstraints.EAST, // anchor
				GridBagConstraints.NONE, // fill
				new Insets(0, 0, 0, 0), // insets
				10, // ipadx
				0 // ipady
				));

		add(tfFu, new GridBagConstraints(1, // gridx
				2, // gridy
				1, // gridwidth
				1, // gridheigth
				0.0, // weightx
				0.0, // weighty
				GridBagConstraints.EAST, // anchor
				GridBagConstraints.NONE, // fill
				new Insets(0, 0, 0, 0), // insets
				tfFu.getPreferredSize().width, // ipadx
				0 // ipady
				));
		
		add(lbH, new GridBagConstraints(0, // gridx
				3, // gridy
				1, // gridwidth
				1, // gridheigth
				0.0, // weightx
				0.0, // weighty
				GridBagConstraints.WEST, // anchor
				GridBagConstraints.NONE, // fill
				new Insets(0, 0, 0, 0), // insets
				0, // ipadx
				0 // ipady
				));

		add(tfH, new GridBagConstraints(1, // gridx
				3, // gridy
				1, // gridwidth
				1, // gridheigth
				0.0, // weightx
				0.0, // weighty
				GridBagConstraints.WEST, // anchor
				GridBagConstraints.NONE, // fill
				new Insets(0, 0, 0, 0), // insets
				tfH.getPreferredSize().width, // ipadx
				0 // ipady
				));
		
		add(lbN, new GridBagConstraints(0, // gridx
				4, // gridy
				1, // gridwidth
				1, // gridheigth
				0.0, // weightx
				0.0, // weighty
				GridBagConstraints.WEST, // anchor
				GridBagConstraints.NONE, // fill
				new Insets(0, 0, 0, 0), // insets
				0, // ipadx
				0 // ipady
				));

		add(tfN, new GridBagConstraints(1, // gridx
				4, // gridy
				1, // gridwidth
				1, // gridheigth
				0.0, // weightx
				0.0, // weighty
				GridBagConstraints.WEST, // anchor
				GridBagConstraints.NONE, // fill
				new Insets(0, 0, 0, 0), // insets
				tfN.getPreferredSize().width, // ipadx
				0 // ipady
				));
		
		WebImage imgYieldGoal = new WebImage();

		try {
			imgYieldGoal.setImage(ImageUtil
					.loadResourceImage("yield_goal_80.png"));
		} catch (NullPointerException ex) {
			System.out.println("Could not load Image: yield_goal_80.png");
		}

		imgYieldGoal.setDisplayType(DisplayType.fitComponent);

		add(imgYieldGoal, new GridBagConstraints(4, // gridx
				0, // gridy
				1, // gridwidth
				5, // gridheigth
				0.0, // weightx
				0.0, // weighty
				GridBagConstraints.CENTER, // anchor
				GridBagConstraints.NONE, // fill
				new Insets(0, 0, 0, 0), // insets
				80, // ipadx
				80 // ipady
				));
	}

	public void actionPerformed(ActionEvent ae) {
		controller.viewAction();
	}

	public void focusGained(FocusEvent fe) {

	}

	/**
	 * Part of the <code>FocusListener</code> interface. Is used to fire an
	 * action as soon as a textfield loses focus.
	 */
	public void focusLost(FocusEvent fe) {
		ActionEvent ae = new ActionEvent(this, 0, "focus_action");
		actionPerformed(ae);

	}

}
