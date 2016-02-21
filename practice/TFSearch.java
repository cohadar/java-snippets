import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class TFSearch {

	public static int bruteLower(boolean[] B) {
		for (int i = 0; i < B.length; i++) {
			if (B[i] == true) {
				return i;
			}
		}
		return B.length;
	}

	public static int bruteUpper(boolean[] B) {
		int upper = -1;
		for (int i = 0; i < B.length; i++) {
			if (B[i] == false) {
				upper = i;
			}
		}
		return upper;
	}	

	public static int lowerBountTrue(boolean[] B, int l, int r) {
		while (l <= r) {
			int m = (l + r) >>> 1;
			if (B[m]) {
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return l;
	}

	public static int upperBoundFalse(boolean[] B, int l, int r) {
		return lowerBountTrue(B, l, r) - 1;
	}	

	public static void main(String[] args) {
		List<boolean[]> L = new ArrayList<>();
		L.add(new boolean[] {false, false});
		L.add(new boolean[] {false, true});
		L.add(new boolean[] {true, true});
		L.add(new boolean[] {false, false, false});
		L.add(new boolean[] {false, false, true});
		L.add(new boolean[] {false, true, true});
		L.add(new boolean[] {true, true, true});
		L.add(new boolean[] {true, true, true, true, true, true, true, true});
		L.add(new boolean[] {false, false, false, true, true, true, true, true});
		L.add(new boolean[] {false, false, false, false, false, false, false, false});
		for (boolean[] B : L) {
			int a = bruteLower(B);
			int b = lowerBountTrue(B, 0, B.length - 1);
			assert a == b : "a=" + a + ", b=" + b;
		}
		for (boolean[] B : L) {
			int a = bruteUpper(B);
			int b = upperBoundFalse(B, 0, B.length - 1);
			assert a == b : "a=" + a + ", b=" + b;
		}		
	}

}
