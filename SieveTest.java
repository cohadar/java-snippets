import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class SieveTest {

	public static boolean[] sieve(int n) {
		boolean[] P = new boolean[n];
		Arrays.fill(P, true);
		P[0] = false;
		P[1] = false;
		for (int i = 2; i * i <= n; i++) {
			if (P[i]) {
				for (int j = i * i; j < n; j += i) {
					P[j] = false;
				}
			}
		}
		return P;
	}

	@Test
	public void test() {
		int n = 100;
		boolean[] P = sieve(n);
		for (int i = 0; i < n; i++) {
			if (P[i]) {
				assertTrue("not prime: " + i, isPrime(i));
				debug(i);
			}
		}
	}

	public static boolean isPrime(long num) {
		if (num < 2) { 
			return false; 
		}
		if (num == 2) { 
			return true; 
		}
		if (num % 2 == 0) { 
			return false;
		}
		for (int i = 3; i * i <= num; i += 2) {
			if (num % i == 0) { 
				return false;
			}
		}
		return true;
	}	

	static void debug(Object...os) {
		// System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
