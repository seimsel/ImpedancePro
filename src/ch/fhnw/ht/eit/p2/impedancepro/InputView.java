package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputView extends JPanel {
	private static final long serialVersionUID = 1L;

	public InputView () {
		super();
		
		setLayout(new GridLayout(1, 0));
		
		InputPanel sourceInput = new InputPanel(InputPanel.SOURCE);
		InputPanel loadInput = new InputPanel(InputPanel.LOAD);
		
		JPanel sourceInputBorderPanel = new JPanel(new BorderLayout());
		JPanel loadInputBorderPanel = new JPanel(new BorderLayout());
		
		sourceInput.add(new FrequencyPanel());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridy = 0;
		
		sourceInputBorderPanel.setBorder(BorderFactory.createTitledBorder("Quelle"));
		loadInputBorderPanel.setBorder(BorderFactory.createTitledBorder("Last"));
		
		sourceInputBorderPanel.add(sourceInput, BorderLayout.NORTH);
		loadInputBorderPanel.add(loadInput, BorderLayout.NORTH);
		
		add(sourceInputBorderPanel);
		add(loadInputBorderPanel);
	}
	
	private class FrequencyPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		private FrequencyPanel() {
			super();
			
			setLayout(new GridBagLayout());
			
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.weightx = 1.0;
			gbc.gridy = 0;
			
			gbc.anchor = GridBagConstraints.EAST;
			gbc.fill = GridBagConstraints.NONE;
			add(new JLabel("f:"), gbc);
			
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			add(new JTextField("100k", 4), gbc);
			
			gbc.anchor = GridBagConstraints.WEST;
			gbc.fill = GridBagConstraints.NONE;
			add(new JLabel("Hz"), gbc);
		}
	}
	
}