import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class UnionFindTest {

	@Test
	public void testMake() {
		int n = 100;
		UnionFind uf = new UnionFind(n);
		assertEquals(n, uf.count());
		for (int i = 0; i < uf.size(); i++) {
			assertEquals(i, uf.find(i));
		}
		for (int a = 0; a < uf.size(); a++) {
			for (int b = 0; b < uf.size(); b++) {
				assertEquals(a == b, uf.connected(a, b));
			}
		}
	}

	@Test(expected=IllegalArgumentException.class)
	public void testBadN() {
		UnionFind uf = new UnionFind(-1);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testValidateBelow() {
		int n = 100;
		UnionFind uf = new UnionFind(n);
		uf.find(-1);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testValidateAbove() {
		int n = 100;
		UnionFind uf = new UnionFind(n);
		uf.find(n);
	}

	@Test
	public void testUnionSimple() {
		UnionFind uf = new UnionFind(3);
		assertFalse(uf.connected(0, 1));
		assertFalse(uf.connected(0, 2));
		assertFalse(uf.connected(1, 2));
		uf.union(1, 2);
		assertFalse(uf.connected(0, 1));
		assertFalse(uf.connected(0, 2));
		assertTrue(uf.connected(1, 2));
	}

	@Test
	public void testCountSimple() {
		UnionFind uf = new UnionFind(3);
		assertEquals(3, uf.count());
		uf.union(1, 2);
		assertEquals(2, uf.count());
	}	

	final int N = 1000000;

	@Test(timeout=1000)
	public void testMergeAllUp() {
		Random random = new Random();
		UnionFind uf = new UnionFind(N);		
		// random disconnection test
		for (int i = 0; i < uf.size(); i++) {
			int a = random.nextInt(uf.size());
			int b = random.nextInt(uf.size());
			assertEquals(a == b, uf.connected(a, b));
		}
		for (int i = 0; i < uf.size() - 1; i++) {
			uf.union(i, i + 1);
		}
		// random connection test
		for (int i = 0; i < uf.size(); i++) {
			int a = random.nextInt(uf.size());
			int b = random.nextInt(uf.size());
			assertTrue(uf.connected(a, b));
		}
	}

	@Test(timeout=1000)
	public void testMergeAllDown() {
		Random random = new Random();
		UnionFind uf = new UnionFind(N);		
		// random disconnection test
		for (int i = uf.size() - 1; i >= 0; i--) {
			int a = random.nextInt(uf.size());
			int b = random.nextInt(uf.size());
			assertEquals(a == b, uf.connected(a, b));
		}
		for (int i = uf.size() - 1; i >= 1; i--) {
			uf.union(i, i - 1);
		}
		// random connection test
		for (int i = uf.size() - 1; i >= 0; i--) {
			int a = random.nextInt(uf.size());
			int b = random.nextInt(uf.size());
			assertTrue(uf.connected(a, b));
		}
	}	

	@Test(timeout=1000)
	public void testMergeAllRandom() {
		Random random = new Random();
		UnionFind uf = new UnionFind(N);		
		// random disconnection test
		for (int i = uf.size() - 1; i >= 0; i--) {
			int a = random.nextInt(uf.size());
			int b = random.nextInt(uf.size());
			assertEquals(a == b, uf.connected(a, b));
		}
		while (uf.count() > 1) {
			int a = random.nextInt(uf.size());
			int b = random.nextInt(uf.size());
			uf.union(a, b);
			int m = (a + b) >>> 1;
			uf.union(a, m);
		}
		// random connection test
		for (int i = uf.size() - 1; i >= 0; i--) {
			int a = random.nextInt(uf.size());
			int b = random.nextInt(uf.size());
			assertTrue(uf.connected(a, b));
		}
	}	

}