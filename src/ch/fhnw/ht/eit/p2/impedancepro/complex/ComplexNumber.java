/**
 * 
 */
package ch.fhnw.ht.eit.p2.impedancepro.complex;

/**
 * @author Richard Gut
 *
 */
public class ComplexNumber {
	private double re;
	private double im;

	public ComplexNumber() {
		this(0.0, 0.0);
	}

	public ComplexNumber(double re, double im) {
		setRe(re);
		setIm(im);
	}

	public ComplexNumber(double re) {
		this(re, 0.0);
	}

	public ComplexNumber(ComplexNumber z) {
		this(z.re, z.im);
	}
	
	public double getRe() {
		return re;
	}
	
	public void setRe(double re) {
		this.re = re;
	}
	
	public double getIm() {
		return im;
	}
	
	public void setIm(double im) {
		this.im = im;
	}

	static public ComplexNumber pow(ComplexNumber a, double x) {
		return new ComplexNumber(Math.pow(a.abs(), x) * Math.cos(x * angle(a)), Math.pow(a.abs(), x) * Math.sin(x * angle(a)));
	}

	public ComplexNumber add(ComplexNumber z) {
		return new ComplexNumber(this.re + z.re, this.im + z.im);
	}

	public ComplexNumber sub(ComplexNumber z) {
		return new ComplexNumber(this.re - z.re, this.im - z.im);
	}

	public ComplexNumber mul(ComplexNumber z) {
		return new ComplexNumber((re * z.re) - (im * z.im), (re * z.im) + (im * z.re));
	}

	public ComplexNumber mul(double z) {
		return new ComplexNumber((re * z), (im * z));
	}

	public ComplexNumber div(ComplexNumber b) {
		return new ComplexNumber((this.abs() / b.abs()) * Math.cos(angle(this) - angle(b)), (this.abs() / b.abs())
				* Math.sin(angle(this) - angle(b)));
	}

	public ComplexNumber div(double b) {
		return new ComplexNumber(this.re / b, this.im / b);
	}

	public double abs() {
		return Math.sqrt(re * re + im * im);
	}

	public static double angle(ComplexNumber c) {
		return Math.atan2(c.im, c.re);
	}

	public static double[] angle(ComplexNumber[] c) {
		double[] res = new double[c.length];
		for (int i = 0; i < res.length; i++) {
			res[i] = angle(c[i]);
		}
		return res;
	}

	public static double[] abs(ComplexNumber[] c) {
		double[] res = new double[c.length];
		for (int i = 0; i < c.length; i++) {
			res[i] = c[i].abs();
		}
		return res;
	}

	public static String toString(ComplexNumber z) {
		return "Realteil: " + z.re + " Imaginaerteil: " + z.im;
	}
}