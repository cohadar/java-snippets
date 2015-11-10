import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;


public class PairingHeapTest {

	@Test(expected = NoSuchElementException.class)
	public void testGetMinNoneKey() {
		PairingHeap<Character> ph = new PairingHeap<>();
		ph.element();
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetMinNoneValue() {
		PairingHeap<Character> ph = new PairingHeap<>();
		ph.element();
	}

	@Test
	public void testElement() {
		PairingHeap<Integer> ph = new PairingHeap<>();
		
		ph.add(5);
		assertEquals(5, (int)ph.element());
		
		ph.add(1);
		assertEquals(1, (int)ph.element());		
		
		ph.add(7);
		assertEquals(1, (int)ph.element());
	}

	@Test
	public void testMeld00() {
		PairingHeap<Integer> ph1 = new PairingHeap<>();
		PairingHeap<Integer> ph2 = new PairingHeap<>();		
		assertEquals(0, ph1.size());
		assertEquals(0, ph2.size());
		ph1.meld(ph2);
		assertEquals(0, ph1.size());
		assertEquals(0, ph2.size());
	}

	@Test
	public void testMeld10() {
		PairingHeap<Integer> ph1 = new PairingHeap<>();
		PairingHeap<Integer> ph2 = new PairingHeap<>();		
		ph1.add(5);
		assertEquals(1, ph1.size());
		assertEquals(0, ph2.size());
		ph1.meld(ph2);
		assertEquals(1, ph1.size());
		assertEquals(0, ph2.size());
	}

	@Test
	public void testMeld01() {
		PairingHeap<Integer> ph1 = new PairingHeap<>();
		PairingHeap<Integer> ph2 = new PairingHeap<>();		
		ph2.add(9);
		assertEquals(0, ph1.size());
		assertEquals(1, ph2.size());
		ph1.meld(ph2);
		assertEquals(1, ph1.size());
		assertEquals(0, ph2.size());
	}

	@Test
	public void testMeld11() {
		PairingHeap<Integer> ph1 = new PairingHeap<>();
		PairingHeap<Integer> ph2 = new PairingHeap<>();		
		ph1.add(5);
		ph2.add(9);
		assertEquals(1, ph1.size());
		assertEquals(1, ph2.size());
		ph1.meld(ph2);
		assertEquals(2, ph1.size());
		assertEquals(0, ph2.size());
	}	

	@Test
	public void testMeld() {
		PairingHeap<Integer> ph1 = new PairingHeap<>();
		PairingHeap<Integer> ph2 = new PairingHeap<>();
		ph1.add(5);
		ph1.add(1);
		ph1.add(7);

		ph2.add(9);
		ph2.add(4);
		ph2.add(0);

		assertEquals(3, ph1.size());
		assertEquals(3, ph2.size());
		ph1.meld(ph2);
		assertEquals(6, ph1.size());
		assertEquals(0, ph2.size());
	}

	@Test
	public void testRemove() {
		PairingHeap<Integer> ph = new PairingHeap<>();
		ph.add(5);
		ph.add(1);
		ph.add(7);
		assertEquals(1, (int)ph.remove());
		assertEquals(5, (int)ph.remove());
		assertEquals(7, (int)ph.remove());
	}

	@Test
	public void testMeldRemove() {
		PairingHeap<Integer> ph1 = new PairingHeap<>();
		PairingHeap<Integer> ph2 = new PairingHeap<>();
		ph1.add(5);
		ph1.add(1);
		ph1.add(7);

		ph2.add(9);
		ph2.add(4);
		ph2.add(0);

		ph1.meld(ph2);
		
		assertEquals(6, ph1.size());
		assertEquals(0, ph2.size());

		String values = "";
		for (int i = 0; i < 6; i++) {
			values += ph1.remove();
		}

		assertEquals(values, "014579");

		assertEquals(0, ph1.size());
		assertEquals(0, ph2.size());
	}	

	@Test(timeout=1000)
	public void testPriorityQueueSorting() {
		int N = 1023456;
		Random random = new Random();
		PriorityQueue<Integer> ph = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			ph.add(random.nextInt());
		}
		assertFalse(ph.isEmpty());
		int prev = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			int m = ph.remove();
			assertTrue(prev <= m);
			prev = m;
		}
		assertTrue(ph.isEmpty());
	}

	// PairingHeap is about 2 times slower than PriorityQueue
	@Test(timeout=2000)
	public void testSorting() {
		int N = 1023456;
		Random random = new Random();
		PairingHeap<Integer> ph = new PairingHeap<>();
		for (int i = 0; i < N; i++) {
			ph.add(random.nextInt());
		}
		assertFalse(ph.isEmpty());
		int prev = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			int m = ph.remove();
			assertTrue(prev <= m);
			prev = m;
		}
		assertTrue(ph.isEmpty());
	}

	@Test(timeout=1000)
	public void testStress() {
		int N = 102345;
		Random random = new Random();
		PairingHeap<Integer> ph = new PairingHeap<>();
		for (int i = 0; i < N; i++) {
			ph.add(random.nextInt());
			ph.add(random.nextInt());
			ph.add(random.nextInt());
			ph.remove();
			ph.remove();
			ph.add(random.nextInt());
			ph.remove();
			ph.add(random.nextInt());
			ph.remove();
		}
		assertFalse(ph.isEmpty());
		int prev = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			int m = ph.remove();
			assertTrue(prev <= m);
			prev = m;
		}
		assertTrue(ph.isEmpty());
	}	

}
