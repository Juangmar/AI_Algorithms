package controller;

import java.io.File;
import java.util.HashMap;

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
	public HashMap<String, Integer>[] loadValues(File e, String[] names) {
		AttributesValues i = new AttributesValues(); 
		
		for(int j = 0; j < names.length; j++) {
			// Cargar el atributo correspondiente
		}
		
		return null;
	}
	
	public Object decitionTree(File attributesNames, File attributesValues) {
		String[] names = loadNames(attributesNames);
		if(names!=null) {
			HashMap<String, Integer>[] attributes = loadValues(attributesValues, names);
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