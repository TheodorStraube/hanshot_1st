package spiel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import iteration.Action;
import iteration.Action.ACTION_TYPE;
import iteration.Checkpoint;

public class History implements Iterable<Action> {

	public static int reuseCounter = 0;

	private final Set<Checkpoint> checkpointRepository;

	private ArrayList<Checkpoint> checkpoints;

	private ArrayList<Action> queue;

	public History(Set<Checkpoint> checkpointRepository) {

		this.checkpointRepository = checkpointRepository;

		this.checkpoints = new ArrayList<Checkpoint>();
		this.queue = new ArrayList<Action>();
	}

	public void add(Action a) {

		queue.add(a);

		if (a.type == ACTION_TYPE.ADD_ROW) {
			reachCheckpoint();
		}
	}

	public Action pop() {
		if (queue.isEmpty()) {
			if (checkpoints.isEmpty()) {
				System.err.println("History is empty - cant pop.");
				return null;
			}
			Checkpoint c = checkpoints.remove(checkpoints.size() - 1);
			queue = c.getElements();
		}
		return queue.remove(queue.size() - 1);
	}

	private void reachCheckpoint() {
		Checkpoint checkpoint = new Checkpoint(queue);
		queue.clear();
		for (Checkpoint c : checkpointRepository) {
			if (c.equals(checkpoint)) {
				reuseCounter++;
				checkpoints.add(c);
				return;
			}
		}
		checkpointRepository.add(checkpoint);
		checkpoints.add(checkpoint);
	}

	public boolean isEmpty() {
		return checkpoints.isEmpty() && queue.isEmpty();
	}

	@Override
	public Iterator<Action> iterator() {
		ArrayList<Action> list = new ArrayList<Action>();
		for (Checkpoint c : checkpoints) {
			list.addAll(c.getElements());
		}
		list.addAll(queue);
		return list.iterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public History clone() {
		History h = new History(checkpointRepository);

		h.checkpoints = (ArrayList<Checkpoint>) checkpoints.clone();
		h.queue = (ArrayList<Action>) queue.clone();

		return h;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof History)) {
			return false;
		}
		if (!((History) o).queue.equals(queue)) {
			return false;
		}
		return isAtSameCheckpoint((History) o);
	}

	@Override
	public int hashCode() {
		return checkpoints.size() + queue.size();
	}

	public boolean isAtSameCheckpoint(History h) {
		if (!queue.isEmpty() || !h.queue.isEmpty()) {
			return false;
		}
		return h.checkpoints.equals(checkpoints);
	}

}
