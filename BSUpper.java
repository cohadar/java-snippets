import java.util.*;
import java.io.*;

public class BSUpper {

	// MUST be sorted in asc order.
	final int[] A;
	
	public BSUpper(int[] A) {
		this.A = A;
	}
	
	// find first index in [l, r] for which A[index] > v
	public int upperBound(int l, int r, int v) {
		if (l <= r) {
			int m = (l + r) >>> 1;
			if (A[m] > v) {
				return upperBound(l, m - 1, v);
			} else {
				return upperBound(m + 1, r, v);
			}
		}
		return l;
	}



}
