package org.asu.cse551.sensorcoverage.ui;


import java.util.LinkedList;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class DisplayGraph {
	protected static String styleSheet = "node { fill-color: red;}" ;



	public static void display(org.asu.cse551.sensorcoverage.graph.Graph g) throws Exception {

		Graph graph = new SingleGraph("Connected Sensor Graph");
		graph.addAttribute("ui.stylesheet", styleSheet);
		graph.addAttribute("ui.quality");
		graph.addAttribute("ui.antialias");
		

		int numOfVertices = g.numOfNodes;

		for(int i = 0 ; i < numOfVertices; i++) {

			org.asu.cse551.sensorcoverage.graph.Node curr = g.getNodeById(i);
			Node n = graph.getNode(curr.getId());
			if(n == null) {
				n = graph.addNode(Integer.valueOf(curr.getId()).toString());
				n.addAttribute("ui.label", getGSGraphNodeLabel(curr));
			}

			LinkedList<org.asu.cse551.sensorcoverage.graph.Node> neighbours = curr.getNeighbours();
			for(org.asu.cse551.sensorcoverage.graph.Node neighbour : neighbours) {
				Node neighbourGS = graph.getNode(neighbour.getId());
				if( neighbourGS == null) {
					neighbourGS = graph.addNode(Integer.valueOf(neighbour.getId()).toString());
					neighbourGS.addAttribute("ui.label", getGSGraphNodeLabel(neighbour));
				}
				if(!n.hasEdgeBetween(Integer.valueOf(neighbour.getId()).toString())) {
					graph.addEdge(curr.getId()+""+neighbour.getId(), curr.getId(), neighbour.getId(), false);
				}
			}
		}
		graph.display();
		graph.addAttribute("ui.screenshot", "screenshot.png");
	}

	public static String getGSGraphNodeLabel(org.asu.cse551.sensorcoverage.graph.Node curr) {
		return "( " + curr.getxCordinate() + " , " + curr.getyCordinate() + " )";
	}

}
