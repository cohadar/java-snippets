import java.util.BitSet;

public class BitMatrix {
	private final int width;
	private final int height;
	private final BitSet vector;
	private int index(int x, int y) {
		return y * width + x;
	}
	public BitMatrix(int width, int height) {
		this.width = width;
		this.height = height;
		this.vector = new BitSet(width * height);
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public boolean get(int x, int y) {
		return vector.get(index(x, y));
	}
	public void set(int x, int y) {
		vector.set(index(x, y));
	}
	public void clear(int x, int y) {
		vector.clear(index(x, y));
	}
	public void flip(int x, int y) {
		vector.flip(index(x, y));
	}
}