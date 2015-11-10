import java.util.Queue;

public interface MeldableQueue<E> extends Queue<E> {
	public void meld(MeldableQueue<E> other);
}