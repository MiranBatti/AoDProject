package builder;

import solver.Dijkstra;

public class Test {
	public static void main(String[] args) {
		RecursiveBacktracker rb = new RecursiveBacktracker(9, 9);
		rb.printMaze();
		Dijkstra dj = new Dijkstra(rb);
	}
}

