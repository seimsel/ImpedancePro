/**
 * 
 */
package ch.fhnw.ht.eit.p2.impedancepro.electrical;

/**
 * @author Simon Zumbrunnen
 *
 */
public class GenericImpedance extends ElectricalComponent {
	private static final String unit="Ohm";
	private static final String designator = "Z";
	
	public String getDesignator() {
		return designator;
	}
	
	public String getUnit() {
		return unit;
	}
}
