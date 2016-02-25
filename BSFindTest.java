import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class BSFindTest {

	static final Random random = new Random();

	private void test(BSFind o) {
		for (int v = -1100; v <= 1100; v++) {
			int e = Arrays.binarySearch(o.A, 0, o.A.length, v); // note exclusive right
			int l = o.binarySearch(0, o.A.length - 1, v); // note inclusive right
			if (e >= 0) {
				assertEquals(o.A[e], o.A[l]);
			} else {
				assertEquals(e, l);
			}
		}
	}

	@Test
	public void test() {
		for (int i = 0; i < 100; i++) {
			int[] A = randArray(2000, -1000, 1000);
			Arrays.sort(A);
			test(new BSFind(A));
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
