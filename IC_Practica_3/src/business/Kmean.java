package business;

import java.util.HashMap;

public class Kmean {

	private HashMap<Double[][], String> traningData;
	private double tolerance, weigth;
	private Double[] means;
	
	public Kmean(HashMap<Double[][], String> data) {
		this.traningData = data;
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
		
		return false;
	}

	public void setMeans(Double[] e) {
		this.means = e;
	}

	

}
