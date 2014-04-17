package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.fhnw.ht.eit.p2.impedancepro.electrical.*;
import ch.fhnw.ht.eit.p2.impedancepro.util.ImageUtil;

import com.alee.extended.image.DisplayType;
import com.alee.extended.image.WebImage;

public class SolutionPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public Color color;

	public SolutionPanel(Color color) {
		super();
		this.color = color;
		
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(color, 2));
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        WebImage matchingNetworkImage = new WebImage();
        
        try {
            matchingNetworkImage.setImage(ImageUtil.loadResourceImage("matching_1_512.png"));
        } catch(NullPointerException ex) {
        	System.out.println("Could not load Solution-Panel-Images");
        }
        
        matchingNetworkImage.setDisplayType(DisplayType.fitComponent);
        
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridheight = 4;
        gbc.gridx = 0;
        
        add(matchingNetworkImage, gbc);
        
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        
        add(new JLabel("C1: "), gbc);
        add(new JLabel("L1: "), gbc);
        add(new JLabel("Q: "), gbc);
        add(new JLabel("B: "), gbc);
        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 2;
        gbc.insets = new Insets(0, 0, 0, 10);
        
        add(new JLabel("10uF"), gbc);
        add(new JLabel("100uF"), gbc);
        add(new JLabel("1"), gbc);
        add(new JLabel("1MHz"), gbc);
        
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 4;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        
        add(new ValuePanel(new ElectricalComponent[]{new Capacitor(), new Inductor()}), gbc);
	}
	
	private class ValuePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		
		private ValuePanel() {
			super();
			setLayout(new GridBagLayout());
		}
		
		public ValuePanel(ElectricalComponent[] components) {
			this();
			
			setBorder(BorderFactory.createTitledBorder("Monte-Carlo"));
			
			GridBagConstraints gbc = new GridBagConstraints();
			
	        gbc.anchor = GridBagConstraints.WEST;
	        gbc.gridx = 0;
	        gbc.gridwidth = components.length;
	        add(new JLabel("Erfüllt: 80%"), gbc);
	        
			for (int i = 0; i < components.length; i++) {
				JLabel lbDesignator = new JLabel(components[i].getDesignator()+":");
				JTextField tfValue = new JTextField(components[i].getValueString(), 4);
				JLabel lbUnit = new JLabel(components[i].getUnit()+" ");
				JTextField tfTolerance = new JTextField(String.valueOf(components[i].getTolerance()*100), 2);
				JLabel lbPercent = new JLabel("%");
				
				
				gbc.anchor = GridBagConstraints.EAST;
				gbc.fill = GridBagConstraints.NONE;
				gbc.gridx = GridBagConstraints.RELATIVE;
				gbc.gridy = i+1;
				gbc.gridwidth = 1;
				add(lbDesignator, gbc);
				
				gbc.anchor = GridBagConstraints.CENTER;
				add(tfValue, gbc);
				
				gbc.anchor = GridBagConstraints.WEST;
				add(lbUnit, gbc);
				
				gbc.anchor = GridBagConstraints.CENTER;
				add(tfTolerance, gbc);
				
				gbc.anchor = GridBagConstraints.WEST;
				add(lbPercent, gbc);
			}
			
		}
		
	}
}
