package org.asu.cse551.sensorcoverage.graph;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.asu.cse551.sensorcoverage.util.SensorUtil;

public class Graph implements Serializable{

	private static final long serialVersionUID = -828725917652137089L;
	
	
	public int numOfNodes = 0 ;
	public Map<Integer, Node> g = null;
	public double[][] weightMatrix = null;

	public Graph(int numOfNodes) {
		this.numOfNodes = numOfNodes;
		g = new HashMap<>();
		weightMatrix = new double[numOfNodes][numOfNodes];
	}

	public void addNode(int id, int x, int y) {
		Node src = new Node(id, x, y);
		g.put(id, src);

		Collection<Node> nodes = g.values();
		for(Node dest : nodes ) {
			if(!src.equals(dest)) {
				addEdge(src, dest);
				addWeight(src, dest);
				addEdge(dest, src);
				addWeight(dest, src);
			}
		}
	}

	public void addNode(Node node) {
		if(!g.containsKey(node.getId())) {
			g.put(node.id, node);
		}
	}

	public void addEdge(Node src, Node dest) {
		if(g.containsKey(src.getId()) && g.containsKey(dest.getId())) {
			src.getNeighbours().addLast(dest);
		}
	}

	public void addWeight(Node src, Node dest) {
		if(g.containsKey(src.getId()) && g.containsKey(dest.getId())) {

			int x1 = src.getxCordinate();
			int x2 = dest.getxCordinate();
			int y1 = src.getyCordinate();
			int y2 = dest.getyCordinate();

			weightMatrix[src.getId()][dest.getId()] = SensorUtil.calculateEuclidianDistance(x1, x2, y1, y2);
		}
	}
	
	
	public void addWeight(Node src, Node dest, double weight) {
		if(g.containsKey(src.getId()) && g.containsKey(dest.getId())) {
			weightMatrix[src.getId()][dest.getId()] = weight;
		}
	}
	
	public void removeEdge(Node src, Node dest) {
		if(g.containsKey(src.getId()) && g.containsKey(dest.getId())) {
			src.getNeighbours().remove(dest);
		}
	}

	public Node getNodeById(int n) {
		if(g.containsKey(n)) {
			return g.get(n);
		}
		return null;
	}

	public Node getNodeByCordinates(int x, int y) {
		Collection<Node> nodes = g.values();
		for(Node next : nodes ) {
			if(next.getxCordinate() == x && next.getyCordinate() == y) {
				return next;
			}
		}
		return null;
	}
	
	public double calculateLength() {
		double len = 0;
		for(int i=0; i<weightMatrix.length; i++) {
			for(int j=0; j<weightMatrix[i].length; j++) {
				len += weightMatrix[i][j];
			}
		}
		return len/2;
	}
	
	public Edge maxWeightEdge() {
		double max = 0;
		Edge e = new Edge(-1,-1);
		
		for(int i=0; i<weightMatrix.length; i++) {
			for(int j=0; j<weightMatrix[i].length; j++) {
				if(max< weightMatrix[i][j]) {
					max = weightMatrix[i][j];
					e.src = i;
					e.dest = j;
				}
			}
		}
		return e;
	}

}
