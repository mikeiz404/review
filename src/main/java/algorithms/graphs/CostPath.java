package algorithms.graphs;

import javax.validation.constraints.NotNull;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Represents a path to a vertex and stores the total path cost.
 *
 * @param <V> vertex type
 * @param <E> edge type
 */
public class CostPath<V extends Vertex, E extends CostEdge<V>> extends Path<V, E> implements Comparable<CostPath<V, E>>
{
	private int cost;
	
	/**
	 * See {@link algorithms.graphs.Path#Path(algorithms.graphs.Path)}. Sets cost to zero.
	 * @param destination
	 */
	public CostPath( @NotNull V destination )
	{
		super(destination);
		this.cost = 0;
	}
	
	/**
	 * See {@link algorithms.graphs.Path#Path(Vertex)}. Sets cost to path cost.
	 * @param path
	 */
	public CostPath( @NotNull CostPath<V, E> path )
	{		
		super(path);
		this.cost = path.getCost();
	}
	
	/**
	 * Unsupported.
	 */
	@Override
	public void update( Path<V, E> path )
	{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * See {@link algorithms.graphs.Path#update(algorithms.graphs.Path). Sets cost to path cost.
	 */
	public void update( @NotNull CostPath<V, E> path )
	{
		super.update(path);
		this.cost = path.getCost();
	}
	
	/**
	 * See {@link algorithms.graphs.Path#addEdge(Edge). Increments the path cost by the edge cost.
	 */
	@Override
	public void addEdge( @NotNull E edge )
	{
		super.addEdge(edge);
		
		this.cost += edge.getCost();
	}

	/**
	 * Gets sum of path cost for all added edges.
	 * @return path cost.
	 */
	public int getCost( )
	{
		return this.cost;
	}

	@Override
	public boolean equals( Object o )
	{
		if( o instanceof CostPath )
		{
			CostPath<?, ?> path = (CostPath<?, ?>) o;
			return super.equals(path) &&
			       path.getCost() == path.getCost();
		}
		else // ! o instanceof Path
		{
			return false;
		}
	} 

	@Override
	public int compareTo( @NotNull CostPath<V, E> path )
	{
		checkNotNull(path);
		
		return this.getCost() - path.getCost();
	}
	
	@Override
	public String toString( )
	{
		return String.format("<Path to=%s cost=%d %s>", getDestination(), getCost(), getVertices());
	}
	
}
