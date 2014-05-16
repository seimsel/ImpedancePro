package ch.fhnw.ht.eit.p2.impedancepro;

import java.util.Observable;

/**
 * @author Stephan Fahrni
 */
public class ImpedanceProModel extends Observable {

	private Network network;
	
	private double upperFrequency;
	private double lowerFrequency;
	private double h;

	public ImpedanceProModel() {
		setNetwork(new Network());
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

	public void triggerCalculations(final SourceLoadNetwork sourceNetwork,
			final MatchingNetwork[] monteCarloMatchingNetworks,
			final SourceLoadNetwork loadNetwork, final double frequency,
			final double lowerFrequency, final double upperFrequency,
			final double h, final int n, final double yieldGoalSpan,
			final boolean monteCarloEnabled) {
		
		Thread calculationEngine = new Thread() {
			public void run() {
				setUpperFrequency(upperFrequency);
				setLowerFrequency(lowerFrequency);
				setH(h);
				
				getNetwork().calculateMatchingNetworks(sourceNetwork, loadNetwork,
						frequency);
				getNetwork().calculateReturnLossOfAllSolutions(
						frequency * (1 - yieldGoalSpan),
						frequency * (1 + yieldGoalSpan));

				if (monteCarloEnabled) {
					MatchingNetwork[] matchingNetworks = getNetwork()
							.getMatchingNetworks().clone();
					
					for (int i = 0; i < matchingNetworks.length; i++) {
						if (monteCarloMatchingNetworks[i].getElectricalComponents()[0]
								.getValue() >= 0) {
							matchingNetworks[i].getElectricalComponents()[0]
									.setValue(monteCarloMatchingNetworks[i]
											.getElectricalComponents()[0].getValue());
						}

						if (monteCarloMatchingNetworks[i].getElectricalComponents()[1]
								.getValue() >= 0) {
							matchingNetworks[i].getElectricalComponents()[1]
									.setValue(monteCarloMatchingNetworks[i]
											.getElectricalComponents()[1].getValue());
						}

						if (monteCarloMatchingNetworks[i].getElectricalComponents()[0]
								.getTolerance() >= 0) {
							matchingNetworks[i].getElectricalComponents()[0]
									.setTolerance(monteCarloMatchingNetworks[i]
											.getElectricalComponents()[0]
											.getTolerance());
						}

						if (monteCarloMatchingNetworks[i].getElectricalComponents()[1]
								.getTolerance() >= 0) {
							matchingNetworks[i].getElectricalComponents()[1]
									.setTolerance(monteCarloMatchingNetworks[i]
											.getElectricalComponents()[1]
											.getTolerance());
						}
					}

					getNetwork().calculateMonteCarlo(matchingNetworks,
							lowerFrequency, upperFrequency, h, n);
				} else {
					getNetwork().setMonteCarloResults(null);
				}
				
				setChanged();
				notifyObservers();
			}
		};
		
		calculationEngine.start();
	}

	public double getUpperFrequency() {
		return upperFrequency;
	}

	public void setUpperFrequency(double upperFrequency) {
		this.upperFrequency = upperFrequency;
	}

	public double getLowerFrequency() {
		return lowerFrequency;
	}

	public void setLowerFrequency(double lowerFrequency) {
		this.lowerFrequency = lowerFrequency;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}
}