
public class UnionFind {
	final int size;
	int[] parent;
	byte[] rank;
	int count;
	public UnionFind(int n) {
		if (n < 0) throw new IllegalArgumentException("size cannot be negative: " + n);
		this.size = n;
		this.count = n;
		this.parent = new int[n];
		this.rank = new byte[n];
		for (int i = 0; i < n; i++) {
			this.parent[i] = i;
			this.rank[i] = 0;
		}
	}
	public int find(int index) {
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException("index: " + index);
		if (parent[index] != index) {
			parent[index] = find(parent[index]);
		}
		return parent[index];
	}
	public void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if (pa == pb) return;
		if (rank[pa] > rank[pb]) {
			parent[pb] = pa;
		} else if (rank[pa] < rank[pb]) {
			parent[pa] = pb;
		} else {
			parent[pa] = pb;
			rank[pb]++;
		}
		count--;
	}
	public boolean connected(int a, int b) {
		return find(a) == find(b);
	}
	public int count() {
		return count;
	}
	public int size() {
		return size;
	}		
}
