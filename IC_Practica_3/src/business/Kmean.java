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
	private List<Double[]> means;
	private int n;
	private int c;
	private Double[][] probabilities;
	
	public Kmean(HashMap<Double[], String> data) {
		this.trainingData = data;
		tolerance = 0;
		weigth = 0;
	}
	
	public Kmean() {
		// TODO Auto-generated constructor stub
	}

	public List<Double[]> getMeans() {
		return means;
	}
	
	public Double[][] getProbabilities() {
		return probabilities;
	}

	public boolean execute(double tol, double w) {
		this.tolerance = tol;
		this.weigth = w;
		boolean success = false;
		
		n = trainingData.size();
		c = nClass();
		
		means = initializeCenters();
		
		probabilities = computeProbablties();
		Double change = Double.MAX_VALUE;
		while(change < tolerance) {
			List<Double[]> newMeans = redoCenters();
			probabilities = computeProbablties();
			change = computeChange(means, newMeans);
			means = newMeans;
		}
		
		return success;
	}
	
	private Double computeChange(List<Double[]> means2, List<Double[]> newMeans) {
		
		//here see how the means had changed.
		
		return 0.0;
	}
		

	private List<Double[]> redoCenters() {
		
		//here the formula:
		//vi = ( sum[j=1...n](probabilidad(ci/xj))^b * xj ) / (sum[j=1...n](probabilidad(ci/xj))^b)
		
		return null;
	}

	public void setMeans(List<Double[]> e) {
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
	
	private List<Double[]> initializeCenters() {
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
	
	private Double[][] computeProbablties(){
		Double[][] result = new Double[c][n];
		Iterator<Entry<Double[], String>> iterator = trainingData.entrySet().iterator();
		int count = 0;
		while(iterator.hasNext()) {
			Entry<Double[], String> current = iterator.next();
			for (int i = 0; i < c; i++){
				result[i][count] = probablties(means.get(i), current.getKey());
			}
			count++;
		}
		
		return result;
	}

	private double probablties(Double[] center, Double[] value) {
		double exp = 1 / (weigth-1);
		double numer = Math.pow( (1/dist(value, center)) , exp);
		double denom = 0.0;
		for(int i = 0; i < c; i++) {
			denom += (1/dist(value, means.get(i)));
		}
		denom = Math.pow(denom, exp);
		
		return  numer/denom;
	}
	
	private double dist(Double[] i, Double[] j) {
		
		if(i.length!=j.length) throw new IllegalArgumentException();
		else {
			 double sum = 0.0;
		        for(int x=0;x<i.length;x++) {
		           sum = sum + Math.pow( (i[x]-j[x]) , 2.0);
		        }
		        return Math.sqrt(sum);
		}
	}

}
