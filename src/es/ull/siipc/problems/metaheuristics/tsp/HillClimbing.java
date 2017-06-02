package es.ull.siipc.problems.metaheuristics.tsp;
import java.util.ArrayList;
import es.ull.siipc.graph.Graph;
import java.util.concurrent.ThreadLocalRandom;

import es.ull.siipc.problems.TSP;

public class HillClimbing extends TSP {
	
	private ArrayList<Integer> list;
	
	public HillClimbing(Graph graph) {
		super(graph);
		list = new ArrayList<Integer>();
	}
	
	public ArrayList<Integer> solve() {

		int n = getTSP().getN();
		
		int startNode = ThreadLocalRandom.current().nextInt(0, n);
		int actualNode = startNode;
		list.add(actualNode); // Add start node as first node in list
		boolean end = false;
		
		while(!end) {
			
			if (list.size() == n) {
				end = true;
			} else {
				actualNode = getSuccessor(actualNode, list);
				list.add(actualNode);
			}
			
		}

		return list;
		
	}
	
	public int getSuccessor(int actualNode, ArrayList<Integer> list) {
			
		ArrayList<Integer> listSuccessors = new ArrayList<Integer>();
		ArrayList<Double> listSuccessorsValue = new ArrayList<Double>();

		double infinite = Double.MAX_VALUE;
		int n = getTSP().getN();
		
		for (int i = 0; i < n; i++) { // Loop for actualNode column
			if (getTSP().getMatrixItem(i, actualNode) != infinite) { // If there is a connection between nodes
				if (actualNode !=  i) { // If actual node is not itself (there isn't distance)
					listSuccessors.add(i);
					listSuccessorsValue.add(getTSP().getMatrixItem(i, actualNode));
				}
			}
		}
		
		for (int v = 0; v < list.size(); v++) { // Loop for check visited nodes
			for (int i = 0; i < listSuccessorsValue.size(); i++) {
				if (list.get(v) == listSuccessors.get(i)) {
					listSuccessors.remove(i);
					listSuccessorsValue.remove(i);
				}
			}
		}
		
		int successor = listSuccessors.get(0);
		double bestDistance = listSuccessorsValue.get(0); // Get first node distance
		for (int i = 0; i < listSuccessors.size(); i++) {
			if (listSuccessorsValue.get(i) < bestDistance) {
				successor = listSuccessors.get(i);
				bestDistance = listSuccessorsValue.get(i);
			}
		}

		return successor;
	}
	
	public void printResult() {
		
		System.out.print("Hill Climbing result: ");
		for (int i = 0; i  < list.size(); i++){
			System.out.print(list.get(i)+" ");
		}
		System.out.println();
		
	}
}
