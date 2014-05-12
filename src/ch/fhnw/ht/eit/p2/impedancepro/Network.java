package ch.fhnw.ht.eit.p2.impedancepro;

import org.jfree.data.xy.XYDataset;

import ch.fhnw.ht.eit.p2.impedancepro.complex.ComplexNumber;
import ch.fhnw.ht.eit.p2.impedancepro.util.EngineeringUtil;

/**
 * @author Stephan Fahrni
 */

public class Network {
	private double frequency;
	private String frequencyString;
	private XYDataset swrData, reflectanceData, amplitudeData, amplitudeDBData;
	private double monteCarloResult;

	private MatchingNetwork[] matchingNetworks;
	private SourceLoadNetwork sourceNetwork;
	private SourceLoadNetwork loadNetwork;

	private ComplexNumber Zq;
	private ComplexNumber Zl;

	private ImpedanceProModel model;

	private double LSG1BT1, LSG1BT2, LSG2BT1, LSG2BT2, LSG3BT1, LSG3BT2,
			LSG4BT1, LSG4BT2;

	public Network(ImpedanceProModel model) {
		this.model = model;
		sourceNetwork = new SourceLoadNetwork();
		loadNetwork = new SourceLoadNetwork();
	}

	public double getFrequency() {
		return frequency;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
		this.frequencyString = EngineeringUtil.convert(frequency, 2);
	}

	public String getFrequencyString() {
		return frequencyString;
	}

	public void setFrequencyString(String frequencyString) {
		this.frequency = EngineeringUtil.parse(frequencyString);
		this.frequencyString = EngineeringUtil.convert(frequency, 2);
	}

	public XYDataset getSwrData() {
		return swrData;
	}

	public void setSwrData(XYDataset swrData) {
		this.swrData = swrData;
	}

	public XYDataset getReflectanceData() {

//		
//		  
//		  double RS = 0, XS = 0, RL = 0, XL = 0;
//		  
//		  double w0 = 0, w = 0;
//		  
//		  Zq = new ComplexNumber(); Zq =
//		  sourceNetwork.getImpedanceAtFrequency(frequency);
//		  
//		  Zl = new ComplexNumber(); Zl =
//		  loadNetwork.getImpedanceAtFrequency(frequency);
//		  
//		  RS = Zq.getRe(); XS = Zq.getIm();
//		  
//		  RL = Zl.getRe(); XL = Zl.getIm();
//		  
//			public static double[] linspace(double begin, double end, double step) {
//				int n = (int) Math.floor((end - begin) / step) + 1;
//				double[] res = new double[n];
//				for (int i = 0; i < res.length; i++) {
//					res[i] = begin + i * step;
//				}
//				return res;
//		  
//		  //f=linspace(0.8*f0,1.2*f0,1e4); w0=2*Math.PI*f0; w=2*pi*f;
//		 
//		  if(XS < 0){
//		 
//		  Cq=-1/(w0*Xq); Xq=-1./(w*Cq);
//		  
//		  } else{
//		  
//		  Lq=Xq/w0; Xq=w*Lq; }
//		  
//		  if(Xl < 0) Cl=-1/(w0*Xl); Xl=-1./(w*Cl); else Ll=Xl/w0; Xl=w*Ll; end
//		  
//		  Zq=Rq+1j*Xq; Zl=Rl+1j*Xl;
//		  
//		  if(Lsg1.error == 0) if(strcmp(Lsg1.type1,'C1') ||
//		  strcmp(Lsg1.type1,'C2')) X1 = -1./(w*Lsg1.bt1);
//		  elseif(strcmp(Lsg1.type1,'L1') || strcmp(Lsg1.type1,'L2')) X1 =
//		  w*Lsg1.bt1; end
//		  
//		  if(strcmp(Lsg1.type2,'C1') || strcmp(Lsg1.type2,'C2')) X2 =
//		  -1./(w*Lsg1.bt2); elseif(strcmp(Lsg1.type2,'L1') ||
//		  strcmp(Lsg1.type2,'L2')) X2 = w*Lsg1.bt2; end
//		  
//		  if(Rq==Rl) ZxL=Zq; else ZxL=par(1j*X1,Zq); end ZxR=Zl+1j*X2;
//		  
//		  r1=abs((ZxL-conj(ZxR))./(ZxL+ZxR)); end
//		  
//		  if(Lsg2.error == 0) if(strcmp(Lsg2.type1,'C1') ||
//		  strcmp(Lsg2.type1,'C2')) X1 = -1./(w*Lsg2.bt1);
//		  elseif(strcmp(Lsg2.type1,'L1') || strcmp(Lsg2.type1,'L2')) X1 =
//		  w*Lsg2.bt1; end
//		  
//		  if(strcmp(Lsg2.type2,'C1') || strcmp(Lsg2.type2,'C2')) X2 =
//		  -1./(w*Lsg2.bt2); elseif(strcmp(Lsg2.type2,'L1') ||
//		  strcmp(Lsg2.type2,'L2')) X2 = w*Lsg2.bt2; end
//		 
//		  ZxL=par(1j*X1,Zq); ZxR=Zl+1j*X2;
//		  
//		  r2=abs((ZxL-conj(ZxR))./(ZxL+ZxR)); end
//		  
//		  if(Lsg3.error == 0)
//		  
//		  if(strcmp(Lsg3.type1,'C1') || strcmp(Lsg3.type1,'C2')) X1 =
//		  -1./(w*Lsg3.bt1); elseif(strcmp(Lsg3.type1,'L1') ||
//		  strcmp(Lsg3.type1,'L2')) X1 = w*Lsg3.bt1; end
//		  
//		  if(strcmp(Lsg3.type2,'C1') || strcmp(Lsg3.type2,'C2')) X2 =
//		  -1./(w*Lsg3.bt2); elseif(strcmp(Lsg3.type2,'L1') ||
//		  strcmp(Lsg3.type2,'L2')) X2 = w*Lsg3.bt2; end
//		  
//		  ZxL=1j*X1+Zq; ZxR=par(Zl,1j*X2);
//		  
//		  r3=abs((ZxL-conj(ZxR))./(ZxL+ZxR)); end
//		  
//		  if(Lsg4.error == 0) if(strcmp(Lsg4.type1,'C1') ||
//		  strcmp(Lsg4.type1,'C2')) X1 = -1./(w*Lsg4.bt1);
//		 elseif(strcmp(Lsg4.type1,'L1') || strcmp(Lsg4.type1,'L2')) X1 =
//		  w*Lsg4.bt1; end
//		  
//		  if(strcmp(Lsg4.type2,'C1') || strcmp(Lsg4.type2,'C2')) X2 =
//		  -1./(w*Lsg4.bt2); elseif(strcmp(Lsg4.type2,'L1') ||
//		  strcmp(Lsg4.type2,'L2')) X2 = w*Lsg4.bt2; end
//		  
//		  ZxL=1j*X1+Zq; ZxR=par(Zl,1j*X2);
//		  
//		  r4=abs((ZxL-conj(ZxR))./(ZxL+ZxR)); end
//		 
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

	public void calculateMatchingNetworks() {

		// Initialize varbiable with zero

		byte[] topology = new byte[4];

		double w = 0;

		double re = 0, a = 0, b = 0, c = 0;
		double X11 = 0, X12 = 0, X21 = 0, X22 = 0, X31 = 0, X32 = 0, X41 = 0, X42 = 0;
		double RS = 0, XS = 0, RL = 0, XL = 0;

		MatchingNetwork solution1 = new MatchingNetwork();
		MatchingNetwork solution2 = new MatchingNetwork();
		MatchingNetwork solution3 = new MatchingNetwork();
		MatchingNetwork solution4 = new MatchingNetwork();

		// Get load and source network

		Zq = new ComplexNumber();
		Zq = sourceNetwork.getImpedanceAtFrequency(frequency);

		Zl = new ComplexNumber();
		Zl = loadNetwork.getImpedanceAtFrequency(frequency);

		RS = Zq.getRe();
		XS = Zq.getIm();

		RL = Zl.getRe();
		XL = Zl.getIm();

		w = 2 * Math.PI * frequency;

		if (RS == RL) {

			X11 = 0;
			X12 = -(Zq.getIm() + Zl.getIm());

			X21 = -Math.pow(Zq.abs(), 2) / (2 * XS);
			X22 = -((Math.pow(XS, 2) * X21 + XS * Math.pow(X21, 2) + Math.pow(
					RS, 2) * X21)
					/ (Math.pow(RS, 2) + Math.pow(XS, 2) + 2 * X21 * XS + Math
							.pow(X21, 2)) + Zl.getIm());

			X32 = -Math.pow(Zl.abs(), 2) / (2 * XL);
			X31 = -((Math.pow(XL, 2) * X32 + XL * Math.pow(X32, 2) + Math.pow(
					RL, 2) * X32)
					/ (Math.pow(RL, 2) + Math.pow(XL, 2) + 2 * X32 * XL + Math
							.pow(X32, 2)) + Zq.getIm());

			X41 = 0;
			X42 = 0;

		}

		else {

			// In this part, solution 1 and 2 is calculated

			re = Zl.getRe();
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
								.pow(X11, 2)) + Zl.getIm());

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
								.pow(X21, 2)) + Zl.getIm());

			} else {

				solution2 = null;

			}

			// In this part, solution 3 and 4 is calculated

			re = Zq.getRe();

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
								.pow(X32, 2)) + Zq.getIm());

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
								.pow(X42, 2)) + Zq.getIm());

			} else {

				solution4 = null;

			}
		}

		// determine C or L of solution 1

		if (solution1 != null) {

			if (X11 == 0 && X12 == 0) {

				solution1 = null;

			} else {

				if (X11 == 0) {

					// only wire

					topology[0] = MatchingNetwork.EMPTY;
					topology[1] = MatchingNetwork.EMPTY;

				} else {

					if (X11 > 0) {

						LSG1BT1 = X11 / w;

						topology[0] = MatchingNetwork.PAR;
						topology[1] = MatchingNetwork.L;

					} else {

						LSG1BT1 = -1 / (w * X11);

						topology[0] = MatchingNetwork.PAR;
						topology[1] = MatchingNetwork.C;

					}
				}

				// determine C or L of solution 1

				if (X12 == 0) {

					topology[2] = MatchingNetwork.EMPTY;
					topology[3] = MatchingNetwork.EMPTY;

				} else {

					if (X12 > 0) {

						LSG1BT2 = X12 / w;

						topology[2] = MatchingNetwork.SER;
						topology[3] = MatchingNetwork.L;

					} else {

						LSG1BT2 = -1 / (w * X12);

						topology[2] = MatchingNetwork.SER;
						topology[3] = MatchingNetwork.C;

					}

				}

				solution1.electricalComponents[0].setValue(LSG1BT1);
				solution1.electricalComponents[1].setValue(LSG1BT2);
				solution1.setTopology(byteArrayToInt(topology));

			}

		}

		if (solution2 != null) {

			// determine C or L of solution 2
			if (X21 == 0 && X22 == 0) {

				solution2 = null;

			} else {

				if (X21 == 0) {

					topology[0] = MatchingNetwork.EMPTY;
					topology[1] = MatchingNetwork.EMPTY;

				} else if (X22 == 0) {

					topology[2] = MatchingNetwork.EMPTY;
					topology[3] = MatchingNetwork.EMPTY;

				} else {

					if (X21 > 0) {

						LSG2BT1 = X21 / w;

						topology[0] = MatchingNetwork.PAR;
						topology[1] = MatchingNetwork.L;

					} else {

						LSG2BT1 = -1 / (w * X21);

						topology[0] = MatchingNetwork.PAR;
						topology[1] = MatchingNetwork.C;

					}

					// determine C or L of solution 2

					if (X22 > 0) {

						LSG2BT2 = X22 / w;

						topology[2] = MatchingNetwork.SER;
						topology[3] = MatchingNetwork.L;

					} else {

						LSG2BT2 = -1 / (w * X22);

						topology[2] = MatchingNetwork.SER;
						topology[3] = MatchingNetwork.C;

					}

				}

				solution2.electricalComponents[0].setValue(LSG2BT1);
				solution2.electricalComponents[1].setValue(LSG2BT2);
				solution2.setTopology(byteArrayToInt(topology));

			}

		}

		if (solution3 != null) {

			// determine C or L of solution 3

			if (X31 == 0 && X32 == 0) {

				solution3 = null;

			} else {

				if (X31 == 0) {

					topology[0] = MatchingNetwork.EMPTY;
					topology[1] = MatchingNetwork.EMPTY;

				} else {

					if (X31 > 0) {

						LSG3BT1 = X31 / w;

						topology[0] = MatchingNetwork.SER;
						topology[1] = MatchingNetwork.L;

					} else {

						LSG3BT1 = -1 / (w * X31);

						topology[0] = MatchingNetwork.SER;
						topology[1] = MatchingNetwork.C;
					}
				}

				if (X32 == 0) {

					topology[2] = MatchingNetwork.EMPTY;
					topology[3] = MatchingNetwork.EMPTY;

				} else {

					// determine C or L of solution 3

					if (X32 > 0) {

						LSG3BT2 = X32 / w;

						topology[2] = MatchingNetwork.PAR;
						topology[3] = MatchingNetwork.L;

					} else {

						LSG3BT2 = -1 / (w * X32);

						topology[2] = MatchingNetwork.PAR;
						topology[3] = MatchingNetwork.C;
					}
				}

				solution3.electricalComponents[0].setValue(LSG3BT1);
				solution3.electricalComponents[1].setValue(LSG3BT2);
				solution3.setTopology(byteArrayToInt(topology));

			}

		}

		if (solution4 != null) {

			// determine C or L of solution 4

			if (X41 == 0 && X42 == 0) {

				solution4 = null;

			} else {

				if (X41 == 0) {

					topology[0] = MatchingNetwork.EMPTY;
					topology[1] = MatchingNetwork.EMPTY;

				} else {

					if (X41 >= 0) {

						LSG4BT1 = X41 / w;

						topology[0] = MatchingNetwork.SER;
						topology[1] = MatchingNetwork.L;

					} else {

						LSG4BT1 = -1 / (w * X41);

						topology[0] = MatchingNetwork.SER;
						topology[1] = MatchingNetwork.C;
					}
				}

				// determine C or L of solution 4

				if (X42 == 0) {

					topology[2] = MatchingNetwork.EMPTY;
					topology[3] = MatchingNetwork.EMPTY;
				} else {

					if (X42 > 0) {

						LSG4BT2 = X42 / w;

						topology[2] = MatchingNetwork.PAR;
						topology[3] = MatchingNetwork.L;

					} else {

						LSG4BT2 = -1 / (w * X42);

						topology[2] = MatchingNetwork.PAR;
						topology[3] = MatchingNetwork.C;
					}

				}

				solution4.electricalComponents[0].setValue(LSG4BT1);
				solution4.electricalComponents[1].setValue(LSG4BT2);
				solution4.setTopology(byteArrayToInt(topology));

			}

		}

		matchingNetworks = new MatchingNetwork[] { solution1, solution2,
				solution3, solution4 };

		model.setChanged();
		model.notifyObservers();
	}

	public void calculateMonteCarlo() {

	}

	public MatchingNetwork[] getMatchingNetworks() {
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

	private int byteArrayToInt(byte[] encodedValue) {
		int value = 0;

		for (int i = 0; i < encodedValue.length; i++) {
			value += encodedValue[i]
					* Math.pow(10, encodedValue.length - 1 - i);
		}

		return value;
	}
}
