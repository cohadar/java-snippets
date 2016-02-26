import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class ArrayzTest {

	@Test
	public void test_random() {
		int n = 2000;
		int low = -1000;
		int high = 1000;
		int[] A = Arrayz.random(n, low, high);
		assertEquals(n, A.length);
		for (int i = 0; i < A.length; i++) {
			assertTrue(low <= A[i]);
			assertTrue(A[i] <= high);
		}
	}

	@Test
	public void test_sum() {
		assertEquals(15, Arrayz.sum(new int[] { 2, 3, 4, 1, 5 } ));
		assertEquals(-2, Arrayz.sum(new int[] { 3, -3, 2, 4, -1, -7 } ));
	}

	@Test
	public void test_sum_range() {
		assertEquals(8, Arrayz.sum(new int[] { 2, 3, 4, 1, 5 }, 1, 3));
		assertEquals(6, Arrayz.sum(new int[] { 3, -3, 2, 4, -1, -7 }, 0, 3));
	}

	@Test
	public void test_cumul() {
		int[] A = Arrayz.random(2000, -1000, 1000);
		int[] C = Arrayz.cumul(A);
		assertEquals(A.length, C.length);
		for (int i = 0; i < A.length; i++) {
			assertEquals(Arrayz.sum(A, 0, i), C[i]);
		}
	}

}
