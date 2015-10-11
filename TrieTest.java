
public class TrieTest {
	public static void main(String[] args) {
		Trie<Integer> trie = new Trie<Integer>(128);

		trie.put("abc", 1);
		trie.put("abd", 2);
		trie.put("bcd", 3);
		trie.put("bfg", 4);
		trie.put("bfz", 5);

		assert trie.hasPrefix("a");
		assert trie.hasPrefix("ab");
		assert trie.hasPrefix("abd");
		assert trie.hasPrefix("abz") == false;

		assert trie.get("abc") == 1;
		assert trie.get("abd") == 2;
		assert trie.get("bcd") == 3;
		assert trie.get("bfg") == 4;
		assert trie.get("bfz") == 5;
		assert trie.get("abx") == null;
		assert trie.get("a") == null;
		assert trie.get("") == null;

		trie.put("", 10);
		assert trie.get("") == 10;
	}
}