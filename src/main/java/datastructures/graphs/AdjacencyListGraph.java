package datastructures.graphs;

import java.util.Set;
import javax.validation.constraints.NotNull;

import java.util.Collection;
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
	 * Complexity: O(1) per vertex.
	 * @see {@link Graph#addVertices(Collection)}
	 */
	@Override
	public void addVertices( @NotNull Collection<V> vertices )
	{
		checkNotNull(vertices);
		// check
		for( V vertex : vertices )
		{
			checkNotNull(vertex);
			checkArgument(!this.map.containsKey(vertex), "Vertex already exists in graph: %s.", vertex);
		}
		
		// add
		for( V vertex : vertices )
		{
			this.map.put(vertex, new LinkedList<>());
		}
	}

	/**
	 * Complexity: O(|E|) per vertex.
	 * @see {@link Graph#removeVertices(Collection)}
	 */
	@Override
	public void removeVertices( @NotNull Collection<V> vertices )
	{
		checkNotNull(vertices);
		// check
		for( V vertex : vertices )
		{
			checkNotNull(vertex);
			checkArgument(this.map.containsKey(vertex), "Vertex does does not exist in the graph: %s.", vertex);
		}
		
		// remove
		for( V vertex : vertices )
		{
			this.map.remove(vertex);
			
			// remove associated edges
			// note: could remove in O(|V|) if used a set for storing edges with a trade off in memory for larger array
			// backing. Might be OK if sets are initialized with a size of a node's average edge count and the variability
			// of the edge count is low for the graph. Or if the maximum edge count count for each node is known on node
			// addition.
			for( V v : this.getVertices() )
			{
				Iterator<E> edgeIt = this.map.get(v).iterator();
				while( edgeIt.hasNext() )
				{
					E e = edgeIt.next();
					
					if( e.getSource().equals(vertex) || e.getDestination().equals(vertex) )
					{
						edgeIt.remove();
					}
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
	 * Complexity: O(1) per edge.
	 * @see {@link Graph#addEdges(Collection)}
	 */
	@Override
	public void addEdges( @NotNull Collection<E> edges )
	{
		checkNotNull(edges);
		// check
		for( E edge : edges )
		{
			checkNotNull(edge);
			checkArgument(this.map.containsKey(edge.getSource()),
			              "Edge source (%s) does not exist in the graph.",
			              edge.getSource());
			checkArgument(this.map.containsKey(edge.getDestination()),
			              "Edge destination (%s) does not exist in the graph.",
			              edge.getDestination());
			checkArgument(!this.map.get(edge.getSource()).contains(edge), 
			              "Edge (%s) already exists in the graph.",
			              edge);
		}
		
		// add
		for( E edge : edges )
		{
			this.map.get(edge.getSource()).add(edge);
		}
	}

	/**
	 * Complexity: O(|V|) per edge.
	 * @see {@link Graph#removeEdges(Collection)}
	 */
	@Override
	public void removeEdges( @NotNull Collection<E> edges )
	{
		checkNotNull(edges);
		// check
		for( E edge : edges )
		{
			checkNotNull(edge);
			checkArgument(this.map.containsKey(edge.getSource()),
			             "Edge source (%s) does not exist in the graph.",
			             edge.getSource());
			checkArgument(this.map.containsKey(edge.getDestination()),
			             "Edge destination (%s) does not exist in the graph.",
			             edge.getDestination());
			checkArgument(this.map.get(edge.getSource()).contains(edge), "Edge does not exist in the graph: %s.", edge);
		}
		
		// remove
		for( E edge : edges )
		{
			// note: complexity: remove iterates over edge list and a vertex can have at most |V| edges
			this.map.get(edge.getSource()).remove(edge);
		}
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

	/**
	 * Complexity: O(|V|) to iterate over list of edges at source vertex.
	 * See {@link Graph#getEdge(Vertex, Vertex)}.
	 */
	@Override
	public E getEdge( @NotNull V source, @NotNull V destination )
	{
		checkNotNull(source);
		checkNotNull(destination);
		checkArgument(this.map.containsKey(source), "Graph does not contain the edge source vertex.", source);
		checkArgument(this.map.containsKey(destination), "Graph does not contain the edge destination vertex.", destination);
		
		// look for edge from source
		for( E edge : this.map.get(source) )
		{
			if( edge.getDestination().equals(destination) )
			{
				return edge;
			}
		}
		
		// edge not found
		return null;
	}
}