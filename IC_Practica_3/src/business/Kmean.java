package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class Kmean {

	private HashMap<Double[], String> trainingData;
	private double tolerance, weigth;
	private Double[] means;
	
	public Kmean(HashMap<Double[], String> data) {
		this.trainingData = data;
		tolerance = 0;
		weigth = 0;
	}
	
	public Kmean() {
		// TODO Auto-generated constructor stub
	}

	public Double[] getMeans() {
		return means;
	}

	public boolean execute(double tol, double w) {
		this.tolerance = tol;
		this.weigth = w;
		boolean success = false;
		
		int n = trainingData.size();
		int c = nClass();
		
		List<Double[]> v = initializeCenters(c, n);
		
		Double[][] probablties = initializeProbablties(c, n, v);
		
		//Random centers initialized. Time for the probabilities
		
		
		return success;
	}
	
	public void setMeans(Double[] e) {
		this.means = e;
	}

	private int nClass() {
		int p = 0;
		List<String> a = new ArrayList<String>();
		if(trainingData!=null) {
			trainingData.forEach((k,v)->{
				if(!a.contains(v)) a.add(v);
			});
			p = a.size();
		}
		
		return p;
	}
	
	private List<Double[]> initializeCenters(int c, int n) {
		List<Double[]> v = new ArrayList<Double[]>();
		Random r = new Random();
		while(v.size() < c) {
			int ind = r.nextInt(n-1);
			
			Iterator<Entry<Double[], String>> a = trainingData.entrySet().iterator();
			int j = 0;
			while(a.hasNext() && j<=ind) {
				
				if(j==ind) {
					Map.Entry<Double[], String> pair = (Map.Entry<Double[], String>) a.next();
					v.add(pair.getKey());
				} else {
					j++;
				}
			}

		}
		return v;
	}
	
	private Double[][] initializeProbablties(int c, int n, List<Double[]> center){
		Double[][] result = new Double[c][n];
		Iterator<Entry<Double[], String>> iterator = trainingData.entrySet().iterator();
		int count = 0;
		while(iterator.hasNext()) {
			Entry<Double[], String> current = iterator.next();
			for (int i = 0; i < c; i++){
				result[i][count] = probablties(center.get(i), current.getKey());
			}
			count++;
		}
		
		return result;
	}

	private double probablties(Double[] center, Double[] value) {
		double result = (1/0);
		
		
		
		return 0.0;
	}

}
