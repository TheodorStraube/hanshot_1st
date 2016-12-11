package spiel;

import java.util.ArrayList;
import java.util.List;

public class Spielfeld {

	private int columnCount = 9;

	private int nextElementPointer;

	private List<Byte> feld;

	public Spielfeld() {
		feld = new ArrayList<Byte>();
		nextElementPointer = 0;
	}

	public void addNumber(byte b) {
		if (b < 0 || b > 19) {
			throw new IllegalArgumentException();
		}
		if (b > 9) {
			addNumber((byte) 1);
			addNumber((byte) (b - 10));
		} else {
			feld.add(b);
			nextElementPointer++;
			if (nextElementPointer >= columnCount) {
				nextElementPointer = 0;
			}
		}
	}

	public byte get(int i) {
		if (i < 0 || i > size() - 1) {
			System.out.println(i);
			System.out.println(toString());
			System.out.println(size() - 1);
			throw new IllegalArgumentException();
		}
		return feld.get(i);
	}

	public void set(int i, byte value) {
		if (i < 0 || i > size() || value > 9 || value > 9 || value < 0) {
			System.out.println(toString());
			throw new IllegalArgumentException();
		}

		feld.set(i, value);
	}

	public int size() {
		return feld.size();
	}

	public int rewriteNumbers() {
		// returns the number of rewrote numbers

		int counter = 0;
		int startingSize = size();
		for (int k = 0; k < startingSize; k++) {
			byte p = get(k);
			if (p != 0) {
				counter++;
				addNumber(p);
			}
		}
		return counter;
	}

	public int eraseRewroteNumbers(int start) {
		int secondIndex = start;
		for (int i = 0; i < start; i++) {
			if (get(i) != 0) {
				if (get(i) == get(secondIndex)) {
					set(secondIndex, (byte) 0);
					secondIndex++;
				} else {
					System.err.println("error!");
				}
			}
		}
		return secondIndex - start;
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
