package algorithms.graphs;

import javax.validation.constraints.NotNull;
import static com.google.common.base.Preconditions.checkNotNull;

public class Vertex
{
	private final String name;
	
	/**
	 * Creates a new vertex with a name.
	 * @param name name of vertex.
	 */
	public Vertex( @NotNull String name )
	{
		this.name = checkNotNull(name);
	}
	
	/**
	 * Gets the name of the vertex
	 * @return name of vertex
	 */
	public @NotNull String getName( )
	{
		return this.name;
	}
	
	@Override
	public boolean equals( @NotNull Object o )
	{
		checkNotNull(o);
		
		if( o instanceof Vertex )
		{
			Vertex v = (Vertex) o;
			
			return this.getName().equals(v.getName());
		}
		else // ! o instanceof Vertex
		{
			return false;
		}
	}
	
	@Override
	public @NotNull String toString( )
	{
		return String.format("<Vertex '%s'>", this.getName());
	}
}