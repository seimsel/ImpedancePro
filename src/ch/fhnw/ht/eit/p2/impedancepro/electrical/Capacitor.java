/**
 * 
 */
package ch.fhnw.ht.eit.p2.impedancepro.electrical;

/**
 * @author Simon Zumbrunnen
 *
 */
public class Capacitor extends ElectricalComponent {
	private static final String unit = "F";
	private static final String designator = "C";
	
	public String getDesignator() {
		return designator;
	}
	
	public String getUnit() {
		return unit;
	}
}
