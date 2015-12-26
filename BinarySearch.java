
public abstract class BinarySearch {

	// this MUST be monotonically rising function!
	public abstract int getValue(int index);

	// find any index in [left, right) for which getValue(index) == value
	public int find(int left, int right, int value) {
		right--;
		while (left <= right) {
			int mid = (left + right) >>> 1;
			int midValue = getValue(mid);
			if (midValue == value) {
				return mid;
			} else if (midValue > value) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return -(left + 1);
	}

	// find first index in [left, right) for which getValue(index) > value
	public int upperBound(int left, int right, int value) {
		right--;
		while (left <= right) {
			int mid = (left + right) >>> 1;
			int midValue = getValue(mid);
			if (midValue > value) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	// find first index in [left, right) for which getValue(index) >= value
	public int lowerBound(int left, int right, int value) {
		right--;
		while (left <= right) {
			int mid = (left + right) >>> 1;
			int midValue = getValue(mid);
			if (midValue >= value) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

}