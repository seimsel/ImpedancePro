package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class SolutionView extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public SolutionPanel[] solutionPanels;
	
	private int numberOfSolutions;
	private Color[] colors;

	public SolutionView(ImpedanceProController controller) {
		super();
		
		setBorder(BorderFactory.createTitledBorder("Anpass-Netzwerke"));
		setLayout(new GridLayout(1, 0));
		
		numberOfSolutions = 4;
		
		solutionPanels = new SolutionPanel[numberOfSolutions];
		colors = new Color[]{
				ImpedanceProView.LIGHT_BLUE,
				ImpedanceProView.LIGHT_GREEN,
				ImpedanceProView.LIGHT_RED,
				ImpedanceProView.LIGHT_YELLOW
		};
		
		for (int i = 0; i < solutionPanels.length; i++) {
			solutionPanels[i] = new SolutionPanel(colors[i], 0);
			add(solutionPanels[i]);
		}
	}

	public int getNumberOfSolutions() {
		return numberOfSolutions;
	}

	public void setNumberOfSolutions(int numberOfSolutions) {
		this.numberOfSolutions = numberOfSolutions;
	}
	
}
