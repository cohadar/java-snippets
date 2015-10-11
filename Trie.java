
public class Trie<V> {
	private final int alphabetSize;
	private final Node root;

	private static class Node {
		Object value;
		Node[] children;

		Node(int alphabetSize) {
			this.children = new Node[alphabetSize];
		}
	}

	public Trie(int alphabetSize) {
		this.alphabetSize = alphabetSize;
		this.root = new Node(alphabetSize);
	}

	public V put(String key, V value) {
		int n = key.length();
		int i = 0;
		Node node = this.root;
		while (i < n) {
			char c = key.charAt(i);
			assert c < alphabetSize;
			if (node.children[c] == null) {
				node.children[c] = new Node(alphabetSize);
			}
			node = node.children[c];
			i++;
		}
		@SuppressWarnings("unchecked")
		V ret = (V)node.value;
		node.value = value;
		return ret;
	}

	public V get(String key) {
		int n = key.length();
		int i = 0;
		Node node = this.root;
		while (i < n) {
			char c = key.charAt(i);
			assert c < alphabetSize;
			if (node.children[c] == null) {
				break;
			}
			node = node.children[c];
			i++;
		}
		if (i == n) {
			@SuppressWarnings("unchecked")
			V ret = (V)node.value;
			return ret;
		}
		return null;
	}

	public boolean hasPrefix(String key) {
		int n = key.length();
		int i = 0;
		Node node = this.root;
		while (i < n) {
			char c = key.charAt(i);
			assert c < alphabetSize;
			if (node.children[c] == null) {
				return false;
			}
			node = node.children[c];
			i++;
		}
		return true;
	}
}
