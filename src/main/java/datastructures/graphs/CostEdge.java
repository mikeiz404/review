package datastructures.graphs;

import javax.validation.constraints.NotNull;

public class CostEdge<V extends datastructures.graphs.Vertex> extends datastructures.graphs.Edge<V> implements CostEdgeInterface
{
	private final int cost;
	
	/**
	 * Create am edge with source and destination vertices, and a cost.
	 * @param source vertex edge is coming from.
	 * @param destination vertex edge is going to.
	 * @param cost cost of vertex.
	 */
	public CostEdge( @NotNull V source, @NotNull V destination, int cost )
	{
		super(source, destination);
		
		this.cost = cost;
	}

	/**
	 * Get the edge cost.
	 * @return edge cost.
	 */
	public int getCost( )
	{
		return this.cost;
	}
	
	@Override
	public String toString( )
	{
		return String.format("<Edge (%s, %s) cost=%d>", this.getSource(), this.getDestination(), cost);
	}
}
