package integration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * This class is used to obtain the data from a file, which contains the Attributes names 
 * for the program.
 * @author Juan Gomez-Martinho Gonzalez
 *
 */
public class AttributesNames {

	private final String guard = ","; //Used to store the character that separates the file's attributes.
	
	public String[] load(File file) {
		if (file.canRead()) { //Execute only if the file is readable 
			try {
				FileInputStream stream = new FileInputStream(file); //The file will be read only, loaded into a Input Stream
				StringBuilder builder = new StringBuilder(); //String builder to transform the file content into a string
				int ch; //Character used into the reading process.
				while((ch = stream.read()) != -1){ //Iterate while the inputStream returns a valid character
				    builder.append((char)ch); //ad to the builder the red character 
				}
				stream.close(); //When there's no more chars to read, close the stream.
				String rawString = builder.toString(); //Execute the conversion from builder to a temp string.
				return rawString.split(guard); //The attributes names are obtained splitting the raw string with the stored guard
			} catch (IOException e1) { //If there's an exception while reading the file
				e1.printStackTrace(); //Print the stack trace in the console.
				return null; //If there's an error, return a null value.
			} 
		} else return null; //If the file is not readable, return a null value (as an error)
	}
}
