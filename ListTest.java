import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class ListTest {

	@Test
	public void test() {
		Integer[] A = new Integer[1000];
		List<Integer> B = new LinkedList<>();
		for (int i = 0; i < 1000; i++) {
			A[i] = i;
			B.add(i);
		}
		System.out.println(Arrays.stream(A).map(Object::toString).collect(Collectors.joining(", ")));
	}

	public static int nextInt(int low, int high) {
		return low + random.nextInt(high - low + 1);
	}

	public static Random random = new Random();
	
	public static int[] randomArray(int n, int min, int max) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = min + random.nextInt(max - min + 1);
		}
		return A;
	}

	static void debug(Object...os) {
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
