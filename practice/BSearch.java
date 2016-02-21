import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class BSearch {

	public static Random random = new Random();

	final int n;
	final int[] A;
	
	public BSearch(int n) {
		this.n = n;
		this.A = new int[n];
		for (int i = 0; i < A.length; i++) {
			A[i] = random.nextInt(1000) - 500;
		}
		Arrays.sort(A);
	}

	public int lib(int val) {
		return Arrays.binarySearch(A, val);
	}

	public int brute(int val) {
		return Arrays.binarySearch(A, val);
	}	

	public int bsearch(int val, int l, int r) {
		if (l <= r) {
			int m = (l + r) >>> 1;
			if (A[m] > val) {
				return bsearch(val, l, m - 1);
			} else if (A[m] < val) {
				return bsearch(val, m + 1, r);
			} else {
				return m;
			}
		}
		return ~l;
	}

	public int solve(int val) {
		return bsearch(val, 0, A.length - 1);
	}

	public static BSearch load() {
		return new BSearch(1000);
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
			BSearch o = BSearch.load();
			int val = random.nextInt(1200) - 600;
			o.assertIndices(o.brute(val), o.solve(val));
			o.assertIndices(o.lib(val), o.solve(val));
		}
	}

}
