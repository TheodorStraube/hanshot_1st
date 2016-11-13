package iteration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import spiel.ZahlenStreichen;

public class QBasedTree {

	private int mainPointer;
	private int depth;
	private int width;
	private int width_last;

	private final List<Action> queue;
	private final List<Integer> widths;
	private final List<Integer> indices;

	private final ZahlenStreichen spiel;

	private final long printDelay = 0;
	private long timer = 0;

	public QBasedTree() {
		this.spiel = new ZahlenStreichen();

		mainPointer = 0;
		depth = 0;

		queue = new ArrayList<Action>();
		widths = new ArrayList<Integer>();
		indices = new ArrayList<Integer>();

		addAll();

		indices.add(queue.size() - 1);

	}

	public void next() {

		Action a = queue.get(mainPointer);
		


		spiel.Do(a);

		status();

		if (spiel.hasWon()) {
			hasWon();
			return;
		}

		addAll();

		if (width >= widths.get(mainPointer - width) - 1) { // At last vertex
															// reachable
															// from this
															// parent

			spiel.unDo();

			
			boolean nextLayer = true;
			for (int i = 2; i <= indices.size(); i++) {
				if (indices.get(indices.size() - i) > 0) {
					nextLayer = false;
				}
			}

			if (!nextLayer) { // There are still other vertices reachable
									// from this' parent's parent i.e. move one
									// up and on to the next one

				spiel.unDo();
				spiel.Do(queue.get(mainPointer - (width_last + width)));
				width = 0;
				width_last--;

			} else { // At last vertex reachable from this depth, move into the next layer

				depth++;

				spiel.Do(queue.get(mainPointer - width));

				width_last = width;
				
				int value = widths.get(0);
				
				indices.set(0, value - 1);
				
				int acc = 1;
				for(int i = 0; i < indices.size(); i++){
					acc += value;	
					indices.set(i, value - 1);
					value = widths.get(acc);
					
				}

				indices.add(width);

				width = 0;
			}

		} else {
			spiel.unDo();
			width++;
		}

		mainPointer++;
	}

	private void status() {
		if (System.nanoTime() - timer > printDelay * 1000000000) {
			timer = System.nanoTime();
			System.out.println("[STATUS] Index: " + mainPointer + ", depth:" + depth + ", width: " + width + "/"
					+ (widths.get(depth) - 1));
			System.out.println(spiel.spiel);
			System.out.println(spiel.history);
			System.out.println();
		}
	}

	private void addAll() {
		HashSet<Action> l = spiel.getAllActions();
		queue.addAll(l);
		widths.add(l.size());
		System.out.println(queue);
		System.out.println(widths);
	}

	private void hasWon() {
		System.out.println("yaay");
		System.out.println(spiel.history);
		System.exit(0);
	}
}
