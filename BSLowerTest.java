import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class BSLowerTest {

	static final Random random = new Random();

	private int lowerBound(int[] A, int l, int r, int v) {
		for (int i = l; i <= r; i++) {
			if (A[i] >= v) {
				return i;
			}
		}
		return r + 1;
	}

	private void test(BSLower o) {
		for (int v = -1100; v <= 1100; v++) {
			int e = lowerBound(o.A, 0, o.A.length-1, v);
			int l = o.lowerBound(0, o.A.length - 1, v);
			assertEquals(e, l);
		}
	}

	@Test
	public void test() {
		for (int i = 0; i < 100; i++) {
			int[] A = randArray(2000, -1000, 1000);
			Arrays.sort(A);
			test(new BSLower(A));
		}
	}

	static int[] randArray(int n, int low, int high) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = low + random.nextInt(high - low + 1);
		}
		return A;
	}

}
