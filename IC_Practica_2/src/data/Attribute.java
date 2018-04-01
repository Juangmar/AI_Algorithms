package data;

import java.util.HashMap;

public class Attribute {
	private String name;
	
	private HashMap<String, Integer> values;
	
	public Attribute() {
		name = "";
		values = new HashMap<String, Integer>();
	}
	public Attribute(String n) {
		name = n;
		values = new HashMap<String, Integer>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void addValue(String n, Integer r) {
		values.put(n, r);
	}
	public void removeValue(String n) {
		values.remove(n);
	}
}
