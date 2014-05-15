package ch.fhnw.ht.eit.p2.impedancepro;

import java.util.Observable;
import java.util.Random;

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

	public double calculateMonteCarlo(SourceLoadNetwork sourceNetwork,
			MatchingNetwork matchingNetwork, SourceLoadNetwork loadNetwork,
			double fgo, double fgu, int n) {

		Random rand = new Random(System.currentTimeMillis());

		double tolerance0 = sourceNetwork.getElectricalComponents()[0]
				.getTolerance();
		double tolerance1 = sourceNetwork.getElectricalComponents()[1]
				.getTolerance();

		double tolerance2 = loadNetwork.getElectricalComponents()[0]
				.getTolerance();
		double tolerance3 = loadNetwork.getElectricalComponents()[1]
				.getTolerance();

		double value0 = sourceNetwork.getElectricalComponents()[0].getValue();
		double value1 = sourceNetwork.getElectricalComponents()[1].getValue();

		double value2 = loadNetwork.getElectricalComponents()[0].getValue();
		double value3 = loadNetwork.getElectricalComponents()[1].getValue();

		double[] component0valuesource;
		component0valuesource = new double[n];

		double[] component1valuesource;
		component1valuesource = new double[n];

		double[] component0valueload;
		component0valueload = new double[n];

		double[] component1valueload;
		component1valueload = new double[n];

		SourceLoadNetwork[] sourceNetworkMonteCarlo;
		sourceNetworkMonteCarlo = new SourceLoadNetwork[n];

		SourceLoadNetwork[] loadNetworkMonteCarlo;
		loadNetworkMonteCarlo = new SourceLoadNetwork[n];

		for (int i = 0; i < component0valuesource.length; i++) {

			component0valuesource[i] = rand.nextDouble()
					* rand.nextDouble()
					* ((((tolerance0 / 100.0) + 1.0) * value0) - ((1.0 - (tolerance0 / 100)) * value0))
					+ ((1.0 - (tolerance0 / 100)) * value0);

			component1valuesource[i] = rand.nextDouble()
					* rand.nextDouble()
					* ((((tolerance1 / 100.0) + 1.0) * value1) - ((1.0 - (tolerance1 / 100)) * value1))
					+ ((1.0 - (tolerance1 / 100)) * value1);

	//		sourceNetworkMonteCarlo[i] = new SourceLoadNetwork(sourceNetwork.getTopology(), sourceNetwork.setElectricalComponents(electricalComponents);

			for (int i = 0; i < component0valueload.length; i++) {

				component0valueload[i] = rand.nextDouble()
						* rand.nextDouble()
						* ((((tolerance2 / 100.0) + 1.0) * value2) - ((1.0 - (tolerance2 / 100)) * value2))
						+ ((1.0 - (tolerance2 / 100)) * value2);

				component1valuesource[i] = rand.nextDouble()
						* rand.nextDouble()
						* ((((tolerance3 / 100.0) + 1.0) * value3) - ((1.0 - (tolerance3 / 100)) * value3))
						+ ((1.0 - (tolerance3 / 100)) * value3);

			}

			return 0.0; // Prozentangabe fŸr 1 Netzwerk
		}

	}
}