package org.asu.cse551.sensorcoverage.mlcc;

import org.asu.cse551.sensorcoverage.graph.Graph;
import org.asu.cse551.sensorcoverage.graph.KMST;

public class MLCCAlgorithm {

	public static Graph generateMLCCGraph(Graph mst, double budget){
		int n = mst.g.size();
		for(int k = n; k>1; k--) {
			mst = KMST.generateKMST(mst, k);
			if(mst.calculateLength() <= budget) {
				return mst;
			}
		}
		return mst;
	}
}
