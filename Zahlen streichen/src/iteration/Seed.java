package iteration;

public interface Seed<T extends Branch> extends Cloneable {

	public Iterable<T> getFork();

	public void in(T branch);

	public void out();

	public default boolean isFinished() {
		return !getFork().iterator().hasNext();
	}

	public void reset();

	public Seed<T> clone();

}
