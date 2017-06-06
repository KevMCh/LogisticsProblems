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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Statistics {
	
	private static final String STATISTICSPATH = System.getProperty("user.dir") + "/statistics/";
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
	 * Function to save the statistics in the file
	 * @param fileName
	 * @param delimiter
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void saveStatistics(String fileName, String delimiter)
			throws FileNotFoundException, IOException{
		
		String content = "";
		File af = new File(STATISTICSPATH + fileName);
		if(af.exists()){ 
			content = readFile(STATISTICSPATH, fileName);
		}
		
		writeFile(STATISTICSPATH, fileName,
				content + getBetter() + delimiter + getWorse() + delimiter + getAverage());
	}
	
	/**
	 * Function to read a statistics file
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static String readFile(String path, String fileName)
			throws FileNotFoundException, IOException {
		
		String string;
		String finalString = "";

		FileReader f = new FileReader(path + fileName);
		if(f != null){
			BufferedReader b = new BufferedReader(f);
		
			while((string = b.readLine()) != null) {
				finalString += string + "\n";
			}
			
			b.close();
		}
		
		return finalString;
	}
	
	/**
	 * Function to save the data in the file
	 * @throws FileNotFoundException 
	 */
	private void writeFile(String path, String fileName, String data) {
		
		FileWriter file = null;
		try {

			file = new FileWriter(path + fileName);
			
			file.write(data);
			
			file.close();

		} catch (Exception ex) {
			System.out.println(ex);
		}
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