import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class SegmentTreeTest {

	static class SumOP implements SegmentTree.OP {
		@Override
		public int zero() {
			return 0;
		}
		@Override
		public int binary(int va, int vb) {
			return va + vb;
		}
	}

	static class MinOP implements SegmentTree.OP {
		@Override
		public int zero() {
			return Integer.MAX_VALUE;
		}
		@Override
		public int binary(int va, int vb) {
			return (va <= vb) ? va : vb;
		}
	}	

	@Test
	public void test_sum() {
		int[] A = Arrayz.random(2000, -1000, 1000);
		SegmentTree ST = new SegmentTree(new SumOP(), A);
		for (int l = 0; l < A.length; l++) {
			for (int r = l; r < A.length; r++) {
				assertEquals(Arrayz.sum(A, l, r), ST.queryRange(l, r));
			}
		}
	}

	@Test
	public void test_sum2() {
		int[] A = Arrayz.random(2000, -1000, 1000);
		SegmentTree ST = new SegmentTree(new SumOP(), A.length);
		for (int i = 0; i < A.length; i++) {
			ST.updateValue(i, A[i]);
		}
		for (int l = 0; l < A.length; l++) {
			for (int r = l; r < A.length; r++) {
				assertEquals(Arrayz.sum(A, l, r), ST.queryRange(l, r));
			}
		}
	}	

	@Test
	public void test_min() {
		int[] A = Arrayz.random(2000, -1000, 1000);
		SegmentTree ST = new SegmentTree(new MinOP(), A);
		for (int l = 0; l < A.length; l++) {
			for (int r = l; r < A.length; r++) {
				assertEquals(Arrayz.min(A, l, r), ST.queryRange(l, r));
			}
		}
	}	

	Random random = new Random();

	public int nextInt(int low, int high) {
		return low + random.nextInt(high - low + 1);
	}

	@Test
	public void test_updateValue_SumOP() {
		int[] A = Arrayz.random(2000, -1000, 1000);
		SegmentTree ST = new SegmentTree(new SumOP(), A);
		for (int i = 0; i < A.length; i++) {
			int val = nextInt(-1000, 1000);
			A[i] = val;
			ST.updateValue(i, val);
		}
		for (int l = 0; l < A.length; l++) {
			for (int r = l; r < A.length; r++) {
				assertEquals(Arrayz.sum(A, l, r), ST.queryRange(l, r));
			}
		}		
	}

	@Test
	public void test_updateValue_MinOP() {
		int[] A = Arrayz.random(2000, -1000, 1000);
		SegmentTree ST = new SegmentTree(new MinOP(), A);
		for (int i = 0; i < A.length; i++) {
			int val = nextInt(-1000, 1000);
			A[i] = val;
			ST.updateValue(i, val);
		}
		for (int l = 0; l < A.length; l++) {
			for (int r = l; r < A.length; r++) {
				assertEquals(Arrayz.min(A, l, r), ST.queryRange(l, r));
			}
		}		
	}	

}
