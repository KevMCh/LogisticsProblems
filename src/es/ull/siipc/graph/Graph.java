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
 * Class to represent the details of graph.
 */
package es.ull.siipc.graph;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Graph extends Instance {

	String name;						// Name of the graph.
	String source;						// Source of the graph.
	String description;					// Description of the graph.
	double doublePrecision;				// Double precision of the graph.
	int ignoredDigits;					// Ignored digits of the graph.
	
	/**
	 * Default builder.
	 */
	public Graph (){
		
	}
	
	/**
	 * Builder.
	 * @param problem Name of the problem.
	 */
	public Graph (String problem){
		read(problem);
	}
	
	/**
	 * Function to print the details of the graph.
	 */
	public void printAll(){
		System.out.println("\nDetails\n");
		
		System.out.println("Name of the problem: " + name);
		System.out.println("Source: " + source);
		System.out.println("Description: " + description);
		System.out.println("Double precision: " + doublePrecision);
		System.out.println("Ignored digits: " + ignoredDigits);
		printMatrix();
	}
	
	/**
	 * Function to read a file graph.
	 * @param nameProblem Name of the graph.
	 */
	private void read(String nameProblem){
		Document dom;
		DocumentBuilderFactory dbf;
		DocumentBuilder db;
		
		dbf = DocumentBuilderFactory.newInstance();
		
		try {
			db = dbf.newDocumentBuilder();
		  	String curDir = System.getProperty("user.dir");
		  	String path = curDir + "/files/"+ nameProblem + ".xml";
		  	dom = db.parse(path);
		  
		  	name = dom.getElementsByTagName("name").item(0).getTextContent();
			source = dom.getElementsByTagName("source").item(0).getTextContent();
			description = dom.getElementsByTagName("description").item(0).getTextContent();
			doublePrecision = Double.valueOf(dom.getElementsByTagName("doublePrecision").item(0).getTextContent()).doubleValue();
			ignoredDigits = Integer.parseInt(dom.getElementsByTagName("ignoredDigits").item(0).getTextContent());
				  
			Element rootElement = dom.getDocumentElement();
			NodeList vertexList = rootElement.getElementsByTagName("vertex");
		  
			setN(vertexList.getLength());
			
			buildMatrix(n);
		  		  
			if(vertexList != null && vertexList.getLength() > 0){
				
				for(int i = 0; i < vertexList.getLength(); i++){
					Element elem1 = (Element) vertexList.item(i);
					NodeList edgeList = elem1.getElementsByTagName("edge");				
				
					if(edgeList != null && edgeList.getLength() > 0){
						
						for(int j = 0; j < edgeList.getLength(); j++){
							Element elem2 = (Element) edgeList.item(j);
							double aux = Double.valueOf(elem2.getAttribute("cost")).doubleValue();
							setMatrixItem(i, Integer.parseInt(elem2.getFirstChild().getNodeValue()), aux);
						}
					}
				}
			}
		} catch(Exception ex) {
			System.out.println("Error loading file: " + ex);
		}
	}

    
	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public String getSource() { return source; }

	public void setSource(String source) { this.source = source; }

	public String getDescription() { return description; }

	public void setDescription(String description) { this.description = description; }

	public double getDoublePrecision() { return doublePrecision; }

	public void setDoublePrecision(double doublePrecision) { this.doublePrecision = doublePrecision; }

	public int getIgnoredDigits() { return ignoredDigits; }

	public void setIgnoredDigits(int ignoredDigits) { this.ignoredDigits = ignoredDigits; }
}
