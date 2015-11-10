import java.util.*;

public class PairingHeap<E> implements MeldableQueue<E> {
	Node root;
	int size;

	class Node {
		E element;
		Node prev;
		Node next;
		Node child;
		Node(E element) {
			this.element = element;
		}
	}

	public void clear() {
		this.root = null;
		this.size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public E element() {
		if (size == 0) throw new NoSuchElementException();
		return root.element;
	}	

	public E peek() {
		if (size == 0) return null;
		return root.element;
	}	

	// b becomes leftmost child of a
	private void linkUnder(Node a, Node b) {
		b.prev = a;
		b.next = a.child;
		if (a.child != null) {
			a.child.prev = b;
		}
		a.child = b;
	}

	public void meld(MeldableQueue<E> that) {
		if (that.size == 0) {
			return;
		}
		if (this.size == 0) {
			this.root = that.root;
			this.size = that.size;
			that.size = 0;
			that.root = null;
			return;
		}
		Node a = this.root;
		Node b = that.root;
		if (a.element.compareTo(b.element) <= 0) {
			linkUnder(a, b);
		} else {
			linkUnder(b, a);
			this.root = that.root;
		}
		this.size += that.size;
		that.size = 0;
		that.root = null;		
	}

	public boolean offer(E element) {
		add(element);
		return true;
	}

	public boolean add(E element) {
		Node n = new Node(element);
		if (root == null) {
			root = n;
		} else if (root.element.compareTo(element) <= 0) {
			linkUnder(root, n);
		} else {
			linkUnder(n, root);
			root = n;
		}
		size++;
		return true;
	}

	public E poll() {
		if (size == 0) return null;
		return remove();
	}

	public E remove() {
		if (size == 0) throw new NoSuchElementException();
		size--;
		E ret = root.element;
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
				if (a.element.compareTo(b.element) <= 0) {
					linkUnder(a, b);
					Q.add(a);
				} else {
					linkUnder(b, a);
					Q.add(b);
				}
			}
			root = Q.remove();
		}
		return ret;
	}

}
