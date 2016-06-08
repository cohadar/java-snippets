import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class MyMapTest {

	static final int N = 1_000_000;

	public static Random random = new Random();

	@Test
	public void testPutGet() {
		Map<Integer, Integer> myMap = new MyMap<>();
		Map<Integer, Integer> control = new HashMap<>();
		for (int i = 0; i < N; i++) {
			int k = random.nextInt();
			int v = random.nextInt();
			myMap.put(k, v);
			control.put(k, v);
		}
		for (int k : control.keySet()) {
			assertEquals(myMap.get(k), control.get(k));
			int r = random.nextInt();
			assertEquals(myMap.get(r), control.get(r));
		}
	}

	@Test
	public void testPutRemoveGet() {
		Map<Integer, Integer> myMap = new MyMap<>();
		Map<Integer, Integer> control = new HashMap<>();
		for (int i = 0; i < N; i++) {
			int k = random.nextInt();
			int v = random.nextInt();
			myMap.put(k, v);
			control.put(k, v);
		}
		Set<Integer> keysToRemove = new HashSet<>();
		for (int k : control.keySet()) {
			if (random.nextBoolean()) {
				keysToRemove.add(k);
			}
		}
		for (int k : keysToRemove) {
			control.remove(k);
			myMap.remove(k);
		}
		assertEquals(myMap.size(), control.size());
		for (int k : control.keySet()) {
			assertEquals(myMap.get(k), control.get(k));
			int r = random.nextInt();
			assertEquals(myMap.get(r), control.get(r));
		}
	}

}
