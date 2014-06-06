package ch.fhnw.ht.eit.p2.impedancepro;

/**
 * <pre>
 * An instance of the <code>ElectricalComponent</code> class represents an
 * electrical component (e.g. a capacitor) with a value and a tolerance.
 * </pre>
 * 
 * @author Stephan Fahrni
 */
public class ElectricalComponent {
	
	private double value, tolerance;
	
	ElectricalComponent() {
		setValue(0);
		setTolerance(0);
	}
	
	ElectricalComponent(double value) {
		setValue(value);
	}
	
	ElectricalComponent(double value, double tolerance) {
		this(value);
		setTolerance(tolerance);
	}
	
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getTolerance() {
		return tolerance;
	}

	public void setTolerance(double tolerance) {
		this.tolerance = tolerance;
	}
}