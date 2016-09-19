package datastructures.graphs;

import javax.validation.constraints.NotNull;
import static com.google.common.base.Preconditions.checkNotNull;

public class Edge<V extends Vertex>
{
	private final V source;
	private final V destination;
	
	/**
	 * Creates an edge with source and destination vertices.
	 * @param source the start vertex. Not null.
	 * @param destination the end vertex. Not null.
	 */
	public Edge( @NotNull V source, @NotNull V destination )
	{
		this.source = checkNotNull(source);
		this.destination = checkNotNull(destination);
	}
	
	/**
	 * Gets the source node.
	 * @return source node.
	 */
	public @NotNull V getSource( )
	{
		return this.source;
	}
	
	/**
	 * Gets the destination node.
	 * @return destination node.
	 */
	public @NotNull V getDestination( )
	{
		return this.destination;
	}
	
	@Override
	public @NotNull String toString( )
	{
		return String.format("<Edge %s->%s>", this.getSource(), this.getDestination());
	}
}