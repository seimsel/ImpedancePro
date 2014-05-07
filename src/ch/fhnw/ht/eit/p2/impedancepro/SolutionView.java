package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * The <code>SolutionView</code> class combines up to 4
 * <code>SolutionPanel</code>s.
 * 
 * @author Simon Zumbrunnen
 */
public class SolutionView extends JPanel {
	private static final long serialVersionUID = 1L;

	private SolutionPanel[] solutionPanels;

	public SolutionView(ImpedanceProController controller) {
		super();

		setBorder(BorderFactory.createTitledBorder("Anpass-Netzwerke"));
		setLayout(new GridLayout(1, 0));
	}

	public SolutionPanel[] getSolutionPanels() {
		return solutionPanels;
	}

	public void setSolutionPanels(SolutionPanel[] solutionPanels) {
		this.solutionPanels = solutionPanels;
		
		for (int i = 0; i < solutionPanels.length; i++) {
			removeAll();
			add(solutionPanels[i]);
			revalidate();
			repaint();
		}
	}
	
	
}
