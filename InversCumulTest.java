import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class InversCumulTest {

	@Test
	public void test() {
		long[] A = new long[200];
		InversCumul ic = new InversCumul(200);
		for (int j = 0; j < 100; j++) {
			int a = random.nextInt(A.length);
			int b = random.nextInt(A.length);
			int l = Math.min(a, b);
			int r = Math.min(a, b);
			int d = random.nextInt(1000);
			for (int c = l; c <= r; c++) {
				A[c] += d;
			}
			ic.addValueToRange(l, r, d);
		}
		long[] B = ic.toArray();
		assertArrayEquals(A, B);
	}

	public static Random random = new Random();
}
