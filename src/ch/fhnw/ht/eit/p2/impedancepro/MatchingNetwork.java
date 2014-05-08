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
	public static final int SER_C_PAR_L = 2122;
	public static final int SER_L_PAR_C = 2211;
	public static final int SER_L_PAR_L = 2212;
	public static final int SER_C = 0021;
	public static final int SER_L = 0022;
	public static final int PAR_C = 1100;
	public static final int PAR_L = 1200;
	
	private int topology;
	public ElectricalComponent[] electricalComponents;
	
	
	public MatchingNetwork() {
		
		electricalComponents = new ElectricalComponent[2];
		
		electricalComponents[0] = new ElectricalComponent();
		electricalComponents[1] = new ElectricalComponent();
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
