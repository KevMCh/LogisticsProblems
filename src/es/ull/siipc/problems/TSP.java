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
		getTSP().printDataGraph();;
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