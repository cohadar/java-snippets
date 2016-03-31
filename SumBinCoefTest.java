import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class SumBinCoefTest {

	static long sumBinomialCoef(long[][] B, int n, int k) {
		assert 0 <= k && k <= n : "out of range, k: " + k;
		long sum = 0;
		for (int i = 0; i <= k; i++) {
			sum += B[n][i];
		}
		return sum;
	}

	@Test
	public void test() {
		long[][] B = SumBinCoef.binomial(66);
		for (int n = 0; n <= 61; n++) {
			for (int k = 0; k <= n; k++) {
				long s1 = SumBinCoef.sumBinomialCoef(n, k);
				long s2 = sumBinomialCoef(B, n, k);
				assertEquals(s1, s2);
			}
		}
	}

}
