import java.util.*;

public class MyMap<K, V> implements Map<K, V>{

	public static final int MAX_TABLE_SIZE = 1 << 30;
	public static final int MIN_TABLE_SIZE = 1 << 4;

	static class Node<K, V> implements Map.Entry<K, V> {
		final int h;
		final K key;
		V value;
		Node<K, V> next;
		
		public Node(int h, K key, V value, Node<K, V> next) {
			this.h = h;
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V value) {
			V ret = this.value;
			this.value = value;
			return ret;
		}
	}

	Node<K, V>[] data;

	@SuppressWarnings("unchecked")
	public MyMap() {
		this.data = new Node[16];
	}

	@SuppressWarnings("unchecked")
	public MyMap(int capacity) {
		int tableSize = (int)(capacity / loadFactor);
		if (tableSize < MIN_TABLE_SIZE) {
			tableSize = MIN_TABLE_SIZE;
		}		
		if (tableSize > MAX_TABLE_SIZE) {
			tableSize = MAX_TABLE_SIZE;
		}
		tableSize = pow2Size(tableSize);
		this.data = new Node[tableSize];
	}

	private int size;
	private final float loadFactor = 0.75f;


	static final int hash(Object key) {
		if (key == null) {
			return 0;
		}
		int h = key.hashCode();
		return h ^ (h >>> 16);
	}

	final int index(int hash) {
		return hash & (data.length - 1); // assuming power of 2 lengths
	}

	static final int pow2Size(int n) {
		n--;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return n + 1;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean containsKey(Object key) {
		return false;
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	public V get(Object key) {
		int h = hash(key);
		for (Node<K, V> e = data[index(h)]; e != null; e = e.next) {
			if (e.h == h && Objects.equals(e.key, key)) {
				return e.value;
			}			
		}		
		return null;
	}

	public V put(K key, V value) {
		int h = hash(key);
		Node<K, V> p = null;
		for (Node<K, V> e = data[index(h)]; e != null; p = e, e = e.next) {
			if (e.h == h && Objects.equals(e.key, key)) {
				return e.setValue(value);
			}			
		}	
		Node<K, V> e = new Node<>(hash(key), key, value, null);	
		if (p == null) {
			data[index(h)] = e;
		} else {
			p.next = e;
		}
		return null;
	}

	public V remove(Object key) {
		int h = hash(key);
		int i = index(h);
		Node<K, V> p = null;
		for (Node<K, V> e = data[i]; e != null; p = e, e = e.next) {
			if (e.h == h && Objects.equals(e.key, key)) {
				V ret = e.value;
				if (p == null) {
					data[i] = e.next;
				} else {
					p.next = e.next;	
				}
				return ret;
			}
		}
		return null;
	}

	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}

	public void clear() {
		// TODO Auto-generated method stub
		
	}

	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

}
