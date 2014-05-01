package ch.fhnw.ht.eit.p2.impedancepro;

import ch.fhnw.ht.eit.p2.impedancepro.electrical.ElectricalComponent;

/**
 * @author Stephan Fahrni
 */
public class MatchingNetwork {
	
	private int topology;
	private ElectricalComponent[] electricalComponents;

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
