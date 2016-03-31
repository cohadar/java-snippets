import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class Trie26<V> {

	V value;
	Trie26[] children;

	@SuppressWarnings("unchecked")
	public V put(String key, V value) {
		Trie26<V> T = this;
		for (int index = 0; index < key.length(); index++) {
			if (T.children == null) {
				T.children = new Trie26[26];
			}				
			int i = index4char(key.charAt(index));	
			if (T.children[i] == null) {
				T.children[i] = new Trie26();
			}			
			T = T.children[i];
		}
		V ret = T.value;
		T.value = value;
		return ret;
	}	

	@SuppressWarnings("unchecked")
	public V get(String key) {
		Trie26<V> T = this;
		for (int index = 0; index < key.length(); index++) {
			if (T.children == null) {
				return null;
			}
			int i = index4char(key.charAt(index));
			if (T.children[i] == null) {
				return null;
			}
			T = T.children[i];
		}
		return T.value;
	}

	@SuppressWarnings("unchecked")
	public boolean hasPrefix(String key) {
		Trie26<V> T = this;
		for (int index = 0; index < key.length(); index++) {
			if (T.children == null) {
				return false;
			}
			int i = index4char(key.charAt(index));
			if (T.children[i] == null) {
				return false;
			}
			T = T.children[i];
		}
		return true;
	}

	public static char char4index(int index) {
		return (char)('a' + index);
	}

	public static int index4char(char c) {
		return c - 'a';
	}

}
