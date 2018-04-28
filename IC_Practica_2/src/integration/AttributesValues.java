package integration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * This class is used to obtain the data from a file, which contains the Attributes values 
 * for the program.
 * 
 * @author Juan Gómez-Martinho González
 *
 */
public class AttributesValues { 
	private final String guard = ","; //Used to store the character that separates the file's attributes.
	private final String lineSep = System.getProperty("line.separator"); //Used to store the system's line separator. This way it works on Windows/Unix 
	
	/**
	 * This method loads the Attribute's values from the specified file and returns it in a String matrix.
	 * @param file File containing the data.
	 * @return String matrix containing the attributes (columns) and the training data (rows) or null if there's an error.
	 */
	public String[][] load(File file) {
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
				String[] row = rawString.split(lineSep); //When the raw string is split, generating an array of rows.
				String[][] result = new String[row.length][]; //Initialize a matrix with N rows, there N is the number of rows obtained before.
				for (int i = 0; i < row.length; i++) {  //Iterate every row
					result[i] = row[i].split(guard); //The matrix row is raw row separated by the stored guard.
				}
				return result; //when every row is split, return the result.
				
			} catch (IOException e1) { //If there's an exception while reading the file
				e1.printStackTrace(); //Print the stack trace in the console.
				return null; //If there's an error, return a null value.
			} 
		} else return null; //If the file is not readable, return a null value (as an error)
	}

	/**
	 * This method returns the number of cases contained in a file.
	 * @param file File containing the data.
	 * @return number of cases (rows) or -1 if there's an error.
	 */
	public int numberOfCases(File file) {
		if (file.canRead()) { //Execute only if the file is readable 
			try {
				FileInputStream stream = new FileInputStream(file); //The file will be read only, loaded into a Input Stream
				StringBuilder builder = new StringBuilder(); //String builder to transform the file content into a string
				int ch; //Character used into the reading process.
				while((ch = stream.read()) != -1){ //Iterate while the inputStream returns a valid character
				    builder.append((char)ch); //ad to the builder the red character 
				}
				stream.close(); //When there's no more chars to read, close the stream.
				String raw = builder.toString(); //Execute the conversion from builder to a temp string.
				return raw.split(lineSep).length; //The number of rows is the number of parts resulted from splitting the raw string using the line separator stored.
				
			} catch (IOException e1) { //If there's an exception while reading the file
				e1.printStackTrace(); //Print the stack trace in the console.
				return -1; //If there's an error, return -1.
			} 
		}else return -1; //If the file is not readable, return -1 (as an error)
	}

	/**
	 * This method returns the number of attributes contained in a file.
	 * @param file File containing the data.
	 * @return number of cases (rows) or -1 if there's an error, or different rows has different number of attributes.
	 */
	public int numberOfAttributes(File file) {
		if (file.canRead()) { //Execute only if the file is readable 
			try {
				FileInputStream stream = new FileInputStream(file); //The file will be read only, loaded into a Input Stream
				StringBuilder builder = new StringBuilder(); //String builder to transform the file content into a string
				int ch; //Character used into the reading process.
				while((ch = stream.read()) != -1){ //Iterate while the inputStream returns a valid character
				    builder.append((char)ch); //ad to the builder the red character 
				}
				stream.close(); //When there's no more chars to read, close the stream.
				String raw = builder.toString(); //Execute the conversion from builder to a temp string.
				String[] rows = raw.split(lineSep); //When the raw string is split, generating an array of rows.
				int numberOfAttributes = rows[0].split(guard).length; //The number of attributes should be the number of strings obtained from splitting using the stored guard.
					//The first row is stored, and now it's checked if it's the same for every row. If there's at least a different row, the method returns an error.
				for (int i = 0; i < rows.length; i++) {  //Iterate every row
					if(numberOfAttributes!=rows[i].split(guard).length) return -1; //If there's at least a different row, the method returns an error.
				}
				return numberOfAttributes; //If there's no errors, return the stored value
				
			} catch (IOException e1) { //If there's an exception while reading the file
				e1.printStackTrace(); //Print the stack trace in the console.
				return -1; //If there's an error, return -1.
			} 
		}else return -1; //If the file is not readable, return -1 (as an error)
	}

}
