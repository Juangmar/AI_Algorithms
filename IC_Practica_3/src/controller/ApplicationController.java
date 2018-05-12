package controller;

import java.io.File;
import java.util.HashMap;

import business.Kmean;
import integration.DataManager;

public class ApplicationController {

	public String[] loadClasses(File e) {
		DataManager manager = new DataManager();
		String[] result = manager.loadClass(e);
		return result;
	}
	public HashMap<Double[][], String> loadData(File e){
		DataManager manager = new DataManager();
		HashMap<Double[][], String> result = manager.loadData(e);
		return result;
	}
	public Kmean kmeans(File f, double tolerance, double weigth) {
		HashMap<Double[][], String> data = loadData(f);
		if(data != null) {
			Kmean manager = new Kmean(data);
			boolean success = manager.execute(tolerance, weigth);
			if(success) {
				return manager;
			}
			else return null;
		}
		else return null;
	}
	
}
