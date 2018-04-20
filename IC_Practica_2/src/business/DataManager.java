package business;

import java.util.HashMap;
import java.util.Map.Entry;
import data.Node;
import data.Vertex;

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
			double best = 10;
			String bestName = "";
			String[] values = null;
			int bestAttrIndex = 0;
			for (int j = 0; j < attributes[0].length; j++) {
			
				String name = names[j];
				int N = attributes.length;
				HashMap<String, Integer> pApariciones = new HashMap<String, Integer>();
				HashMap<String, Integer> nApariciones = new HashMap<String, Integer>();
				HashMap<String, Integer> a = new HashMap<String, Integer>();
				for (int i = 0; i < attributes.length; i++) {	
					if(!a.containsKey(attributes[i][j])) {
						a.put(attributes[i][j], 1);
						if(attributes[i][names.length-1].equals(positive)) {
							pApariciones.put(attributes[i][j], 1);
							nApariciones.put(attributes[i][j], 0);
							
						}
						else if (attributes[i][names.length-1].equals(negative)) {
							nApariciones.put(attributes[i][j], 1);
							pApariciones.put(attributes[i][j], 0);
						}
						else throw new Exception();
					}
					else {
						int newA = a.get(attributes[i][j]) + 1;
						a.put(attributes[i][j], newA);
						
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
				double total = 0;
				String[] tempValues = new String[a.size()];
				int index = 0;
				for(Entry<String, Integer> entry : a.entrySet()) {
					double p = (double) pApariciones.get(entry.getKey()) / (double) entry.getValue();
					double n = (double) nApariciones.get(entry.getKey()) / (double) entry.getValue();
					double r = (pApariciones.get(entry.getKey()) + nApariciones.get(entry.getKey())) / (double) N;
					double res =  r*infor(p,n);
					total = total + res;
					tempValues[index] = entry.getKey();
					index++;	
				}
				if (total < best && !name.equals("Jugar")) {
					best = total;
					bestName = name;
					values = tempValues;
					bestAttrIndex = j;
				}
			}
			Node res = new Node(bestName);
			for(String val : values) {
				Vertex v = new Vertex(val);
				v.setCameFrom(res);
				String[][] newAttributes = new String[attributes.length][];
				int nextRow = 0;
				for (int i = 0; i < attributes.length; i++) {
					String temp = attributes[i][bestAttrIndex];
					if(temp.equals(val)) {
							newAttributes[nextRow] = attributes[i];
							nextRow++;
					}
				}
				String[][] nextStepAttributes = new String[nextRow][];
				for (int i = 0; i < nextRow; i++) {
					nextStepAttributes[i] = newAttributes[i];
				}
				v.setNext(recursiveID3(res,names, nextStepAttributes));
				res.addVertex(v);
			}
			return res;
		}
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
		
		double first = 0;
		if (p != 0) {
			first = (-p * (Math.log(p)/Math.log(2)));
		}
		
		double second = 0;
		if (n != 0) {
			second = (-n * (Math.log(n)/Math.log(2)));
		}
		
		result = first + second;
		
		return result;
	}
	
}
