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
 * Statistics class.
 */

package es.ull.siipc.statistics;

import java.util.ArrayList;

public class Statistics {
	
	private Double better;				// Minus cost
	private Double worse;				// Mayor cost
	private Double average;				// Cost average
	
	/**
	 * Default builder
	 */
	public Statistics (){
		setDefaultValues();

	}
	
	/**
	 * Builder
	 * @param results
	 */
	public Statistics (ArrayList<Double> results){
		setDefaultValues();
		
		getBetterResult(results);
		getWorseResult(results);
		getAverageResult(results);
	}
	
	/**
	 * Function to initialice the values 
	 */
	private void setDefaultValues(){
		better = Double.MAX_VALUE;
		worse = Double.MIN_VALUE;
		average = 0.0;
	}
	
	/**
	 * Function to calculate the worse solution
	 * @param results
	 */
	private void getWorseResult(ArrayList<Double> results) {
		for(int i = 0; i < results.size(); i++){
			if(getWorse() < results.get(i)){
				setWorse(results.get(i));
			}
		}
	}

	/**
	 * Function to calculate the average
	 * @param results
	 */
	private void getAverageResult(ArrayList<Double> results) {
		for(int i = 0; i < results.size(); i++){
			setAverage(getAverage() + results.get(i));
		}
		
		setAverage(getAverage() / results.size());
	}

	/**
	 * Function to calculate the best solution
	 * @param results
	 */
	private void getBetterResult(ArrayList<Double> results) {
		for(int i = 0; i < results.size(); i++){
			if(getBetter() > results.get(i)){
				setBetter(results.get(i));
			}
		}
	}

	/**
	 * Function to show the statistics
	 */
	public void showStatistics(){
		System.out.println("Better value: " + getBetter());
		System.out.println("Wortse value: " + getWorse());
		System.out.println("Average value: " + getAverage());
	}
	
	/**
	 * Getter best solution
	 * @return
	 */
	public Double getBetter() {
		return better;
	}

	/**
	 * Setter best solution
	 * @param better
	 */
	public void setBetter(Double better) {
		this.better = better;
	}

	/**
	 * Getter worse solution
	 * @return
	 */
	public Double getWorse() {
		return worse;
	}

	/**
	 * Setter worse solution
	 * @param worse
	 */
	public void setWorse(Double worse) {
		this.worse = worse;
	}

	/**
	 * Getter average
	 * @return
	 */
	public Double getAverage() {
		return average;
	}

	/**
	 * Setter average
	 * @param average
	 */
	public void setAverage(Double average) {
		this.average = average;
	}
}