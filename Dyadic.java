import java.util.*;
import java.io.*;
import java.math.BigInteger;

/**
  * @author Mighty Cohadar 
  * Turns out Dyadic numbers are mostly useless in coding.
  */
public class Dyadic {

	final long p;
	final int q;

	public static Dyadic ZERO = new Dyadic(0);
	public static Dyadic ONE = new Dyadic(1);
	
	public Dyadic(long p) {
		this(p, 0);
	}

	public Dyadic(long p, int q) {
		assert q >= 0 : "negative q: " + q;
		while (p != 0 && p % 2 == 0) {
			p /= 2;
			q++;
		}
		this.p = p;
		this.q = q;
	}
	
	public double toDouble() {
		return Math.scalb(p, q);
	}

	public long toLong() {
		long ret = p;
		for (int i = 0; i < q; i++) {
			ret = Math.multiplyExact(ret, 2);
		}
		return ret;
	}

	public BigInteger toBigInteger() {
		BigInteger ret = new BigInteger(String.valueOf(p));
		return ret.shiftLeft(q);
	}

	public Dyadic mul(Dyadic d) {
		return new Dyadic(Math.multiplyExact(p, d.p), Math.addExact(q, d.q));
	}

	public Dyadic mul(long x) {
		return this.mul(new Dyadic(x, 0));
	}

	public Dyadic div(Dyadic d) {
		if (p % d.p != 0) {
			throw new ArithmeticException(String.format("cannot divide dyadic: %s and %s", this.toString(), d.toString()));
		}
		long pp = p / d.p;
		int qq = Math.addExact(q, -d.q);
		while (qq < 0) {
			qq++;
			pp = Math.multiplyExact(pp, 2);
		}
		return new Dyadic(pp, qq);
	}

	public Dyadic div(long x) {
		return this.div(new Dyadic(x, 0));
	}

	public String toString() {
		return String.format("(p=%d, q=%d)", p, q);
	}	

}

