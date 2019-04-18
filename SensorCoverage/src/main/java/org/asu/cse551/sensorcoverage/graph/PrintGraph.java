package org.asu.cse551.sensorcoverage.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class PrintGraph {
	
	public static void printGraph(Graph graph) {
		Set<Map.Entry<Integer, Node>> set = (Set<Map.Entry<Integer, Node>>) graph.g.entrySet();
		Iterator<Map.Entry<Integer, Node>> itr = set.iterator();

		while(itr.hasNext()) {
			Map.Entry<Integer, Node> curr = itr.next();
			Node parent = curr.getValue();
			System.out.print(parent + " -- ");

			LinkedList<Node> neighbours = parent.getNeighbours();
			for(Node neighbour : neighbours) {
				System.out.print(neighbour+ " -> ");
			}
			System.out.println();
		}
	}
	
	public static void printWeightMatrix(Graph graph) {
		for(int i =0 ; i<graph.weightMatrix.length; i++) {
			for(int j=0; j<graph.weightMatrix[i].length; j++) {
				System.out.print(graph.weightMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}

}
