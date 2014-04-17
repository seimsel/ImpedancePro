package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class SolutionView extends JPanel {
	private static final long serialVersionUID = 1L;

	public SolutionView() {
		super();
		
		setBorder(BorderFactory.createTitledBorder("Anpass-Netzwerke"));
		
		setLayout(new GridLayout(1, 0));
		
		add(new SolutionPanel(ImpedanceProView.BLUE));
		add(new SolutionPanel(ImpedanceProView.GREEN));
		add(new SolutionPanel(ImpedanceProView.RED));
		add(new SolutionPanel(ImpedanceProView.YELLOW));
	}
	
}
