package ch.fhnw.ht.eit.p2.impedancepro;

import ch.fhnw.ht.eit.p2.impedancepro.electrical.ElectricalComponent;

/**
 * @author Stephan Fahrni
 */

public class MatchingNetwork {
	
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
