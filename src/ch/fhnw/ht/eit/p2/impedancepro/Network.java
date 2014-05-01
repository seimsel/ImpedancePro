package ch.fhnw.ht.eit.p2.impedancepro;

import org.jfree.data.xy.XYDataset;

/**
 * @author Stephan Fahrni
 */
public class Network {
	
	public static final byte PAR_C_SER_C = 0;
	public static final byte PAR_C_SER_L = 1;
	public static final byte PAR_L_SER_C = 2;
	public static final byte PAR_L_SER_L = 3;
	public static final byte SER_C_PAR_C = 4;
	public static final byte SER_C_PAR_L = 5;
	public static final byte SER_L_PAR_C = 6;
	public static final byte SER_L_PAR_L = 7;
	
	private double frequency;
	private XYDataset swrData, reflectanceData, amplitudeData, amplitudeDBData;
	private double monteCarloResult;
	
	private MatchingNetwork[] matchingNetworks;
	private SourceLoadNetwork sourceNetwork;
	private SourceLoadNetwork loadNetwork;
	
	public double getFrequency() {
		return frequency;
	}
	
	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}
	
	public XYDataset getSwrData() {
		return swrData;
	}
	
	public void setSwrData(XYDataset swrData) {
		this.swrData = swrData;
	}
	
	public XYDataset getReflectanceData() {
		return reflectanceData;
	}
	
	public void setReflectanceData(XYDataset reflectanceData) {
		this.reflectanceData = reflectanceData;
	}
	
	public XYDataset getAmplitudeData() {
		return amplitudeData;
	}
	
	public void setAmplitudeData(XYDataset amplitudeData) {
		this.amplitudeData = amplitudeData;
	}

	public XYDataset getAmplitudeDBData() {
		return amplitudeDBData;
	}
	
	public void setAmplitudeDBData(XYDataset amplitudeDBData) {
		this.amplitudeDBData = amplitudeDBData;
	}

	public double getMonteCarloResult() {
		return monteCarloResult;
	}
	
	public void setMonteCarloResult(double monteCarloResult) {
		this.monteCarloResult = monteCarloResult;
	}
	
	public void calculateMatchingNetworks() {
		
	}
	
	public void calculateMonteCarlo() {
		
	}

	public MatchingNetwork[] getMatchingNetwork() {
		return matchingNetworks;
	}

	public void setMatchingNetworks(MatchingNetwork[] matchingNetworks) {
		this.matchingNetworks = matchingNetworks;
	}

	public SourceLoadNetwork getSourceNetwork() {
		return sourceNetwork;
	}

	public void setSourceNetwork(SourceLoadNetwork sourceNetwork) {
		this.sourceNetwork = sourceNetwork;
	}

	public SourceLoadNetwork getLoadNetwork() {
		return loadNetwork;
	}

	public void setLoadNetwork(SourceLoadNetwork loadNetwork) {
		this.loadNetwork = loadNetwork;
	}
}
