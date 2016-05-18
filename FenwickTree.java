import java.util.Arrays;

/**
* All indexes in FenwickTree are 1-based.
*/
class FenwickTree {
	private final long[] add;
	private final long[] mul;
	private final long[] min;
	private final long[] max;
	private final int n;

	public FenwickTree(int n) {
		this.add = new long[1 + n];
		this.mul = new long[1 + n];
		this.min = new long[1 + n];
		this.max = new long[1 + n];
		Arrays.fill(this.min, Long.MAX_VALUE);
		Arrays.fill(this.max, Long.MIN_VALUE);
		this.n = n;
	}

	private void internalUpdate(final int index, long factor, long delta) {
		assert index > 0 && index <= n;
		long oldMin = getMin(index);
		long oldMax = getMax(index);
		for (int i = index; i <= n; i += (i & -i)) {
			mul[i] += factor;
			add[i] += delta;
		}
		long newValue = getValue(index);
		if (newValue < oldMin) {
			for (int i = index; i <= n; i += (i & -i)) {
				min[i] = newValue;
			}
		}
		if (newValue > oldMax) {
			for (int i = index; i <= n; i += (i & -i)) {
				max[i] = newValue;
			}
		}
	}

	// @return sum(A[1]..A[index])
	public long getSum(final int index) {
		assert index >= 0 && index <= n;
		long sum = 0;
		long fact = 0;
		for (int i = index; i > 0; i -= (i & -i)) {
			sum += add[i];
			fact += mul[i];
		}
		return fact * index + sum;		
	}

	// @return min(A[1]..A[index])
	public long getMin(final int index) {
		assert index > 0 && index <= n;
		long m = min[index];
		for (int i = index - (index & -index); i > 0; i -= (i & -i)) {
			m = Math.min(m, min[i]);
		}
		return m;
	}	

	// @return max(A[1]..A[index])
	public long getMax(final int index) {
		assert index > 0 && index <= n;
		long m = max[index];
		for (int i = index - (index & -index); i > 0; i -= (i & -i)) {
			m = Math.max(m, max[i]);
		}
		return m;
	}		

	// @return sum(A[low]..A[high])
	public long getRangeSum(int low, int high) {
		assert low <= high;
		return getSum(high) - getSum(low - 1);
	}

	// @return A[index]
	public long getValue(int index) {
		assert index > 0 && index <= n;
		return getSum(index) - getSum(index - 1);
	}	

	// A[index] += value
	public void addValue(int index, long value) {
		internalUpdate(index, 0, value);
	}	

	// for (int i = low; i <= high; i++) A[i] += value
	public void addValueToRange(int low, int high, long value) {
		internalUpdate(low, value, -value * (low - 1));
		internalUpdate(high, -value, value * high); 
	}

	// @return min index, where getSum(index) > sum
	long findHigherSumIndex(long sum) {
		int low = 1;
		int high = n + 1;
		while (low < high) {
			int mid = (low + high) >>> 1;
			long midSum = getSum(mid);
			if (midSum <= sum) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return high;
	}	

	// @return A[]
	public long[] toArray() {
		long[] A = new long[n];
		long cumul = 0;
		for (int i = 0; i < A.length; i++) {
			A[i] = this.getSum(i + 1) - cumul;
			cumul += A[i];
		}
		return A;
	}

}
