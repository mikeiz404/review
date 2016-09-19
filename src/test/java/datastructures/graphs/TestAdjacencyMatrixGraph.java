package datastructures.graphs;

import datastructures.graphs.AdjacencyMatrixGraph;
import datastructures.graphs.Edge;
import datastructures.graphs.Vertex;

public class TestAdjacencyMatrixGraph extends TestGraph<AdjacencyMatrixGraph<Vertex, Edge<Vertex>>>
{
	public TestAdjacencyMatrixGraph( )
	{
		super(AdjacencyMatrixGraph::new);
	}
}
