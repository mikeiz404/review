package algorithms.graphs;

public class TestAdjacencyMatrixGraph extends TestGraph<AdjacencyMatrixGraph<Vertex, Edge<Vertex>>>
{
	public TestAdjacencyMatrixGraph( )
	{
		super(AdjacencyMatrixGraph::new);
	}
}
