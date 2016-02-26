import java.util.*;
import java.io.*;

/**
  * integer array manipulation functions
  */
public class Arrayz {

	static Random random = new Random();

	// warning: assuming sum will now overflow integer
	public static int sum(int[] A) {
		int ret = 0;
		for (int i = 0; i < A.length; i++) {
			ret += A[i];
		}
		return ret;
	}

	// warning: assuming sum will now overflow integer
	public static int sum(int[] A, int l, int r) {
		int ret = 0;
		for (int i = l; i <= r; i++) {
			ret += A[i];
		}
		return ret;
	}	

	// warning: assuming cumul sums will now overflow integer
	public static int[] cumul(int[] A) {
		int[] C = new int[A.length];
		int cumul = 0;
		for (int i = 0; i < A.length; i++) {
			cumul += A[i];
			C[i] = cumul;
		}
		return C;
	}

	public static int[] random(int n, int low, int high) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = low + random.nextInt(high - low + 1);
		}
		return A;
	}

}
