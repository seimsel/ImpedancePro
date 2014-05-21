package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.alee.extended.panel.WebButtonGroup;
import com.alee.laf.button.WebButton;

public class WebIncDecButton extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private int value;
	private int maxValue;
	private int minValue;
	
	private WebButton btnInc = new WebButton ("+");
    private WebButton btnDec = new WebButton ("-");
    
    private ArrayList<ActionListener> actionListeners;
	
	public WebIncDecButton() {
		super();
		
		actionListeners = new ArrayList<ActionListener>();
		
        btnInc = new WebButton ("+");
        btnDec = new WebButton ("-");
        WebButtonGroup incDecGroup = new WebButtonGroup (true, btnDec, btnInc);
        
        btnInc.addActionListener(this);
        btnDec.addActionListener(this);
        btnInc.setFocusable(false);
        btnDec.setFocusable(false);
        
        setValue(0);
        setMinValue(Integer.MIN_VALUE);
        setMaxValue(Integer.MAX_VALUE);
        
        add(incDecGroup);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof WebButton) {
			WebButton btn = (WebButton) e.getSource();
			
			if(btn.getText() == "+") {
				if(getValue() < getMaxValue()) {
					setValue(getValue()+1);
				}
			} else if(btn.getText() == "-") {
				if(getValue() > getMinValue()) {
					setValue(getValue()-1);
				}
			}
		}
		
		if(getActionListeners() != null) {
			for(ActionListener a: getActionListeners()) {
				e.setSource(this);
			    a.actionPerformed(e);
			}
		}
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		if(value > getMaxValue()) {
			this.value = getMaxValue();
		} else if(value < getMinValue()) {
			this.value = getMinValue();
		} else {
			this.value = value;
		}
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}
	
	public void addActionListener(ActionListener a) {
		actionListeners.add(a);
	}
	
	public ArrayList<ActionListener> getActionListeners() {
		return actionListeners;
	}
}
