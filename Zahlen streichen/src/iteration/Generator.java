package iteration;

import java.util.ArrayList;
import java.util.Collection;

import spiel.ZahlenStreichen;

public class Generator<S extends Seed<B>, B extends Branch> {

	private ArrayList<S> seeds;

	private ArrayList<B> queue;

	private int index = 0;
	private int seedIndex = 0;

	private ArrayList<Integer> indices;

	int iterationDepth = 0;

	public boolean textStatus = true;

	public Generator(S seed) {

		this.seeds = new ArrayList<S>();
		seeds.add(seed);

		queue = new ArrayList<B>();

		indices = new ArrayList<Integer>();

	}

	public void next() {

		if (seeds.isEmpty() && queue.isEmpty()) {
			finish(0);
			return;
		}

		if (seedIndex > seeds.size() - 1) {
			iterationDepth++;

			if (textStatus)
				System.out.println("Advancing to depth: " + iterationDepth);

			nextLayer();

		}

		appendFork();

		// if (textStatus)
		// System.out.println("[Next] position: " + index + " (" + seedIndex +
		// ") " + ", breadth: " + seeds.size()
		// + " depth: " + iterationDepth);

		index++;
		seedIndex++;

	}

	private void finish(int status) {
		switch (status) {
		case 1: {
			System.err.println("The result can not be created with the given Seed.");
			break;
		}
		case 0:{
			System.out.println("WIN");
		}
		}
		System.exit(0);
	}

	private void appendFork() {

		S seed = seeds.get(seedIndex);

		Iterable<B> i = seed.getFork();

		if (!i.iterator().hasNext()) {
			onFindLeaf(seed);
			return;
		}

		addFork((Collection<B>) i);

		// if (textStatus)
		// System.out.println("append " + ((Collection<B>) i).size() + "
		// elements");

	}

	@SuppressWarnings("unchecked")
	private void nextLayer() {

		ArrayList<S> nextSeeds = new ArrayList<S>();
		int queueIndex = 0;

		for (int i = 0; i < indices.size(); i++) {

			for (int k = 0; k < indices.get(i); k++) {

				S seed = seeds.get(i);

				seed.in(queue.get(queueIndex));

				S clone = (S) seed.clone();

				nextSeeds.add(clone);

				if (clone.isFinished()) {
					System.out.println(clone);
					ZahlenStreichen z = (ZahlenStreichen) clone;
					for(Action a : z.history){
						System.out.println(a);
					}
					finish(0);
				}

				seed.out();

				queueIndex++;
			}

		}

		afterEnterNextLayer(nextSeeds);

		if (nextSeeds.size() <= 0) {
			finish(1);
		}

		if (textStatus) {

			System.out.println("Next layer has " + nextSeeds.size() + " vertices.");

		}

		seeds = nextSeeds;
		queue.clear();
		indices.clear();
		seedIndex = 0;
	}

	@Override
	public String toString() {
		String s = "";

		return s;
	}

	protected int getIterationDepth() {
		return iterationDepth;
	}

	protected void addEmptyBranch() {
		addEmptyBranch(0);
	}

	protected void addEmptyBranch(int i) {
		indices.add(i);
	}

	protected void addToFork(B branch) {

		indices.set(indices.size() - 1, indices.get(indices.size() - 1) + 1);
		queue.add(branch);

	}

	protected void addFork(B branch) {

		addEmptyBranch(1);
		queue.add(branch);

	}

	protected void addFork(Collection<B> branches) {

		addEmptyBranch(branches.size());
		queue.addAll(branches);

	}

	protected void onFindLeaf(S leaf) {
		throw (new UnsupportedOperationException("No more branches, 'finish' shouldve been called"));
	}

	protected void afterEnterNextLayer(ArrayList<S> oldSeeds) {

	}
}
