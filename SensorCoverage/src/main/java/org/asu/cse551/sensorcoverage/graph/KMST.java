package org.asu.cse551.sensorcoverage.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class KMST {

	public static Graph generateKMST(Graph mst, int k) {

		List<Integer> terminalNodes = new ArrayList<>();
		Map<Integer, Node> g = mst.g;
		Set<Map.Entry<Integer, Node>> set = g.entrySet();
		Iterator<Map.Entry<Integer, Node>> itr = set.iterator();
		while(itr.hasNext()) {
			Map.Entry<Integer, Node> entry = itr.next();
			if(entry.getValue().neighbours==null || entry.getValue().neighbours.size()==0) {
				terminalNodes.add(entry.getKey());
			}
		}
		for(Integer i : terminalNodes) {
			g.remove(i);
		}

		int n = g.size();
		int numOfNodesToBeRemoved = n-k;

		for (int i=0; i< numOfNodesToBeRemoved; i++) {
			Edge e = getTerminalNodeEdgeWithMaximumWeightEdge(mst);
			mst.removeEdge(mst.getNodeById(e.src), mst.getNodeById(e.dest));
			mst.removeEdge( mst.getNodeById(e.dest), mst.getNodeById(e.src));
			mst.addWeight(mst.getNodeById(e.src), mst.getNodeById(e.dest), 0);
			mst.addWeight( mst.getNodeById(e.dest), mst.getNodeById(e.src), 0);
		}

		terminalNodes.clear();
		Map<Integer, Node> g1 = mst.g;
		Set<Map.Entry<Integer, Node>> set1 = g1.entrySet();
		Iterator<Map.Entry<Integer, Node>> itr1 = set1.iterator();
		while(itr1.hasNext()) {
			Map.Entry<Integer, Node> entry = itr1.next();
			if(entry.getValue().neighbours==null || entry.getValue().neighbours.size()==0) {
				terminalNodes.add(entry.getKey());
			}
		}
		for(Integer i : terminalNodes) {
			g.remove(i);
		}

		return mst;

	}

	public static Edge getTerminalNodeEdgeWithMaximumWeightEdge(Graph graph) {

		Map<Double, Edge> map = new TreeMap<>(Collections.reverseOrder());

		Set<Integer> terminalNodes = new HashSet<>();

		Map<Integer, Node> g = graph.g;
		Set<Map.Entry<Integer, Node>> set = g.entrySet();
		Iterator<Map.Entry<Integer, Node>> itr = set.iterator();
		while(itr.hasNext()) {
			Map.Entry<Integer, Node> entry = itr.next();
			if(entry.getValue().neighbours==null || entry.getValue().neighbours.size()==1) {
				terminalNodes.add(entry.getKey());
			}
		}

		double[][] weightMatrix = graph.weightMatrix;
		for(int i=0; i<weightMatrix.length; i++) {
			for(int j=0; j<weightMatrix[i].length; j++) {
				if( weightMatrix[i][j] != 0 && terminalNodes.contains(j)) {
					map.put(weightMatrix[i][j], new Edge(i,j));
				}
			}
		}

		Map<Double, Edge> mapInOrder = new LinkedHashMap<>(map);
		Map.Entry<Double, Edge> entry = mapInOrder.entrySet().iterator().next();
		return entry.getValue();
	}

}
