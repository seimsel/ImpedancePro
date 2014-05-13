package ch.fhnw.ht.eit.p2.impedancepro;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import ch.fhnw.ht.eit.p2.impedancepro.complex.ComplexNumber;

/**
 * @author Stephan Fahrni
 */

public class Network {
	private XYSeriesCollection swrData, returnLossData;
	private double monteCarloResult;

	private SourceLoadNetwork sourceNetwork;
	private MatchingNetwork[] matchingNetworks;
	private SourceLoadNetwork loadNetwork;

	private ImpedanceProModel model;

	public Network(ImpedanceProModel model) {
		this.model = model;
	}

	public XYSeriesCollection getSwrData() {
		return swrData;
	}

	public XYSeriesCollection getReturnLossData() {
		return returnLossData;
	}

	public void setSwrData(XYSeriesCollection swrData) {
		this.swrData = swrData;
	}

	public void setReturnLossData(XYSeriesCollection returnLossData) {
		this.returnLossData = returnLossData;
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

	public double getMonteCarloResult() {
		return monteCarloResult;
	}

	public MatchingNetwork[] getMatchingNetworks() {
		return matchingNetworks;
	}

	/**
	 * <pre>
	 *  In this part we calculate the matched network
	 *  There are max. 4 solutions
	 *  First the impedance of load and source network are initialized
	 *  Next, check if the real part of source network is the real part of load network -> no matching network needed!
	 * 	
	 * Solution 1&2
	 * 
	 * X11 = L or C parallel source
	 * X12 = L or C in series of source
	 * X21 = L or C parallel source
	 * X22 = L or C in series of source
	 * 
	 * 	 *	Solution 3&4
	 * 
	 * X31 = L or C in series of load
	 * X32 = L or C parallel load
	 * X41 = L or C in series of load
	 * X42 = L or C parallel load
	 * </pre>
	 */

	public void calculateMatchingNetworks(SourceLoadNetwork sourceNetwork,
			SourceLoadNetwork loadNetwork, double frequency) {

		this.sourceNetwork = sourceNetwork;
		this.loadNetwork = loadNetwork;

		// Initialize varbiable with zero

		double w = 0;

		double re = 0, a = 0, b = 0, c = 0;
		double X11 = 0, X12 = 0, X21 = 0, X22 = 0, X31 = 0, X32 = 0, X41 = 0, X42 = 0;
		double RS = 0, XS = 0, RL = 0, XL = 0;

		MatchingNetwork solution1 = new MatchingNetwork();
		MatchingNetwork solution2 = new MatchingNetwork();
		MatchingNetwork solution3 = new MatchingNetwork();
		MatchingNetwork solution4 = new MatchingNetwork();

		// Get load and source network

		ComplexNumber ZS = sourceNetwork.getImpedanceAtFrequency(frequency);
		ComplexNumber ZL = loadNetwork.getImpedanceAtFrequency(frequency);

		RS = ZS.getRe();
		XS = ZS.getIm();

		RL = ZL.getRe();
		XL = ZL.getIm();

		w = 2 * Math.PI * frequency;

		if (RS == RL) {

			X11 = 0;
			X12 = -(ZS.getIm() + ZL.getIm());

			X21 = exceptionhandler(-Math.pow(ZS.abs(), 2) / (2 * XS));

			X22 = exceptionhandler(-((Math.pow(XS, 2) * X21 + XS
					* Math.pow(X21, 2) + Math.pow(RS, 2) * X21)
					/ (Math.pow(RS, 2) + Math.pow(XS, 2) + 2 * X21 * XS + Math
							.pow(X21, 2)) + ZL.getIm()));

			X32 = exceptionhandler(-Math.pow(ZL.abs(), 2) / (2 * XL));

			X31 = exceptionhandler(-((Math.pow(XL, 2) * X32 + XL
					* Math.pow(X32, 2) + Math.pow(RL, 2) * X32)
					/ (Math.pow(RL, 2) + Math.pow(XL, 2) + 2 * X32 * XL + Math
							.pow(X32, 2)) + ZS.getIm()));

			X41 = 0;
			X42 = 0;

		}

		else {

			// In this part, solution 1 and 2 is calculated

			re = ZL.getRe();
			a = 1 - RS / re;

			b = XS;
			c = (Math.pow(RS, 2)) + (Math.pow(XS, 2));

			// Check, if there a imaginary part of solution 1
			// If there a imaginary part, component is not applicable to the
			// matched network

			if ((Math.pow(b, 2)) - (a * c) >= 0) {

				X11 = (-b + Math.sqrt((Math.pow(b, 2)) - a * c)) / (a);

				X12 = -((Math.pow(XS, 2) * X11 + XS * Math.pow(X11, 2) + Math
						.pow(RS, 2) * X11)
						/ (Math.pow(RS, 2) + Math.pow(XS, 2) + 2 * X11 * XS + Math
								.pow(X11, 2)) + ZL.getIm());

			} else {

				solution1 = null;

			}

			// Check, if there a imaginary part of solution 2
			// If there a imaginary part, component is not applicable to the
			// matched network

			if ((Math.pow(b, 2)) - (a * c) >= 0) {

				X21 = (-b - Math.sqrt((Math.pow(b, 2)) - a * c)) / (a);

				X22 = -((Math.pow(XS, 2) * X21 + XS * Math.pow(X21, 2) + Math
						.pow(RS, 2) * X21)
						/ (Math.pow(RS, 2) + Math.pow(XS, 2) + 2 * X21 * XS + Math
								.pow(X21, 2)) + ZL.getIm());

			} else {

				solution2 = null;

			}

			// In this part, solution 3 and 4 is calculated

			re = ZS.getRe();

			a = 1 - RL / re;
			b = XL;
			c = Math.pow(RL, 2) + Math.pow(XL, 2);

			// Check, if there a imaginary part of solution 3
			// If there a imaginary part, component is not applicable to the
			// matched network

			if (Math.pow(b, 2) - (a * c) >= 0) {

				X32 = (-b + Math.sqrt(Math.pow(b, 2) - a * c)) / (a);

				X31 = -((Math.pow(XL, 2) * X32 + XL * Math.pow(X32, 2) + Math
						.pow(RL, 2) * X32)
						/ (Math.pow(RL, 2) + Math.pow(XL, 2) + 2 * X32 * XL + Math
								.pow(X32, 2)) + ZS.getIm());

			} else {

				solution3 = null;

			}

			// Check, if there a imaginary part of solution 4
			// If there a imaginary part, component is not applicable to the
			// matched network

			if (Math.pow(b, 2) - (a * c) >= 0) {

				X42 = (-b - Math.sqrt(Math.pow(b, 2) - a * c)) / (a);

				X41 = -((Math.pow(XL, 2) * X42 + XL * Math.pow(X42, 2) + Math
						.pow(RL, 2) * X42)
						/ (Math.pow(RL, 2) + Math.pow(XL, 2) + 2 * X42 * XL + Math
								.pow(X42, 2)) + ZS.getIm());

			} else {

				solution4 = null;

			}
		}

		solution1 = createNetwork(solution1, X11, X12, w);
		solution2 = createNetwork(solution2, X21, X22, w);
		solution3 = createNetwork(solution3, X31, X32, w);
		solution4 = createNetwork(solution4, X41, X42, w);

		matchingNetworks = new MatchingNetwork[] { solution1, solution2,
				solution3, solution4 };

		model.setChanged();
		model.notifyObservers();
	}

	public MatchingNetwork createNetwork(MatchingNetwork solution,
			double reactance1, double reactance2, double w) {

		double sol1 = 0, sol2 = 0;

		byte[] topology = new byte[4];

		if (solution != null) {

			// determine C or L of solution

			if (reactance1 == 0 && reactance2 == 0) {

				solution = null;

			} else {

				if (reactance1 == 0) {

					topology[0] = MatchingNetwork.EMPTY;
					topology[1] = MatchingNetwork.EMPTY;

				} else if (reactance2 == 0) {

					topology[2] = MatchingNetwork.EMPTY;
					topology[3] = MatchingNetwork.EMPTY;

				} else {

					if (reactance1 > 0) {

						sol1 = reactance1 / w;

						topology[0] = MatchingNetwork.PAR;
						topology[1] = MatchingNetwork.L;

					} else {

						sol1 = -1 / (w * reactance1);

						topology[0] = MatchingNetwork.PAR;
						topology[1] = MatchingNetwork.C;

					}

					// determine C or L of solution 2

					if (reactance2 > 0) {

						sol2 = reactance2 / w;

						topology[2] = MatchingNetwork.SER;
						topology[3] = MatchingNetwork.L;

					} else {

						sol2 = -1 / (w * reactance2);

						topology[2] = MatchingNetwork.SER;
						topology[3] = MatchingNetwork.C;

					}

				}

				solution.electricalComponents[0].setValue(sol1);
				solution.electricalComponents[1].setValue(sol2);
				solution.setTopology(byteArrayToInt(topology));

			}

		}

		return solution;
	}

	public double calculateReturnLossAtFrequency(
			SourceLoadNetwork sourceNetwork, MatchingNetwork matchingNetwork,
			SourceLoadNetwork loadNetwork, double frequency) {
		ComplexNumber ZS = sourceNetwork.getImpedanceAtFrequency(frequency);
		ComplexNumber ZL = loadNetwork.getImpedanceAtFrequency(frequency);

		double value1 = matchingNetwork.getElectricalComponents()[0].getValue();
		double value2 = matchingNetwork.getElectricalComponents()[1].getValue();

		ComplexNumber Z1 = new ComplexNumber();
		ComplexNumber Z2 = new ComplexNumber();

		ComplexNumber ZR = new ComplexNumber();
		ComplexNumber r = new ComplexNumber();

		double w = 2 * Math.PI * frequency;

		switch (matchingNetwork.getTopology()) {
		default:
		case MatchingNetwork.PAR_C_SER_C:
			Z1 = new ComplexNumber(0.0, -1 / (w * value1));
			Z2 = new ComplexNumber(0.0, -1 / (w * value2));
			ZR = ComplexNumber.parallel(new ComplexNumber[] { Z1, Z2.add(ZL) });
			break;

		case MatchingNetwork.PAR_C_SER_L:
			Z1 = new ComplexNumber(0.0, -1 / (w * value1));
			Z2 = new ComplexNumber(0.0, w * value2);
			ZR = ComplexNumber.parallel(new ComplexNumber[] { Z1, Z2.add(ZL) });
			break;

		case MatchingNetwork.PAR_L_SER_C:
			Z1 = new ComplexNumber(0.0, w * value1);
			Z2 = new ComplexNumber(0.0, -1 / (w * value2));
			ZR = ComplexNumber.parallel(new ComplexNumber[] { Z1, Z2.add(ZL) });
			break;

		case MatchingNetwork.PAR_L_SER_L:
			Z1 = new ComplexNumber(0.0, w * value1);
			Z2 = new ComplexNumber(0.0, w * value2);
			ZR = ComplexNumber.parallel(new ComplexNumber[] { Z1, Z2.add(ZL) });
			break;

		case MatchingNetwork.SER_C_PAR_C:
			Z1 = new ComplexNumber(0.0, -1 / (w * value1));
			Z2 = new ComplexNumber(0.0, -1 / (w * value2));
			ZR = Z1.add(ComplexNumber.parallel(new ComplexNumber[] { Z2, ZL }));
			break;

		case MatchingNetwork.SER_C_PAR_L:
			Z1 = new ComplexNumber(0.0, -1 / (w * value1));
			Z2 = new ComplexNumber(0.0, w * value2);
			ZR = Z1.add(ComplexNumber.parallel(new ComplexNumber[] { Z2, ZL }));
			break;

		case MatchingNetwork.SER_L_PAR_C:
			Z1 = new ComplexNumber(0.0, w * value1);
			Z2 = new ComplexNumber(0.0, -1 / (w * value2));
			ZR = Z1.add(ComplexNumber.parallel(new ComplexNumber[] { Z2, ZL }));
			break;

		case MatchingNetwork.SER_L_PAR_L:
			Z1 = new ComplexNumber(0.0, w * value1);
			Z2 = new ComplexNumber(0.0, w * value2);
			ZR = Z1.add(ComplexNumber.parallel(new ComplexNumber[] { Z2, ZL }));
			break;

		case MatchingNetwork.SER_C:
			Z1 = new ComplexNumber(0.0, -1 / (w * value1));
			ZR = Z1.add(ZL);
			break;

		case MatchingNetwork.SER_L:
			Z1 = new ComplexNumber(0.0, w * value1);
			ZR = Z1.add(ZL);
			break;

		case MatchingNetwork.PAR_C:
			Z1 = new ComplexNumber(0.0, -1 / (w * value1));
			ZR = ComplexNumber.parallel(new ComplexNumber[] { Z1, ZL });
			break;

		case MatchingNetwork.PAR_L:
			Z1 = new ComplexNumber(0.0, w * value1);
			ZR = ComplexNumber.parallel(new ComplexNumber[] { Z1, ZL });
			break;
		}

		r = (ZS.sub(ZR.conj())).div(ZS.add(ZR));

		return r.abs();
	}

	public void calculateReturnLossOfAllSolutions(double lowerFrequency,
			double upperFrequency) {
		XYSeries rData[] = new XYSeries[getMatchingNetworks().length];
		XYSeriesCollection rDataCollection = new XYSeriesCollection();

		for (int i = 0; i < getMatchingNetworks().length; i++) {
			rData[i] = new XYSeries("return_loss" + i);
			double[] f = linspace(lowerFrequency, upperFrequency, 100e3);

			if (getMatchingNetworks()[i] != null) {
				for (int j = 0; j < f.length; j++) {
					rData[i].add(
							f[j],
							calculateReturnLossAtFrequency(getSourceNetwork(),
									getMatchingNetworks()[i], getLoadNetwork(),
									f[j]));
				}
			}
			rDataCollection.addSeries(rData[i]);
		}

		setReturnLossData(rDataCollection);

		model.setChanged();
		model.notifyObservers();
	}

	public void calculateMonteCarlo() {

	}

	private int byteArrayToInt(byte[] encodedValue) {
		int value = 0;

		for (int i = 0; i < encodedValue.length; i++) {
			value += encodedValue[i]
					* Math.pow(10, encodedValue.length - 1 - i);
		}

		return value;
	}

	public double[] linspace(double begin, double end, double step) {
		int n = (int) Math.floor((end - begin) / step) + 1;
		double[] res = new double[n];
		for (int i = 0; i < res.length; i++) {
			res[i] = begin + i * step;
		}
		return res;
	}

	public double exceptionhandler(double check) {
		try {

			if (Double.isNaN(check)) {
				throw new Exception("NaN result!");
			}
			if (Double.isInfinite(check)) {
				throw new Exception("Result is Infinite");
			}
		} catch (Exception e) {
			check = 0.0;
		}
		return check;

	}
}