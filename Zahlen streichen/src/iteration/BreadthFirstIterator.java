package iteration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;

import spiel.History;
import spiel.ZahlenStreichen;

public class BreadthFirstIterator {

	private ZahlenStreichen spiel;

	private ZahlenStreichen bestes;

	private int lowestElemCount = 42;

	private ArrayList<History> queue;

	private boolean isFinished = false;

	private final long printDelay = 2;
	private long timer = 0;

	private Set<Checkpoint> checkpointRepo;

	ListIterator<History> iterator;

	public BreadthFirstIterator() {

		checkpointRepo = new HashSet<Checkpoint>();

		spiel = new ZahlenStreichen(checkpointRepo);
		queue = new ArrayList<History>();

		bestes = new ZahlenStreichen(checkpointRepo);

		iterator = queue.listIterator();

		appendActions();
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void next() {
		spiel.setState(iterator.next());
		iterator.remove();

		if (spiel.elemCount < lowestElemCount) {
			lowestElemCount = spiel.elemCount;

		} else if (spiel.elemCount == lowestElemCount) {
			bestes.setState(spiel.history);
		}
		status();
		if (spiel.hasWon()) {
			isFinished = true;
			System.out.println("done: ");
			System.out.println(spiel.history);
			System.exit(0);
		}
		appendActions();
		spiel.unDo();
	}

	private void status() {
		if (System.nanoTime() - timer > printDelay * 1000000000) {
			timer = System.nanoTime();
			System.out.println("_____________________________ [STATUS] _____________________________");
			System.out.println(spiel.spiel);
			System.out.println(History.reuseCounter);
			System.out.println("Size: " + queue.size());
			System.out.println("Smallest Field: " + lowestElemCount);
			System.out.println(bestes.spiel);
			System.out.println();
		}
	}

	private void appendActions() {
		// watchout: iterator gets placed before first element
		iterator = queue.listIterator(queue.size());
		HashSet<Action> nextActions = spiel.getAllActions();
		if (nextActions.isEmpty()) {
			spiel.Do(spiel.getAddRowAction());
			if (!queue.contains(spiel.history)) {
				iterator.add(spiel.history.clone());
			}
			spiel.unDo();
		}
		for (Action a : nextActions) {
			spiel.Do(a);
			iterator.add(spiel.history.clone());
			spiel.unDo();
		}
		iterator = queue.listIterator();
	}
}
