package spiel;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

import iteration.Action;
import iteration.Action.ACTION_TYPE;

public class ZahlenStreichen {

	public Spielfeld spiel;

	public ArrayList<Action> history;

	public ZahlenStreichen() {

		spiel = new Spielfeld();
		history = new ArrayList<Action>();

		for (int i = 1; i < 20; i++) {
			if (i != 10) {
				spiel.addNumber(i);
			}
		}
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
		spiel.checkForEmptySpace();
		return spiel.isEmpty();
	}

	public void Do(Action a) {
		if (a.type == ACTION_TYPE.ADD_ROW) {
			spiel.rewriteNumbers();
		} else {
			spiel.set(a.a, 0);
			spiel.set(a.b, 0);
		}
		history.add(a);
	}

	public void unDo() {
		Action a = history.remove(history.size() - 1);
		if (a.type == ACTION_TYPE.ADD_ROW) {
			spiel.eraseRewroteNumbers(a.a);
		} else {
			if (spiel.get(a.a) == 0 && spiel.get(a.b) == 0) {
				spiel.set(a.a, a.valueOfA);
				spiel.set(a.b, a.valueOfB);
			} else {
				System.err.println("error");
			}
		}
	}

	public void setState(List<Action> history) {
		this.spiel = new Spielfeld();

		this.history = new ArrayList<Action>();

		for (int i = 1; i < 20; i++) {
			if (i != 10) {
				spiel.addNumber(i);
			}
		}
		for (Action a : history) {
			Do(a);
		}
	}

}
