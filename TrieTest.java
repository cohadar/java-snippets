import static org.junit.Assert.*;
import org.junit.Test;

// TODO: performance tests
public class TrieTest {

	@Test
	public void test() {
		Trie<Integer> trie = new Trie<Integer>(128);

		trie.put("abc", 1);
		trie.put("abd", 2);
		trie.put("bcd", 3);
		trie.put("bfg", 4);
		trie.put("bfz", 5);

		assertTrue(trie.hasPrefix("a"));
		assertTrue(trie.hasPrefix("ab"));
		assertTrue(trie.hasPrefix("abd"));
		assertFalse(trie.hasPrefix("abz"));

		assertEquals(1, (int)trie.get("abc"));
		assertEquals(2, (int)trie.get("abd"));
		assertEquals(3, (int)trie.get("bcd"));
		assertEquals(4, (int)trie.get("bfg"));
		assertEquals(5, (int)trie.get("bfz"));
		assertNull(trie.get("abx"));
		assertNull(trie.get("a"));
		assertNull(trie.get(""));

		trie.put("", 10);
		assertEquals(10, (int)trie.get(""));
	}
}