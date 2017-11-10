/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;
import week3example.MazeNode;

/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	private int numVertices;
	private int numEdges;
	private HashMap <GeographicPoint, MapNode> vertices;
	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		numVertices = 0;
		numEdges = 0;
		vertices = new HashMap<GeographicPoint, MapNode>();
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		return numVertices;
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		Set<GeographicPoint>  setOfVertices = vertices.keySet();
		return setOfVertices;
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		return numEdges;
	}

	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		if (location == null || vertices.containsKey(location)) {
			return false;
		}
		else {
			vertices.put(location, new MapNode(location));
			numVertices++;
			return true;
		}
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {
		
		if (from == null || to == null || roadName == null || roadType == null || length < 0) {
			throw new IllegalArgumentException();
		}
		
		if (!getVertices().contains(from) || !getVertices().contains(to)) {
			throw new IllegalArgumentException();
		}
		
		MapNode node = vertices.get(from);
		node.createEdge(from, to, roadName, roadType, length);
		numEdges++;
		
	}
	

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		MapNode startingPoint = vertices.get(start);
		MapNode endingPoint = vertices.get(goal);
		
		Queue<MapNode> queue = new LinkedList<MapNode>();
		HashSet<MapNode> visited = new HashSet<MapNode>();
		HashMap<MapNode, MapNode> parent = new HashMap<MapNode, MapNode>();
		
		queue.add(startingPoint);
		visited.add(startingPoint);

		while (!queue.isEmpty()) {
			MapNode curr = queue.remove();
			nodeSearched.accept(curr.getLocation());
			List<MapNode> neighbors = curr.getNeighbors();

			if (curr.getLocation().equals(goal)) {
				return constructPath(startingPoint, endingPoint, parent);
			}
			for (MapNode n : neighbors) {
				if (!visited.contains(n)) {
					visited.add(n);
					parent.put(n, curr);
					queue.add(n);
				}
			}
			
		}	
		return null;
	}
	
	private List<GeographicPoint> constructPath(MapNode start, MapNode goal, HashMap<MapNode, MapNode> parentMap) {
		LinkedList<GeographicPoint> path = new LinkedList<GeographicPoint>();
		MapNode curr = goal;
		while (curr != start) {
			path.addFirst(curr.getLocation());
			curr = parentMap.get(curr);
		}
		path.addFirst(start.getLocation());
		return path;
	}
	

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{

		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		MapNode startingPoint = vertices.get(start);
		MapNode endingPoint = vertices.get(goal);
		
		//we initialize the usuals
		//we have to overwrite the compare method of mapnode so we can use the pqueue
		PriorityQueue<MapNode> queue = new PriorityQueue<MapNode>();
		HashSet<MapNode> visited = new HashSet<MapNode>();
		HashMap<MapNode, MapNode> parent = new HashMap<MapNode, MapNode>();
		
		//first create a distance variable in the class and getter/setters
		//now we have to set distance of every node to infinity
		for (MapNode node : vertices.values()) {
			node.setDistance(Double.POSITIVE_INFINITY);
		}
		//then do algorithm
		queue.add(startingPoint);
		startingPoint.setDistance(0);
		
		while (!queue.isEmpty()) {
			MapNode curr = queue.remove();
			//System.out.println("Dijktras VISITED NODE AT: " + curr.getLocation());
			nodeSearched.accept(curr.getLocation());
			if (!visited.contains(curr)) {
				visited.add(curr);
				if (curr.getLocation().equals(endingPoint.getLocation())) {
					return constructPath(startingPoint, endingPoint, parent);
				}			
				for (MapNode n : curr.getNeighbors()) {
					if (!visited.contains(n)) {
						//refactored to get just current -> n edge instead of all edges
						MapEdge edge = curr.returnEdge(n);
						Double totalDistance = curr.getDistance() + edge.getDistanceOfEdge();
						if (totalDistance < n.getDistance()) {
							n.setDistance(totalDistance);
							parent.put(n, curr);
							queue.add(n);
						}
					}
				}
			}
		}
		//We didnt find anything
		return null;
	}

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{	
		// Hook for visualization.  See writeup.
				//nodeSearched.accept(next.getLocation());
				MapNode startingPoint = vertices.get(start);
				MapNode endingPoint = vertices.get(goal);
				
				//we initialize the usuals
				//we have to overwrite the compare method of mapnode so we can use the pqueue
				PriorityQueue<MapNode> queue = new PriorityQueue<MapNode>();
				HashSet<MapNode> visited = new HashSet<MapNode>();
				HashMap<MapNode, MapNode> parent = new HashMap<MapNode, MapNode>();
				
				//first create a distance variable in the class and getter/setters
				//now we have to set distance of every node to infinity
				for (MapNode node : vertices.values()) {
					node.setDistance(Double.POSITIVE_INFINITY);
					node.setActualDistance(Double.POSITIVE_INFINITY);
				}
				//then do algorithm
				queue.add(startingPoint);
				startingPoint.setDistance(0);
				startingPoint.setActualDistance(0);
				
				
				while (!queue.isEmpty()) {
					MapNode curr = queue.remove();
					//System.out.println("A* VISITED NODE AT: " + curr.getLocation());
					nodeSearched.accept(curr.getLocation());
					if (!visited.contains(curr)) {
						visited.add(curr);
						if (curr.getLocation().equals(endingPoint.getLocation())) {
							return constructPath(startingPoint, endingPoint, parent);
						}						
						for (MapNode n : curr.getNeighbors()) {
							if (!visited.contains(n)) {
									MapEdge edge = curr.returnEdge(n);
									Double totalDistance = curr.getActualDistance() + edge.getDistanceOfEdge();
									//get distance between our current neighbor node and the end node using GeographicPoint methods
									Double PredictedDistance = totalDistance + n.getLocation().distance(endingPoint.getLocation());
									if (PredictedDistance < n.getDistance()) {
										n.setDistance(PredictedDistance);
										n.setActualDistance(totalDistance);
										parent.put(n, curr);
										queue.add(n);
									}
							}
						}
						
					}
				}
				//We didnt find anything
				return null;
	}

	private class MapNode implements Comparable<MapNode> {
		private List <MapEdge> edges;
		private GeographicPoint location;
		private double distance;
		private double actualDistance;
		
		private MapNode(GeographicPoint l) {
			this.location = l;
			this.edges = new LinkedList<MapEdge>();
			this.distance = 0;
			this.actualDistance = 0;
		}
		
		
		public GeographicPoint getLocation() {
			return location;
		}
		
		public double getDistance() {
			return distance;
		}
		
		public void setDistance(double newDistance) {
			distance = newDistance;
		}
		
		public double getActualDistance() {
			return actualDistance;
		}
		
		public void setActualDistance(double newDistance) {
			actualDistance = newDistance;
		}


		public boolean createEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) {
			edges.add(new MapEdge(from, to, roadName, roadType, length));
			return true;
		}
		
		public List<MapEdge> returnEdges() {
			List<MapEdge> list = new LinkedList<MapEdge>();
			
			for (MapEdge e : edges) {
				list.add(e);
			}
			return list;
		}
		
		public MapEdge returnEdge(MapNode neighbor) {
			for (MapEdge e : edges) {
				//System.out.println("this is our current nodes location: " + this.getLocation());
				//System.out.println("this is our current edge point: " + e.getEnd());
				if (e.getEnd().equals(neighbor.getLocation())) {
					return e;
				}
			}
			//we shouldnt reach this
			return null;
		}
		
		public List<MapNode> getNeighbors() {
			List<MapNode> nodes = new LinkedList<MapNode>();
			
			for (MapEdge m : edges) {
				nodes.add(vertices.get(m.getEnd()));
			}
			
			return nodes;
		}
		@Override
		public int compareTo(MapNode mNode) {
			MapNode m = (MapNode)mNode;
			return ((Double)this.getDistance()).compareTo((Double)m.getDistance());
		}

	}
	
	 private class MapEdge {
		private GeographicPoint start;
		private GeographicPoint end;
		private String streetName;
		private String roadType;
		private double distance;
		
		private MapEdge(GeographicPoint start, GeographicPoint end, String street, String roadType, double distance) {
			this.start = start;
			this.end = end;
			this.streetName = street;
			this.roadType = roadType;
			this.distance = distance;
		}
		
		private GeographicPoint getEnd() {
			return end;
		}
		
		private void printEdge(MapNode s, MapNode e) {
			System.out.println(s.getLocation());
			System.out.println(e.getLocation());
			System.out.println(distance);
			System.out.println("----------");
		}
		
		private double getDistanceOfEdge() {
			return distance;	
		}
		
		private GeographicPoint returnStart() {
			return start;
		}
		
		private GeographicPoint returnEnd() {
			return end;
		}
		
		private String returnName() {
			return streetName;
		}
		
		private String returnType() {
			return roadType;
		}
		
	}
	
	
	public static void main(String[] args)
	{
	    MapGraph simpleTestMap = new MapGraph();
			GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
			
			GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
			GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
			
			System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
			List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
			List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
			
			
			MapGraph testMap = new MapGraph();
			GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
			
			// A very simple test using real data
			testStart = new GeographicPoint(32.869423, -117.220917);
			testEnd = new GeographicPoint(32.869255, -117.216927);
			System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
			testroute = testMap.dijkstra(testStart,testEnd);
			testroute2 = testMap.aStarSearch(testStart,testEnd);
			
			
			// A slightly more complex test using real data
			testStart = new GeographicPoint(32.8674388, -117.2190213);
			testEnd = new GeographicPoint(32.8697828, -117.2244506);
			System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
			testroute = testMap.dijkstra(testStart,testEnd);
			testroute2 = testMap.aStarSearch(testStart,testEnd);
		
	}
	
}
