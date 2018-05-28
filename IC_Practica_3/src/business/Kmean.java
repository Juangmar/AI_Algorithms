package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Kmean {

	private HashMap<Double[], String> trainingData;
	private double tolerance, weigth;
	private List<Double[]> means;
	private int n;
	private int c;
	private List<String> classes;
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
		Double change = tolerance+1;
		while(change > tolerance) {
			List<Double[]> newMeans = redoCenters(means, probabilities);
			probabilities = computeProbablties();
			change = computeBiggestChange(means, newMeans);
			means = newMeans;
		}
		
		if(change < tolerance) return true;
		
		return success;
	}
	
	private Double computeBiggestChange(List<Double[]> means, List<Double[]> newMeans) {
		double change = 0.0;

		for (int i = 0; i < means.size(); i++) {
			
			double distance = dist(means.get(i), newMeans.get(i));
			if(distance > change) change = distance;
		}
		
		return change;
	}
		

	private List<Double[]> redoCenters(List<Double[]> v, Double[][] p) {
		
		List<Double[]> new_V = new ArrayList<Double[]>();
		
		for(int i = 0; i < v.size(); i++) {
			Double newMean[] = new Double[v.get(i).length];
			double num[] = new double[v.get(i).length];
			double den[] = new double[v.get(i).length];
			Iterator<Entry<Double[], String>> iterator = trainingData.entrySet().iterator();
			int t_case = 0;
			while(iterator.hasNext()) {
				Map.Entry<Double[], String> pair = (Map.Entry<Double[], String>)iterator.next();
				for(int j = 0; j < pair.getKey().length; j++) {
					double w = Math.pow(p[i][t_case], weigth);
					num[j] += w * pair.getKey()[j];
					den[j] += w;
				}
				t_case++;
			}
			for(int j = 0; j < v.get(i).length; j++) {
				newMean[j] = num[j]/den[j];
			}
			new_V.add(newMean);
		}
					
		return new_V;
	}

	public void setMeans(List<Double[]> e) {
		this.means = e;
	}

	private int nClass() {
		int p = 0;
	
		classes = new ArrayList<String>();
		if(trainingData!=null) {
			trainingData.forEach((k,v)->{
				if(!classes.contains(v)) {
					classes.add(v);
				}
			});
			p = classes.size();
		}
		
		return p;
	}
	
	private List<Double[]> initializeCenters() {
		List<Double[]> v = new ArrayList<Double[]>();
		/*Random r = new Random();
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

		}*/
		Double[] one = {4.6, 3.0, 4.0, 0.0};
		Double[] two = {6.8, 3.4, 4.6, 0.7};
		v.add(one);
		v.add(two);
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

	public double test(HashMap<Double[], String> data) {
		double acc = 0;
		int cases = 0;
		int correct = 0;
		
		Iterator<Entry<Double[], String>> iterator = data.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<Double[], String> current = iterator.next();
			cases++;
			String predict = predict(current.getKey());
			if (predict.equals(current.getValue())) correct++;
		}
		acc = (double) correct / (double) cases;
		return acc;
	}
	
	private String predict(Double[] value) {
		double valueMostProbable = 0.0;
		int classMostProbable = 0;
		for (int i = 0; i < c; i++){
			double p = probablties(means.get(i),value);
			if( p > valueMostProbable) {
				valueMostProbable=p;
				classMostProbable=i;
			}
		}
		return classes.get(classMostProbable);
	}

}
