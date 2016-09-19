package datastructures.graphs;

import java.util.EnumMap;
import javax.validation.constraints.NotNull;
import static com.google.common.base.Preconditions.checkNotNull;

public class MarkedVertex<M extends Enum<M>> extends Vertex implements MarkedVertexInterface<M>
{
	private final EnumMap<M, Boolean> marks;
	
	public MarkedVertex( String name, Class<M> markEnum )
	{
		super(name);
		
		this.marks = new EnumMap<>(markEnum);
	}

	/**
	 * Sets marked to true.
	 * @param mark
	 */
	public void mark( @NotNull M mark )
	{
		checkNotNull(mark);
		
		this.marks.put(mark, true);
	}
	
	/**
	 * Sets marked to false.
	 * @param mark
	 */
	public void unmark( @NotNull M mark )
	{
		checkNotNull(mark);
		
		this.marks.put(mark, false);
	}
	
	/**
	 * Return true if marked, false otherwise.
	 * @param mark
	 * @return true if marked, false otherwise.
	 */
	public boolean isMarked( @NotNull M mark )
	{
		return this.marks.getOrDefault(mark, false);
	}
}
