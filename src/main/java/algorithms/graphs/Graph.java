package algorithms.graphs;

import java.util.Set;

import javax.validation.constraints.NotNull;

public interface Graph<V extends Vertex, E extends Edge<V>>
{
	/**
	 * Adds a vertex to the graph.
	 * @param vertex vertex to add
	 */
	public void addVertex( @NotNull V vertex );
	
	/**
	 * Removes a vertex and all edges associated edges from the graph.
	 * @param vertex vertex to remove
	 */
	public void removeVertex( @NotNull V vertex );
	
	/**
	 * Gets the vertices in the graph
	 * @return the set of vertices in the graph
	 */
	public @NotNull Set<V> getVertices( );
	
	/**
	 * Adds an edge to the graph.
	 * @param edge edge to add
	 */
	public void addEdge( @NotNull E edge );
	
	/**
	 * Removes an edge from the graph.
	 * @param edge edge to remove
	 */
	public void removeEdge( @NotNull E edge );
	
	/**
	 * Gets the edges coming from the given vertex.
	 * @param vertex
	 * @return the set of vertices coming from vertex.
	 */
	public @NotNull Set<E> getEdges( @NotNull V vertex );
}