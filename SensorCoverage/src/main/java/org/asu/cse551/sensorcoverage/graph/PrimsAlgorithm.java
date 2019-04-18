package org.asu.cse551.sensorcoverage.graph;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class PrimsAlgorithm {


	public static Graph createSpanningTree(Graph graph, int start){

		Map<Integer, DistanceInfo> map = new HashMap<>();

		for(int i=0; i<graph.numOfNodes; i++) {
			if(i==start) {
				map.put(i, new DistanceInfo(i, 0, i));
			}
			else {
				map.put(i, new DistanceInfo(i, Integer.MAX_VALUE, -1));
			}
		}

		Set<Integer> spanningTree = new HashSet<>();
		Set<Integer> visited = new HashSet<>();

		Queue<DistanceInfo> q = new PriorityQueue<>();
		q.add(map.get(start));
		visited.add(start);

		while(!q.isEmpty()) {

			DistanceInfo df = q.poll();
			spanningTree.add(df.node);

			int parentNodeId = df.node;
			Node parent = graph.g.get(parentNodeId);

			LinkedList<Node> neighbours = parent.getNeighbours();

			for(Node neighbour : neighbours) {
				if(!spanningTree.contains(neighbour.id)) {
					if(visited.contains(neighbour.id)) {
						if( map.get(neighbour.id).distance > graph.weightMatrix[parent.id][neighbour.id]) {
							map.get(neighbour.id).distance =  graph.weightMatrix[parent.id][neighbour.id];
							map.get(neighbour.id).lastNode = parentNodeId;
						}
					}
					else {
						map.get(neighbour.id).distance =  graph.weightMatrix[parent.id][neighbour.id];
						map.get(neighbour.id).lastNode = parentNodeId;
						q.add(map.get(neighbour.id));
						visited.add(neighbour.id);
					}
				}
			}

		}

		Graph spanTree = new Graph(graph.numOfNodes, graph.range);

		Set<Map.Entry<Integer, DistanceInfo>> set = map.entrySet();
		Iterator<Map.Entry<Integer, DistanceInfo>> itr = set.iterator();
		while(itr.hasNext()) {
			Map.Entry<Integer, DistanceInfo> entry = itr.next();
			DistanceInfo df = entry.getValue();

			if(df.distance != Integer.MAX_VALUE && df.distance != 0){

				int srcId = df.lastNode;
				int destID = df.node;
				double edgeLen = df.distance;

				Node srcNodeST = spanTree.g.get(srcId) ;
				if(srcNodeST== null) {
					Node srcNode = graph.getNodeById(srcId);
					srcNodeST= new Node(srcNode.id, srcNode.xCordinate, srcNode.yCordinate);
				}
				Node destNodeST = spanTree.g.get(destID);
				if(destNodeST == null) {
					Node destNode = graph.getNodeById(destID);
					destNodeST=	new Node(destNode.id, destNode.xCordinate, destNode.yCordinate);
				}
				spanTree.addNode(srcNodeST);
				spanTree.addNode(destNodeST);
				spanTree.addEdge(srcNodeST, destNodeST);
				spanTree.addEdge(destNodeST, srcNodeST);
				spanTree.addWeight(srcNodeST, destNodeST, edgeLen);	
				spanTree.addWeight(destNodeST, srcNodeST, edgeLen);	
			}
		}
		return spanTree;
	}

}

class DistanceInfo implements Comparable<DistanceInfo>{
	int node;
	double distance;
	int lastNode;

	public DistanceInfo(int node, double distance, int lastNode) {
		super();
		this.node = node;
		this.distance = distance;
		this.lastNode = lastNode;
	}

	@Override
	public int compareTo(DistanceInfo o) {
		return Double.compare(distance, o.distance);
	}

	@Override
	public String toString() {
		return "(" + node + "," + distance + "," + lastNode + ")";
	}


}
