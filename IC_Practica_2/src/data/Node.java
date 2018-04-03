package data;

public class Node {
	private String name;
	private Vertex[] sons;
	private Node father;
	
	public Node(String value, Vertex[] object, Node father) {
		name = value;
		sons = object;
		this.father = father;
	}
	
}
