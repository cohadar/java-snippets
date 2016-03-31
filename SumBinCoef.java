import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class SumBinCoef {

	static long[][] binomial(int max_n) {
		assert 0 <= max_n && max_n <= 66 : "out of range, max_n: " + max_n;
		long[][] B = new long[max_n + 1][max_n + 1];
		for (int n = 0; n < B.length; n++) {
			B[n][0] = 1;
			B[n][n] = 1;
		}
		for (int n = 0; n < B.length; n++) {
			for (int k = 1; k < n; k++) {
				B[n][k] = B[n - 1][k] + B[n - 1][k - 1];
			}
		}
		return B;
	}

	static long sumBinomialCoef(int n, int k) {
		assert 0 <= n && n <= 61 : "out of range, n: " + n;
		assert 0 <= k && k <= n : "out of range, k: " + k;
		long sum = 1;
		long nCi = 1;
		for (int i = 1; i <= k; i++) {
			nCi = nCi * (n - i + 1) / i;
			sum += nCi;
		}
		return sum;
	}

}
