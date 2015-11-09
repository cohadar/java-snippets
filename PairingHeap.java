import java.util.*;

// TODO: make it fit into Queue interface and make MeldableQueue interface
public class PairingHeap<K extends Comparable<K>, V> {

	Node root;

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
		return true;
	}

	public int size() {
		return 0;
	}

	public K getMaxKey() {
		return null;
	}

	public V getMaxValue() {
		return null;
	}	

	public void meld(PairingHeap<K, V> that) {
		/***/
	}

	public void put(K key, V value) {
		Node n = new Node(key, value);
		if (root == null) {
			root = n;
			debug("newroot", n);
		} else if (root.key.compareTo(key) <= 0) {
			n.prev = root;
			n.next = root.child;
			if (root.child != null) {
				root.child.prev = n;
			}
			root.child = n;
			debug("smaller", n);
		} else {
			n.child = root;
			root.prev = n;
			root = n;
			debug("bigger", n);
		}
	}

	public void decreaseKey(K key, V value) {

	}

	public V removeMin() {
		return root.value;
	}

	public void remove(V value) {

	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
