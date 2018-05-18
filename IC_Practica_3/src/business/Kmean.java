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
		//Random centers initialized
		
		
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

}
