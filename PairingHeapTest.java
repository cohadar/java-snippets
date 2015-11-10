import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;


public class PairingHeapTest {

	@Test(expected = NoSuchElementException.class)
	public void testGetMinNoneKey() {
		PairingHeap<Integer, Character> ph = new PairingHeap<>();
		ph.getMinKey();
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetMinNoneValue() {
		PairingHeap<Integer, Character> ph = new PairingHeap<>();
		ph.getMinValue();
	}

	@Test
	public void testGetMin() {
		PairingHeap<Integer, Character> ph = new PairingHeap<>();
		
		ph.put(5, 'A');
		assertEquals(5, (int)ph.getMinKey());
		assertEquals('A', (char)ph.getMinValue());
		
		ph.put(1, 'B');
		assertEquals(1, (int)ph.getMinKey());
		assertEquals('B', (char)ph.getMinValue());		
		
		ph.put(7, 'C');
		assertEquals(1, (int)ph.getMinKey());
		assertEquals('B', (char)ph.getMinValue());	
	}

	@Test
	public void testMeld00() {
		PairingHeap<Integer, Character> ph1 = new PairingHeap<>();
		PairingHeap<Integer, Character> ph2 = new PairingHeap<>();		
		assertEquals(0, ph1.size());
		assertEquals(0, ph2.size());
		ph1.meld(ph2);
		assertEquals(0, ph1.size());
		assertEquals(0, ph2.size());
	}

	@Test
	public void testMeld10() {
		PairingHeap<Integer, Character> ph1 = new PairingHeap<>();
		PairingHeap<Integer, Character> ph2 = new PairingHeap<>();		
		ph1.put(5, 'A');
		assertEquals(1, ph1.size());
		assertEquals(0, ph2.size());
		ph1.meld(ph2);
		assertEquals(1, ph1.size());
		assertEquals(0, ph2.size());
	}

	@Test
	public void testMeld01() {
		PairingHeap<Integer, Character> ph1 = new PairingHeap<>();
		PairingHeap<Integer, Character> ph2 = new PairingHeap<>();		
		ph2.put(9, 'D');
		assertEquals(0, ph1.size());
		assertEquals(1, ph2.size());
		ph1.meld(ph2);
		assertEquals(1, ph1.size());
		assertEquals(0, ph2.size());
	}

	@Test
	public void testMeld11() {
		PairingHeap<Integer, Character> ph1 = new PairingHeap<>();
		PairingHeap<Integer, Character> ph2 = new PairingHeap<>();		
		ph1.put(5, 'A');
		ph2.put(9, 'D');
		assertEquals(1, ph1.size());
		assertEquals(1, ph2.size());
		ph1.meld(ph2);
		assertEquals(2, ph1.size());
		assertEquals(0, ph2.size());
	}	

	@Test
	public void testMeld() {
		PairingHeap<Integer, Character> ph1 = new PairingHeap<>();
		PairingHeap<Integer, Character> ph2 = new PairingHeap<>();
		ph1.put(5, 'A');
		ph1.put(1, 'B');
		ph1.put(7, 'C');

		ph2.put(9, 'D');
		ph2.put(4, 'E');
		ph2.put(0, 'F');

		assertEquals(3, ph1.size());
		assertEquals(3, ph2.size());
		ph1.meld(ph2);
		assertEquals(6, ph1.size());
		assertEquals(0, ph2.size());
	}

	@Test
	public void testRemoveMin() {
		PairingHeap<Integer, Character> ph = new PairingHeap<>();
		ph.put(5, 'A');
		ph.put(1, 'B');
		ph.put(7, 'C');
		assertEquals((Character)'B', ph.removeMin());
		assertEquals((Character)'A', ph.removeMin());
		assertEquals((Character)'C', ph.removeMin());
	}

	@Test
	public void testMeldRemoveMin() {
		PairingHeap<Integer, Character> ph1 = new PairingHeap<>();
		PairingHeap<Integer, Character> ph2 = new PairingHeap<>();
		ph1.put(5, 'A');
		ph1.put(1, 'B');
		ph1.put(7, 'C');

		ph2.put(9, 'D');
		ph2.put(4, 'E');
		ph2.put(0, 'F');

		ph1.meld(ph2);
		
		assertEquals(6, ph1.size());
		assertEquals(0, ph2.size());

		String keys = "";
		String values = "";
		for (int i = 0; i < 6; i++) {
			keys += ph1.getMinKey();
			values += ph1.removeMin();
		}

		assertEquals(keys, "014579");
		assertEquals(values, "FBEACD");

		assertEquals(0, ph1.size());
		assertEquals(0, ph2.size());
	}	

}
