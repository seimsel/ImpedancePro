package ch.fhnw.ht.eit.p2.impedancepro;


/**
 * @author Stephan Fahrni
 */

public class MatchingNetwork {
	
	public static final byte PAR_C_SER_C = 0;
	public static final byte PAR_C_SER_L = 1;
	public static final byte PAR_L_SER_C = 2;
	public static final byte PAR_L_SER_L = 3;
	public static final byte SER_C_PAR_C = 4;
	public static final byte SER_C_PAR_L = 5;
	public static final byte SER_L_PAR_C = 6;
	public static final byte SER_L_PAR_L = 7;
	public static final byte SER_C = 8;
	public static final byte SER_L = 9;
	public static final byte PAR_C = 10;
	public static final byte PAR_L = 11;
	
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
