package spiel;

import java.util.ArrayList;
import java.util.HashSet;

import iteration.Action;
import iteration.Action.ACTION_TYPE;
import iteration.Seed;

public class ZahlenStreichen implements Seed<Action> {

	public int elemCount;

	public Spielfeld spiel;

	public ArrayList<Action> history;

	public ZahlenStreichen() {

		history = new ArrayList<Action>();

		reset();
	}

	private ArrayList<Integer> getMoves(int i) {
		ArrayList<Integer> list = new ArrayList<Integer>();
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
		ArrayList<Integer> intMoves = getMoves(i);

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
			byte valueA = spiel.get(a.a);
			byte valueB = spiel.get(a.b);

			assert (elemCount >= 2);
			assert (valueA == valueB || valueA + valueB == 10);
			assert (valueA != 0 && valueB != 0);

			spiel.set(a.a, (byte) 0);
			spiel.set(a.b, (byte) 0);

			elemCount -= 2;
		}
		history.add(a);
	}

	public void unDo() {

		assert (!history.isEmpty());

		Action a = history.remove(history.size() - 1);

		if (a.type == ACTION_TYPE.ADD_ROW) {
			elemCount -= spiel.eraseRewroteNumbers(a.a);
		} else {
			assert (spiel.get(a.a) == 0 && spiel.get(a.b) == 0);

			spiel.set(a.a, a.valueOfA);
			spiel.set(a.b, a.valueOfB);

			elemCount += 2;
		}
	}

	public void setState(History history) {

		reset();

		for (Action a : history) {
			Do(a);
		}
	}

	public void reset() {
		this.spiel = new Spielfeld();

		elemCount = 27;

		this.history.clear();

		for (byte i = 1; i < 20; i++) {
			if (i != 10) {
				spiel.addNumber(i);
			}
		}
	}

	public Action getAddRowAction() {
		return new Action(ACTION_TYPE.ADD_ROW, spiel.size(), 0, (byte) 0, (byte) 0);
	}

	@Override
	public Iterable<Action> getFork() {
		return getAllActions();
	}

	@Override
	public void out() {
		unDo();
	}

	@Override
	public void in(Action branch) {
		Do(branch);
	}

	@Override
	public ZahlenStreichen clone() {
		ZahlenStreichen z = new ZahlenStreichen();
		z.elemCount = elemCount;
		z.spiel = spiel.clone();

		@SuppressWarnings("unchecked")
		ArrayList<Action> clone = (ArrayList<Action>) history.clone();
		z.history = clone;

		return z;

	}

	@Override
	public String toString() {
		return spiel.toString();
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object o) {

		if (o instanceof ZahlenStreichen) {
			ZahlenStreichen z = (ZahlenStreichen) o;

			if (z.spiel.size() == spiel.size()) {

				for (int i = 0; i < spiel.size(); ++i) {
					if (z.spiel.get(i) != spiel.get(i)) {
						return false;
					}
				}

				return true;
			}

		}

		return false;
	}
	@Override
	public boolean isFinished(){
		return hasWon();
	}
}
