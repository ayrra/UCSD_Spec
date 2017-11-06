package roadgraph;

import java.util.Set;

import geography.GeographicPoint;
import util.GraphLoader;

public class TestingMapGraph {

	public static void main(String[] args) {
		System.out.print("Making a new map...");
		MapGraph firstMap = new MapGraph();
		//System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
		//System.out.println("DONE.");
		
		GeographicPoint start = new GeographicPoint(1.0,1.0);
		GeographicPoint end = new GeographicPoint(8.0, -1.0);
		
		System.out.println(firstMap.getVertices());
		System.out.println("we have this many vertices: " + firstMap.getNumVertices());
		System.out.println("we have this many edges: " + firstMap.getNumEdges());
		System.out.println("-----------------------------------------");

		//firstMap.addVertex(start);
		//firstMap.addVertex(end);
		
		//GeographicPoint testPoint = new GeographicPoint(7.0, -1.0);
		
		//firstMap.addEdge(start, end, "Main Street", "Residential", 100);
		
		System.out.println("we have this many vertices: " + firstMap.getNumVertices());
		System.out.println("we have this many edges: " + firstMap.getNumEdges());
		
		Set<GeographicPoint> listOfVertices = firstMap.getVertices();
		
		for (GeographicPoint point : listOfVertices) {
			System.out.println(point);
			System.out.println("-----------------------------------------");
		}
		
		System.out.println(firstMap.bfs(start, end));

	}

}
