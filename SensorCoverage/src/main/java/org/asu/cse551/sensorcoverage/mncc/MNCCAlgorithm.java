package org.asu.cse551.sensorcoverage.mncc;

import org.asu.cse551.sensorcoverage.graph.Edge;
import org.asu.cse551.sensorcoverage.graph.Graph;

public class MNCCAlgorithm {
	
	public static Graph generateMNCCGraphs(Graph mst, double budget){
		
		while(mst.calculateLength() > budget) {
			Edge e = mst.maxWeightEdge();
			mst.removeEdge(mst.getNodeById(e.src), mst.getNodeById(e.dest));
			mst.removeEdge( mst.getNodeById(e.dest), mst.getNodeById(e.src));
			mst.addWeight(mst.getNodeById(e.src), mst.getNodeById(e.dest), 0);
			mst.addWeight( mst.getNodeById(e.dest), mst.getNodeById(e.src), 0);
		}
		return mst;
	}
}
