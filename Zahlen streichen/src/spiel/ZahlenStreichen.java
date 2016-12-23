package spiel;

import java.util.HashSet;
import java.util.Set;

import iteration.Action;
import iteration.Action.ACTION_TYPE;
import iteration.Checkpoint;

public class ZahlenStreichen {

	public int elemCount;

	public Spielfeld spiel;

	public History history;

	private final Set<Checkpoint> checkpointRepository;

	public ZahlenStreichen(Set<Checkpoint> checkpointRepo) {

		elemCount = 0;

		this.checkpointRepository = checkpointRepo;

		spiel = new Spielfeld();
		history = new History(checkpointRepo);

		for (byte i = 1; i < 20; i++) {
			if (i != 10) {
				spiel.addNumber(i);
			}
		}
		elemCount = 27;
	}

	private HashSet<Integer> getMoves(int i) {
		HashSet<Integer> list = new HashSet<Integer>();
		int number = spiel.get(i);

		if (number == 0) {
			return list;
		}

		// Nach rechts
		int index = i;
		do {
			index++;

			if (index >= spiel.size()) {
				break;
			}

			if (spiel.get(index) == number || spiel.get(index) + number == 10) {
				list.add(index);
				break;
			}
		} while (spiel.get(index) == 0 && index < spiel.size() - 1);

		// Nach unten
		index = i;
		do {
			index -= 9;
			if (index >= spiel.size() || index < 0) {
				break;
			}
			if (spiel.get(index) == number || spiel.get(index) + number == 10) {
				list.add(index);
				break;
			}
		} while (spiel.get(index) == 0);

		return list;
	}

	public HashSet<Action> getActions(int i) {
		HashSet<Action> list = new HashSet<Action>();
		HashSet<Integer> intMoves = getMoves(i);

		for (Integer move : intMoves) {
			list.add(new Action(ACTION_TYPE.ERASE_NUMBERS, i, move, spiel.get(i), spiel.get(move)));

		}
		// list.add(new Action(ACTION_TYPE.ADD_ROW, spiel.size(), 0, 0, 0));
		return list;
	}

	public HashSet<Action> getAllActions() {
		HashSet<Action> list = new HashSet<Action>();
		for (int i = 0; i < spiel.size(); i++) {
			list.addAll(getActions(i));
		}
		return list;
	}

	public boolean hasWon() {
		return elemCount == 0;
	}

	public void Do(Action a) {
		if (a.type == ACTION_TYPE.ADD_ROW) {
			elemCount += spiel.rewriteNumbers();
		} else {

			assert (elemCount >= 2);
			assert (spiel.get(a.a) == spiel.get(a.b));
			assert (spiel.get(a.a) != 0);
			assert (false);

			spiel.set(a.a, (byte) 0);
			spiel.set(a.b, (byte) 0);

			elemCount -= 2;
		}
		history.add(a);
	}

	public boolean unDo() {
		if (history.isEmpty()) {
			return false;
		}

		Action a = history.pop();
		if (a.type == ACTION_TYPE.ADD_ROW) {
			elemCount -= spiel.eraseRewroteNumbers(a.a);
		} else {
			if (spiel.get(a.a) == 0 && spiel.get(a.b) == 0) {
				spiel.set(a.a, a.valueOfA);
				spiel.set(a.b, a.valueOfB);
				elemCount += 2;
			} else {
				System.err.println("error unDo");
				return false;
			}
		}
		return true;
	}

	public void setState(History history) {
		this.spiel = new Spielfeld();

		elemCount = 27;

		this.history = new History(checkpointRepository);

		for (byte i = 1; i < 20; i++) {
			if (i != 10) {
				spiel.addNumber(i);
			}
		}
		for (Action a : history) {
			Do(a);
		}
	}

	public Action getAddRowAction() {
		return new Action(ACTION_TYPE.ADD_ROW, spiel.size(), 0, (byte) 0, (byte) 0);
	}

}
