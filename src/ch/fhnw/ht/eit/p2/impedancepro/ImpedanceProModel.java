package ch.fhnw.ht.eit.p2.impedancepro;

import java.util.Observable;

/**
 * @author Stephan Fahrni
 */
public class ImpedanceProModel extends Observable implements Runnable {

	private Network network;

	private SourceLoadNetwork sourceNetwork, loadNetwork;
	private MatchingNetwork[] monteCarloMatchingNetworks;
	private double frequency, lowerFrequency, upperFrequency, h, yieldGoalSpan;
	int n;
	boolean monteCarloEnabled;

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

	/**
	 * <pre>
	 * Starts the calculations in a new <code>Thread</code>
	 * </pre>
	 * 
	 * @param sourceNetwork
	 * @param monteCarloMatchingNetworks
	 *            If the matching networks to be used in the monte-carlo
	 *            simulation differ from the calculated networks they are saved
	 *            in this array
	 * @param loadNetwork
	 * @param frequency
	 * @param lowerFrequency
	 * @param upperFrequency
	 * @param h
	 * @param n
	 * @param yieldGoalSpan
	 *            Is used to set the range in which the return loss is
	 *            calculated
	 * @param monteCarloEnabled
	 */
	public void triggerCalculations(SourceLoadNetwork sourceNetwork,
			MatchingNetwork[] monteCarloMatchingNetworks,
			SourceLoadNetwork loadNetwork, double frequency,
			double lowerFrequency, double upperFrequency, double h, int n,
			double yieldGoalSpan, boolean monteCarloEnabled) {

		this.sourceNetwork = sourceNetwork;
		this.monteCarloMatchingNetworks = monteCarloMatchingNetworks;
		this.loadNetwork = loadNetwork;
		this.frequency = frequency;
		this.lowerFrequency = lowerFrequency;
		this.upperFrequency = upperFrequency;
		this.h = h;
		this.n = n;
		this.yieldGoalSpan = yieldGoalSpan;
		this.monteCarloEnabled = monteCarloEnabled;

		Thread calculationEngine = new Thread(this);
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

	/**
	 * The whole <code>run()</code> method is a synchronized block, so it never
	 * runs more than once at the same time.
	 */
	public void run() {
		synchronized (this) {
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
										.getElectricalComponents()[0]
										.getValue());
					}

					if (monteCarloMatchingNetworks[i].getElectricalComponents()[1]
							.getValue() >= 0) {
						matchingNetworks[i].getElectricalComponents()[1]
								.setValue(monteCarloMatchingNetworks[i]
										.getElectricalComponents()[1]
										.getValue());
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
	}
}