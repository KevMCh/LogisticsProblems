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
 * Main class to ejecute the program.
 */

import java.util.ArrayList;

import es.ull.siipc.graph.Graph;
import es.ull.siipc.problems.TSP;
import es.ull.siipc.problems.metaheuristics.tsp.SimulatedAnnealing;
import es.ull.siipc.statistics.Statistics;

public class Main {
	/**
	 * Main function of the program
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length == 1) {
			Graph graph = new Graph(args[0]);
			SimulatedAnnealing tspProblem = new SimulatedAnnealing (graph);
						
			ArrayList<Double> results = new ArrayList<Double> ();
			
			for(int i = 0; i < 100; i++) {
				ArrayList<Integer> finalSolution = tspProblem.randomSolution();
				ArrayList<Integer> solutionVariableNeighborhoodSearch =
						tspProblem.solveVariableNeighborhoodSearch(finalSolution, 32);
						
				// System.out.println(solutionVariableNeighborhoodSearch);
			
				results.add(tspProblem.calculateCostTour(solutionVariableNeighborhoodSearch));
			}
						
			Statistics statistics = new Statistics(results);
			statistics.showStatistics();
		}
	}
}