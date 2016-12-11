package test;

import java.util.ArrayList;
import java.util.ListIterator;

import iteration.Action;
import iteration.BreadthFirstIterator;
import iteration.Action.ACTION_TYPE;
import spiel.ZahlenStreichen;

public class Main {

	public static void main(String[] args) {

		// ZahlenStreichen spiel = new ZahlenStreichen();
		//
		// LinkedList<Action> queue = new LinkedList<Action>();
		//
		// spiel.Do(new Action(ACTION_TYPE.ERASE_NUMBERS, 0, 9, 1, 1));
		// spiel.unDo();
		// spiel.Do(spiel.getAllActions().iterator().next());
		// spiel.Do(spiel.getAllActions().iterator().next());
		//
		// for (Action a : spiel.getAllActions()) {
		// spiel.Do(a);
		// System.out.println(spiel.spiel);
		// spiel.unDo();
		// }

		BreadthFirstIterator it = new BreadthFirstIterator();

		ArrayList<Integer> list = new ArrayList<Integer>();
		ListIterator<Integer> iterator = list.listIterator(0);
		for (int i = 0; i < 10; i++) {
			iterator.add(i);
			// System.out.println("prev: " + iterator.previous());
		}

		iterator = list.listIterator();
		System.out.println("of size: " + list.size());
		for (int i = 0; i < 10; i++) {
			System.out.println("remove: " + iterator.next());
			iterator.remove();
		}

		for (int i : list) {
			System.out.println(i);
		}
		ZahlenStreichen z = new ZahlenStreichen();

		System.out.println(z.spiel);

		z.Do(new Action(ACTION_TYPE.ERASE_NUMBERS, 10, 11, (byte) 1, (byte) 1));
		z.Do(new Action(ACTION_TYPE.ERASE_NUMBERS, 8, 9, (byte) 9, (byte) 1));
		z.Do(new Action(ACTION_TYPE.ERASE_NUMBERS, 7, 12, (byte) 8, (byte) 2));
		z.Do(new Action(ACTION_TYPE.ERASE_NUMBERS, 26, 17, (byte) 9, (byte) 1));

		System.out.println(z.spiel);
		System.out.println(z.elemCount);

		z.Do(z.getAddRowAction());
		System.out.println(z.elemCount);

		System.out.println(z.spiel);

		z.Do(new Action(ACTION_TYPE.ERASE_NUMBERS, 42, 33, (byte) 7, (byte) 7));
		z.Do(new Action(ACTION_TYPE.ERASE_NUMBERS, 43, 34, (byte) 1, (byte) 1));
		z.Do(new Action(ACTION_TYPE.ERASE_NUMBERS, 36, 27, (byte) 1, (byte) 1));

		System.out.println(z.spiel);
		System.out.println(z.elemCount);

		z.Do(z.getAddRowAction());

		System.out.println(z.spiel);

		z.Do(new Action(ACTION_TYPE.ERASE_NUMBERS, 10, 11, (byte) 1, (byte) 1));
		z.Do(new Action(ACTION_TYPE.ERASE_NUMBERS, 10, 11, (byte) 1, (byte) 1));

		// t.next();
		// t.next();

		// for (int i = 0; i < 100000; i++) {
		// it.next();
		// }
		while (true) {
			it.next();
		}
	}

}
