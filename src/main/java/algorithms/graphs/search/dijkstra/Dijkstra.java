package algorithms.graphs.search.dijkstra;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Dijkstra
{
	/**
	 * Computes the shortest path from start node to end node or shortest path to all nodes when end is null. Edges must
	 * have a cost >= 0.
	 * 
	 * Complexity is O(|E|1 + |V|log(|V|)) when using fib heap. Interestingly the overhead of a fib heap is very
	 * large/impractical so that is probably why it is not in java.util (ref: comments on stack exchange).
	 * Current complexity with normal heap is O((|E|-|V|)(|V| * log(|V|)) + |V|(log(|V|))) => O(|V|log(|V|)((|E|-|V|) + 1)).
	 *   (|E|-|V|) since heap paths are only updated after a node has been seen once
	 *     |V| to linearly scan heap and remove path
	 *     log(|V|) to add updated path back into heap
	 *   |V|(log(|V|)) since a node is added to the heap with a worst case cost of log(|V|) at most |V| times
	 * 
	 * Insight: The shortest path to a node is guaranteed once that node is explored since the current path (P) to the
	 * explored node was the shortest one at that time. Not all paths to the node have necessarily been explored BUT
	 * all potentially shorter paths have been.
	 * Suppose a shorter path than P to the node exists, called P'. P' could be divided into two subpaths of seen edges
	 * (P's) and unseen edges (P'u). Since P' must be composed of edges with non-negative costs P's must be shorter than
	 * P. However if P's were indeed shorter than P then it would be the path currently being explored. Any further
	 * discovery of edges in P'u could only maintain or increase the cost of P's. Therefore P' cannot exist and P must
	 * be the shortest path to the node.
	 * @param start Node to start path from. Not null.
	 * @param end Node to end path at. If null, shortest path is found for all nodes.
	 * @return map of nodes to their shortest path from the start node.
	 */
	public static HashMap<Node, Path> computeSssp( Node start, Node end )
	{
		if( start == null )
		{
			throw new IllegalArgumentException("Start cannot be null");
		}
		
		// init
		PriorityQueue<Path> q = new PriorityQueue<>();
		HashMap<Node, Path> paths = new HashMap<>();
		
		// add start node as candidate with cost 0
		// note: the remaining nodes will be added when they are first seen
		Path startPath = new Path(start);
		q.add(startPath);
		paths.put(startPath.getDestination(), startPath);
		
		// explore from current shortest path until the end is found or all reachable nodes are found if end is null
		while( !q.isEmpty() && (end == null || (end != null && !q.peek().getDestination().equals(end))) )
		{
			Path path = q.remove();
			Node node = path.getDestination();
			
			// mark current node as explored
			node.markExplored();
			
			for( Edge edge : node.getEdges() )
			{
				// note: Dijkstra only works with edge costs which are non-negative
				if( edge.getCost() < 0 )
				{
					throw new InvalidParameterException("Edges with negative costs are not allowed: " + edge);
				}
				
				Node neighbor = edge.getDestination();
				
				// update path cost if the node has not yet been explored (all nodes which have been explored must
				// already have the shortest path found) and the node has a shorter path.
				// note: checking if explored is really only important if the cost to compute or retrieve path costs is
				// prohibitively expensive. In this case it is not since previous path lookup is done via a map and the
				// cost is stored on the nodes.
				if( !neighbor.isExplored() )
				{
					Path newNeighborPath = new Path(path);
					newNeighborPath.addEdge(edge);
					
					if( !neighbor.isSeen() )
					{ // neighbor path is not in heap yet
						q.add(newNeighborPath);
						paths.put(neighbor, newNeighborPath);
						
						neighbor.markSeen();
					}
					else // neighbor.isSeen()
					{
						Path minNeighborPath = paths.get(neighbor);
						
						if( newNeighborPath.getCost() < minNeighborPath.getCost() )
						{ // a cheaper path to neighbor node has been found
							// "update key"
							// note: here is where a fib heap would be useful
							q.remove(minNeighborPath);
							minNeighborPath.update(newNeighborPath);
							q.add(minNeighborPath);
						}
					}
				}
			}
		}
		
		return paths;
	}
	
	/**
	 * Computes single source shortest path from start node by calling computeSssp(start, null).
	 * See HashMap<Node, Path> computeSssp( Node start, Node end ) for more details.
	 * @param start node to start from.
	 * @return map of nodes to their shortest path from the start node.
	 */
	public static HashMap<Node, Path> computeSssp( Node start )
	{
		return computeSssp(start, null);
	}
	
	/**
	 * Computes single source single destination shortest path from start node by calling computeSssp(start, end).
	 * @param start Node to start path from. Not null.
	 * @param end Node to end path at. If null, shortest path is found for all nodes.
	 * @return shortest path from the start node.
	 * @throws NoPathException when no path from start node to end node exists.
	 */
	public static Path computeSssdsp( Node start, Node end )
	throws NoPathException
	{
		Path endPath = computeSssp(start, end).get(end);
		if( endPath == null )
		{ // no path to end node exists
			throw new NoPathException("Path from " + start + " to " + end + " does not exist");
		}
		
		return endPath;
	}
}
