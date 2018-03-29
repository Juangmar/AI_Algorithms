package integration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AttributesNames {

	public AttributesNames() {
		
	}
	
	public String[] load(File e) {
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
				String[] cad = end.split(",");
				return cad;
			} catch (IOException e1) {
				e1.printStackTrace();
				return null;
			} 
		}else return null;
	}

}
