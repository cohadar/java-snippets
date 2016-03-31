import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class Trie26<V> {

	V value;
	Trie26[] children;

	public V put(String key, V value) {
		return put(key, value, 0);
	}	

	@SuppressWarnings("unchecked")
	private V put(String key, V value, int index) {
		if (index == key.length()) {
			V ret = this.value;
			this.value = value;
			return ret;
		}
		if (this.children == null) {
			this.children = new Trie26[26];
		}	
		int i = index4char(key.charAt(index));	
		if (this.children[i] == null) {
			this.children[i] = new Trie26();
		}
		return (V)this.children[i].put(key, value, index + 1);
	}

	public V get(String key) {
		return get(key, 0);
	}

	@SuppressWarnings("unchecked")
	private V get(String key, int index) {
		if (index == key.length()) {
			return this.value;
		}
		if (this.children == null) {
			return null;
		}
		int i = index4char(key.charAt(index));	
		if (this.children[i] == null) {
			return null;
		}
		return (V)this.children[i].get(key, index + 1);
	}

	public boolean hasPrefix(String key) {
		return hasPrefix(key, 0);
	}

	private boolean hasPrefix(String key, int index) {
		if (index == key.length()) {
			return true;
		}
		if (this.children == null) {
			return false;
		}
		int i = index4char(key.charAt(index));	
		if (this.children[i] == null) {
			return false;
		}
		return this.children[i].hasPrefix(key, index + 1);		
	}

	public static char char4index(int index) {
		return (char)('a' + index);
	}

	public static int index4char(char c) {
		return c - 'a';
	}

}
