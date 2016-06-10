import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;
import java.nio.charset.*;

/**
  * @author Mighty Cohadar 
  */
public class UnicodeTest {

	static final Charset UTF_16_BE = Charset.forName("UTF-16BE");

	public static String allChars() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Character.MIN_SURROGATE; i++) {
			sb.append((char)i);
		}
		// -2 for last two non-characters in a unicode plane
		for (int i = Character.MAX_SURROGATE + 1; i < Character.MIN_SUPPLEMENTARY_CODE_POINT - 2; i++) {
			sb.append((char)i);	
		}
		// for (int i = Character.MIN_SUPPLEMENTARY_CODE_POINT; i <= Character.MAX_CODE_POINT; i++) {
		// 	sb.append(Character.highSurrogate(i));
		// 	sb.append(Character.lowSurrogate(i));
		// }
		return sb.toString();
	}

	@Test
	public void testUTF16BE() {
		String A = allChars();
		byte[] B = A.getBytes(UTF_16_BE);
		String C = new String(B, UTF_16_BE);
		byte[] D = C.getBytes(UTF_16_BE);
		for (int i = 0; i < A.length(); i++) {
			if (A.charAt(i) != C.charAt(i)) {
				System.out.printf("%X %X\n", (int)A.charAt(i), (int)C.charAt(i));
				return;
			}
		}
		assertEquals(A, C);
		assertArrayEquals(B, D);
	}

}
