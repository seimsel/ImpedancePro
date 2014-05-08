package ch.fhnw.ht.eit.p2.impedancepro;

import java.util.Observable;

/**
 * @author Stephan Fahrni
 */
public class ImpedanceProModel extends Observable {

	private Network network;

	public ImpedanceProModel() {
		network = new Network(this);
	}
	
	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}
	
	public void setChanged() {
		super.setChanged();
	}
}