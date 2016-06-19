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

	public static int compareUnsigned(int x, int y) {
		return Integer.compare(x + Integer.MIN_VALUE, y + Integer.MAX_VALUE);
	}

	@Test
	public void testCompareUnsigned() {
		for (int i = 0; i < 10000; i++) {
			Integer x = random.nextInt();
			Integer y = random.nextInt();
			assertEquals(Integer.compareUnsigned(x, y), compareUnsigned(x, y));
		}
	}

	public static int highestOneBit(int x) {
		x = x | (x >> 1);
		x = x | (x >> 2);
		x = x | (x >> 4);
		x = x | (x >> 8);
		x = x | (x >> 16);
		return x ^ (x >>> 1);
	}

	@Test
	public void testHighestOneBit() {
		for (int i = 0; i < 10000; i++) {
			Integer x = random.nextInt();
			assertEquals(Integer.highestOneBit(x), highestOneBit(x));
		}
	}

	public static int lowestOneBit(int x) {
		return x & ~(x-1);
	}

	@Test
	public void testLowestOneBit() {
		for (int i = 0; i < 10000; i++) {
			Integer x = random.nextInt();
			assertEquals(Integer.lowestOneBit(x), lowestOneBit(x));
		}
	}

	@Test
	public void testComplement2Negative() {
		for (int i = 0; i < 10000; i++) {
			int x = random.nextInt();
			assertEquals(-x, ~x + 1);
			assertEquals(-x, ~(x - 1));
		}
	}

	public static int numberOfLeadingZeros(int x) {
		int n = 0;
		if (x == 0) {
			return 32;
		}
		if ((x & 0xFFFF0000) == 0) {
			n += 16;
			x <<= 16;
		}
		if ((x & 0xFF000000) == 0) {
			n += 8;
			x <<= 8;
		}
		if ((x & 0xF0000000) == 0) {
			n += 4;
			x <<= 4;
		}
		if ((x & 0xC0000000) == 0) {
			n += 2;
			x <<= 2;
		}
		if (x >= 0) {
			n++;
		}
		return n;		
	}

	@Test
	public void testNumberOfLeadingZeros() {
		for (int i = 0; i < 10000; i++) {
			int x = random.nextInt();
			assertEquals(Integer.numberOfLeadingZeros(x), numberOfLeadingZeros(x));
		}
	}

	public static int numberOfTrailingZeros(int x) {
		if (x == 0) {
			return 32;
		}
		int n = 0;
		if ((x << 16) == 0) {
			x >>>= 16;
			n += 16;
		}
		if ((x << 24) == 0) {
			x >>>= 8;
			n += 8;
		}
		if ((x << 28) == 0) {
			x >>>= 4;
			n += 4;
		}
		if ((x << 30) == 0) {
			x >>>= 2;
			n += 2;
		}
		if ((x << 31) == 0) {
			x >>>= 1;
			n += 1;
		}
		return n;
	}

	@Test
	public void testNumberOfTrailingZeros() {
		for (int i = 0; i < 10000; i++) {
			int x = random.nextInt();
			assertEquals(Integer.numberOfTrailingZeros(x), numberOfTrailingZeros(x));
		}
	}

	public static Random random = new Random();
}
