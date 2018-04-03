package integration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AttributesValues {

	private final String guard = ",";
	private final String lineSep = System.getProperty("line.separator");
	
	public String[][] load(File e) {
		if (e.canRead()) {

			try {
				FileInputStream in = new FileInputStream(e);
				StringBuilder builder = new StringBuilder();
				int ch;
				while((ch = in.read()) != -1){
				    builder.append((char)ch);
				}
				in.close();
				String end = builder.toString();
				String[] cad = end.split(lineSep);
				String[][] res = new String[cad.length][];
				for (int i = 0; i < cad.length; i++) {
					res[i] = cad[i].split(guard);
				}
				return res;
				
			} catch (IOException e1) {
				e1.printStackTrace();
				return null;
			} 
		}else return null;
	}

	public int numberOfCases(File e) {
		if (e.canRead()) {

			try {
				FileInputStream in = new FileInputStream(e);
				StringBuilder builder = new StringBuilder();
				int ch;
				while((ch = in.read()) != -1){
				    builder.append((char)ch);
				}
				in.close();
				String end = builder.toString();
				return end.split(lineSep).length;
				
			} catch (IOException e1) {
				e1.printStackTrace();
				return -1;
			} 
		}else return -1;
	}

	public int numberOfAttributes(File e) {
		if (e.canRead()) {

			try {
				FileInputStream in = new FileInputStream(e);
				StringBuilder builder = new StringBuilder();
				int ch;
				while((ch = in.read()) != -1){
				    builder.append((char)ch);
				}
				in.close();
				String end = builder.toString();
				String[] cad = end.split(lineSep);
				int cant = cad[0].split(guard).length;
				for (int i = 0; i < cad.length; i++) {
					if(cant!=cad[i].split(guard).length) return -1;
				}
				return cant;
				
			} catch (IOException e1) {
				e1.printStackTrace();
				return -1;
			} 
		}else return -1;
	}

}
