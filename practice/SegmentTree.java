import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class SegmentTree {

	final int n;
	final int[] A;
	final int[] T;
	
	public SegmentTree(int[] A) {
		this.n = A.length;
		this.A = A;
		this.T = new int[2 * n + 100]; // to n - 1
		load(0, n-1, 0);
	}

	public int load(int l, int r, int p) {
		debug(l, r, p);
		if (l == r) {
			return T[p] = A[l];
		}
		int m = (l + r) >>> 1;
		int vl = load(l, m, 2 * p + 1);
		int vr = load(m + 1, r, 2 * p + 2);
		return T[p] = Math.min(vl, vr);
	}

	public int rmq(int l, int r, int al, int ar, int p) {
		if (l > ar || r < al) {
			return Integer.MAX_VALUE;
		}
		if (l <= al && ar <= r) {
			return T[p];
		}
		int m = (l + r) >>> 1;
		int ml = rmq(l, r, al, m, 2 * p + 1);
		int mr = rmq(l, r, m + 1, ar, 2 * p + 2);
		return Math.min(ml, mr);
	}

	public int rmq(int l, int r) {
		return rmq(l, r, 0, n-1, 0);
	}
	
	public static int min(int[] A, int l, int r) {
		int min = A[l];
		for (int i = l; i <= r; i++) {
			if (min > A[i]) {
				min = A[i];
			}
		}
		return min;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] A = scanArray(scanner, n);
		SegmentTree o = new SegmentTree(A);
		debug(o.n);
		debug(o.A);
		debug(o.T);
		for (int l = 0; l < n; l++) {
			for (int r = l; r < n; r++) {
				debug(l, r, o.rmq(l, r));
				assert min(A, l, r) == o.rmq(l, r) : "min(A, l, r) == o.rmq(l, r)";	
			}
		}
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
