package controller;

import java.io.File;
import business.DataManager;
import integration.AttributesNames;
import integration.AttributesValues;

public class ApplicationController {

	public ApplicationController() {
		
	}
	public String[] loadNames(File e){
		AttributesNames i = new AttributesNames();
		String[] n = i.load(e);
		return n;
	}
	public String[][] loadValues(File e, String[] names) {
		AttributesValues v = new AttributesValues();
		int nv = v.numberOfAttributes(e);
		if(nv!=names.length) return null;
		else {
			String[][] n = v.load(e);
			return n;
		}
	}
	
	public Object decitionTree(File attributesNames, File attributesValues) {
		String[] names = loadNames(attributesNames);
		if(names!=null) {
			String[][] attributes = loadValues(attributesValues, names);
			if(attributes!=null) {
				DataManager m = new DataManager();
				Object tree = m.id3(names, attributes);
				return tree;
			} else return null;
		}else {
			return null;
		}
	}
}