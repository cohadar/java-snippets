import java.util.*;

// TODO: make it fit into Queue interface and make MeldableQueue interface
public class PairingHeap<K extends Comparable<K>, V> {

	Node root;
	int size;

	class Node {
		K key;
		V value;
		Node prev;
		Node next;
		Node child;
		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
		public String toString() {
			return String.format("key=%d, value='%c'", key, value);
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public K getMaxKey() {
		if (size == 0) throw new NoSuchElementException();
		return root.key;
	}

	public V getMaxValue() {
		if (size == 0) throw new NoSuchElementException();
		return root.value;
	}	

	// b under a
	private void linkUnder(Node a, Node b) {
		debug("linkUnder", a, b);
		b.prev = a;
		b.next = a.child;
		if (a.child != null) {
			a.child.prev = b;
		}
		a.child = b;
	}

	public void meld(PairingHeap<K, V> that) {
		if (that.size == 0) {
			return;
		}
		if (this.size == 0) {
			this.root = that.root;
			this.size = that.size;
			return;
		}
		Node a = this.root;
		Node b = that.root;
		if (a.key.compareTo(b.key) <= 0) {
			linkUnder(a, b);
		} else {
			linkUnder(b, a);
			this.root = that.root;
		}
		this.size += that.size;
	}

	public void put(K key, V value) {
		Node n = new Node(key, value);
		if (root == null) {
			root = n;
		} else if (root.key.compareTo(key) <= 0) {
			linkUnder(root, n);
		} else {
			linkUnder(n, root);
			root = n;
		}
		size++;
	}

	public void decreaseKey(K key, V value) {

	}

	public V removeMin() {
		if (size == 0) throw new NoSuchElementException();
		size--;
		V ret = root.value;
		Queue<Node> Q = new ArrayDeque<>();
		Node c = root.child;
		while (c != null) {
			Node next = c.next;
			c.prev = null;
			c.next = null;
			Q.add(c);
			c = next;
		} 
		root = null;
		if (!Q.isEmpty()) {
			while (Q.size() > 1) {
				Node a = Q.remove();
				Node b = Q.remove();
				if (a.key.compareTo(b.key) <= 0) {
					linkUnder(a, b);
					Q.add(a);
				} else {
					linkUnder(b, a);
					Q.add(b);
				}
			}
			root = Q.remove();
		}
		debug("newRoot", root);
		return ret;
	}

	public void remove(V value) {

	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
