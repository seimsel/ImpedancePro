package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.alee.extended.panel.WebButtonGroup;
import com.alee.laf.button.WebToggleButton;
import com.alee.managers.tooltip.TooltipManager;
import com.alee.managers.tooltip.TooltipWay;

/**
 * Contains all inputs of a source or a load.
 * 
 * @author Simon Zumbrunnen
 */
public class InputPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	public static final byte SOURCE = 0;
	public static final byte LOAD = 1;

	public ValuePanel valuePanel;
	public FrequencyPanel frequencyPanel;

	private int type;
	private int topology;
	private WebToggleButton[] topologyChooseButtons = new WebToggleButton[6];
	private ImpedanceProController controller;

	/**
	 * Creates an <code>InputPanel</code>
	 * 
	 * @param type
	 *            Can either be "SOURCE" or "LOAD"
	 */
	public InputPanel(int type, ImpedanceProController controller) {
		super();
		this.type = type;
		this.controller = controller;

		valuePanel = new ValuePanel();
		frequencyPanel = new FrequencyPanel();

		setLayout(new GridBagLayout());

		for (int i = 0; i < topologyChooseButtons.length; i++) {
			WebToggleButton btn = new WebToggleButton();
			btn.setFocusable(false);
			btn.addActionListener(this);
			if (this.type == SOURCE) {
				btn.setName(String.valueOf(i));
				try {
					btn.setIcon(new ImageIcon(ImageUtil
							.loadResourceImage("source_" + i + "_30.png")));
				} catch (NullPointerException ex) {
					System.out.println("Could not load Source-Image: source_"
							+ i + "_30.png");
				}
			} else {
				btn.setName(String.valueOf(i));
				try {
					btn.setIcon(new ImageIcon(ImageUtil
							.loadResourceImage("load_" + i + "_30.png")));
				} catch (NullPointerException ex) {
					System.out.println("Could not load Load-Image: load" + i
							+ "_30.png");
				}
			}

			topologyChooseButtons[i] = btn;
		}

		TooltipManager.setTooltip(topologyChooseButtons[0], "R",
				TooltipWay.down, 0);
		TooltipManager.setTooltip(topologyChooseButtons[1], "R+C",
				TooltipWay.down, 0);
		TooltipManager.setTooltip(topologyChooseButtons[2], "R+L",
				TooltipWay.down, 0);
		TooltipManager.setTooltip(topologyChooseButtons[3], "R//C",
				TooltipWay.down, 0);
		TooltipManager.setTooltip(topologyChooseButtons[4], "R//L",
				TooltipWay.down, 0);
		TooltipManager.setTooltip(topologyChooseButtons[5], "Z",
				TooltipWay.down, 0);

		WebButtonGroup topologyChoose = new WebButtonGroup(true,
				topologyChooseButtons);
		topologyChoose.setLayout(new GridLayout());

		setTopology(0);

		add(topologyChoose, new GridBagConstraints(GridBagConstraints.RELATIVE, // gridx
				0, // gridy
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

		if (type == SOURCE) {
			frequencyPanel = new FrequencyPanel();
			add(frequencyPanel, new GridBagConstraints(
					GridBagConstraints.RELATIVE, // gridx
					0, // gridy
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

		add(valuePanel, new GridBagConstraints(GridBagConstraints.RELATIVE, // gridx
				1, // gridy
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
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getTopology() {
		return topology;
	}

	public void setTopology(int topology) {
		WebToggleButton btn = (WebToggleButton) topologyChooseButtons[topology];
		btn.setSelected(true);

		valuePanel.lbValue1.setText("<html><i>R</i>:</html>");
		valuePanel.lbValue1Unit.setText("\u2126");
		valuePanel.lbTolerance1Unit.setText("%");
		valuePanel.lbTolerance2Unit.setText("%");

		valuePanel.lbValue1.setVisible(true);
		valuePanel.tfValue1.setVisible(true);
		valuePanel.lbValue1Unit.setVisible(true);
		valuePanel.tfTolerance1.setVisible(true);
		valuePanel.lbTolerance1Unit.setVisible(true);

		valuePanel.lbValue2.setVisible(true);
		valuePanel.tfValue2.setVisible(true);
		valuePanel.lbValue2Unit.setVisible(true);
		valuePanel.tfTolerance2.setVisible(true);
		valuePanel.lbTolerance2Unit.setVisible(true);

		switch (topology) {
		default:
		case SourceLoadNetwork.R:
			valuePanel.lbValue2.setVisible(false);
			valuePanel.tfValue2.setVisible(false);
			valuePanel.lbValue2Unit.setVisible(false);
			valuePanel.tfTolerance2.setVisible(false);
			valuePanel.lbTolerance2Unit.setVisible(false);
			break;
		case SourceLoadNetwork.R_PAR_C:
		case SourceLoadNetwork.R_SER_C:
			valuePanel.lbValue2.setText("<html><i>C</i></html>:");
			valuePanel.lbValue2Unit.setText("F");
			break;
		case SourceLoadNetwork.R_PAR_L:
		case SourceLoadNetwork.R_SER_L:
			valuePanel.lbValue2.setText("<html><i>L</i></html>:");
			valuePanel.lbValue2Unit.setText("H");
			break;
		case SourceLoadNetwork.Z:
			valuePanel.lbValue1.setText("Re:");
			valuePanel.lbValue1Unit.setVisible(false);
			valuePanel.tfTolerance1.setVisible(false);
			valuePanel.lbTolerance1Unit.setVisible(false);
			valuePanel.lbValue2.setText("Im:");
			valuePanel.lbValue2Unit.setText("\u2126");
			valuePanel.tfTolerance2.setVisible(false);
			valuePanel.lbTolerance2Unit.setVisible(false);
			break;
		}

		if (topology == SourceLoadNetwork.Z) {
			valuePanel.tfValue2.setRange(-1e21, 1e21);
		} else {
			valuePanel.tfValue2.setRange(1e-21, 1e21);
		}

		this.topology = topology;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof WebToggleButton) {
			WebToggleButton btn = (WebToggleButton) e.getSource();
			setTopology(Integer.parseInt(btn.getName()));
		}

		controller.viewAction();
	}

	/**
	 * The <code>ValuePanel</code> class contains all the inputs.
	 * 
	 * @author Simon Zumbrunnen
	 */
	public class ValuePanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public JEngineeringTextField tfValue1, tfValue2, tfTolerance1,
				tfTolerance2;
		public JLabel lbValue1, lbValue2, lbValue1Unit, lbValue2Unit;
		public JLabel lbTolerance1Unit, lbTolerance2Unit;

		public ValuePanel() {

			lbValue1 = new JLabel();
			lbValue2 = new JLabel();

			lbValue1Unit = new JLabel();
			lbValue2Unit = new JLabel();
			lbTolerance1Unit = new JLabel();
			lbTolerance2Unit = new JLabel();

			tfValue1 = new JEngineeringTextField(ImpedanceProView.TF_WIDTH_BIG);
			tfValue2 = new JEngineeringTextField(ImpedanceProView.TF_WIDTH_BIG);
			tfTolerance1 = new JEngineeringTextField(
					ImpedanceProView.TF_WIDTH_SMALL);
			tfTolerance2 = new JEngineeringTextField(
					ImpedanceProView.TF_WIDTH_SMALL);

			tfValue1.addActionListener(InputPanel.this);
			tfValue2.addActionListener(InputPanel.this);
			tfTolerance1.addActionListener(InputPanel.this);
			tfTolerance2.addActionListener(InputPanel.this);

			tfValue1.setRange(1e-21, 1e21);
			tfValue2.setRange(1e-21, 1e21);
			tfTolerance1.setRange(0, 99);
			tfTolerance2.setRange(0, 99);

			add(lbValue1);
			add(tfValue1);
			add(lbValue1Unit);

			add(tfTolerance1);
			add(lbTolerance1Unit);

			add(lbValue2);
			add(tfValue2);
			add(lbValue2Unit);

			add(tfTolerance2);
			add(lbTolerance2Unit);
		}
	}

	/**
	 * The <code>FrequencyPanel</code> class is used to add a frequency input to
	 * the source input.
	 * 
	 * @author Simon Zumbrunnen
	 */
	public class FrequencyPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public JEngineeringTextField tfFrequency;
		private JLabel lbFrequency, lbFrequencyUnit;

		public FrequencyPanel() {
			tfFrequency = new JEngineeringTextField(
					ImpedanceProView.TF_WIDTH_BIG);
			lbFrequency = new JLabel("<html><i>f</i>:</html>");
			lbFrequencyUnit = new JLabel("Hz");

			tfFrequency.addActionListener(InputPanel.this);
			tfFrequency.setRange(1, 100e9);

			add(lbFrequency);
			add(tfFrequency);
			add(lbFrequencyUnit);
		}

	}
}
