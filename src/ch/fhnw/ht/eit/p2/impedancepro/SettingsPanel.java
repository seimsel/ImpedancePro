package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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

	String[] returnLossStrings = { "R", "SWR" };

	public WebIncDecButton btnSpan;
	public JComboBox<String> cbReturnLoss;
	public WebSwitch btnMonteCarlo;

	private JLabel lbReturnLoss;
	private JLabel lbMonteCarlo;
	private JButton btnInfo;
	private JButton btnHelp;
	private ImpedanceProController controller;

	public SettingsPanel(ImpedanceProController controller) {
		super();
		
		this.controller = controller;
		
		setLayout(new GridBagLayout());
		
		btnSpan = new WebIncDecButton();
		btnSpan.setValue(2);
		btnSpan.setMaxValue(5);
		btnSpan.setMinValue(1);
		btnSpan.addActionListener(this);

		lbReturnLoss = new JLabel("Reflexion:");
		cbReturnLoss = new JComboBox<String>(returnLossStrings);

		cbReturnLoss.setFocusable(false);
		cbReturnLoss.addActionListener(this);
				
		btnMonteCarlo = new WebSwitch();
		btnMonteCarlo.setSelected(true, false);
		btnMonteCarlo.setFocusable(false);
		btnMonteCarlo.addActionListener(this);
		
		lbMonteCarlo = new JLabel("Monte-Carlo:");
		
		btnInfo = new JButton();
		btnHelp = new JButton();
		
		try {
			btnInfo.setIcon(new ImageIcon(ImageUtil
					.loadResourceImage("info_20.png")));
			btnHelp.setIcon(new ImageIcon(ImageUtil
					.loadResourceImage("help_20.png")));
		} catch (NullPointerException ex) {
			System.out.println("Could not load Icons");
		}
		
		btnInfo.setFocusable(false);
		btnInfo.setContentAreaFilled(false);
		btnInfo.setBorderPainted(false);
		btnInfo.addActionListener(this);
		btnInfo.setName("info");
		
		btnHelp.setFocusable(false);
		btnHelp.setContentAreaFilled(false);
		btnHelp.setBorderPainted(false);
		btnHelp.addActionListener(this);
		btnHelp.setName("help");
		
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
		
		add(new JLabel("Span: "), new GridBagConstraints(0, // gridx
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
		
		add(btnSpan, new GridBagConstraints(1, // gridx
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
		
		add(btnInfo, new GridBagConstraints(0, // gridx
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
		
		add(btnHelp, new GridBagConstraints(1, // gridx
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
		
		if(e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();		
			if(btn.getName() == "info") {
				controller.openInfoPDF();
			} else if(btn.getName() == "help") {
				controller.openHelpPDF();
			}
		} else if(e.getSource() instanceof WebSwitch) {
			WebSwitch sw = (WebSwitch) e.getSource();
			controller.displayMonteCarlo(sw.isSelected());
			controller.viewAction();
		} else if(e.getSource() instanceof JComboBox) {
			JComboBox<?> cb = (JComboBox<?>) e.getSource();
			controller.setGraphType(cb.getSelectedIndex());
			controller.viewAction();
		} else if(e.getSource() instanceof WebIncDecButton) {
			controller.viewAction();
		}
	}
}
