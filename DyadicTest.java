import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class DyadicTest {

	@Test
	public void testToLong() {
		for (long i = 0; i < 100000; i++) {
			long l = random.nextLong();
			Dyadic dl = new Dyadic(l);
			assert l == dl.toLong() : String.format("testToLong %d %d", l, dl.toLong()); 
		}
	}

	@Test
	public void testToDouble() {
		for (long i = 0; i < 100000; i++) {
			long l = random.nextLong();
			double dl = new Dyadic(l).toDouble();
			assert fuzzyEqual(l, dl, 1e-6) : String.format("testToDouble %d: %d %g", i, l, dl);
		}
	}

	@Test
	public void testToBigInteger() {
		for (long i = 0; i < 100000; i++) {
			long l = random.nextLong();
			long l2 = new Dyadic(l).toBigInteger().longValue();
			assert l == l2 : String.format("testToBigInteger %d %d", l, l2);
		}
	}	

	@Test
	public void testMul() {
		for (long i = 0; i < 100000; i++) {
			Dyadic di = new Dyadic(i);
			long j = random.nextInt(100000);
			Dyadic dj = new Dyadic(j);
			assert i * j == di.mul(dj).toLong() : String.format("testMul %d * %d != %d", i, j, di.mul(dj).toLong()); 
		}
	}

	@Test
	public void testDiv() {
		for (long i = 0; i < 100000; i++) {
			Dyadic di = new Dyadic(i);
			long j = 1 + random.nextInt(100);
			if (i % j == 0) {
				Dyadic dj = new Dyadic(j);
				assert i / j == di.div(dj).toLong() : String.format("testDiv %d * %d != %d", i, j, di.div(dj).toLong()); 
			}
		}
	}	

	/** 
	 *	@returns true iff y is within relative or absolute 'epsilon' of x. 
	 *  By default, 'epsilon' is 1e-6.
	 */
	public static boolean fuzzyEqual(double x, double y, double epsilon) {
		// Check absolute precision.
		if (Math.abs(x - y) <= epsilon) {
			return true;
		}
		// Is x or y too close to zero?
		if ((Math.abs(x) <= epsilon) || (Math.abs(y) <= epsilon)) {
				return false;
			}
		// Check relative precision.
		return (Math.abs((x - y) / x) <= epsilon) || (Math.abs((x - y) / y) <= epsilon);
	}

	public static Random random = new Random();	
}
