import java.util.*;
import java.io.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class FastScannerTest {

	private Scanner $(String input) {
		return new Scanner(new ByteArrayInputStream(input.getBytes()));
	}

	@Test
	public void test_nextInt() {
		int[] A = new int[] {0, 1, -1, 123, -7652, Integer.MIN_VALUE, Integer.MAX_VALUE};
		StringBuilder sb = new StringBuilder();
		for (int a : A) {
			sb.append(a);
			if (a != A[A.length - 1]) {
				sb.append(' ');
			}
		}
		Scanner scanner = $(sb.toString());		
		for (int a : A) {
			assertEquals(a, scanner.nextInt());
		}
	}	

	@Test
	public void test_nextLong() {
		long[] A = new long[] {0, 1, -1, 1235465767934L, -7652234234234234L, Long.MIN_VALUE, Long.MAX_VALUE};
		StringBuilder sb = new StringBuilder();
		for (long a : A) {
			sb.append(a);
			if (a != A[A.length - 1]) {
				sb.append(' ');
			}
		}
		Scanner scanner = $(sb.toString());		
		for (long a : A) {
			assertEquals(a, scanner.nextLong());
		}
	}		

	@Test 
	public void test_next() {
		Scanner scanner = $("trla\nbaba lan   da\njoj\t\tprodje\r\ndan");
		assertEquals("trla", scanner.next());
		assertEquals("baba", scanner.next());
		assertEquals("lan", scanner.next());
		assertEquals("da", scanner.next());
		assertEquals("joj", scanner.next());
		assertEquals("prodje", scanner.next());
		assertEquals("dan", scanner.next());
	}

	private void test_nextLine(Scanner scanner) {
		int n = scanner.nextInt();
		int t = scanner.nextInt();
		assertEquals(123, n);
		assertEquals(456, t);
		assertEquals("", scanner.nextLine());
		assertEquals("AA AA", scanner.nextLine());
		assertEquals("BB BB", scanner.nextLine());
	}

	private void test_nextLineFromMid(Scanner scanner) {
		assertEquals(123, scanner.nextInt());
		assertEquals(456, scanner.nextInt());
		assertEquals("", scanner.nextLine());
		assertEquals(555, scanner.nextInt());
		assertEquals(" 777", scanner.nextLine());
		assertEquals("AA", scanner.next());
		assertEquals(" AA", scanner.nextLine());
		assertEquals("BB BB", scanner.nextLine());
	}	

	@Test
	public void test_nextLine() {
		String NL_MACOSX9 = "123 456\rAA AA\rBB BB";
		String NL_WINDOWS = "123 456\r\nAA AA\r\nBB BB";
		String NL_UNIX    = "123 456\nAA AA\nBB BB";
		test_nextLine($(NL_MACOSX9));
		test_nextLine($(NL_WINDOWS));
		test_nextLine($(NL_UNIX));
	}

	@Test
	public void test_nextLineFromMid() {
		String NL_MACOSX9 = "123 456\r555 777\rAA AA\rBB BB";
		String NL_WINDOWS = "123 456\r\n555 777\r\nAA AA\r\nBB BB";
		String NL_UNIX    = "123 456\n555 777\nAA AA\nBB BB";
		test_nextLineFromMid($(NL_MACOSX9));
		test_nextLineFromMid($(NL_WINDOWS));
		test_nextLineFromMid($(NL_UNIX));
	}	

}
