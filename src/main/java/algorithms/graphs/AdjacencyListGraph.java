package algorithms.graphs;

import java.util.Set;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

/**
 * A graph which is backed by a map of vertices to lists of edges.
 * Space: O(|V| + |E|).
 * @param <V> vertex type.
 * @param <E> edge type.
 */
public class AdjacencyListGraph<V extends Vertex, E extends Edge<V>> implements Graph<V, E>
{
	private final HashMap<V, LinkedList<E>> map;
	
	/**
	 * Creates a graph backed by an adjacency list with an initial has size.
	 * @param maxVerticesCount initial hash size.
	 */
	public AdjacencyListGraph( int maxVerticesCount )
	{
		this.map = new HashMap<>(maxVerticesCount);
	}
	
	/**
	 * Creates a graph backed by an adjacency list.
	 */
	public AdjacencyListGraph( )
	{
		this.map = new HashMap<>();
	}
	
	/**
	 * Complexity: O(1)
	 * @see {@link Graph#addVertex(Vertex)}
	 */
	@Override
	public void addVertex( @NotNull V vertex )
	{
		checkNotNull(vertex);
		checkArgument(!this.map.containsKey(vertex), "Vertex already exists in graph: %s.", vertex);
		
		this.map.put(vertex, new LinkedList<>());
	}

	/**
	 * Complexity: O(|E|)
	 * @see {@link Graph#removeVertex(Vertex)}
	 */
	@Override
	public void removeVertex( @NotNull V vertex )
	{
		checkNotNull(vertex);
		checkArgument(this.map.containsKey(vertex), "Vertex does does not exist in the graph: %s.", vertex);
		
		this.map.remove(vertex);
		
		// remove associated edges
		// note: could remove in O(|V|) if used a set for storing edges with a trade off in memory for larger array
		// backing. Might be OK if sets are initialized with a size of a node's average edge count and the variability
		// of the edge count is low for the graph. Or if the maximum edge count count for each node is known on node
		// creation.
		for( V v : this.getVertices() )
		{
			Iterator<E> eIt = this.map.get(v).iterator();
			while( eIt.hasNext() )
			{
				E e = eIt.next();
				
				if( e.getSource().equals(vertex) || e.getDestination().equals(vertex) )
				{
					eIt.remove();
				}
			}
		}
	}
	
	/**
	 * Complexity: O(1)
	 * @see {@link Graph#getVertices()}
	 */
	@Override
	public @NotNull Set<V> getVertices( )
	{
		return this.map.keySet();
	}

	/**
	 * Complexity: O(1)
	 * @see {@link Graph#addEdge(Edge)}
	 */
	@Override
	public void addEdge( @NotNull E edge )
	{
		checkNotNull(edge);
		checkArgument(this.map.containsKey(edge.getSource()), "Edge source (%s) does not exist in graph.", edge.getSource());
		checkArgument(this.map.containsKey(edge.getDestination()), "Edge destination (%s) does not exist in graph.", edge.getDestination());
		
		LinkedList<E> vertices = this.map.get(edge.getSource());
		vertices.add(edge);
	}

	/**
	 * Complexity: O(|V|)
	 * @see {@link Graph#removeEdge(Edge)}
	 */
	@Override
	public void removeEdge( @NotNull E edge )
	{
		checkNotNull(edge);
		checkArgument(this.map.containsKey(edge.getSource()), "Edge source (%s) does not exist in graph.", edge.getSource());
		checkArgument(this.map.containsKey(edge.getDestination()), "Edge destination (%s) does not exist in graph.", edge.getDestination());
		
		// note: complexity: a vertex can have at move |V| edges
		boolean removed = this.map.get(edge.getSource()).remove(edge);
		
		checkArgument(removed, "Edge does not exist in the graph: %s.", edge);
	}

	/**
	 * Complexity: O(1)
	 * @see {@link Graph#getEdges(Vertex)}
	 */
	@Override
	public @NotNull Set<E> getEdges( @NotNull V vertex )
	{
		checkNotNull(vertex);
		checkArgument(this.map.containsKey(vertex), "Vertex does not exist in the graph: %s.", vertex);
		
		return new HashSet<>(this.map.get(vertex));
	}
}