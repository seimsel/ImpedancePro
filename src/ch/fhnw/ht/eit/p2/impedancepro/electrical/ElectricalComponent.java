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

	private String designator, unit, valueString;
	private double value, tolerance;
	
	public String getDesignator() {
		return designator;
	}

	public void setDesignator(String designator) {
		this.designator = designator;
	}
	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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
	
	public ComplexNumber getImpedanceAtFrequency(Double frequency) {
		return null;
	}
}