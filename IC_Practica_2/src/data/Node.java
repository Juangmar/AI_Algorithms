package data;

import java.util.ArrayList;

public class Node {
	private String name;
	private ArrayList<Vertex> sons;
	private Node father;
	
	public Node(String value, ArrayList<Vertex> object, Node father) {
		name = value;
		sons = object;
		this.father = father;
	}

	public Node(String bestName) {
		name = bestName;
		sons = new ArrayList<Vertex>();
		father = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Vertex> getSons() {
		return sons;
	}

	public void setSons(ArrayList<Vertex> sons) {
		this.sons = sons;
	}

	public Node getFather() {
		return father;
	}

	public void setFather(Node father) {
		this.father = father;
	}

	public void addVertex(Vertex v) {
		this.sons.add(v);
	}
	
}
