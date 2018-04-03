package business;

import java.util.HashMap;

import data.Node;

public class DataManager {

	private final String positive = "si";
	private final String negative = "no";
	
	public DataManager() {
		
	}

	public Node id3(String[] names, String[][] attributes) {
		Node previousTree = null;
		Node result;
		try {
			result = recursiveID3(previousTree, names, attributes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return result;
	}
	
	private Node recursiveID3(Node previous, String[] names, String[][] attributes) throws Exception {
		if (attributes.length==0) return previous;
		else if (names.length==0) throw new Exception();
		else if (allPositive(attributes)) return new Node(positive, null, previous);
		else if (allNegative(attributes)) return new Node(negative, null, previous);
		else {
			double better = 1;
			for (int i = 0; i < attributes.length; i++) {
				
				String name = names[i];
				int N = attributes[i].length;
				HashMap<String, Double> p = new HashMap<String, Double>();
				HashMap<String, Double> n = new HashMap<String, Double>();
				HashMap<String, Double> r = new HashMap<String, Double>();
				/*
				 * Hay que sacar de cada valor de atributo: Valor, p, n, r
				 */
				
				for (int j = 0; j < attributes[i].length; j++) {
					
				}
								
			}
		}
		
		return null;
	}
	private boolean allPositive (String[][] attributes) {
		boolean allPositives = true;
		for (int i = 0; i< attributes.length; i++) {
			if (attributes[i][attributes[i].length-1].equals(negative)) return false;
		}
		return allPositives;
	}
	private boolean allNegative (String[][] attributes) {
		boolean allNegatives = true;
		for (int i = 0; i< attributes.length; i++) {
			if (attributes[i][attributes[i].length-1].equals(positive)) return false;
		}
		return allNegatives;
	}
	
	public double infor(double p, double n) {
		double result = -1;
		result = (-p*(Math.log(p)/Math.log(2))) - (-n*(Math.log(n)/Math.log(2)));
		return result;
	}
	
}
