import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class Pick3 {

	static Random random = new Random();

	static class Vaze {
		final int p;
		final int c;
		final int z;
		Vaze(int p, int c, int z) {
			this.p = p;
			this.c = c;
			this.z = z;
		}
		public Vaze sub(Vaze that) {
			if (this.p >= that.p && this.c >= that.c && this.z >= that.z) {
				return new Vaze(this.p - that.p, this.c - that.c, this.z - that.z);
			}
			return null;
		}
		public Vaze add(Vaze that) {
			return new Vaze(this.p + that.p, this.c + that.c, this.z + that.z);
		}
		public String toString() {
			return String.format("(p=%d, c=%d, z=%d)", p, c, z);
		}	
	}

	public static void test(Vaze[] A, Vaze[] B) {
		Vaze v = new Vaze(100, 100, 100);
		for (int i = 0; i < 10000; i++) {
			int r = random.nextInt(A.length);
			Vaze t = v.sub(A[r]);
			if (t != null) {
				v = t.add(B[r]);
			}
		}
		System.out.println(v);
	}

	public static void main(String[] args) {
		Vaze[] A = new Vaze[3];
		Vaze[] B = new Vaze[3];
		
		A[0] = new Vaze(3, 0, 0);
		B[0] = new Vaze(1, 1, 0);
		
		A[1] = new Vaze(0, 3, 0);
		B[1] = new Vaze(1, 1, 0);
		
		A[2] = new Vaze(0, 0, 3);
		B[2] = new Vaze(1, 1, 0);

		for (int i = 0; i < 100; i++) {
			test(A, B);
		}
	}

}
