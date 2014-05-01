package ch.fhnw.ht.eit.p2.impedancepro;

import ch.fhnw.ht.eit.p2.impedancepro.complex.ComplexNumber;
import ch.fhnw.ht.eit.p2.impedancepro.electrical.ElectricalComponent;

/**
 * @author Stephan Fahrni
 */
public class SourceLoadNetwork {
	public static final byte R = 0;
	public static final byte R_SER_C = 1;
	public static final byte R_SER_L = 2;
	public static final byte R_PAR_C = 3;
	public static final byte R_PAR_L = 4;
	public static final byte Z = 5;
	
	private int topology;
	private ComplexNumber impedance;
	private ElectricalComponent[] electricalComponents;
	
	public int getTopology() {
		return topology;
	}
	
	public void setTopology(int topology) {
		this.topology = topology;
	}
	
	public ComplexNumber getImpedance() {
		return impedance;
	}
	
	public void setImpedance(ComplexNumber impedance) {
		this.impedance = impedance;
	}

	public ElectricalComponent[] getElectricalComponents() {
		return electricalComponents;
	}

	public void setElectricalComponents(ElectricalComponent[] electricalComponents) {
		this.electricalComponents = electricalComponents;
	}
}
