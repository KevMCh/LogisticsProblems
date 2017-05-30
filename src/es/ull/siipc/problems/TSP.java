/**
 * Project of metaheuristics
 * 
 * Máster Universitario en Ingeniería Informática
 * E.S.I.T.– INFORMÁ́TICA
 * Departamento de Ingeniería Informática y de Sistemas
 * Sistemas Inteligentes e Interacción Persona Computador
 * 
 * Project to study of metaheuristics, it is a higher-level procedure or heuristic 
 * designed to find, generate, or select a heuristic (partial search algorithm) that
 * may provide a sufficiently good solution to an optimization problem, especially 
 * with incomplete or imperfect information or limited computation capacity.
 *
 * Class to represent the TSP problem.
 */
package es.ull.siipc.problems;

import java.util.ArrayList;

import es.ull.siipc.graph.Graph;

public class TSP {
	
	private Graph tsp;				// Graph
	
	/**
	 * Default builder
	 */
	public TSP(){}		
	
	/**
	 * Builder
	 * @param tsp
	 */
	public TSP(Graph tsp){
		load(tsp);
	}
	
	/**
	 * Function to load the file
	 * @param tsp
	 */
	public void load(Graph tsp){
		this.tsp = tsp;		
	}
	
	/**
	 * Function to print the graph data.
	 */
	public void printData(){
		getTSP().printDataGraph();
	}
	
	/**
	 * Function to generate a aleatory solution
	 * @return
	 */
	public ArrayList<Integer> randomSolution (){
		
		ArrayList<Integer> nodes = new ArrayList<Integer> ();
		for(int i = 0; i < getTSP().getN(); i++) {
			 nodes.add(i);
		}
		
		ArrayList<Integer> randomTour = new ArrayList<Integer> ();
		do {
			
			int indexNode = (int) Math.floor(Math.random() * (getTSP().getN() - randomTour.size()));
			if(randomTour.size() == 0) {
				randomTour.add(nodes.get(indexNode));
				
			} else {
				
				Double cost = getTSP().getMatrixItem(randomTour.get(randomTour.size() - 1), nodes.get(indexNode));
				if(cost != Double.POSITIVE_INFINITY){
					randomTour.add(nodes.get(indexNode));
				}	
			}
			
			nodes.remove(indexNode);
		} while (randomTour.size() != getTSP().getN());

		return randomTour;
	}
	
	/**
	 * Calculate final tour using a simulated annealing algorithm
	 * @return
	 */
	public ArrayList<Integer> simulatedAnnealing() {
		ArrayList<Integer> finalSolution = randomSolution ();	
		ArrayList<Integer> newSolution = new ArrayList<Integer> ();
		
		int i = 0;
		while (i < 1000) {
			newSolution = twoOPT (finalSolution);
			
			Double costFinalSolution = calculateCostTour(finalSolution);
			Double costNewSolution = calculateCostTour(newSolution);
			
			if (costFinalSolution < costNewSolution) {
				finalSolution = newSolution;
			} else {
				if(1 - (costNewSolution / costFinalSolution) > Math.random()){
				// if((costNewSolution / costFinalSolution) > Math.random()){
					finalSolution = newSolution;
				}
			}
			
			i++;
		}
		
		return finalSolution;
	}
	
	/**
	 * Function to calculate the cost tour
	 * @param tour
	 * @return
	 */
	public Double calculateCostTour(ArrayList<Integer> tour) {
		Double cost = 0.0;
		
		for(int i = 0; i < tour.size() - 1; i++) {
			cost += getTSP().getMatrixItem(tour.get(i),
					tour.get(i + 1));
		}
		
		cost += getTSP().getMatrixItem(tour.get(tour.size() - 1),
				tour.get(0));
		
		return cost;
	}
	
	/**
	 * Function 2-opt algorithm 
	 * @param bT
	 * @return
	 */
	private ArrayList<Integer> twoOPT(ArrayList<Integer> bT) {
		boolean go_start = false;
		boolean improvement = true;
		
		double tempValue = calculateCostTour(bT);
		double bestValue = calculateCostTour(bT);
		
		ArrayList<Integer> tempTour = bT;
		ArrayList<Integer> bestTour = bT;

		while(improvement){
			improvement = false;
			
			for(int i = 0; i < getTSP().getN();i++){
				if(go_start){
					break;
				}
				
				for(int k = i + 1; k < tsp.getN(); k++){
					
					tempTour = twoOptSwap(tempTour, i, k);
					tempValue = calculateCostTour(tempTour);
					
					if(tempValue < bestValue){
						bestValue = tempValue;
						bestTour = tempTour;
						improvement = true;
						go_start = true;
						
						break;
					
					}
				}
			}
		}
		
		return bestTour;
	}
	
	/**
	 * 2-Opt swap function
	 * @param bestTour
	 * @param i
	 * @param k
	 * @return
	 */
	private ArrayList<Integer> twoOptSwap(ArrayList<Integer> bestTour, int i, int k) {
		ArrayList<Integer> newRoute = new ArrayList<Integer> ();
			
		for(int j = 0; j < i; j++){
			newRoute.add(bestTour.get(j));
		}
		
		for(int j = k; j >= i; j--){
			newRoute.add(bestTour.get(j));
		}
		
		for(int j = k+1; j < bestTour.size(); j++){
			newRoute.add(bestTour.get(j));
		}
		
		return newRoute;
	}

	/**
	 * Getter TSP graph
	 * @return
	 */
	public Graph getTSP() { return tsp; }

	/**
	 * Setter TSP graph
	 * @param tsp
	 */
	public void setTSP(Graph tsp) { this.tsp = tsp; }
}