package data;

import java.util.ArrayList;

/**
 * This class represents the node of a decision tree.
 * It has a node name (name of the attribute), a collection of vertex 
 * (sons) and a pointer to the previous node
 * 
 * @author Juan Gomez-Martinho Gonzalez
 *
 */
public class Node {
	private String name; //Name of the attribute it represents
	private ArrayList<Vertex> sons; //Array of vertex, one for each value it has.
	private Node father; //Pointer to the previous node.
	
	/**
	 * Complete constructor. Initializes every attribute.
	 * @param value Name of the node.
	 * @param vertexes ArrayList of vertexes.
	 * @param father Initialized Node it comes from.
	 */
	public Node(String value, ArrayList<Vertex> vertexes, Node father) {
		name = value;
		sons = vertexes;
		this.father = father;
	}

	/**
	 * Standard constructor. 
	 * It sets the node name; and the vertexes and the father are null.
	 * @param nodeName
	 */
	public Node(String nodeName) {
		name = nodeName;
		sons = new ArrayList<Vertex>(); //Initializes the ArrayList, ready to add new Vertexes.
		father = null;
	}

	/**
	 * Getter for the node name
	 * @return The name of the node.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the node.
	 * @param name String with the node name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the son vertexes.
	 * @return ArrayList containing the sons. It doasn't assure the content of the list.
	 */
	public ArrayList<Vertex> getSons() {
		return sons;
	}

	/**
	 * Setter for the son vertexes.
	 * @param sons ArrayList already initialized.
	 */
	public void setSons(ArrayList<Vertex> sons) {
		this.sons = sons;
	}

	/**
	 * Getter for the previous node.
	 * @return Initialized node or null.
	 */
	public Node getFather() {
		return father;
	}

	/**
	 * Setter for the previous node.
	 * @param father Initialized node or null.
	 */
	public void setFather(Node father) {
		this.father = father;
	}

	/**
	 * This method directly adds an initialized vertex into the List of sons.
	 * @param v Initialized vertex
	 */
	public void addVertex(Vertex v) {
		this.sons.add(v);
	}
	
}
