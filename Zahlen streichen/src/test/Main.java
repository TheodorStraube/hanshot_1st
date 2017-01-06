package test;

import java.util.HashSet;

import iteration.Action;
import iteration.Action.ACTION_TYPE;
import iteration.BreadthFirstIterator;
import iteration.Checkpoint;
import spiel.ZahlenStreichen;

public class Main {

	public static void main(String[] args) {



		BreadthFirstIterator it = new BreadthFirstIterator();


		HashSet<Checkpoint> repo = new HashSet<Checkpoint>();

		ZahlenStreichen z = new ZahlenStreichen(repo);

//		System.out.println(z.spiel);
//
//		z.Do(new Action(ACTION_TYPE.ERASE_NUMBERS, 10, 11, (byte) 1, (byte) 1));
//		z.Do(new Action(ACTION_TYPE.ERASE_NUMBERS, 8, 9, (byte) 9, (byte) 1));
//		z.Do(new Action(ACTION_TYPE.ERASE_NUMBERS, 7, 12, (byte) 8, (byte) 2));
//		z.Do(new Action(ACTION_TYPE.ERASE_NUMBERS, 26, 17, (byte) 9, (byte) 1));
//
//		System.out.println(z.spiel);
//		System.out.println(z.getHistory());
//		System.out.println(z.elemCount);
//
//		z.Do(z.getAddRowAction());
//		
//		System.out.println(z.getHistory());
//		
//		System.out.println(z.elemCount);
//
//		System.out.println(z.spiel);
//
//		z.Do(new Action(ACTION_TYPE.ERASE_NUMBERS, 42, 33, (byte) 7, (byte) 7));
//		z.Do(new Action(ACTION_TYPE.ERASE_NUMBERS, 43, 34, (byte) 1, (byte) 1));
//		z.Do(new Action(ACTION_TYPE.ERASE_NUMBERS, 36, 27, (byte) 1, (byte) 1));
//
//		System.out.println(z.spiel);
//		System.out.println(z.elemCount);
//		
//		System.out.println(z.getHistory());
//
//		z.Do(z.getAddRowAction());
//
//		System.out.println(z.spiel);
//
//		System.out.println("foooooo");
//		
//		System.out.println(z.getHistory());
//		
//		System.out.println(z.spiel);
//		System.out.println();
//		z.Do(new Action(ACTION_TYPE.ERASE_NUMBERS, 10, 11, (byte) 1, (byte) 1));
//		z.Do(new Action(ACTION_TYPE.ERASE_NUMBERS, 10, 11, (byte) 1, (byte) 1));
//
//		System.out.println(z.spiel);
//		
//		System.out.println(z.getHistory());
		
		// t.next();
		// t.next();

//		for (int i = 0; i < 100000; i++) {
//			it.next();
//		}
		while (true) {
			it.next();
		}
	}

}
