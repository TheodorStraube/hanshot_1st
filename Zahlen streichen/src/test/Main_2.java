package test;

import spiel.MyGenerator;
import spiel.ZahlenStreichen;

public class Main_2 {

	public static void main(String[] args) {

		ZahlenStreichen z = new ZahlenStreichen();

		MyGenerator gen = new MyGenerator(z);

//		for (int i = 0; i < 1000; i++) {
//			gen.next();
//		}
		while(true){
			gen.next();
		}

//		System.out.println("done");

	}

}
