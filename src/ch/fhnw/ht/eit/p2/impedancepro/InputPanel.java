package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.fhnw.ht.eit.p2.impedancepro.util.ImageUtil;

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

	public FrequencyPanel frequencyPanel;

	private int type;
	private int topology;
	private ValuePanel[] valuePanels;
	private JPanel valuePanel;
	private WebToggleButton[] topologyChooseButtons = new WebToggleButton[6];

	/**
	 * Creates an <code>InputPanel</code>
	 * 
	 * @param type
	 *            Can either be "SOURCE" or "LOAD"
	 */
	public InputPanel(int type) {
		super();
		this.type = type;

		setLayout(new GridBagLayout());

		valuePanels = new ValuePanel[6];
		valuePanel = new JPanel(new CardLayout());

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
			valuePanels[i] = new ValuePanel(i);
			valuePanel.add(valuePanels[i], String.valueOf(i));
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

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof WebToggleButton) {
			WebToggleButton btn = (WebToggleButton) e.getSource();
			setTopology(Integer.parseInt(btn.getName()));
		}
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
		CardLayout cl = (CardLayout) valuePanel.getLayout();
		cl.show(valuePanel, String.valueOf(topology));

		WebToggleButton btn = (WebToggleButton) topologyChooseButtons[topology];
		btn.setSelected(true);

		this.topology = topology;
	}

	public ValuePanel getActiveValuePanel() {
		return valuePanels[topology];
	}

	/**
	 * The <code>ValuePanel</code> class contains all the inputs.
	 * 
	 * @author Simon Zumbrunnen
	 */
	public class ValuePanel extends JPanel {
		private static final long serialVersionUID = 1L;

		public JTextField tfValue1, tfValue2, tfTolerance1, tfTolerance2;

		public ValuePanel(int topology) {
			tfValue1 = new JTextField("", 5);
			tfValue2 = new JTextField("", 5);
			tfTolerance1 = new JTextField("", 3);
			tfTolerance2 = new JTextField("", 3);

			switch (topology) {
			default:

			case 0:
				add(new JLabel("R:"));
				add(tfValue1);
				add(new JLabel("Ohm"));
				add(tfTolerance1);
				add(new JLabel("%"));
				break;

			case 1:
			case 3:
				add(new JLabel("R:"));
				add(tfValue1);
				add(new JLabel("Ohm"));
				add(tfTolerance1);
				add(new JLabel("%"));
				add(new JLabel("C:"));
				add(tfValue2);
				add(new JLabel("F"));
				add(tfTolerance2);
				add(new JLabel("%"));
				break;

			case 2:
			case 4:
				add(new JLabel("R:"));
				add(tfValue1);
				add(new JLabel("Ohm"));
				add(tfTolerance1);
				add(new JLabel("%"));
				add(new JLabel("L:"));
				add(tfValue2);
				add(new JLabel("H"));
				add(tfTolerance2);
				add(new JLabel("%"));
				break;

			case 5:
				add(new JLabel("Re:"));
				add(tfValue1);
				add(new JLabel("Im:"));
				add(tfValue2);
				add(new JLabel("Ohm"));
				add(tfTolerance1);
				add(new JLabel("%"));
				break;
			}
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

		public JTextField tfFrequency;

		public FrequencyPanel() {
			tfFrequency = new JTextField("", 5);
			add(new JLabel("f:"));
			add(tfFrequency);
			add(new JLabel("Hz"));
		}

	}
}
