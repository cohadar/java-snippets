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
		this.T = new int[2 * in];
		fill(null);
	}	
	
	public SegmentTree(OP op, int[] A) {
		this.op = op;
		this.n = A.length;
		this.in = indexLength(n);
		this.T = new int[2 * in];
		fill(A);
	}

	private static int left(int p) {
		return 2 * p;
	}

	private static int right(int p) {
		return 2 * p + 1;
	}

	private static int parent(int p) {
		return p / 2;
	}	

	private int indexLength(int n) {
		int height = (int) Math.ceil(Math.log(n) / Math.log(2));
		return 1 << height;
	}

	private void fill(int[] A) {
		Arrays.fill(T, op.zero());
		if (A != null) {
			for (int i = 0; i < A.length; i++) {
				T[in + i] = A[i];
			}
		}
		for (int p = in - 1; p > 0; p--) {
			T[p] = op.binary(T[left(p)], T[right(p)]);
		}
	}

	// warning: assuming OP will now overflow integer
	public int queryRange(int l, int r) {
		assert l <= r;
		assert 0 <= l;
		assert r < in;
		int res = op.zero();
		for (l += in, r += in; l <= r; l = parent(l), r = parent(r)) {
			if (l % 2 == 1) res = op.binary(res, T[l++]);
			if (r % 2 == 0) res = op.binary(res, T[r--]);
		}
		return res;		
	}

	public void updateValue(int i, int val) {
		assert 0 <= i;
		assert i < n;
		int p = in + i;
		while (p > 0) {
			T[p] = val;
			p = parent(p);
			val = op.binary(T[left(p)], T[right(p)]);
		}
	}

}
