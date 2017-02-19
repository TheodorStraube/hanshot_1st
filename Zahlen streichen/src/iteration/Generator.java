package iteration;

import java.util.ArrayList;
import java.util.Collection;

public class Generator<S extends Seed<B>, B extends Branch> {

	private S seed;

	private ArrayList<B> queue;

	private int index = 0;

	private ArrayList<Integer> indices;

	int acc = 0;

	int position = 0;

	int depth = 0;

	public boolean textStatus = true;

	public Generator(S seed) {
		this.seed = seed;

		queue = new ArrayList<B>();

		index = 0;

		indices = new ArrayList<Integer>();

		acc = appendFork();

		indices.add(acc);

	}

	public void next() {

		if (textStatus)
			System.out.println("[Next] position: " + position + ", acc: " + acc);

		if (queue.isEmpty() && seed.isFinished()) {
			finish(1);
			return;
		}
		if (indices.isEmpty() || position >= indices.get(indices.size() - 1)) {
			nextLayer();
		}

		seed.in(queue.get(index));

		appendFork();

		seed.out();

		index++;
		position++;

	}

	private void finish(int status) {
		switch (status) {
		case 1: {
			System.err.println("The result can not be created with the given Seed.");
			break;
		}
		}
	}

	private int appendFork() {

		/**
		 * returns the number of elements appended
		 * 
		 */

		Iterable<B> i = seed.getFork();

		if (!i.iterator().hasNext()) {
			throw (new UnsupportedOperationException("No more branches, 'finish' shouldve been called"));
		}

		int size = queue.size();

		queue.addAll((Collection<? extends B>) i);
		size = queue.size() - size;

		if (textStatus)
			System.out.println("append " + size + " elements");

		return size;

	}

	private void nextLayer() {

		indices.add(acc);

		if (textStatus)
			System.out.println("finishing up layer " + depth + " with " + acc + " elements.");

		index -= acc;

		depth++;
		acc = 0;

	}

	@Override
	public String toString() {
		String s = "";

		return s;
	}

	public String status() {
		String s = "";

		s += seed.toString() + "\n";
		s += "depth: " + depth + "position: " + position + ", acc: " + acc;

		return s;
	}

}
