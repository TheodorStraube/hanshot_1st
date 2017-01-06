package spiel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import iteration.Action;
import iteration.Action.ACTION_TYPE;
import iteration.Checkpoint;

public class History implements Iterable<Action>, Cloneable {

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
			reachCheckpoint(a);
		}
	}

	public Optional<Action> pop() {
		if (queue.isEmpty() && checkpoints.isEmpty()) {
			return Optional.empty();
		} else if (!queue.isEmpty()) {
			return Optional.of(queue.remove(queue.size() - 1));
		} else {
			Checkpoint lastCheckpoint = checkpoints.remove(checkpoints.size() - 1);
			Action lastAction = lastCheckpoint.getAddRowAction();
			queue = lastCheckpoint.getElements();
			return Optional.of(lastAction);
		}
	}

	private void reachCheckpoint(Action addRow) {
		Checkpoint checkpoint = new Checkpoint(queue, addRow);
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

	private ArrayList<Action> getAll() {
		ArrayList<Action> list = new ArrayList<Action>();
		for (Checkpoint c : checkpoints) {
			list.addAll(c.getElements());
		}
		list.addAll(queue);
		return list;
	}

	public boolean isEmpty() {
		return checkpoints.isEmpty() && queue.isEmpty();
	}

	@Override
	public Iterator<Action> iterator() {
		return getAll().iterator();
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