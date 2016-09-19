package datastructures.graphs;

import javax.validation.constraints.NotNull;

public interface MarkedVertexInterface<M extends Enum<M>>
{
	/**
	 * Sets marked to true.
	 * @param mark
	 */
	public void mark( @NotNull M mark );
	
	/**
	 * Sets marked to false.
	 * @param mark
	 */
	public void unmark( @NotNull M mark );
	
	/**
	 * Return true if marked, false otherwise.
	 * @param mark
	 * @return true if marked, false otherwise.
	 */
	public boolean isMarked( @NotNull M mark );
}
