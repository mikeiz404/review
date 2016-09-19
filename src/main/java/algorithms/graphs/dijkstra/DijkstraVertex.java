package algorithms.graphs.dijkstra;

import javax.validation.constraints.NotNull;

import datastructures.graphs.MarkedVertex;

public class DijkstraVertex extends MarkedVertex<VertexMark>
{
	public DijkstraVertex( @NotNull String name )
	{
		super(name, VertexMark.class);
	}

}
