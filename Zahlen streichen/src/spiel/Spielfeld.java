package spiel;

import java.util.ArrayList;
import java.util.List;

public class Spielfeld {

	private int nextElementPointer = 0;

	private int columnCount = 9;

	private List<Integer> feld;

	public Spielfeld() {
		feld = new ArrayList<Integer>();
	}

	public void addNumber(int i) {
		if (i < 0 || i > 19) {
			throw new IllegalArgumentException();
		}
		if (i > 9) {
			addNumber(1);
			addNumber(i - 10);
		} else {
			feld.add(i);
			nextElementPointer++;
			if (nextElementPointer >= columnCount) {
				nextElementPointer = 0;
			}
		}
	}

	public int get(int i) {
		if (i < 0 || i > size() - 1) {
			throw new IllegalArgumentException();
		}
		return feld.get(i);
	}

	public void set(int i, int value) {
		if (i < 0 || i > size() || value > 9 || value > 9 || value < 0) {
			throw new IllegalArgumentException();
		}

		feld.set(i, value);
	}

	public int size() {
		return feld.size();
	}

	public void rewriteNumbers() {
		int startingSize = size();
		for (int k = 0; k < startingSize; k++) {
			int p = get(k);
			if (p != 0)
				addNumber(p);
		}
	}

	public void eraseRewroteNumbers(int start) {
		int secondIndex = start;
		for (int i = 0; i < start; i++) {
			if (get(i) != 0) {
				if (get(i) == get(secondIndex)) {
					set(secondIndex, 0);
					secondIndex++;
				} else {
					System.err.println("error!");
				}
			}
		}
		checkForEmptySpace();
	}

	public void checkForEmptySpace() {

		boolean flag = false; // Flag up if nonzeros in a row

		for (int i = 0; i < feld.size(); i++) {

			if (get(i) != 0)
				flag = true;
			
			if (i % 9 == 8) {
				if (!flag) {
					for (int l = 0; l < 9; l++) {
						feld.remove(i - l);
					}
					i -= 9;
				}
				flag = false;
			}
		}
	}

	@Override
	public String toString() {
		String s = "";

		int j;

		for (int i = 0; i < feld.size(); i++) {
			j = get(i);

			if (i % 9 == 0) {
				s += '\n';
			}

			s += j + "  ";

		}

		return s;
	}

	public boolean isEmpty() {
		return feld.isEmpty();
	}
}
