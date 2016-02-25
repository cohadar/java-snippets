import java.util.*;
import java.io.*;

public class BSLower {

	// MUST be sorted in asc order.
	final int[] A;
	
	public BSLower(int[] A) {
		this.A = A;
	}
	
	// find first index in [l, r] for which A[index] >= v
	public int lowerBound(int l, int r, int v) {
		if (l <= r) {
			int m = (l + r) >>> 1;
			if (A[m] >= v) {
				return lowerBound(l, m - 1, v);
			} else {
				return lowerBound(m + 1, r, v);
			}
		}
		return l;
	}



}
