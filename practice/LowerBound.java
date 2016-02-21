import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class LowerBound {

	public static Random random = new Random();

	final int n;
	final int[] A;
	
	public LowerBound(int n) {
		this.n = n;
		this.A = new int[n];
		for (int i = 0; i < A.length; i++) {
			A[i] = random.nextInt(1000) - 500;
		}
		Arrays.sort(A);
	}

	public int brute(int val) {
		for (int i = 0; i < A.length; i++) {
			if (A[i] >= val) {
				return i;
			}
		}
		return A.length;
	}	

	public int lowerBound(int val, int l, int r) {
		if (l <= r) {
			int m = (l + r) >>> 1;
			if (A[m] >= val) {
				return lowerBound(val, l, m - 1);
			} else {
				return lowerBound(val, m + 1, r);
			}
		}
		return l;
	}

	public int solve(int val) {
		return lowerBound(val, 0, A.length - 1);
	}

	public static LowerBound load() {
		return new LowerBound(1000);
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
			LowerBound o = LowerBound.load();
			int val = random.nextInt(1200) - 600;
			System.out.println(val);
			o.assertIndices(o.brute(val), o.solve(val));
		}
	}

}
