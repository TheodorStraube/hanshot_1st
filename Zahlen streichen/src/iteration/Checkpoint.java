package iteration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Checkpoint {

	private ArrayList<Action> queue;

	private HashSet<Action> allActions;

	private Action addRow;

	public Checkpoint(List<Action> queue, Action addRow) {
		this.queue = new ArrayList<Action>(queue);
		allActions = new HashSet<>(queue);
		this.addRow = addRow;
	}

	@Override
	public int hashCode() {
		return queue.size() + addRow.hashCode();
	}

	public Action getAddRowAction() {
		return addRow;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Checkpoint) {
			return allActions.equals(((Checkpoint) o).allActions) && addRow.equals(((Checkpoint) o).getAddRowAction());
		}
		return false;
	}

	public boolean isEmpty() {
		return this.queue.isEmpty();
	}

	public ArrayList<Action> getElements() {
		return queue;
	}
}
