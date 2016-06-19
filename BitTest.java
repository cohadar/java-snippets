import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class BitTest {

	@Test
	public void testMaxInt() {
		assertEquals(Integer.MAX_VALUE, -1 ^ (1 << 31));
		assertEquals(Integer.MAX_VALUE, -1 >>> 1);
		assertEquals(Integer.MAX_VALUE, 0x7FFFFFFF);
	}

	@Test
	public void testMinInt() {
		assertEquals(Integer.MIN_VALUE, 0x80000000);
		assertEquals(Integer.MIN_VALUE, 1 << 31);
	}

	public static int bitCountFor(int x) {
		int count = 0;
		for (int i = 0; i < 32; i++) {
			if ((x & 1) != 0) {
				count++;
			}
			x >>>= 1;
		}
		return count;
	}

	public static int bitCount(int x) {
		x = x - ((x >>> 1) & 0x55555555);
		x = (x & 0x33333333) + ((x >>> 2) & 0x33333333);
		x = (x + (x >>> 4)) & 0x0f0f0f0f;
		x = x + (x >>> 8);
		x = x + (x >>> 16);
		return x & 0x3f;
	}

	@Test
	public void testBitCount() {
		for (int i = 0; i < 10000; i++) {
			int x = random.nextInt();
			assertEquals(Integer.bitCount(x), bitCountFor(x));	
			assertEquals(Integer.bitCount(x), bitCount(x));	
		}
	}

	public static int compare(int x, int y) {
		return (x < y) ? -1 : (x > y) ? 1 : 0;
	}

	@Test
	public void testCompareTo() {
		for (int i = 0; i < 10000; i++) {
			Integer x = random.nextInt();
			Integer y = random.nextInt();
			assertEquals(x.compareTo(y), compare(x, y));
		}
	}

	public static Random random = new Random();
}
