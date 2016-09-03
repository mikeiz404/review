package algorithms.graphs;

public class TestAdjacencyListGraph extends TestGraph<AdjacencyListGraph<Vertex, Edge<Vertex>>>
{
	public TestAdjacencyListGraph( )
	{
		super(AdjacencyListGraph::new);
	}
}
