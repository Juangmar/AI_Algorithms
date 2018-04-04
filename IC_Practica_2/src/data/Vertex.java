package data;

public class Vertex {
	private Node cameFrom;
	private String value;
	private Node next;
	
	public Vertex(String name) {
		value = name;
		cameFrom = null;
		next = null;
	}
	
	public Node getCameFrom() {
		return cameFrom;
	}
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
