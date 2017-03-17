package tools;

public class ModVector {

	private int value;
	private int range;

	public ModVector(int range) {
		this.range = range;
		this.value = 0;
	}

	public ModVector(int value, int range) {
		this.value = value;
		this.range = range;
	}

	public void inc() {
		value = (value + 1) % range;
	}

	public int get() {
		return value;
	}

	public int getRange() {
		return range;
	}

}
