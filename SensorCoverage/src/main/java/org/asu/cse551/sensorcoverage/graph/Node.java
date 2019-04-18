package org.asu.cse551.sensorcoverage.graph;

import java.io.Serializable;
import java.util.LinkedList;

public class Node  implements Serializable{
	
	private static final long serialVersionUID = 3103225805241147693L;
	
	public int id = -1;
	public int xCordinate = 0;
	public int yCordinate = 0 ;
	public LinkedList<Node> neighbours = null;
	
	public Node(int id, int xCordinate, int yCordinate) {
		super();
		this.id = id;
		this.xCordinate = xCordinate;
		this.yCordinate = yCordinate;
		neighbours = new LinkedList<>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getxCordinate() {
		return xCordinate;
	}

	public void setxCordinate(int xCordinate) {
		this.xCordinate = xCordinate;
	}

	public int getyCordinate() {
		return yCordinate;
	}

	public void setyCordinate(int yCordinate) {
		this.yCordinate = yCordinate;
	}

	public LinkedList<Node> getNeighbours() {
		return neighbours;
	}

	@Override
	public int hashCode() {
		int hashValue = 11;
	    hashValue = 31 * hashValue + id;
	    return hashValue;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)                              
	      return true;
	    if (!(obj instanceof Node))              
	      return false;
	    Node node = (Node) obj;            
	    return node.id   == this.id;
	}

	@Override
	public String toString() {
		return id + ":(" + xCordinate + "," + yCordinate + ")";
	}
	
}
