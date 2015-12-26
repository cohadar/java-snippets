import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class FenwickTreeTest {
	private Random random = new Random();

	private int[] randomArray(int n) {
		int[] A = new int[n];
		for (int i = 0; i < A.length; i++) {
			A[i] = random.nextInt(1000);
		}
		return A;
	}

	private int[] cumulArray(int[] A) {
		int[] C = new int[A.length];
		for (int i = 0, cumul = 0; i < A.length; i++) {
			cumul += A[i];
			C[i] = cumul;
		}
		return C;
	}

	private int rangeSum(int[] A, int low, int high) {
		int sum = 0;
		for (int i = low; i <= high; i++) {
			sum += A[i];
		}
		return sum;
	}

	private void rangeUpdate(int[] A, int low, int high, int value) {
		for (int i = low; i <= high; i++) {
			A[i] += value;
		}
	}	

	@Test
	public void testPointUpdate() {
		int n = 2000;
		int[] A = randomArray(n);
		int[] C = cumulArray(A);
		FenwickTree fw = new FenwickTree(n);
		for (int i = 0; i < A.length; i++) {
			fw.pointUpdate(i + 1, A[i]);
		}
		for (int i = 0; i < A.length; i++) {
			assertEquals(C[i], fw.pointQuery(i + 1));
		}
		for (int i = 0; i < 3 * n; i++) {
			int a = random.nextInt(n);
			int b = random.nextInt(n);
			if (a > b) {
				int t = a;
				a = b;
				b = t;
			}
			assertEquals(rangeSum(A, a, b), fw.rangeQuery(a + 1, b + 1));	
		}
	}

	@Test
	public void testRangeUpdate() {
		int n = 2000;
		int[] A = randomArray(n);
		FenwickTree fw = new FenwickTree(n);
		for (int i = 0; i < A.length; i++) {
			fw.pointUpdate(i + 1, A[i]);
		}
		for (int i = 0; i < 3 * n; i++) {
			int a = random.nextInt(n);
			int b = random.nextInt(n);
			if (a > b) {
				int t = a;
				a = b;
				b = t;
			}
			assertEquals(rangeSum(A, a, b), fw.rangeQuery(a + 1, b + 1));	
			int val = random.nextInt(1000);
			rangeUpdate(A, a, b, val);
			fw.rangeUpdate(a + 1, b + 1, val);
		}
	}	

}
