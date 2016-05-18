import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class FenwickTreeTest {

	private Random random = new Random();

	private long[] randomArray(int n) {
		long[] A = new long[n];
		for (int i = 0; i < A.length; i++) {
			A[i] = random.nextInt(1000);
		}
		return A;
	}

	private long rangeSum(long[] A, int low, int high) {
		long sum = 0;
		for (int i = low; i <= high; i++) {
			sum += A[i];
		}
		return sum;
	}

	private long getMin(long[] A, int high) {
		long min = A[0];
		for (int i = 1; i <= high; i++) {
			min = Math.min(min, A[i]);
		}
		return min;
	}

	private long getMax(long[] A, int high) {
		long max = A[0];
		for (int i = 1; i <= high; i++) {
			max = Math.max(max, A[i]);
		}
		return max;
	}

	private void addValueToRange(long[] A, int low, int high, long value) {
		for (int i = low; i <= high; i++) {
			A[i] += value;
		}
	}	

	private long findHigherSumIndex(long[] A, long sum) {
		for (int i = 0, cumul = 0; i < A.length; i++) {
			cumul += A[i];
			if (cumul > sum) {
				return i;
			}
		}
		return A.length;
	}		

	@Test
	public void testToArray() { 
		int n = 2000;
		long[] A = randomArray(n);
		FenwickTree fw = new FenwickTree(A.length);
		for (int i = 0; i < A.length; i++) {
			fw.addValue(i + 1, A[i]);			
		}		
		long[] B = fw.toArray();
		assertArrayEquals(A, B);
	}	

	@Test
	public void testAddValue() { 
		int n = 2000;
		FenwickTree fw = new FenwickTree(n);		
		long[] A = randomArray(n);
		for (int i = 0; i < A.length; i++) {
			fw.addValue(i + 1, A[i]);
		}
		long[] B = fw.toArray();
		assertArrayEquals(A, B);
	}	

	@Test
	public void testGetValue() { 
		int n = 2000;
		long[] A = randomArray(n);
		FenwickTree fw = fenwickTree(A);		
		for (int i = 0; i < A.length; i++) {
			assertEquals(A[i], fw.getValue(i + 1));
		}
	}		

	@Test
	public void testGetSum() { 
		int n = 2000;
		long[] A = randomArray(n);
		FenwickTree fw = fenwickTree(A);		
		for (int i = 0, cumul = 0; i < A.length; i++) {
			cumul += A[i];
			assertEquals(cumul, fw.getSum(i + 1));
		}
	}		

	@Test
	public void testGetRangeSum() { 
		int n = 500;
		long[] A = randomArray(n);
		FenwickTree fw = fenwickTree(A);		
		for (int i = 0; i < A.length; i++) {
			for (int j = i; j < A.length; j++) {
				long sum = rangeSum(A, i, j);
				assertEquals(sum, fw.getRangeSum(i + 1, j + 1));
			}
		}
	}		

	@Test
	public void testAddValueToRange() {
		int n = 500;
		long[] A = randomArray(n);
		FenwickTree fw = fenwickTree(A);		
		for (int i = 0; i < A.length; i++) {
			for (int j = i; j < A.length; j++) {
				long value = random.nextInt(100);
				addValueToRange(A, i, j, value);
				fw.addValueToRange(i + 1, j + 1, value);
			}
		}
		long[] B = fw.toArray();
		assertArrayEquals(A, B);
	}	

	@Test
	public void testFindHigherValueIndex() {
		int n = 500;
		long[] A = randomArray(n);
		FenwickTree fw = fenwickTree(A);		
		for (int i = 0; i < A.length; i++) {
			long sum = fw.getSum(i + 1);
			assertEquals(findHigherSumIndex(A, sum) + 1, fw.findHigherSumIndex(sum));
		}
		long sum = 10000000;
		assertEquals(findHigherSumIndex(A, sum) + 1, fw.findHigherSumIndex(sum));
		sum = -10000000;
		assertEquals(findHigherSumIndex(A, sum) + 1, fw.findHigherSumIndex(sum));		
	}	

	@Test
	public void testGetMin() {
		int n = 500;
		long[] A = randomArray(n);
		FenwickTree fw = fenwickTree(A);	
		for (int i = 0; i < A.length; i++) {
			assertEquals(getMin(A, i), fw.getMin(i + 1));
		}
	}

	@Test
	public void testGetMax() {
		int n = 500;
		long[] A = randomArray(n);
		FenwickTree fw = fenwickTree(A);	
		for (int i = 0; i < A.length; i++) {
			assertEquals(getMax(A, i), fw.getMax(i + 1));
		}
	}	

	public static FenwickTree fenwickTree(int[] A) {
		FenwickTree fw = new FenwickTree(A.length);
		for (int i = 0; i < A.length; i++) {
			fw.addValue(i + 1, A[i]);
		}
		return fw;
	}

	public static FenwickTree fenwickTree(long[] A) {
		FenwickTree fw = new FenwickTree(A.length);
		for (int i = 0; i < A.length; i++) {
			fw.addValue(i + 1, A[i]);
		}
		return fw;
	}

}
