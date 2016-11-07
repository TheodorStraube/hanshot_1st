package test;

import java.util.LinkedList;

import iteration.Action;
import iteration.Action.ACTION_TYPE;
import spiel.ZahlenStreichen;

public class Main {

	public static void main(String[] args) {

		ZahlenStreichen spiel = new ZahlenStreichen();

		boolean finished = false;

		LinkedList<Action> queue = new LinkedList<Action>();

		int counter = 0;

		int index = 0;

		int depthIndexSize = 0;
		int depthIndex = 0;
		
		spiel.Do(new Action(ACTION_TYPE.ERASE_NUMBERS, 0, 9, 1, 1));
		spiel.unDo();
		
		System.out.println(spiel.getAllActions());

		System.out.println(spiel.spiel);
		

	}

}
