/**
 * 
 */
package ch.fhnw.ht.eit.p2.impedancepro.electrical;

import ch.fhnw.ht.eit.p2.impedancepro.complex.ComplexNumber;
import ch.fhnw.ht.eit.p2.impedancepro.util.EngineeringUtil;

/**
 * @author Simon Zumbrunnen
 *
 */
public abstract class ElectricalComponent {

	private double value;
	private float tolerance;
	private ComplexNumber impedance;
	
	public String getDesignator() {
		return null;
	}
	
	public String getUnit() {
		return null;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public String getValueString() {
		return EngineeringUtil.convertToString(value);
	}

	public void setValueString(String valueString) {
		value = EngineeringUtil.convertToDouble(valueString);
	}

	public float getTolerance() {
		return tolerance;
	}

	public void setTolerance(float tolerance) {
		this.tolerance = tolerance;
	}

	public ComplexNumber getImpedance() {
		return impedance;
	}

	public void setImpedance(ComplexNumber impedance) {
		this.impedance = impedance;
	}
}