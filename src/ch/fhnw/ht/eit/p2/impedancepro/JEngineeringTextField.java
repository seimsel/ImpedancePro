package ch.fhnw.ht.eit.p2.impedancepro;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class JEngineeringTextField extends JTextField implements FocusListener {
	private static final long serialVersionUID = 1L;
		
	private double lowerLimit;
	private double upperLimit;
	
	public JEngineeringTextField() {
		super();
		addFocusListener(this);
		setRange(-1e21, 1e21);
	}
	
	public JEngineeringTextField(int cols) {
		super(cols);
		addFocusListener(this);
		setRange(-1e21, 1e21);
	}

	public double getValue() {
		return EngineeringUtil.parse(getText());
	}

	public void setValue(double value) {
			setText(EngineeringUtil.convert(value, 3));
	}

	public double getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(double lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public double getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(double upperLimit) {
		this.upperLimit = upperLimit;
	}
	
	public void setRange(double lowerLimit, double upperLimit) {
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
	}
	
	public void focusGained(FocusEvent e) {
		selectAll();
	}

	public void focusLost(FocusEvent e) {
		fireActionPerformed();
	}

	protected void fireActionPerformed() {
		if(!getText().isEmpty()) {
			try {
				EngineeringUtil.parse(getText());
				setBackground(Color.WHITE);
				super.fireActionPerformed();
			} catch (Exception e) {
				setBackground(ImpedanceProView.LIGHT_RED);
				requestFocus();
			}
		}
	}
}
