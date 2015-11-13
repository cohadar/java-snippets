import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class BitMatrixTest {
	@Test
	public void testWidthHeight() {
		BitMatrix bm = new BitMatrix(5, 3);
		assertEquals(5, bm.getWidth());
		assertEquals(3, bm.getHeight());
	}

	@Test
	public void testGetSet() {
		BitMatrix bm = new BitMatrix(100, 200);
		for (int y = 0; y < bm.getHeight(); y++) {
			for (int x = 0; x < bm.getWidth(); x++) {
				if (Math.abs(x - y) % 3 == 0) {
					bm.set(x, y);
				}
			}
		}
		for (int y = 0; y < bm.getHeight(); y++) {
			for (int x = 0; x < bm.getWidth(); x++) {
				if (Math.abs(x - y) % 3 == 0) {
					assertTrue(bm.get(x, y));
				} else {
					assertFalse(bm.get(x, y));
				}
			}
		}
	}

	@Test
	public void testClear() {
		BitMatrix bm = new BitMatrix(300, 200);
		for (int y = 0; y < bm.getHeight(); y++) {
			for (int x = 0; x < bm.getWidth(); x++) {
				bm.set(x, y);
			}
		}				
		for (int y = 0; y < bm.getHeight(); y++) {
			for (int x = 0; x < bm.getWidth(); x++) {
				if (Math.abs(x - y) % 2 == 0) {
					bm.clear(x, y);
				}
			}
		}		
		for (int y = 0; y < bm.getHeight(); y++) {
			for (int x = 0; x < bm.getWidth(); x++) {
				if (Math.abs(x - y) % 2 == 0) {
					assertFalse(bm.get(x, y));
				} else {
					assertTrue(bm.get(x, y));
				}
			}
		}				
	}

	@Test
	public void testFlip() {
		BitMatrix bm = new BitMatrix(123, 250);
		for (int y = 0; y < bm.getHeight(); y++) {
			for (int x = 0; x < bm.getWidth(); x++) {
				if ((x + y) % 2 == 0) {
					bm.set(x, y);
				}
			}
		}	
		for (int y = 0; y < bm.getHeight(); y++) {
			for (int x = 0; x < bm.getWidth(); x++) {
				bm.flip(x, y);
			}
		}			
		for (int y = 0; y < bm.getHeight(); y++) {
			for (int x = 0; x < bm.getWidth(); x++) {
				if ((x + y) % 2 == 0) {
					assertFalse(bm.get(x, y));
				} else {
					assertTrue(bm.get(x, y));
				}
			}
		}			
	}
}
