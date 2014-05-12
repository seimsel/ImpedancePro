package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.fhnw.ht.eit.p2.impedancepro.util.ImageUtil;

public class InformationBar extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	public JLabel lbInfoText;

	private ImpedanceProController controller;
	
	InformationBar(ImpedanceProController controller) {
		super();
		
		this.controller = controller;
		
		setLayout(new GridBagLayout());
		
		lbInfoText = new JLabel("");
		
		JButton btnInfo = new JButton();
		JButton btnHelp = new JButton();
		
		try {
			btnInfo.setIcon(new ImageIcon(ImageUtil
					.loadResourceImage("info_26.png")));
			btnHelp.setIcon(new ImageIcon(ImageUtil
					.loadResourceImage("help_26.png")));
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
		
		add(lbInfoText, new GridBagConstraints(GridBagConstraints.RELATIVE, // gridx
				0, // gridy
				1, // gridwidth
				1, // gridheigth
				1.0, // weightx
				0.0, // weighty
				GridBagConstraints.CENTER, // anchor
				GridBagConstraints.HORIZONTAL, // fill
				new Insets(0, 20, 0, 0), // insets
				0, // ipadx
				0 // ipady
				));
		
		add(btnInfo, new GridBagConstraints(GridBagConstraints.RELATIVE, // gridx
				0, // gridy
				1, // gridwidth
				1, // gridheigth
				0.0, // weightx
				0.0, // weighty
				GridBagConstraints.EAST, // anchor
				GridBagConstraints.NONE, // fill
				new Insets(0, 0, 0, 0), // insets
				0, // ipadx
				0 // ipady
				));
		
		add(btnHelp, new GridBagConstraints(GridBagConstraints.RELATIVE, // gridx
				0, // gridy
				1, // gridwidth
				1, // gridheigth
				0.0, // weightx
				0.0, // weighty
				GridBagConstraints.EAST, // anchor
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
		}
	}
}
