package org.asu.cse551.sensorcoverage.ui;


import java.util.LinkedList;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

public class DisplayGraph {
	protected static String styleSheet = "node { fill-color: red;}" ;



	public static void display(org.asu.cse551.sensorcoverage.graph.Graph g, String outputFileName) throws Exception {

		Graph graph = new MultiGraph("Connected Sensor Graph");
		graph.addAttribute("ui.stylesheet", styleSheet);
		graph.addAttribute("ui.quality");
		graph.addAttribute("ui.antialias");


		int numOfVertices = g.numOfNodes;

		for(int i = 0 ; i < numOfVertices; i++) {

			org.asu.cse551.sensorcoverage.graph.Node curr = g.getNodeById(i);
			if(curr !=  null) {
				Node n = graph.getNode(Integer.valueOf(curr.getId()).toString());
				if(n == null) {
					n = graph.addNode(Integer.valueOf(curr.getId()).toString());
					n.addAttribute("ui.label", getGSGraphNodeLabel(curr));
				}

				LinkedList<org.asu.cse551.sensorcoverage.graph.Node> neighbours = curr.getNeighbours();
				for(org.asu.cse551.sensorcoverage.graph.Node neighbour : neighbours) {
					Node neighbourGS = null;
					neighbourGS = graph.getNode(Integer.valueOf(neighbour.getId()).toString());
					if( neighbourGS == null) {
						neighbourGS = graph.addNode(Integer.valueOf(neighbour.getId()).toString());
						neighbourGS.addAttribute("ui.label", getGSGraphNodeLabel(neighbour));
					}
					if(!n.hasEdgeBetween(Integer.valueOf(neighbour.getId()).toString())) {

						graph.addEdge(curr.getId()+""+neighbour.getId(), n, neighbourGS, false);
					}
				}
			}
		}
		graph.display();
		Thread.sleep(2000);
		graph.addAttribute("ui.screenshot", outputFileName);
	}

	public static String getGSGraphNodeLabel(org.asu.cse551.sensorcoverage.graph.Node curr) {
		return curr.getId()+"( " + curr.getxCordinate() + " , " + curr.getyCordinate() + " )";
	}

}
