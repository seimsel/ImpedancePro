package ch.fhnw.ht.eit.p2.impedancepro;

import ch.fhnw.ht.eit.p2.impedancepro.complex.ComplexNumber;
import ch.fhnw.ht.eit.p2.impedancepro.electrical.ElectricalComponent;

public class SourceLoadNetwork {

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
