package iteration;

public interface Seed<T extends Branch> {

	public Iterable<T> getFork();

	public void in(T branch);

	public void out();

	public default boolean isFinished() {
		return !getFork().iterator().hasNext();
	}

}
