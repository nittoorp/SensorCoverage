package org.asu.cse551.sensorcoverage.graph;

import java.io.Serializable;

public class Edge  implements Serializable{ 
	
	private static final long serialVersionUID = 6762236011757196617L;
	
	public int src, dest;
	
	public Edge(int src, int dest) {
		this.src = src;
		this.dest = dest;
	}

}
