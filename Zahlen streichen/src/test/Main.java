package test;

import iteration.QBasedTree;

public class Main {

	public static void main(String[] args) {

//		ZahlenStreichen spiel = new ZahlenStreichen();
//
//		LinkedList<Action> queue = new LinkedList<Action>();
//
//		spiel.Do(new Action(ACTION_TYPE.ERASE_NUMBERS, 0, 9, 1, 1));
//		spiel.unDo();
//		spiel.Do(spiel.getAllActions().iterator().next());
//		spiel.Do(spiel.getAllActions().iterator().next());
//
//		for (Action a : spiel.getAllActions()) {
//			spiel.Do(a);
//			System.out.println(spiel.spiel);
//			spiel.unDo();
//		}

		QBasedTree t = new QBasedTree();
		
//		t.next();
//		t.next();
		
		for (int i = 0; i < 10; i++) {
			t.next();
		}
//		while(true){
//			t.next();
//		}
	}

}
