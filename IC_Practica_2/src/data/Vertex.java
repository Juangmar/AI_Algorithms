package data;

/**
 * This class represents the vertex of a decision tree.
 * It has a vertex name, the node it came from and the next node.
 * 
 * @author Juan Gomez-Martinho Gonzalez
 *
 */
public class Vertex {
	private Node cameFrom; //The node this vertex comes from.
	private String value; //The name of this vertex.
	private Node next; //The next node if the vertex is followed.
	
	/**
	 * Class constructor. It creates a vertex without previous and next node.
	 * @param name Name of the vertex. It's the attribute's value of the previous node.
	 */
	public Vertex(String name) {
		value = name; //The name of the vertex is stored in the value filed.
		cameFrom = null; //The previous node is set as null
		next = null; //The previous node is set as null
	}
	
	/**
	 * Getter for the previous node attribute
	 * @return the previous node, or null if the current vertex has no father node.
	 */
	public Node getCameFrom() {
		return cameFrom;
	}
	/**
	 * Setter for the previous node attribute.
	 * @param cameFrom Already initialized node 
	 */
	public void setCameFrom(Node cameFrom) {
		this.cameFrom = cameFrom;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
}
