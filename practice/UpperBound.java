import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class UpperBound {

	public static Random random = new Random();

	final int n;
	final int[] A;
	
	public UpperBound(int n) {
		this.n = n;
		this.A = new int[n];
		for (int i = 0; i < A.length; i++) {
			A[i] = random.nextInt(1000) - 500;
		}
		Arrays.sort(A);
	}

	public int brute(int val) {
		for (int i = 0; i < A.length; i++) {
			if (A[i] > val) {
				return i;
			}
		}
		return A.length;
	}	

	public int upperBound(int val, int l, int r) {
		while (l <= r) {
			int m = (l + r) >>> 1;
			if (A[m] > val) {
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return l;
	}

	public int solve(int val) {
		return upperBound(val, 0, A.length - 1);
	}

	public static UpperBound load() {
		return new UpperBound(1000);
	}	

	public void assertIndices(int i, int j) {
		System.out.println(String.format("%d %d", i, j));
		if (i != j) {
			assert A[i] == A[j];
		}
	}

	public static void main(String[] args) {
		int t = 10000;
		while (t-->0) {
			UpperBound o = UpperBound.load();
			int val = random.nextInt(1200) - 600;
			o.assertIndices(o.brute(val), o.solve(val));
		}
	}

}
