/**
 * Project of metaheuristics
 * 
 * MÃ¡ster Universitario en IngenierÃ­a InformÃ¡tica
 * E.S.I.T.â€“ INFORMÃ�Ì�TICA
 * Departamento de IngenierÃ­a InformÃ¡tica y de Sistemas
 * Sistemas Inteligentes e InteracciÃ³n Persona Computador
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
import es.ull.siipc.problems.metaheuristics.tsp.*;
import es.ull.siipc.statistics.Statistics;

public class Main {
	/**
	 * Main function of the program
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length == 1) {
			Graph graph = new Graph(args[0]);
			SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing (graph);
			HillClimbing hillclimbing = new HillClimbing(graph);
						
			ArrayList<Double> results = new ArrayList<Double> ();
			
			/* Hill Climbing (local search) */
			@SuppressWarnings("unused")
			ArrayList<Integer> solutionsHillClimbing = new ArrayList<Integer>();
			solutionsHillClimbing = hillclimbing.solve();
			hillclimbing.printResult();
			
			/* Simulated Annealing */
			System.out.println("Simulated Annealing");
			for(int i = 0; i < 100; i++) {
				ArrayList<Integer> solutionSimulatedAnnealing =
						simulatedAnnealing.solve();
			
				results.add(simulatedAnnealing.calculateCostTour(solutionSimulatedAnnealing));
			}
						
			Statistics statistics = new Statistics(results);
			statistics.showStatistics();
	    }
	}
}