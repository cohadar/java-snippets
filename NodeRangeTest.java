import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class NodeRangeTest {

	@Test
	public void test() {
		for (int nd = 1; nd <= 10000; nd++) {
		for (int nn = 1; nn <= 100; nn++) {
			test(nn, nd);
		}
		}
	}

	public static int[] nodeDataRange(int id, int nn, int nd) {
		int jl = id * nd / nn;
		int jr = (id + 1) * nd / nn - 1;
		return new int[] { jl, jr };
	}

	private void test(int nn, int nd) {
		int[] B = new int[nd];
		for (int id = 0; id < nn; id++) {
			int[] lr = nodeDataRange(id, nn, nd);
			int l = lr[0];
			int r = lr[1];
			for (int j = l; j <= r; j++) {
				B[j]++;
			}
		}
		assertAll(B);
	}

	void assertAll(int[] B) {
		for (int i = 0; i < B.length; i++) {
			if (B[i] != 1) {
				fail("" + i);
			}
		}
	}

	static void debug(Object...os) {
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

	@Test
	public void testRange() {
		int nn = 5;
		int nd = 12;
		for (int id = 0; id < nn; id++) {
			int[] lr = nodeDataRange(id, nn, nd);
			int delta = lr[1] - lr[0] + 1;
			debug(delta, lr);
		}
	}

}
