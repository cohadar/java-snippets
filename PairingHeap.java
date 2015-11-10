import java.util.*;

public class PairingHeap<E extends Comparable<E>> {
	private Node root;
	private int size;

	private class Node {
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

	public boolean offer(E element) {
		return add(element);
	}

	public boolean add(E element) {
		Node n = new Node(element);
		if (root == null || compareAndLink(root, n)) {
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
		while (Q.size() > 1) {
			Node a = Q.remove();
			Node b = Q.remove();
			if (compareAndLink(a, b)) {
				Q.add(b);	
			} else { 
				Q.add(a);
			}
		}
		root = Q.poll();
		return ret;
	}	

	public void meld(PairingHeap<E> that) {
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
		if (compareAndLink(this.root, that.root)) {
			this.root = that.root;
		}
		this.size += that.size;
		that.size = 0;
		that.root = null;		
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

	// return true if b becomes new root
	private boolean compareAndLink(Node a, Node b) {
		boolean ret = a.element.compareTo(b.element) > 0;
		if (ret) {
			linkUnder(b, a);
		} else {
			linkUnder(a, b);
		}
		return ret;
	}	

}
