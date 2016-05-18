import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class InversCumul {
	final int n;
	final long[] X;
	
	public InversCumul(int n) {
		this.n = n;
		this.X = new long[n + 1];
	}

	public void addValueToRange(int l, int r, long d) {
		X[l] += d;
		X[r+1] -= d;
	}

	public long[] toArray() {
		long[] R = new long[n];
		long cumul = 0;
		for (int i = 0; i < n; i++) {
			cumul += X[i];
			R[i] = cumul;
		}
		return R;
	}
}
