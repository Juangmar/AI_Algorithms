package controller;

import java.io.File;
import business.DataManager;
import data.Node;
import integration.AttributesNames;
import integration.AttributesValues;

/**
 * This class provides the program unified functions, that 
 * satisfies all business Use Cases
 * @author Juan Gomez-Martinho Gonzalez
 *
 */
public class ApplicationController {

	/**
	 * Given a file, this method loads the content and gets from it the attributes names.
	 * 
	 * @param file File containing the data.
	 * @return Array of names, null if there's an error.
	 */
	public String[] loadNames(File file){
		AttributesNames manager = new AttributesNames(); //Gets the class that manages the names.
		String[] result = manager.load(file); // Stores in an Array the result of executing the load method.
		return result; //returns the array.
	}
	
	/**
	 * Given a file and the attribute's names, this method loads it's values and makes
	 * sure the file has as many attributes as the given names.
	 * 
	 * @param file File containing the data.
	 * @param names 
	 * @return Matrix with the training cases (rows) and values (columns), or null if there's an error
	 */
	public String[][] loadValues(File file, String[] names) {
		AttributesValues dataManager = new AttributesValues(); //Gets the class that manages the values.
		int nv = dataManager.numberOfAttributes(file); //First of all, check the number of attributes in the given file.
		if(nv!=names.length) return null; //If the number of attribute's names and the number of attributes in the file are different, return error (null)
		else { //If the data is correct
			String[][] result = dataManager.load(file); //Load from the manager the data and store it in a matrix
			return result; //return the result
		}
	}
	
	/**
	 * Given the files containing the data (attribute's names and attribute's values) it loads the data 
	 * and executes the ID3 algorithm, generating a decision tree.
	 * 
	 * @param attributesNames File containing the name of the attributes.
	 * @param attributesValues File containing the value of the attributes.
	 * @return A Node (with null father) that starts the decision tree, or null if there's an error
	 */
	public Node decitionTree(File attributesNames, File attributesValues) {
		String[] names = loadNames(attributesNames); //Loads the array of names contained in the given file.
		if(names!=null) { //Execute only if there's no error loading the names.
			String[][] attributes = loadValues(attributesValues, names); //Executes the loading of the values and stores it in a matrix.
			if(attributes!=null) { //Execute only if there's no loading error.
				DataManager dataManager = new DataManager(); //Creates the manager in charge of executing the algorithm.
				Node tree = dataManager.id3(names, attributes); //Executes the id3 algorithm, resulting a decision tree.
				return tree; //returns the resulting node, which begins the tree.
			} else return null; //If the load returns null, return an error.
		}else { //If the loading result is null, there's an error.
			return null; //Return error (null)
		}
	}
}


