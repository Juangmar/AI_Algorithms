package integration;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

/**
 * @author Juan Gomez-Martinho
 * License MIT, 2018
 */
public class DataManager {

	public HashMap<Double[], String> loadData(File file) {
		if (file.canRead()) { //Execute only if the file is readable 
			try {
				String lineSep = System.lineSeparator();
				FileInputStream stream = new FileInputStream(file); //The file will be read only, loaded into a Input Stream
				StringBuilder builder = new StringBuilder(); //String builder to transform the file content into a string
				int ch; //Character used into the reading process.
				while((ch = stream.read()) != -1){ //Iterate while the inputStream returns a valid character
				    builder.append((char)ch); //ad to the builder the red character 
				}
				stream.close(); //When there's no more chars to read, close the stream.
				String rawString = builder.toString(); //Execute the conversion from builder to a temp string.
				String[] row = rawString.split(lineSep); //When the raw string is split, generating an array of rows.
				HashMap<Double[], String> result = new HashMap<Double[], String>(); //Initialize the result dataset.
				for (int i = 0; i < row.length; i++) {  //Iterate every row
					String[] fields = row[i].split(","); //The matrix row is raw row separated by the stored guard.
					Double[] values = {Double.parseDouble(fields[0]), Double.parseDouble(fields[1]), Double.parseDouble(fields[2]), Double.parseDouble(fields[3]),};
					result.put(values, fields[4]);
				}
				
				return result; //when every row is split, return the result.
				
			} catch (Exception e) { //If there's an exception while reading the file
				e.printStackTrace(); //Print the stack trace in the console.
				return null; //If there's an error, return a null value.
			} 
		} else return null; //If the file is not readable, return a null value (as an error)
	}

}
