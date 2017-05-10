package datastructures.graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import static com.google.common.base.Preconditions.checkNotNull;

public interface Graph<V extends Vertex, E extends Edge<V>>
{
	/**
	 * Adds a vertex to the graph. By default calls {@link #addVertices(Collection)}.
	 * @param vertex vertex to add.
	 */
	public default void addVertex( @NotNull V vertex )
	{
		ArrayList<V> vertices = new ArrayList<>(1);
		vertices.add(vertex);
		
		this.addVertices(vertices);
	}
	
	/**
	 * Adds a collection of vertices to the graph.
	 * @param vertices
	 */
	public void addVertices( @NotNull Collection<V> vertices );
	
	/**
	 * Removes a vertex from the graph and all associated edges from the graph. By default calls
	 * {@link #removeVertices(Collection)}.
	 * @param vertex vertex to remove.
	 */
	public default void removeVertex( @NotNull V vertex )
	{
		ArrayList<V> vertices = new ArrayList<>(1);
		vertices.add(vertex);
		
		this.removeVertices(vertices);
	}
	
	/**
	 * Removes a collection of vertices and all associated edges from the graph.
	 * @param vertices
	 */
	public void removeVertices( @NotNull Collection<V> vertices );
	
	/**
	 * Gets the vertices in the graph
	 * @return the set of vertices in the graph
	 */
	public @NotNull Set<V> getVertices( );
	
	/**
	 * Adds an edge to the graph. By default calls {@link #addEdges(Collection)}.
	 * @param edge edge to add.
	 */
	public default void addEdge( @NotNull E edge )
	{
		ArrayList<E> edges = new ArrayList<>(1);
		edges.add(edge);
		
		this.addEdges(edges);
	}
	
	/**
	 * Adds a collection of edges to the graph.
	 * @param edges edges to add.
	 */
	public void addEdges( @NotNull Collection<E> edges );
	
	/**
	 * Removes an edge from the graph. By default calls {@link #removeEdges(Collection)}.
	 * @param edge edge to remove.
	 */
	public default void removeEdge( @NotNull E edge )
	{
		ArrayList<E> edges = new ArrayList<>(1);
		edges.add(edge);
		
		this.removeEdges(edges);
	}
	
	/**
	 * Removes a collection of edges from the graph.
	 * @param edges edges to remove.
	 */
	public void removeEdges( @NotNull Collection<E> edges );
	
	/**
	 * Gets the edges coming from the given vertex.
	 * @param vertex
	 * @return the set of edges coming from vertex.
	 */
	public @NotNull Set<E> getEdges( @NotNull V vertex );
	
	/**
	 * Gets the neighboring vertices of the given vertex.
	 * @param vertex
	 * @return the set of vertices which have edges coming from vertex.
	 */
	public default @NotNull Set<V> getNeighbors( @NotNull V vertex )
	{
		checkNotNull(vertex);
		
		HashSet<V> neighbors = new HashSet<>();
		
		for( E edge : this.getEdges(vertex) )
		{
			neighbors.add(edge.getDestination());
		}
		
		return neighbors;
	}
	
	/**
	 * Gets the edge between source and destination.
	 * @param source vertex edge is coming from.
	 * @param destination vertex edge is going to.
	 * @return edge between source and destination vertices if one exists, otherwise null.
	 */
	public E getEdge( @NotNull V source, @NotNull V destination );
	
	/**
	 * Determines if there is an edge between source and destination vertices. By default calls
	 * {@link #getEdge(Vertex, Vertex)}.
	 * @param source vertex edge is coming from.
	 * @param destination vertex edge is going to.
	 * @return true if an edge exists, false otherwise.
	 */
	public default boolean hasEdge( @NotNull V source, @NotNull V destination )
	{
		return this.getEdge(source, destination) != null;
	}
}