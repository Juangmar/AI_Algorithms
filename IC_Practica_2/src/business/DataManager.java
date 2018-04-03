package business;

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
	
	private Node recursiveID3(Node previousTree, String[] names, String[][] attributes) throws Exception {
		if (attributes.length==0) return previousTree;
		else if (names.length==0) throw new Exception();
		else if (allPositive(attributes)) return new Node(positive, null);
		else if (allNegative(attributes)) return new Node(negative, null);
		else {
			
		}
		/*
		else{
			buscar (mejorMérito, índice) --> for each atributo calcular su mérito, si mejor sustituir.
			
			mejor --> Raíz de árbol. Para cada valor del nodo:
				* incluir en ejemplos-restantes los elementos de lista-ejemplos
					que tengan valor vi del atributo mejor.
		 		* dejar en atributos-restantes todos los elementos de lista-atributos
				  	excepto mejor.
		 		* devolver el valor de:
				  	ID3 (ejemplos-restantes, atributos-restantes) 
				  	(llamada recursiva al algoritmo)
		}
		*/
		
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
