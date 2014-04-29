package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.fhnw.ht.eit.p2.impedancepro.util.ImageUtil;

import com.alee.extended.button.WebSwitch;
import com.alee.extended.image.DisplayType;
import com.alee.extended.image.WebImage;


public class MonteCarloPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public JTextField tfN,tfFu,tfFo,tfH;
	private JLabel lbN,lbFu,lbFo,lbH;
	public WebSwitch btnMonteCarlo; 
	
	
	public MonteCarloPanel(){
		setLayout(new GridBagLayout());
		
		lbN = new JLabel("Anzahl:");
		lbFu = new JLabel("<html>fg<sub>u</sub>: </html>");
		lbFo = new JLabel("<html>fg<sub>o</sub>: </html>");
		lbH = new JLabel("h:");
        
		btnMonteCarlo = new WebSwitch ();
		btnMonteCarlo.setSelected(true, false);
		btnMonteCarlo.setFocusable(false);
        
		tfN = new JTextField(4);
		tfFu = new JTextField(4);
		tfFo = new JTextField(4);
		tfH = new JTextField(4);
		
		GridBagConstraints gbc = new GridBagConstraints();
				
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = 2;
		gbc.ipadx = 10;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		add(btnMonteCarlo, gbc);
		
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		add(lbFo, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(tfFo, gbc);

		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridy = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		add(lbN, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		add(tfN, gbc);
		
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.NONE;
		add(lbFu, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(tfFu, gbc);
		
        WebImage imgYieldGoal = new WebImage();
        
        try {
        	imgYieldGoal.setImage(ImageUtil.loadResourceImage("yield_goal_80.png"));
        } catch(NullPointerException ex) {
        	System.out.println("Could not load Image: yield_goal_80.png");
        }
        
        imgYieldGoal.setDisplayType(DisplayType.fitComponent);
        
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;
        gbc.gridheight = 2;
		add(imgYieldGoal, gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		add(lbH, gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(tfH, gbc);
	}
	

}
