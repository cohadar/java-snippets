import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;


public class PairingHeapTest {

	@Test
	public void testMeld(){
		PairingHeap<Integer, Character> ph1 = new PairingHeap<>();
		PairingHeap<Integer, Character> ph2 = new PairingHeap<>();
		ph1.put(5, 'A');
		ph1.put(1, 'B');
		ph1.put(7, 'C');

		ph2.put(9, 'D');
		ph2.put(4, 'E');
		ph2.put(0, 'F');

		assertEquals(3, ph1.size());
		ph1.meld(ph2);
		assertEquals(6, ph1.size());
	}

	@Test
	public void testRemoveMin(){
		PairingHeap<Integer, Character> ph = new PairingHeap<>();
		ph.put(5, 'A');
		ph.put(1, 'B');
		ph.put(7, 'C');
		assertEquals((Character)'B', ph.removeMin());
		assertEquals((Character)'A', ph.removeMin());
		assertEquals((Character)'C', ph.removeMin());
	}
}