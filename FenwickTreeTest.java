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

	private int rangeSum(int[] A, int low, int high) {
		int sum = 0;
		for (int i = low; i <= high; i++) {
			sum += A[i];
		}
		return sum;
	}

	private int getMin(int[] A, int high) {
		int min = A[0];
		for (int i = 1; i <= high; i++) {
			min = Math.min(min, A[i]);
		}
		return min;
	}

	private int getMax(int[] A, int high) {
		int max = A[0];
		for (int i = 1; i <= high; i++) {
			max = Math.max(max, A[i]);
		}
		return max;
	}

	private void addValueToRange(int[] A, int low, int high, int value) {
		for (int i = low; i <= high; i++) {
			A[i] += value;
		}
	}	

	private int findHigherSumIndex(int[] A, int sum) {
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
		int[] A = randomArray(n);
		FenwickTree fw = new FenwickTree(A);		
		int[] B = fw.toArray();
		assertArrayEquals(A, B);
	}	

	@Test
	public void testAddValue() { 
		int n = 2000;
		FenwickTree fw = new FenwickTree(n);		
		int[] A = randomArray(n);
		for (int i = 0; i < A.length; i++) {
			fw.addValue(i + 1, A[i]);
		}
		int[] B = fw.toArray();
		assertArrayEquals(A, B);
	}	

	@Test
	public void testGetValue() { 
		int n = 2000;
		int[] A = randomArray(n);
		FenwickTree fw = new FenwickTree(A);		
		for (int i = 0; i < A.length; i++) {
			assertEquals(A[i], fw.getValue(i + 1));
		}
	}		

	@Test
	public void testGetSum() { 
		int n = 2000;
		int[] A = randomArray(n);
		FenwickTree fw = new FenwickTree(A);		
		for (int i = 0, cumul = 0; i < A.length; i++) {
			cumul += A[i];
			assertEquals(cumul, fw.getSum(i + 1));
		}
	}		

	@Test
	public void testGetRangeSum() { 
		int n = 500;
		int[] A = randomArray(n);
		FenwickTree fw = new FenwickTree(A);		
		for (int i = 0; i < A.length; i++) {
			for (int j = i; j < A.length; j++) {
				int sum = rangeSum(A, i, j);
				assertEquals(sum, fw.getRangeSum(i + 1, j + 1));
			}
		}
	}		

	@Test
	public void testAddValueToRange() {
		int n = 500;
		int[] A = randomArray(n);
		FenwickTree fw = new FenwickTree(A);		
		for (int i = 0; i < A.length; i++) {
			for (int j = i; j < A.length; j++) {
				int value = random.nextInt(100);
				addValueToRange(A, i, j, value);
				fw.addValueToRange(i + 1, j + 1, value);
			}
		}
		int[] B = fw.toArray();
		assertArrayEquals(A, B);
	}	

	@Test
	public void testFindHigherValueIndex() {
		int n = 500;
		int[] A = randomArray(n);
		FenwickTree fw = new FenwickTree(A);		
		for (int i = 0; i < A.length; i++) {
			int sum = fw.getSum(i + 1);
			assertEquals(findHigherSumIndex(A, sum) + 1, fw.findHigherSumIndex(sum));
		}
		int sum = 10000000;
		assertEquals(findHigherSumIndex(A, sum) + 1, fw.findHigherSumIndex(sum));
		sum = -10000000;
		assertEquals(findHigherSumIndex(A, sum) + 1, fw.findHigherSumIndex(sum));		
	}	

	@Test
	public void testGetMin() {
		int n = 500;
		int[] A = randomArray(n);
		FenwickTree fw = new FenwickTree(A);	
		for (int i = 0; i < A.length; i++) {
			assertEquals(getMin(A, i), fw.getMin(i + 1));
		}
	}

	@Test
	public void testGetMax() {
		int n = 500;
		int[] A = randomArray(n);
		FenwickTree fw = new FenwickTree(A);	
		for (int i = 0; i < A.length; i++) {
			assertEquals(getMax(A, i), fw.getMax(i + 1));
		}
	}	

	@Test
	public void testAbsLE() {
		int n = 500;
		int[] A = randomArray(n);
		FenwickTree fw = new FenwickTree(A);		
		fw.addValueToRange(1, n, -500);
		int[] B = fw.toArray();	
		int c1 = 0;
		for (int i = 0; i < B.length; i++) {
			if (Math.abs(B[i]) <= 100) {
				c1++;
			}
		}
	}

}
