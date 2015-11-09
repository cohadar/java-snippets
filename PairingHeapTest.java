import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;


public class PairingHeapTest {

	@Test
	public void testRemoveMinx(){
		PairingHeap<Integer, Character> ph = new PairingHeap<>();
		ph.put(5, 'A');
		ph.put(1, 'B');
		ph.put(7, 'C');
		assertEquals((Character)'B', ph.removeMin());
	}
}