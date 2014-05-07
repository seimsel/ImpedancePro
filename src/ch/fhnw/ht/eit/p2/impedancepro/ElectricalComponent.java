package ch.fhnw.ht.eit.p2.impedancepro;

import ch.fhnw.ht.eit.p2.impedancepro.complex.ComplexNumber;
import ch.fhnw.ht.eit.p2.impedancepro.util.EngineeringUtil;

/**
 * @author Stephan Fahrni
 */
public class ElectricalComponent {

	public static final int GENERIC = 0;
	public static final int CAPACITOR = 1;
	public static final int INDUCTOR = 2;
	
	private String valueString;
	private double value, tolerance;
	private int type;
	
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
	
	public ComplexNumber getImpedanceAtFrequency(Double frequency) {
		return null;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}