package spiel;

import java.util.ArrayList;

import iteration.Action;
import iteration.Generator;

public class MyGenerator extends Generator<ZahlenStreichen, Action> {
	
	int minimalKnownRounds = 36;

	public MyGenerator(ZahlenStreichen seed) {
		super(seed);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onFindLeaf(ZahlenStreichen leaf) {

		addFork(leaf.getAddRowAction());

	}

	@Override
	protected void afterEnterNextLayer(ArrayList<ZahlenStreichen> oldSeeds) {

		ArrayList<ZahlenStreichen> reducedSeeds = new ArrayList<ZahlenStreichen>();

		for (ZahlenStreichen z : oldSeeds) {
			if (z.elemCount <= 2 * (minimalKnownRounds - getIterationDepth()) && !reducedSeeds.contains(z) ) {
				reducedSeeds.add(z);
			}
		}

		oldSeeds.clear();
		oldSeeds.addAll(reducedSeeds);
		

	}

}
