package algorithms.graphs.dijkstra;

import java.util.HashMap;
import java.util.PriorityQueue;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import datastructures.graphs.CostEdge;
import datastructures.graphs.CostPath;
import datastructures.graphs.Graph;
import datastructures.graphs.MarkedVertex;
import datastructures.graphs.MarkedVertexInterface;
import datastructures.graphs.NoPathException;
import datastructures.graphs.Vertex;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

public class Dijkstra
{
	/**
	 * Class to compute single source shortest path using Dijkstra algorithm.
	 */
	public static class DijkstraImpl<V extends Vertex & MarkedVertexInterface<VertexMark>, E extends CostEdge<V>>
	{
		private final PriorityQueue<CostPath<V, E>> q;
		private final HashMap<V, CostPath<V, E>> paths;
		private boolean computed;
		private Graph<V, E> graph;
		private V start;
		private V end;
		private static final Logger LOGGER = LoggerFactory.getLogger(DijkstraImpl.class);
		
		/**
		 * Creates a new instance for computing the shortest past of a graph.
		 * @param graph graph
		 * @param start vertex to start path from.
		 * @param end vertex to end path at. If null, shortest path is found for all vertices.
		 */
		public DijkstraImpl( Graph<V, E> graph, @NotNull V start, @Nullable V end )
		{
			this.graph = checkNotNull(graph);
			this.start = checkNotNull(start);
			this.end = end;
			this.q = new PriorityQueue<>();
			this.paths = new HashMap<>();
			this.computed = false;
		}
		
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
		 * @return map of nodes to their shortest path from the start node.
		 */
		public @NotNull HashMap<V, CostPath<V, E>> compute( )
		{
			checkState(!this.computed, "Compute can be called only once per instance.");
			
			if( this.end == null )
			{
				LOGGER.trace("Computing shortest paths from {}.", this.start);
			}
			else // this.end != null
			{
				LOGGER.trace("Computing shortest path from {} to {}.", this.start, this.end);
			}
			
			// mark compute operation
			this.computed = true;
			
			// add start node as candidate with cost 0
			// note: the remaining nodes will be added when they are first seen
			CostPath<V, E> startPath = new CostPath<V, E>(this.start);
			
			LOGGER.trace("Adding: {} with initial path {}.", this.start, startPath);
			
			q.add(startPath);
			paths.put(startPath.getDestination(), startPath);
			
			// explore from current shortest path until the end is found or all reachable vertices are found if end is null
			while( !q.isEmpty() && (this.end == null || (this.end != null && !q.peek().getDestination().equals(this.end))) )
			{
				CostPath<V, E> path = q.remove();
				V vertex = path.getDestination();
				
				LOGGER.trace("Exploring: {} from {}.", vertex, path);
				
				// mark current node as explored
				vertex.mark(VertexMark.EXPLORED);
				
				for( E edge : graph.getEdges(vertex) )
				{
					LOGGER.trace("Visiting edge: {}.", edge);
					
					this.visitEdge(edge, path);
				}
			}
			
			return this.paths;
		}
		
		private void visitEdge( @NotNull E edge, @NotNull CostPath<V, E> sourcePath )
		{
			checkNotNull(edge);
			checkNotNull(sourcePath);
			// note: Dijkstra only works with edge costs which are non-negative
			checkArgument(edge.getCost() >= 0, "Edges with negative costs are not allowed: %s.", edge);
			
			V destination = edge.getDestination();
			
			// update path cost if the node has not yet been explored (all nodes which have been explored must
			// already have the shortest path found) and the node has a shorter path.
			// note: checking if explored is really only important if the cost to compute or retrieve path costs is
			// prohibitively expensive. In this case it is not since previous path lookup is done via a map and the
			// cost is stored on the nodes.
			if( !destination.isMarked(VertexMark.EXPLORED) )
			{
				CostPath<V, E> newDestinationPath = new CostPath<V, E>(sourcePath);
				newDestinationPath.addEdge(edge);
				
				if( !destination.isMarked(VertexMark.SEEN) )
				{ // destination path is not in heap yet
					LOGGER.trace("Updating (add): {} with path {}.", destination, newDestinationPath);
					
					q.add(newDestinationPath);
					paths.put(destination, newDestinationPath);
					
					destination.mark(VertexMark.SEEN);
				}
				else // destination.isMarked(VertexMarks.SEEN)
				{
					CostPath<V, E> currentDestinationPath = paths.get(destination);
					
					if( newDestinationPath.getCost() < currentDestinationPath.getCost() )
					{ // a cheaper path to destination node has been found
						// "update key"
						// note: here is where a fib heap would be useful
						LOGGER.trace("Updating: {} with path {}.", destination, newDestinationPath);
						
						q.remove(currentDestinationPath);
						currentDestinationPath.update(newDestinationPath);
						q.add(currentDestinationPath);
					}
					else // newDestinationPath.getCost() >= currentDestinationPath.getCost()
					{
						LOGGER.trace("NOT Updating: {}. Current path {} < new path {}.",
								destination, currentDestinationPath, newDestinationPath);
					}
				}
			}
		}
	}
	
	/**
	 * Computes single source shortest path from start node. See {@link DijkstraImpl#compute(Graph, Vertex, Vertex)}.
	 * @param start vertex to start from.
	 * @return map of vertices to their shortest paths from the start vertex.
	 */
	public static @NotNull <V extends MarkedVertex<VertexMark>, E extends CostEdge<V>> HashMap<V, CostPath<V, E>>
	compute( Graph<V, E> graph, @NotNull V start )
	{
		return new DijkstraImpl<>(graph, start, null).compute();
	}
	
	/**
	 * Computes single source single destination shortest path from start node. See {@link DijkstraImpl#compute(Graph,
	 * Vertex, Vertex)}.
	 * @param start vertex to start path from.
	 * @param end vertex to end path at.
	 * @return shortest path from the start vertex to end vertex.
	 * @throws NoPathException when no path from start vertex to end vertex exists.
	 */
	public static @NotNull <V extends MarkedVertex<VertexMark>, E extends CostEdge<V>> CostPath<V, E>
	computeSingleDestination( Graph<V, E> graph, @NotNull V start, @NotNull V end )
	throws NoPathException
	{
		checkNotNull(end);
		
		CostPath<V, E> endPath = new DijkstraImpl<>(graph, start, end).compute().get(end);
		if( endPath == null )
		{ // no path to end node exists
			throw new NoPathException("Path from " + start + " to " + end + " does not exist");
		}
		
		return endPath;
	}
}
