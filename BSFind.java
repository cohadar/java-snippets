import java.util.*;
import java.io.*;

/**
  * Simple binary search
  */
public class BSFind {

	// MUST be sorted in asc order.
	final int[] A;
	
	public BSFind(int[] A) {
		this.A = A;
	}
	
	// find any index in [l, r] for which A[index] == v
	public int binarySearch(int l, int r, int v) {
		if (l <= r) {
			int m = (l + r) >>> 1;
			if (A[m] > v) {
				return binarySearch(l, m - 1, v);
			} else if (A[m] < v) {
				return binarySearch(m + 1, r, v);
			} else {
				return m;
			}
		}
		return ~l;
	}

}
