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
	final int[] T;

	public SegmentTree(OP op, int n) {
		this.op = op;
		this.n = n;
		this.T = new int[treeLength(n)];
	}	
	
	public SegmentTree(OP op, int[] A) {
		this.op = op;
		this.n = A.length;
		this.T = new int[treeLength(n)];
		fill(A, 0, n - 1, 0);
	}

	private int treeLength(int n) {
		int height = (int) Math.ceil(Math.log(n) / Math.log(2));
		return 2 * (1 << height) - 1;
	}

	private int fill(int[] A, int l, int r, int p) {
		if (l == r) {
			return T[p] = A[l];
		}
 		int m = (l + r) >>> 1;
 		int va = fill(A, l, m, 2 * p + 1);
 		int vb = fill(A, m + 1, r, 2 * p + 2);
		return T[p] = op.binary(va, vb);
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
		return queryRange(l, r, 0, n - 1, 0);
	}

	private int updateValue(int l, int r, int p, int i, int val) {
		if (i < l || r < i) {
			return T[p];
		}
		if (l == r) {
			return T[p] = val;
		}
 		int m = (l + r) >>> 1;
 		int va = updateValue(l, m, 2 * p + 1, i, val);
 		int vb = updateValue(m + 1, r, 2 * p + 2, i, val);
		return T[p] = op.binary(va, vb);
	}	

	public void updateValue(int i, int val) {
		assert 0 <= i;
		assert i < n;
		updateValue(0, n - 1, 0, i, val);
	}
	
}
