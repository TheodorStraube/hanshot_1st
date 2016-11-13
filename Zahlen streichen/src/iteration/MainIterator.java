package iteration;

import java.util.ArrayList;
import java.util.HashSet;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;

import spiel.ZahlenStreichen;

public class MainIterator {

	private boolean hasNext;

	private final ZahlenStreichen spiel;

	private Graph<Integer, Action> graph;

	private int idCount = 0;
	
	private int pos = 0;

	public MainIterator() {

		spiel = new ZahlenStreichen();

		graph = new DefaultDirectedGraph<Integer, Action>(Action.class);

		hasNext = !spiel.getAllActions().isEmpty();

		graph.addVertex(nextId());
	}

	public boolean hasNext() {
		return hasNext;
	}

	public void next() {
		if (!hasNext) {
			throw new IndexOutOfBoundsException();
		}
		if (spiel.hasWon()) {
			encounterWinningState();
		}
		
		HashSet<Action> nextMoves = spiel.getAllActions();

		int last = getId();
		int next;
		
		for(Action a: nextMoves){
			next = nextId();
			graph.addVertex(next);
			graph.addEdge(last, next, a);
		}
		if(nextMoves.isEmpty()){
			encounterCheckpoint();
		}		
		
	}
	private int getId(){
		return idCount;
	}
	private int nextId() {
		idCount++;
		return idCount - 1;
	}

	private void encounterCheckpoint() {
		System.out.println("CHECKPOINT");
		spiel.Do(spiel.getAddRowAction());
	}

	private void encounterWinningState() {
		System.out.println("WON");
		System.out.println();
		System.out.println(spiel.history);
		System.exit(0);
	}

	@Override
	public String toString() {
		String s = "";

		return s;
	}
}
