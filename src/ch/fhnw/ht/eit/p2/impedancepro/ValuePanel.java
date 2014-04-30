package ch.fhnw.ht.eit.p2.impedancepro;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ValuePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public JTextField tfValue1, tfValue2, tfTolerance1, tfTolerance2;
			
	public ValuePanel(int topology) {
		tfValue1 = new JTextField("",5);
		tfValue2 = new JTextField("",5);
		tfTolerance1 = new JTextField("",3);
		tfTolerance2 = new JTextField("",3);
		
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
	
	public void test() {
		
	}
}