package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.fhnw.ht.eit.p2.impedancepro.util.ImageUtil;

import com.alee.extended.image.DisplayType;
import com.alee.extended.image.WebImage;

public class SolutionPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public JLabel lbValue1;
	public JLabel lbValue2;
	public JLabel lbQ;
	public JLabel lbB;
	
	public SolutionPanel(Color color, int topology) {
		super();
		
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(color, 2));
        
        lbValue1 = new JLabel("100mH");
        lbValue2 = new JLabel("200uF");
        lbQ = new JLabel("2");
        lbB = new JLabel("20");
        
        WebImage matchingNetworkImage = new WebImage();
        
        try {
            matchingNetworkImage.setImage(ImageUtil.loadResourceImage("matching_"+topology+"_512.png"));
        } catch(NullPointerException ex) {
        	System.out.println("Could not load Solution-Panel-Image: matching_"+topology+"_512.png");
        }
        
        matchingNetworkImage.setDisplayType(DisplayType.fitComponent);
        
        add(matchingNetworkImage, new GridBagConstraints(
				GridBagConstraints.RELATIVE,	//gridx
                0,								//gridy
                1,								//gridwidth
                4,								//gridheigth
                1.0,							//weightx
                1.0,							//weighty
                GridBagConstraints.CENTER,		//anchor
                GridBagConstraints.BOTH,		//fill
                new Insets(0, 0, 0, 0),			//insets
                0,								//ipadx
                0								//ipady
        ));
        
        switch (topology) {
        	default:
        	case 0:
        	case 4:
                addToRow(new JLabel("C1: "), 0, GridBagConstraints.EAST);
                addToRow(lbValue1, 0, GridBagConstraints.WEST);
                addToRow(new JLabel("C2 :"), 1, GridBagConstraints.EAST);
                addToRow(lbValue2, 1, GridBagConstraints.WEST);
                break;
                
        	case 1:
        	case 2:
        	case 5:
        	case 6:
                addToRow(new JLabel("C1: "), 0, GridBagConstraints.EAST);
                addToRow(lbValue1, 0, GridBagConstraints.WEST);
                addToRow(new JLabel("L1: "), 1, GridBagConstraints.EAST);
                addToRow(lbValue2, 1, GridBagConstraints.WEST);
                break;
                
        	case 3:
        	case 7:
                addToRow(new JLabel("L1: "), 0, GridBagConstraints.EAST);
                addToRow(lbValue1, 0, GridBagConstraints.WEST);
                addToRow(new JLabel("L2: "), 1, GridBagConstraints.EAST);
                addToRow(lbValue2, 1, GridBagConstraints.WEST);
                break;
        }
        

        addToRow(new JLabel("Q: "), 2, GridBagConstraints.EAST);
        addToRow(lbQ, 2, GridBagConstraints.WEST);
        addToRow(new JLabel("B: "), 3, GridBagConstraints.EAST);
        addToRow(lbB, 3, GridBagConstraints.WEST);
        
        addToRow(new JLabel("  "), 3, GridBagConstraints.EAST);
        
        ValuePanel valuePanel = new ValuePanel(topology);
        valuePanel.setBorder(BorderFactory.createTitledBorder("Monte-Carlo"));
        
        add(valuePanel, new GridBagConstraints(
				GridBagConstraints.RELATIVE,	//gridx
                4,								//gridy
                4,								//gridwidth
                1,								//gridheigth
                1.0,							//weightx
                0.0,							//weighty
                GridBagConstraints.WEST,		//anchor
                GridBagConstraints.HORIZONTAL,	//fill
                new Insets(0, 0, 0, 0),			//insets
                0,								//ipadx
                0								//ipady
        ));
	}
	
	private void addToRow(Component comp, int row, int anchor) {
		add(comp, new GridBagConstraints(
				GridBagConstraints.RELATIVE,	//gridx
                row,							//gridy
                1,								//gridwidth
                1,								//gridheigth
                0.0,							//weightx
                1.0,							//weighty
                anchor,							//anchor
                GridBagConstraints.VERTICAL,	//fill
                new Insets(0, 0, 0, 0),			//insets
                0,								//ipadx
                0								//ipady
        ));
	}
	
	private class ValuePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		
		public JTextField tfValue1;
		public JTextField tfValue2;
		public JTextField tfTolerance1;
		public JTextField tfTolerance2;
				
		public ValuePanel(int topology) {
			setLayout(new GridBagLayout());
			
			tfValue1 = new JTextField("", 5);
			tfValue2 = new JTextField("", 5);
			tfTolerance1 = new JTextField("", 3);
			tfTolerance2 = new JTextField("", 3);
			
			switch (topology) {
			default:
				
			case 0:
			case 4:
				addToRow(new JLabel("C1:"), 0);
				addToRow(tfValue1, 0);
				addToRow(new JLabel("F"), 0);
				addToRow(tfTolerance1, 0);
				addToRow(new JLabel("%"), 0);
				addToRow(new JLabel("C2:"), 1);
				addToRow(tfValue2, 1);
				addToRow(new JLabel("F"), 1);
				addToRow(tfTolerance2, 1);
				addToRow(new JLabel("%"), 1);
				break;
				
			case 1:
			case 2:
			case 5:
			case 6:
				addToRow(new JLabel("C1:"), 0);
				addToRow(tfValue1, 0);
				addToRow(new JLabel("F"), 0);
				addToRow(tfTolerance1, 0);
				addToRow(new JLabel("%"), 0);
				addToRow(new JLabel("L1:"), 1);
				addToRow(tfValue2, 1);
				addToRow(new JLabel("H"), 1);
				addToRow(tfTolerance2, 1);
				addToRow(new JLabel("%"), 1);
				break;
				
			case 3:
			case 7:
				addToRow(new JLabel("L1:"), 0);
				addToRow(tfValue1, 0);
				addToRow(new JLabel("H"), 0);
				addToRow(tfTolerance1, 0);
				addToRow(new JLabel("%"), 0);
				addToRow(new JLabel("L2:"), 1);
				addToRow(tfValue2, 1);
				addToRow(new JLabel("H"), 1);
				addToRow(tfTolerance2, 1);
				addToRow(new JLabel("%"), 1);
				break;
			}
		}
		
		private void addToRow(Component comp, int row) {
			add(comp, new GridBagConstraints(
					GridBagConstraints.RELATIVE,	//gridx
	                row,							//gridy
	                1,								//gridwidth
	                1,								//gridheigth
	                0.0,							//weightx
	                0.0,							//weighty
	                GridBagConstraints.WEST,		//anchor
	                GridBagConstraints.NONE,		//fill
	                new Insets(0, 0, 0, 0),			//insets
	                0,								//ipadx
	                0								//ipady
	        ));
		}
	}
}