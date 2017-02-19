package test;

import iteration.Action;
import iteration.Generator;
import spiel.ZahlenStreichen;

public class Main_2 {

	public static void main(String[] args) {

		ZahlenStreichen z = new ZahlenStreichen();

		Generator<ZahlenStreichen, Action> gen = new Generator<ZahlenStreichen, Action>(z);

		for (int i = 0; i < 10; i++) {
			gen.next();
		}

		System.out.println("done");

	}

}
