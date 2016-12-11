package iteration;

public class Action {

	public enum ACTION_TYPE {
		ADD_ROW, ERASE_NUMBERS;
	}

	public static Action addRow = new Action(ACTION_TYPE.ADD_ROW, 0, 0, (byte) 0, (byte) 0);

	public final ACTION_TYPE type;

	public final int a;
	public final int b;

	public final byte valueOfA;
	public final byte valueOfB;

	public Action(ACTION_TYPE type, int a, int b, byte valueA, byte valueB) {

		this.type = type;
		this.a = a;
		this.b = b;

		this.valueOfA = valueA;
		this.valueOfB = valueB;

	}

	@Override
	public String toString() {
		switch (type) {
		case ADD_ROW:
			return "ACTION: Add Row at index: " + a;
		case ERASE_NUMBERS:
			return "ACTION: Erase " + valueOfA + " from index " + a + " and " + valueOfB + " from index " + b;
		}
		return "error";
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Action && ((Action) o).type == ACTION_TYPE.ADD_ROW) {
			return true;
		} else if (o instanceof Action && ((Action) o).type == type) {
			Action a = (Action) o;
			return (a.a == this.a && a.b == b && a.valueOfA == valueOfA && a.valueOfB == valueOfB)
					|| (a.a == b && a.b == this.a && a.valueOfA == valueOfB && a.valueOfB == valueOfA);
		}
		return false;
	}
}
