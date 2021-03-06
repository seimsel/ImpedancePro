package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * The <code>InputView</code> combines two <code>InputPanel</code>s.
 * 
 * @author Simon Zumbrunnen
 */
public class InputView extends JPanel {
	private static final long serialVersionUID = 1L;

	public InputPanel sourceInput, loadInput;

	public InputView(ImpedanceProController controller) {
		super();

		setLayout(new GridLayout(1, 0));

		sourceInput = new InputPanel(InputPanel.SOURCE, controller);
		loadInput = new InputPanel(InputPanel.LOAD, controller);

		JPanel sourceInputBorderPanel = new JPanel(new BorderLayout());
		JPanel loadInputBorderPanel = new JPanel(new BorderLayout());

		sourceInputBorderPanel.setBorder(BorderFactory
				.createTitledBorder("Quelle"));
		loadInputBorderPanel
				.setBorder(BorderFactory.createTitledBorder("Last"));

		sourceInputBorderPanel.add(sourceInput, BorderLayout.WEST);
		loadInputBorderPanel.add(loadInput, BorderLayout.WEST);

		add(sourceInputBorderPanel);
		add(loadInputBorderPanel);
	}
}