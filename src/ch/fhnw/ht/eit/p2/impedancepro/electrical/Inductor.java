package ch.fhnw.ht.eit.p2.impedancepro.electrical;

/**
 * @author Simon Zumbrunnen
 */
public class Inductor extends ElectricalComponent {
	private static final String unit="H";
	private static final String designator = "L";
	
	public String getDesignator() {
		return designator;
	}
	public String getUnit() {
		return unit;
	}
}
