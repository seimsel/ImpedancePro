package ch.fhnw.ht.eit.p2.impedancepro;


/**
 * @author Stephan Fahrni
 */

public class MatchingNetwork {
	
	public static final byte EMPTY = 0;
	public static final byte PAR = 1;
	public static final byte SER = 2;
	public static final byte C = 1;
	public static final byte L = 2;

	public static final int NONE = 0000;
	public static final int PAR_C_SER_C = 1121;
	public static final int PAR_C_SER_L = 1122;
	public static final int PAR_L_SER_C = 1221;
	public static final int PAR_L_SER_L = 1222;
	public static final int SER_C_PAR_C = 2111;
	public static final int SER_C_PAR_L = 2112;
	public static final int SER_L_PAR_C = 2211;
	public static final int SER_L_PAR_L = 2212;
	public static final int NONE_PAR_C = 11;
	public static final int NONE_PAR_L = 12;
	public static final int NONE_SER_C = 21;
	public static final int NONE_SER_L = 22;
	public static final int PAR_C_NONE = 1100;
	public static final int PAR_L_NONE = 1200;
	public static final int SER_C_NONE = 2100;
	public static final int SER_L_NONE = 2200;
	
	private int topology;
	public ElectricalComponent[] electricalComponents;
	
	
	public MatchingNetwork() {
		
		electricalComponents = new ElectricalComponent[2];
		
		electricalComponents[0] = new ElectricalComponent();
		electricalComponents[1] = new ElectricalComponent();
	}
	
	public MatchingNetwork(ElectricalComponent[] electricalComponents, int topology) {
		setElectricalComponents(electricalComponents);
		setTopology(topology);
	}

	public int getTopology() {
		return topology;
	}

	public void setTopology(int topology) {
		this.topology = topology;
	}

	public ElectricalComponent[] getElectricalComponents() {
		return electricalComponents;
	}

	public void setElectricalComponents(ElectricalComponent[] electricalComponents) {
		this.electricalComponents = electricalComponents;
	}
}
