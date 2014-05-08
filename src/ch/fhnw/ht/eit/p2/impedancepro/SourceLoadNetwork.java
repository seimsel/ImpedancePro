package ch.fhnw.ht.eit.p2.impedancepro;

import ch.fhnw.ht.eit.p2.impedancepro.complex.ComplexNumber;

public class SourceLoadNetwork {
	
	public static final byte R = 0;
	public static final byte R_SER_C = 1;
	public static final byte R_SER_L = 2;
	public static final byte R_PAR_C = 3;
	public static final byte R_PAR_L = 4;
	public static final byte Z = 5;
	
	private int topology;
	private ComplexNumber impedance;
	private ElectricalComponent[] electricalComponents;
	

	
	public int getTopology() {
		return topology;
	}
	
	public void setTopology(int topology) {
		this.topology = topology;
	}
	
	public ComplexNumber getImpedanceAtFrequency(double f) {
		
		double w = 2 * Math.PI * f;
		
		ComplexNumber XR = new ComplexNumber(getElectricalComponents()[0].getValue(), 0);
		ComplexNumber XC = new ComplexNumber(0, -1/(w*getElectricalComponents()[1].getValue()));
		ComplexNumber XL = new ComplexNumber(0, w*getElectricalComponents()[1].getValue());
				
		switch (topology) {
		default:
		case R:
			impedance = XR;
			break;
		case R_SER_C:
			impedance = XR.add(XC);
			break;
		case R_SER_L:
			impedance = XR.add(XL);
			break;
		case R_PAR_C:
			impedance = ComplexNumber.parallel(new ComplexNumber[]{XR, XC});
			break;
		case R_PAR_L:
			impedance = ComplexNumber.parallel(new ComplexNumber[]{XR, XL});
			break;
		case Z:
			impedance = new ComplexNumber(getElectricalComponents()[0].getValue(), getElectricalComponents()[1].getValue());
			break;
		}
				
		return impedance;
	}
	
	public void setImpedance(ComplexNumber impedance) {
		this.impedance = impedance;
	}

	public ElectricalComponent[] getElectricalComponents() {
		return electricalComponents;
	}

	public void setElectricalComponents(ElectricalComponent[] electricalComponents) {
		this.electricalComponents = electricalComponents;
	}
}
