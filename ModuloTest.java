import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

/**
  * @author Mighty Cohadar 
  */
public class ModuloTest {

	@Test
	public void test() {
		for (int i = 0; i < 10000; i++) {
			int a = random.nextInt();
			int b = random.nextInt();
			if (b == 0) {
				b = 1 + random.nextInt(10000);
			}
			assertEquals(a % b, a % (Math.abs(b)));
			assertEquals((-a) % b, -(a % b));
		}
	}

	public static Random random = new Random();

}
