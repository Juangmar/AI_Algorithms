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
		else if (names.length==1) throw new Exception(); //The last name possible is the result y (yes/No)
		else if (allPositive(attributes)) return new Node(positive, null, previous);
		else if (allNegative(attributes)) return new Node(negative, null, previous);
		else {
			
			double best = 1;
			String bestName;
			String[] values;
			
			for (int i = 0; i < attributes.length; i++) {
				
				String name = names[i];
				int N = attributes[i].length;
				HashMap<String, Integer> pApariciones = new HashMap<String, Integer>();
				HashMap<String, Integer> nApariciones = new HashMap<String, Integer>();
				HashMap<String, Integer> a = new HashMap<String, Integer>();
				
				for (int j = 0; j < attributes[i].length; j++) {
					if(!a.containsKey(attributes[i][j])) {
						a.put(attributes[i][j], 1);
						if(attributes[i][names.length-1].equals(positive)) pApariciones.put(attributes[i][j], 1);
						else if (attributes[i][names.length-1].equals(negative)) nApariciones.put(attributes[i][j], 1);
						else throw new Exception();
					}
					else {
						if(attributes[i][names.length-1].equals(positive)) {
							int newVal = pApariciones.get(attributes[i][j]) + 1;
							pApariciones.put(attributes[i][j], newVal);
						}
						else if (attributes[i][names.length-1].equals(negative)) {
							int newVal = nApariciones.get(attributes[i][j]) + 1;
							nApariciones.put(attributes[i][j], newVal);
						}
						else throw new Exception();
					}
				}
				
				/*	At this point, we have the a of each value, the posApperience and the negApperience.
					We have to compute now:
						p for each value
						n for each value
						r for each value
						m = sum(r*infor(p,n))
						if m<best 
							best = m
							bestName = name
							values = allValues names
				*/
				
								
			}
			
			/*	we have the best attribute possible. Now we have to construct:
			 *		The node N with name bestName
			 *			It's vertex with values[]
			 *			For each vertex, 
			 *				end = recursiveID3(N, names without selected name, attributes without the attribute and the rows that satisfy the current value)
			*/
			
		}
		
		//return node N when declared
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
