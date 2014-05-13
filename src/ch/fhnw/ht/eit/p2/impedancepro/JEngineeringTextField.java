package ch.fhnw.ht.eit.p2.impedancepro;

import java.awt.Color;

import javax.swing.JTextField;

public class JEngineeringTextField extends JTextField {
	private static final long serialVersionUID = 1L;
		
	private double lowerLimit;
	private double upperLimit;
	
	public JEngineeringTextField() {
		super();
		setRange(-1e21, 1e21);
	}
	
	public JEngineeringTextField(int cols) {
		super(cols);
		setRange(-1e21, 1e21);
	}

	public double getValue() {
		return EngineeringUtil.parse(getText());
	}

	public void setValue(double value) {
		try {
			setText(EngineeringUtil.convert(value, 3));
			setBackground(Color.WHITE);
		} catch (Exception e) {
			setText(getText());
			setBackground(ImpedanceProView.LIGHT_RED);
		}
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
}
