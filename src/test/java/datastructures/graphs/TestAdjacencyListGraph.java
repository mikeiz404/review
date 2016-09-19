package datastructures.graphs;

import datastructures.graphs.AdjacencyListGraph;
import datastructures.graphs.Edge;
import datastructures.graphs.Vertex;

public class TestAdjacencyListGraph extends TestGraph<AdjacencyListGraph<Vertex, Edge<Vertex>>>
{
	public TestAdjacencyListGraph( )
	{
		super(AdjacencyListGraph::new);
	}
}
