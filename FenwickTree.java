
public class FenwickTree {
	private final int[] add;
	private final int[] mul;
	private final int n;

	public FenwickTree(int n) {
		this.add = new int[1 + n];
		this.mul = new int[1 + n];
		this.n = n;
	}

	public void pointUpdate(int index, int value) {
		assert index > 0 && index <= n;
		while (index <= n) {
			add[index] += value;
			index += (index & -index);
		}
	}

	public int pointQuery(int index) {
		assert index >= 0 && index <= n;
		int sum = 0;
		while (index > 0) {
			sum += add[index];
			index -= (index & -index);
		}
		return sum;		
	}

	public int rangeQuery(int low, int high) {
		assert low <= high;
		return pointQuery(high) - pointQuery(low - 1);
	}

	public void rangeUpdate(int low, int high, int value) {
		for (int i = low; i <= high; i++) {
			this.pointUpdate(i, value);
		}
	}
}
