package iteration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Checkpoint {

	private ArrayList<Action> queue;

	private HashSet<Action> allActions;

	public Checkpoint(List<Action> queue) {
		this.queue = new ArrayList<Action>(queue);
		allActions = new HashSet<>(queue);
	}

	@Override
	public int hashCode() {
		return queue.size();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Checkpoint) {
			return allActions.equals(((Checkpoint) o).allActions);
		}
		return false;
	}

	public boolean isEmpty() {
		return this.queue.isEmpty();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Action> getElements() {
		return (ArrayList<Action>) queue.clone();
	}
}
