package es.ull.siipc.problems.metaheuristics.tsp;

import java.util.ArrayList;
import es.ull.siipc.graph.Graph;
import es.ull.siipc.problems.TSP;

public class TabuSearch extends TSP {
	
	private ArrayList<Integer> finalSolution;
	private ArrayList<Integer> tabu;
	
	public TabuSearch(Graph graph) {
		super(graph);
		finalSolution = new ArrayList<Integer>();
		tabu = new ArrayList<Integer>();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> solve() {
		
		HillClimbing hillclimbing = new HillClimbing(getTSP());
		ArrayList<Integer> currentSolution = new ArrayList<Integer>();
		boolean end = false;
		int n = 100;
		int i = 0;
		
		currentSolution = hillclimbing.solve();
		setFinalSolution((ArrayList<Integer>) currentSolution.clone());
		
		do {

			if (i == n) {
				end = true;
			} else {
				currentSolution = twoOptTabu(currentSolution);
				if (calculateCostTour(currentSolution) < calculateCostTour(finalSolution)){
					finalSolution = (ArrayList<Integer>) currentSolution.clone();
				}
				i++;
			}

		} while(end != true);
                 
		return finalSolution;
	}
	 
	private ArrayList<Integer> twoOptTabu (ArrayList<Integer> currentSolution){
		
		boolean go_start = false;
		boolean improvement = true;
		
		double tempValue = calculateCostTour(currentSolution);
		double bestValue = calculateCostTour(currentSolution);
		
		ArrayList<Integer> tempTour = currentSolution;
		ArrayList<Integer> bestTour = currentSolution;

		while(improvement){
			
			improvement = false;
			
			for(int i = 0; i < getTSP().getN(); i++){
				
				boolean isTabu = false; /* If "i" element is in Tabu list */
				for (int j = 0; j < getTabu().size(); j++){
					
					if (getTabu().get(j) == i) {
						isTabu = true;
						continue;
					}
					
				}
				
				if (!isTabu){
					
					if(go_start){
						break;
					}
					
					for(int k = i + 1; k < getTSP().getN(); k++){
						
						tempTour = twoOptSwap(tempTour, i, k);
						tempValue = calculateCostTour(tempTour);
						
						if(tempValue < bestValue){

							bestValue = tempValue;
							bestTour = tempTour;
							improvement = true;
							go_start = true;
							updateTabu(i);
							
							break;
						
						}
					}
					
				}
			}
		}
		
		return bestTour;
	}
	
	private void updateTabu(int memNode){
		 getTabu().add(memNode);
	}
	
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
	
	
	public void printResult() {
		System.out.println("Tabu Search size: "+getFinalSolution().size());
		System.out.print("Tabu Search result: ");
		for (int i = 0; i  < finalSolution.size(); i++){
			System.out.print(finalSolution.get(i)+" ");
		}
		System.out.println();
		
	}

	public ArrayList<Integer> getTabu() {
		return tabu;
	}

	public void setTabu(ArrayList<Integer> tabu) {
		this.tabu = tabu;
	}
	
	public ArrayList<Integer> getFinalSolution() {
		return finalSolution;
	}

	public void setFinalSolution(ArrayList<Integer> finalSolution) {
		this.finalSolution = finalSolution;
	}

	
}
