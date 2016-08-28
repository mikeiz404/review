package algorithms.graphs;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.validation.constraints.NotNull;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

/**
 * Represents a path to a given vertex.
 * @param <V> vertex type
 * @param <E> edge type
 */
public class Path<V extends Vertex, E extends Edge<V>>
{
	private LinkedList<V> vertices;
	private V destination;
	
	/**
	 * Create an empty path with a destination vertex.
	 * @param destination destination vertex
	 */
	public Path( @NotNull V destination )
	{
		checkNotNull(destination);
		
		this.destination = destination;
		this.vertices = new LinkedList<>();
	}
	
	/**
	 * Creates a copy of an existing path.
	 * @param path path to copy
	 */
	public Path( @NotNull Path<V, E> path )
	{
		checkNotNull(path);
		
		this.vertices = new LinkedList<>(path.getVertices());
		this.destination = path.getDestination();
	}
	
	/**
	 * Replaces the current path with another path. Useful for updating a path in place.
	 * @param path path to copy from
	 */
	public void update( @NotNull Path<V, E> path )
	{
		checkNotNull(path);
		
		this.destination = path.getDestination();
		this.vertices = new LinkedList<>(path.getVertices());
	}
	
	/**
	 * Get the path vertices to the destination vertex.
	 * @return path vertices
	 */
	public @NotNull List<V> getVertices( )
	{
		return Collections.unmodifiableList(vertices);
	}
	
	/**
	 * Appends an edge to the existing path by adding the current destination vertex onto the path list and setting the
	 * edge's destination as the new destination vertex.
	 * @param edge contains the destination vertex the path will be updated with. The edge must come from the current
	 * destination vertex.
	 */
	public void addEdge( @NotNull E edge )
	{
		checkNotNull(edge);
		checkArgument(edge.getSource().equals(this.getDestination()),
				"Edge (%s) must originate from the path's destination vertex (%s).", edge, this.getDestination());
		
		// add previous destination to path list
		vertices.add(this.getDestination());
		
		// update destination
		this.destination = edge.getDestination();;
	}
	
	/**
	 * Gets the current destination vertex.
	 * @return current destination vertex.
	 */
	public @NotNull V getDestination( )
	{
		return this.destination;
	}
	
	@Override
	public boolean equals( Object o )
	{
		if( o instanceof Path )
		{
			Path<?, ?> path = (Path<?, ?>) o;
			return path.getVertices().equals(this.getVertices()) &&
			       path.getDestination().equals(this.getDestination());
		}
		else
		{
			return false;
		}
	} 
	
	@Override
	public @NotNull String toString( )
	{
		return String.format("<Path to=%s %s>", getDestination(), getVertices());
	}
}
