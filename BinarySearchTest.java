import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class BinarySearchTest {

	private Random random = new Random();

	private int[] randomSortedArray(int n) {
		int[] A = new int[n];
		for (int i = 0; i < A.length; i++) {
			A[i] = random.nextInt(1000);
		}
		Arrays.sort(A);
		return A;
	}

	@Test
	public void testFind() {
		int n = 1000;
		final int[] A = randomSortedArray(n);
		BinarySearch bs = new BinarySearch() {
			public int getValue(int i) {
				return A[i];
			}
		};
		for (int i = 0; i < 3 * n; i++) {
			int value = random.nextInt(1000);
			int indexExpected = Arrays.binarySearch(A, 0, A.length, value);
			int indexActual = bs.find(0, A.length, value);
			if (indexExpected < 0) {
				assertEquals(indexExpected, indexActual);
			} else {
				assertEquals(A[indexExpected], A[indexActual]);
			}
		}
	}

	public int upperBound(int[] A, int left, int right, int value) {
		for (int i = left; i < right; i++) {
			if (A[i] > value) {
				return i;
			}
		}
		return right;
	}

	public int lowerBound(int[] A, int left, int right, int value) {
		for (int i = left; i < right; i++) {
			if (A[i] >= value) {
				return i;
			}
		}
		return right;
	}

	@Test
	public void testUpperBound() {
		int n = 1000;
		final int[] A = randomSortedArray(n);
		BinarySearch bs = new BinarySearch() {
			public int getValue(int i) {
				return A[i];
			}
		};
		for (int i = 0; i < 3 * n; i++) {
			int value = random.nextInt(1000);
			int indexExpected = upperBound(A, 0, A.length, value);
			int indexActual = bs.upperBound(0, A.length, value);
			assertEquals(indexExpected, indexActual);
			if (indexExpected >= 0 && indexExpected < A.length) {
				assertEquals(A[indexExpected], A[indexActual]);
			}
		}
	}

	@Test
	public void testLowerBound() {
		int n = 1000;
		final int[] A = randomSortedArray(n);
		BinarySearch bs = new BinarySearch() {
			public int getValue(int i) {
				return A[i];
			}
		};
		for (int i = 0; i < 3 * n; i++) {
			int value = random.nextInt(1000);
			int indexExpected = lowerBound(A, 0, A.length, value);
			int indexActual = bs.lowerBound(0, A.length, value);
			assertEquals(indexExpected, indexActual);
			if (indexExpected >= 0 && indexExpected < A.length) {
				assertEquals(A[indexExpected], A[indexActual]);
			}
		}
	}

}
