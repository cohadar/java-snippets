import java.util.*;
import java.io.*;

/**
  * O(log n) operations on array ranges
  */
public class SegmentTree {

	public static interface OP {
		public int zero();
		public int binary(int va, int vb);
	}

	final OP op;
	final int n;
	final int in;
	final int[] T;

	public SegmentTree(OP op, int n) {
		this.op = op;
		this.n = n;
		this.in = indexLength(n);
		this.T = new int[2 * in + 1];
		fill(null);
	}	
	
	public SegmentTree(OP op, int[] A) {
		this.op = op;
		this.n = A.length;
		this.in = indexLength(n);
		this.T = new int[2 * in + 1];
		fill(A);
	}

	private int indexLength(int n) {
		int height = (int) Math.ceil(Math.log(n) / Math.log(2));
		return (1 << height) - 1;
	}

	private void fill(int[] A) {
		Arrays.fill(T, op.zero());
		if (A != null) {
			for (int i = 0; i < A.length; i++) {
				T[in + i] = A[i];
			}
		}
		for (int p = in - 1; p >= 0; p--) {
			T[p] = op.binary(T[2 * p + 1], T[2 * p + 2]);
		}
	}

	// warning: assuming OP will now overflow integer
	private int queryRange(int l, int r, int sl, int sr, int p) {
		if (l <= sl && sr <= r) {
			return T[p];
		}
		if (sr < l || r < sl) {
			return op.zero();
		}
		int m = (sl + sr) >>> 1;
		int va = queryRange(l, r, sl, m, 2 * p + 1);
		int vb = queryRange(l, r, m + 1, sr, 2 * p + 2);
		return op.binary(va, vb);
	}	

	// warning: assuming OP will now overflow integer
	public int queryRange(int l, int r) {
		assert l <= r;
		assert 0 <= l;
		assert r < n;
		return queryRange(l, r, 0, in, 0);
	}

	public void updateValue(int i, int val) {
		assert 0 <= i;
		assert i < n;
		int p = in + i;
		while (p > 0) {
			T[p] = val;
			p = (p - 1) / 2;
			val = op.binary(T[2 * p + 1], T[2 * p + 2]);
		}
		T[0] = val;
	}

}
