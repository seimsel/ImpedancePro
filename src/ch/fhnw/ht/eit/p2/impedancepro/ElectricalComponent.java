package ch.fhnw.ht.eit.p2.impedancepro;

import ch.fhnw.ht.eit.p2.impedancepro.util.EngineeringUtil;

/**
 * @author Stephan Fahrni
 */
public class ElectricalComponent {
	
	private String valueString;
	private double value, tolerance;
	
	ElectricalComponent() {
	}
	
	ElectricalComponent(String valueString) {
		setValueString(valueString);
	}
	
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
		this.valueString = EngineeringUtil.convert(value, 2);
	}
	
	public String getValueString() {
		return valueString;
	}
	
	public void setValueString(String valueString) {
		this.value = EngineeringUtil.parse(valueString);
		this.valueString = EngineeringUtil.convert(value, 2);
	}

	public double getTolerance() {
		return tolerance;
	}

	public void setTolerance(double tolerance) {
		this.tolerance = tolerance;
	}
}