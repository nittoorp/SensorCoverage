package org.asu.cse551.sensorcoverage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.asu.cse551.sensorcoverage.graph.CopyGraph;
import org.asu.cse551.sensorcoverage.graph.Graph;
import org.asu.cse551.sensorcoverage.graph.PrimsAlgorithm;
import org.asu.cse551.sensorcoverage.graph.PrintGraph;
import org.asu.cse551.sensorcoverage.mlcc.MLCCAlgorithm;
import org.asu.cse551.sensorcoverage.mncc.MNCCAlgorithm;
import org.asu.cse551.sensorcoverage.ui.DisplayGraph;
import org.asu.cse551.sensorcoverage.util.SensorUtil;

/*
 * if you can't impress people with your intelligence , confuse them with your bullshit
 */
public class App{
	

	public static void main( String[] args ){
		
		
		try {
			
			File file = SensorUtil.getFileFromResources("input/input.txt");
			FileReader in = new FileReader(file);
			BufferedReader br = new BufferedReader(in);

			Graph graph = null;
			double budget = 0.0;
			String line;
			int currNodeID = 0 ;
			while ((line = br.readLine()) != null) {
				if(line.contains("START")) {
					System.out.println("Started reading the input file");
					currNodeID = 0 ;
					budget = Integer.parseInt(br.readLine());
					int numOfVertices = Integer.parseInt(br.readLine());
					graph = new Graph(numOfVertices);
					continue;
				}
				else if(line.contains("END")) {
					System.out.println("Finished reading the input file");
					break;
				}
				else if(line.contains("NEXT")) {
					
					System.out.println();
					currNodeID = 0 ;
					budget = Integer.parseInt(br.readLine());
					int numOfVertices = Integer.parseInt(br.readLine());
					graph = new Graph(numOfVertices);
					continue;
				}

				String[] arr = line.split(",");
				if(arr.length >=2) {
					int x = Integer.parseInt(arr[0].trim());
					int y = Integer.parseInt(arr[1].trim());
					graph.addNode(currNodeID++, x, y);
				}
			}
			System.out.println("\nPrinting connected graph");
			PrintGraph.printGraph(graph);
			System.out.println();
			PrintGraph.printWeightMatrix(graph);
			//DisplayGraph.display(graph);
			
			System.out.println("\nPrinting Spanning Tree");
			
			Graph spanningTree = PrimsAlgorithm.createSpanningTree(graph, 0);
			
			PrintGraph.printGraph(spanningTree);
			System.out.println();
			PrintGraph.printWeightMatrix(spanningTree);
			//DisplayGraph.display(spanningTree);
			
			//Algorithm 4
			Graph  copySpanningTreeMNCC  = CopyGraph.deepClone(spanningTree);
			Graph mnccGraphs = MNCCAlgorithm.generateMNCCGraphs(copySpanningTreeMNCC, budget);
			System.out.println("\nPrinting MNCC Graphs");
				PrintGraph.printGraph(mnccGraphs);
				System.out.println();
				PrintGraph.printWeightMatrix(mnccGraphs);
				//DisplayGraph.display(g);
			
			/*Graph mlccGraph = MLCCAlgorithm.generateMLCCGraph(spanningTree, budget);
			System.out.println("\nPrinting MLCC Graph");
			PrintGraph.printGraph(mlccGraph);
			System.out.println();
			PrintGraph.printWeightMatrix(mlccGraph);
			DisplayGraph.display(mlccGraph);*/
			
			in.close();
			br.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
