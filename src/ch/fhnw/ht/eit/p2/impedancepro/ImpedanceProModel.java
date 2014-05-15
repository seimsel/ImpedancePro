package ch.fhnw.ht.eit.p2.impedancepro;

import java.util.Observable;
import java.util.Random;

/**
 * @author Stephan Fahrni
 */
public class ImpedanceProModel extends Observable {

	private Network network;
	private double monteCarloResult;

	public ImpedanceProModel() {
		network = new Network(this);
	}
	
	public double getMonteCarloResult() {
		return monteCarloResult;
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
			double lowerFrequency, double upperFrequency, double h, int n ) {
		
		Random rand = new Random(System.currentTimeMillis());
		
		double monteCarloResultLower = 1;
		double monteCarloResultUpper = 1;
		double delta = 1/n;

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

		double[] componentindex;
		componentindex = new double[n];
		
		SourceLoadNetwork[] sourceNetworkMonteCarlo;
		sourceNetworkMonteCarlo = new SourceLoadNetwork[n];

		SourceLoadNetwork[] loadNetworkMonteCarlo;
		loadNetworkMonteCarlo = new SourceLoadNetwork[n];
		
		double[] reflectionMonteCarloUpperFrequency;
		reflectionMonteCarloUpperFrequency = new double[n];
		
		double[] reflectionMonteCarloLowerFrequency;
		reflectionMonteCarloLowerFrequency = new double[n];

		for (int i = 0; i < componentindex.length; i++) {

			sourceNetworkMonteCarlo[i] = new SourceLoadNetwork(
					sourceNetwork.getTopology(),
					new ElectricalComponent[] {
							new ElectricalComponent(
									rand.nextDouble()
											* ((((tolerance0 / 100.0) + 1.0) * value0) - ((1.0 - (tolerance0 / 100)) * value0))
											+ ((1.0 - (tolerance0 / 100)) * value0)),
							new ElectricalComponent(
									rand.nextDouble()
											* ((((tolerance1 / 100.0) + 1.0) * value1) - ((1.0 - (tolerance1 / 100)) * value1))
											+ ((1.0 - (tolerance1 / 100)) * value1)) });
			
			
			loadNetworkMonteCarlo[i] = new SourceLoadNetwork(
					loadNetwork.getTopology(),
					new ElectricalComponent[] {
							new ElectricalComponent(
									rand.nextDouble()
											* ((((tolerance2 / 100.0) + 1.0) * value2) - ((1.0 - (tolerance2 / 100)) * value2))
											+ ((1.0 - (tolerance2 / 100)) * value2)),
							new ElectricalComponent(
									rand.nextDouble()
											* ((((tolerance3 / 100.0) + 1.0) * value3) - ((1.0 - (tolerance3 / 100)) * value3))
											+ ((1.0 - (tolerance3 / 100)) * value3)) });
			
			//System.out.println("Source0:"+sourceNetworkMonteCarlo[i].getElectricalComponents()[0].getValue());
			//System.out.println("Source1:"+sourceNetworkMonteCarlo[i].getElectricalComponents()[1].getValue());
			
			//System.out.println("Load0:"+loadNetworkMonteCarlo[i].getElectricalComponents()[0].getValue());
			//System.out.println("Load1:"+loadNetworkMonteCarlo[i].getElectricalComponents()[1].getValue());
			
			System.out.println(+(network.calculateReturnLossAtFrequency(sourceNetworkMonteCarlo[i], matchingNetwork, loadNetworkMonteCarlo[i], lowerFrequency)  ));
			
			reflectionMonteCarloLowerFrequency[i] = network.calculateReturnLossAtFrequency(sourceNetworkMonteCarlo[i], matchingNetwork, loadNetworkMonteCarlo[i], lowerFrequency );
			reflectionMonteCarloUpperFrequency[i] = network.calculateReturnLossAtFrequency(sourceNetworkMonteCarlo[i], matchingNetwork, loadNetworkMonteCarlo[i], upperFrequency );
			
			if (reflectionMonteCarloLowerFrequency[i] >= h) {
				
				monteCarloResultLower -= delta;
				
			}
			
			if (reflectionMonteCarloUpperFrequency[i] >= h) {
				
				monteCarloResultUpper -= delta;
				
			}
		}

		return 0.0;
	}
	

}