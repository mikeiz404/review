package algorithms.graphs.dijkstra;

import javax.validation.constraints.NotNull;
import algorithms.graphs.CostEdge;

public class DijkstraEdge extends CostEdge<DijkstraVertex>
{

	public DijkstraEdge( @NotNull DijkstraVertex source, @NotNull DijkstraVertex destination, int cost )
	{
		super(source, destination, cost);
	}

}
