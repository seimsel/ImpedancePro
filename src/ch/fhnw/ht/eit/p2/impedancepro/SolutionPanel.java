package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.fhnw.ht.eit.p2.impedancepro.util.ImageUtil;

import com.alee.extended.image.DisplayType;
import com.alee.extended.image.WebImage;

/**
 * The <code>SolutionPanel</code> displays the solution with an image and
 * numerical values.
 * 
 * @author Simon Zumbrunnen
 */
public class SolutionPanel extends JPanel implements ActionListener, FocusListener {
	private static final long serialVersionUID = 1L;

	public JLabel lbValue1, lbValue2;
	public ValuePanel valuePanel;
	
	private ImpedanceProController controller;
	private int topology;
	private JLabel lbDesignator1, lbDesignator2, lbUnit1, lbUnit2;
	
	private WebImage matchingNetworkImage;

	public SolutionPanel(Color color, int topology,
			ImpedanceProController controller) {
		super();

		matchingNetworkImage = new WebImage();
		matchingNetworkImage.setDisplayType(DisplayType.fitComponent);
		
		lbDesignator1 = new JLabel();
		lbDesignator2 = new JLabel();
		lbValue1 = new JLabel();
		lbValue2 = new JLabel();
		lbUnit1 = new JLabel();
		lbUnit2 = new JLabel();
		
		this.setTopology(topology);
		this.controller = controller;
		
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createLineBorder(color, 2));

		add(matchingNetworkImage, new GridBagConstraints(
				GridBagConstraints.RELATIVE, // gridx
				0, // gridy
				1, // gridwidth
				4, // gridheigth
				1.0, // weightx
				1.0, // weighty
				GridBagConstraints.CENTER, // anchor
				GridBagConstraints.BOTH, // fill
				new Insets(0, 0, 0, 0), // insets
				0, // ipadx
				50 // ipady
				));
		
		addToRow(lbDesignator1, 1, GridBagConstraints.EAST);
		addToRow(lbValue1, 1, GridBagConstraints.EAST);
		addToRow(lbUnit1, 1, GridBagConstraints.WEST);
		addToRow(lbDesignator2, 2, GridBagConstraints.EAST);
		addToRow(lbValue2, 2, GridBagConstraints.EAST);
		addToRow(lbUnit2, 2, GridBagConstraints.WEST);

		addToRow(new JLabel("  "), 1, GridBagConstraints.EAST);

		valuePanel = new ValuePanel(topology);
		valuePanel.tfValue1.addFocusListener(this);
		valuePanel.tfValue2.addFocusListener(this);
		valuePanel.tfTolerance1.addFocusListener(this);
		valuePanel.tfTolerance2.addFocusListener(this);
		
		valuePanel.tfValue1.addActionListener(this);
		valuePanel.tfValue2.addActionListener(this);
		valuePanel.tfTolerance1.addActionListener(this);
		valuePanel.tfTolerance2.addActionListener(this);
		
		valuePanel.setBorder(BorderFactory.createTitledBorder("Monte-Carlo"));

		add(valuePanel, new GridBagConstraints(GridBagConstraints.RELATIVE, // gridx
				4, // gridy
				5, // gridwidth
				1, // gridheigth
				1.0, // weightx
				0.0, // weighty
				GridBagConstraints.WEST, // anchor
				GridBagConstraints.HORIZONTAL, // fill
				new Insets(0, 0, 0, 0), // insets
				0, // ipadx
				0 // ipady
				));
	}

	/**
	 * Simplifies the process of adding components to the layout so the
	 * parameters that stay the same don't have to be typed twice.
	 * 
	 * @param comp
	 *            The component to add
	 * @param row
	 *            The row of the grid (gridy)
	 * @param anchor
	 *            The anchor
	 */
	private void addToRow(Component comp, int row, int anchor) {
		add(comp, new GridBagConstraints(GridBagConstraints.RELATIVE, // gridx
				row, // gridy
				1, // gridwidth
				1, // gridheigth
				0.0, // weightx
				1.0, // weighty
				anchor, // anchor
				GridBagConstraints.VERTICAL, // fill
				new Insets(0, 0, 0, 0), // insets
				0, // ipadx
				0 // ipady
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

	public int getTopology() {
		return topology;
	}

	public void setTopology(int topology) {
		this.topology = topology;
		
		try {
			matchingNetworkImage.setImage(ImageUtil
					.loadResourceImage("matching_" + topology + "_512.png"));
		} catch (NullPointerException ex) {
			System.out.println("Could not load Solution-Panel-Image: matching_"
					+ topology + "_512.png");
		}
		
		switch (topology) {
		default:
		case 0000:
			lbDesignator1.setText(" ");
			lbDesignator2.setText(" ");
			lbUnit1.setText(" ");
			lbUnit2.setText(" ");
			break;
		case 0021:
		case 1100:
			lbDesignator1.setText("C1: ");
			lbDesignator2.setText(" ");
			lbUnit1.setText("F");
			lbUnit2.setText(" ");
			break;
		case 0022:
		case 1200:
			lbDesignator1.setText("L1: ");
			lbDesignator2.setText(" ");
			lbUnit1.setText("H");
			lbUnit2.setText(" ");
			break;
		case 1122:
		case 1221:
		case 2112:
		case 2211:
			lbDesignator1.setText("C1: ");
			lbDesignator2.setText("L1: ");
			lbUnit1.setText("F");
			lbUnit2.setText("H");
			break;
		case 1222:
		case 2212:
			lbDesignator1.setText("L1: ");
			lbDesignator2.setText("L2: ");
			lbUnit1.setText("H");
			lbUnit2.setText("H");
			break;
		case 1121:
		case 2111:
			lbDesignator1.setText("C1: ");
			lbDesignator2.setText("C2: ");
			lbUnit1.setText("F");
			lbUnit2.setText("F");
			break;
		}
	}

	/**
	 * The <code>ValuePanel</code> class contains additional inputs for the
	 * monte-carlo simulation.
	 * 
	 * @author Simon Zumbrunnen
	 */
	public class ValuePanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public JEngineeringTextField tfValue1;
		public JEngineeringTextField tfValue2;
		public JEngineeringTextField tfTolerance1;
		public JEngineeringTextField tfTolerance2;
		public JLabel lbMonteCarlo;

		public ValuePanel(int topology) {
			setLayout(new GridBagLayout());

			tfValue1 = new JEngineeringTextField(5);
			tfValue2 = new JEngineeringTextField(5);
			tfTolerance1 = new JEngineeringTextField(3);
			tfTolerance2 = new JEngineeringTextField(3);
			lbMonteCarlo = new JLabel();

			add(new JLabel("Erfüllt: "), new GridBagConstraints(
					GridBagConstraints.RELATIVE, // gridx
					0, // gridy
					2, // gridwidth
					1, // gridheigth
					0.0, // weightx
					0.0, // weighty
					GridBagConstraints.EAST, // anchor
					GridBagConstraints.NONE, // fill
					new Insets(0, 0, 0, 0), // insets
					0, // ipadx
					0 // ipady
					));

			add(lbMonteCarlo, new GridBagConstraints(
					GridBagConstraints.RELATIVE, // gridx
					0, // gridy
					3, // gridwidth
					1, // gridheigth
					0.0, // weightx
					0.0, // weighty
					GridBagConstraints.WEST, // anchor
					GridBagConstraints.NONE, // fill
					new Insets(0, 0, 0, 0), // insets
					0, // ipadx
					0 // ipady
					));

			switch (topology) {
			default:

			case 0:
			case 4:
				addToRow(new JLabel("C1:"), 1);
				addToRow(tfValue1, 1);
				addToRow(new JLabel("F"), 1);
				addToRow(tfTolerance1, 1);
				addToRow(new JLabel("%"), 1);
				addToRow(new JLabel("C2:"), 2);
				addToRow(tfValue2, 2);
				addToRow(new JLabel("F"), 2);
				addToRow(tfTolerance2, 2);
				addToRow(new JLabel("%"), 2);
				break;

			case 1:
			case 2:
			case 5:
			case 6:
				addToRow(new JLabel("C1:"), 1);
				addToRow(tfValue1, 1);
				addToRow(new JLabel("F"), 1);
				addToRow(tfTolerance1, 1);
				addToRow(new JLabel("%"), 1);
				addToRow(new JLabel("L1:"), 2);
				addToRow(tfValue2, 2);
				addToRow(new JLabel("H"), 2);
				addToRow(tfTolerance2, 2);
				addToRow(new JLabel("%"), 2);
				break;

			case 3:
			case 7:
				addToRow(new JLabel("L1:"), 1);
				addToRow(tfValue1, 1);
				addToRow(new JLabel("H"), 1);
				addToRow(tfTolerance1, 1);
				addToRow(new JLabel("%"), 1);
				addToRow(new JLabel("L2:"), 2);
				addToRow(tfValue2, 2);
				addToRow(new JLabel("H"), 2);
				addToRow(tfTolerance2, 2);
				addToRow(new JLabel("%"), 2);
				break;
			}
		}

		/**
		 * Simplifies the process of adding components to the layout so the
		 * parameters that stay the same don't have to be typed twice.
		 * 
		 * @param comp
		 *            The component to add
		 * @param row
		 *            The row of the grid (gridy)
		 */
		private void addToRow(Component comp, int row) {
			add(comp, new GridBagConstraints(GridBagConstraints.RELATIVE, // gridx
					row, // gridy
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
		}
	}
}