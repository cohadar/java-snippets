
/**
* All indexes in FenwickTree are 1-based.
*/
public class FenwickTree {
	private final int[] add;
	private final int[] mul;
	private final int n;

	public FenwickTree(int n) {
		this.add = new int[1 + n];
		this.mul = new int[1 + n];
		this.n = n;
	}

	public FenwickTree(int[] A) {
		this(A.length);
		for (int i = 0; i < A.length; i++) {
			this.addValue(i + 1, A[i]);
		}
	}	

	private void internalUpdate(final int index, int factor, int value) {
		assert index > 0 && index <= n;
		for (int i = index; i <= n; i += (i & -i)) {
			mul[i] += factor;
			add[i] += value;
		}
	}

	// @return sum(A[1]..A[index])
	public int getSum(final int index) {
		assert index >= 0 && index <= n;
		int sum = 0;
		int fact = 0;
		for (int i = index; i > 0; i -= (i & -i)) {
			sum += add[i];
			fact += mul[i];
		}
		return fact * index + sum;		
	}

	// @return sum(A[low]..A[high])
	public int getRangeSum(int low, int high) {
		assert low <= high;
		return getSum(high) - getSum(low - 1);
	}

	// @return A[index]
	public int getValue(int index) {
		assert index > 0 && index <= n;
		return getSum(index) - getSum(index - 1);
	}	

	// A[index] += value
	public void addValue(int index, int value) {
		internalUpdate(index, 0, value);
	}	

	// for (int i = low; i <= high; i++) A[i] += value
	public void addValueToRange(int low, int high, int value) {
		internalUpdate(low, value, -value * (low - 1));
		internalUpdate(high, -value, value * high); 
	}

	// @return min index, where getSum(index) > sum
	int findHigherSumIndex(int sum) {
		int low = 1;
		int high = n + 1;
		while (low < high) {
			int mid = (low + high) >>> 1;
			int midSum = getSum(mid);
			if (midSum <= sum) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return high;
	}	

	// @return A[]
	public int[] toArray() {
		int[] A = new int[n];
		int cumul = 0;
		for (int i = 0; i < A.length; i++) {
			A[i] = this.getSum(i + 1) - cumul;
			cumul += A[i];
		}
		return A;
	}

}
