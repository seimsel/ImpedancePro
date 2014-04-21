package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.alee.extended.panel.WebButtonGroup;
import com.alee.laf.button.WebToggleButton;
import com.alee.managers.tooltip.TooltipManager;
import com.alee.managers.tooltip.TooltipWay;

import ch.fhnw.ht.eit.p2.impedancepro.electrical.*;
import ch.fhnw.ht.eit.p2.impedancepro.util.ImageUtil;

public class InputPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JPanel valuePanels;
	private WebToggleButton[] topologyChooseButtons;
	
	public static final byte SOURCE = 0;
	public static final byte LOAD = 1;
	public static final byte NUMBER_OF_SOURCE_LOAD_TOPOLOGIES = 6;

	private int type;
	private int topology;
	private ElectricalComponent electricalComponents[];
	
	public InputPanel () {	
		super();
	}
	
	public InputPanel (int type) {	
		this();
		
		this.type = type;
		
		setLayout(new GridLayout(0, 1));
		
        valuePanels = new JPanel();
        valuePanels.setLayout(new CardLayout());
		
        topologyChooseButtons = new WebToggleButton[NUMBER_OF_SOURCE_LOAD_TOPOLOGIES];
		for(int i=0;i<NUMBER_OF_SOURCE_LOAD_TOPOLOGIES;i++) {
			WebToggleButton btn = new WebToggleButton();
			if(this.type == SOURCE) {
				btn.setName(String.valueOf(i));
		        try {
		            btn.setIcon(new ImageIcon(ImageUtil.loadResourceImage("source_"+i+"_30.png")));
		        } catch(NullPointerException ex) {
		        	System.out.println("Could not load Source-Image: source_"+i+"_30.png");
		        }
			} else {
				btn.setName(String.valueOf(i));
		        try {
		            btn.setIcon(new ImageIcon(ImageUtil.loadResourceImage("load_"+i+"_30.png")));
		        } catch(NullPointerException ex) {
		        	System.out.println("Could not load Source-Image: load_"+i+"_30.png");
		        }
			}
			
			btn.addActionListener(this);	
			btn.setFocusable(false);
			
			switch (i) {
			default:
			case 0:
				TooltipManager.setTooltip (btn, "R", TooltipWay.down, 0);
				valuePanels.add(new ValuePanel(new ElectricalComponent[]{new Resistor()}), btn.getName());
				break;
			case 1:
				TooltipManager.setTooltip (btn, "R+C", TooltipWay.down, 0);
		        valuePanels.add(new ValuePanel(new ElectricalComponent[]{new Resistor(), new Capacitor()}), btn.getName());
				break;			
			case 2:
				TooltipManager.setTooltip (btn, "R//C", TooltipWay.down, 0);
		        valuePanels.add(new ValuePanel(new ElectricalComponent[]{new Resistor(), new Capacitor()}), btn.getName());
				break;
			case 3:
				TooltipManager.setTooltip (btn, "R+L", TooltipWay.down, 0);
		        valuePanels.add(new ValuePanel(new ElectricalComponent[]{new Resistor(), new Inductor()}), btn.getName());
				break;
			case 4:
				TooltipManager.setTooltip (btn, "R//L", TooltipWay.down, 0);
		        valuePanels.add(new ValuePanel(new ElectricalComponent[]{new Resistor(), new Inductor()}), btn.getName());
				break;
			case 5:
				TooltipManager.setTooltip (btn, "Z", TooltipWay.down, 0);
		        valuePanels.add(new ValuePanel(new ElectricalComponent[]{new GenericImpedance()}), btn.getName());
				break;
			}
			
			topologyChooseButtons[i] = btn;
		}
        
        WebButtonGroup topologyChoose = new WebButtonGroup(true, topologyChooseButtons);
        topologyChoose.setLayout(new GridLayout());
        setTopology(0);
        add(topologyChoose);
        add(valuePanels);
    }
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof WebToggleButton) {
			WebToggleButton btn = (WebToggleButton) e.getSource();
	        setTopology(Integer.parseInt(btn.getName()));
		}		
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public int getTopology() {
		return topology;
	}

	public void setTopology(int topology) {
		if(topology>=0 && topology<NUMBER_OF_SOURCE_LOAD_TOPOLOGIES) {
			System.out.println(topology);
	        CardLayout cl = (CardLayout) valuePanels.getLayout();
	        cl.show(valuePanels, String.valueOf(topology));
	        WebToggleButton btn = (WebToggleButton) topologyChooseButtons[topology];
	        btn.setSelected(true);
			this.topology = topology;
		}
	}

	public ElectricalComponent[] getElectricalComponents() {
		return electricalComponents;
	}

	public void setElectricalComponents(ElectricalComponent[] electricalComponents) {
		this.electricalComponents = electricalComponents;
	}

	private class ValuePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		
		private ValuePanel() {
			super();
			setLayout(new GridBagLayout());
		}
		
		public ValuePanel(ElectricalComponent[] components) {
			this();
			
			for (int i = 0; i < components.length; i++) {
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.weightx = 1.0;
				gbc.gridy = 0;
				
				JLabel lbDesignator = new JLabel(components[i].getDesignator()+":");
				JTextField tfValue = new JTextField(components[i].getValueString(), 4);
				JLabel lbUnit = new JLabel(components[i].getUnit()+"  ");
				JTextField tfTolerance = new JTextField(String.valueOf(components[i].getTolerance()*100), 2);
				JLabel lbPercent = new JLabel("%    ");
				
				gbc.anchor = GridBagConstraints.EAST;
				gbc.fill = GridBagConstraints.NONE;
				add(lbDesignator, gbc);
				
				gbc.anchor = GridBagConstraints.CENTER;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				add(tfValue, gbc);
				
				gbc.anchor = GridBagConstraints.WEST;
				gbc.fill = GridBagConstraints.NONE;
				add(lbUnit, gbc);
				
				gbc.anchor = GridBagConstraints.CENTER;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				add(tfTolerance, gbc);
				
				gbc.anchor = GridBagConstraints.WEST;
				gbc.fill = GridBagConstraints.NONE;
				add(lbPercent, gbc);
			}
			
		}
		
	}
}
