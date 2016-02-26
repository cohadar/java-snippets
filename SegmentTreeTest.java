import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * O(log n) operations on array ranges
  */
public class SegmentTreeTest {

	static long sumA(int[] A, int l, int r) {
		long ret = 0;
		for (int i = l; i <= r; i++) {
			ret += A[i];
		}
		return ret;
	}

	static void setA(int[] A, int i, int val) {
		A[i] = val;
	}

	static long sumC(int[] C, int l, int r) {
		if (l == 0) {
			return C[r];
		} else {
			return C[r] - C[l - 1];
		}
	}

	static void setC(int[] C, int i, int val) {
		for (int c = i; c < C.length; c++) {
			C[i] += val;
		}
	}

	@Test
	public void test() {
		int[] A = randArray(2000, -1000, 1000);
		int[] C = cumul(A);
	}

	static int[] cumul(int[] A) {
		int[] C = new int[A.length];
		int cumul = 0;
		for (int i = 0; i < A.length; i++) {
			cumul += A[i];
			C[i] = cumul;
		}
		return C;
	}

	static Random random = new Random();

	static int[] randArray(int n, int low, int high) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = low + random.nextInt(high - low + 1);
		}
		return A;
	}

}
