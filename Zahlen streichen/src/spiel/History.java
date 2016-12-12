package spiel;

import iteration.Action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Optional;

public class History implements Iterable<Action>, Cloneable{

	private ArrayList<ArrayList<Action>> checkpoints;

	private ArrayList<Action> queue;

	public History() {
		checkpoints = new ArrayList<ArrayList<Action>>();
		queue = new ArrayList<Action>();
	}

	public Optional<Action> pop() {
		if (queue.isEmpty() && checkpoints.isEmpty()) {
			return Optional.empty();
		} else if (!queue.isEmpty()) {
			return Optional.of(queue.remove(queue.size() - 1));
		} else {
			ArrayList<Action> lastCheckpoint = checkpoints.remove(checkpoints
					.size() - 1);
			Action lastAction = lastCheckpoint
					.remove(lastCheckpoint.size() - 1);
			queue = lastCheckpoint;
			return Optional.of(lastAction);
		}
	}
	private ArrayList<Action> getAll(){
		ArrayList<Action> list = new ArrayList<Action>();
		for (ArrayList<Action> a : checkpoints) {
			list.addAll(a);
		}
		list.addAll(queue);
		return list;
	}
	public Iterator<Action> iterator() {
		return getAll().iterator();
	}

	public void add(Action a) {
		queue.add(a);
	}

	public boolean isEmpty() {
		return queue.isEmpty() && checkpoints.isEmpty();
	}
	@Override
	public String toString(){
		return checkpoints.toString() + queue.toString();
	}
	@SuppressWarnings("unchecked")
	@Override
	public History clone(){
		History s = new History();
		s.checkpoints = (ArrayList<ArrayList<Action>>) this.checkpoints.clone();
		s.queue = (ArrayList<Action>) this.queue.clone();
		return s;
	}
}
