package algorithms.graphs.dijkstra;

import static com.google.common.base.Preconditions.checkArgument;
import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import datastructures.graphs.AdjacencyListGraph;
import datastructures.graphs.CostPath;
import datastructures.graphs.Graph;
import datastructures.graphs.NoPathException;
import datastructures.graphs.Vertex;

public class TestDijkstra
{	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestDijkstra.class);
	private static interface GraphSolution
	{
		public Graph<DijkstraVertex, DijkstraEdge> getGraph( );
		public HashMap<DijkstraVertex, HashMap<DijkstraVertex, CostPath<DijkstraVertex, DijkstraEdge>>> getPaths( );
	}
	
	private static class GraphSolution1 implements GraphSolution
	{
		DijkstraVertex a = new DijkstraVertex("a");
		DijkstraVertex b = new DijkstraVertex("b");
		DijkstraVertex c = new DijkstraVertex("c");
		DijkstraVertex d = new DijkstraVertex("d");
		DijkstraVertex e = new DijkstraVertex("e");
		DijkstraVertex unconnected = new DijkstraVertex("unconnected");
		
		Graph<DijkstraVertex, DijkstraEdge> graph = new AdjacencyListGraph<>();
		{
			graph.addVertex(a);
			graph.addVertex(b);
			graph.addVertex(c);
			graph.addVertex(d);
			graph.addVertex(e);
			
			graph.addEdge(new DijkstraEdge(a, b, 8));
			graph.addEdge(new DijkstraEdge(a, c, 2));
			graph.addEdge(new DijkstraEdge(a, e, 12));
			
			graph.addEdge(new DijkstraEdge(b, d, 1));
			graph.addEdge(new DijkstraEdge(b, e, 1));
			
			graph.addEdge(new DijkstraEdge(c, d, 2));
			
			graph.addEdge(new DijkstraEdge(d, e, 1));
		}
		
		HashMap<DijkstraVertex, HashMap<DijkstraVertex, CostPath<DijkstraVertex, DijkstraEdge>>> paths = new HashMap<>();
		{
			HashMap<DijkstraVertex, CostPath<DijkstraVertex, DijkstraEdge>> pathsA = new HashMap<>();
			paths.put(a, pathsA);
			
			pathsA.put(a, makePath(new Object[]{a}));
			pathsA.put(b, makePath(new Object[]{a, 8, b}));
			pathsA.put(c, makePath(new Object[]{a, 2, c}));
			pathsA.put(d, makePath(new Object[]{a, 2, c, 2, d}));
			pathsA.put(e, makePath(new Object[]{a, 2, c, 2, d, 1, e}));	
		}
		
		@Override
		public Graph<DijkstraVertex, DijkstraEdge> getGraph( )
		{
			return graph;
		}
		
		@Override
		public HashMap<DijkstraVertex, HashMap<DijkstraVertex, CostPath<DijkstraVertex, DijkstraEdge>>> getPaths( )
		{
			return paths;
		}
	}
	
	private static class GraphSolution2 implements GraphSolution
	{
		DijkstraVertex a = new DijkstraVertex("a");
		DijkstraVertex b = new DijkstraVertex("b");
		DijkstraVertex c = new DijkstraVertex("c");
		DijkstraVertex d = new DijkstraVertex("d");
		
		Graph<DijkstraVertex, DijkstraEdge> graph = new AdjacencyListGraph<>();
		{
			graph.addVertex(a);
			graph.addVertex(b);
			graph.addVertex(c);
			graph.addVertex(d);
			
			graph.addEdge(new DijkstraEdge(a, b, 10));
			graph.addEdge(new DijkstraEdge(a, c, 2));
			
			graph.addEdge(new DijkstraEdge(b, a, 10));
			graph.addEdge(new DijkstraEdge(b, c, 1));
			graph.addEdge(new DijkstraEdge(b, d, 1));
			
			graph.addEdge(new DijkstraEdge(c, a, 2));
			graph.addEdge(new DijkstraEdge(c, b, 1));
			graph.addEdge(new DijkstraEdge(c, d, 10));
			
			graph.addEdge(new DijkstraEdge(d, b, 1));
			graph.addEdge(new DijkstraEdge(d, c, 10));
		}
		
		HashMap<DijkstraVertex, HashMap<DijkstraVertex, CostPath<DijkstraVertex, DijkstraEdge>>> paths = new HashMap<>();
		{
			HashMap<DijkstraVertex, CostPath<DijkstraVertex, DijkstraEdge>> pathsA = new HashMap<>();
			paths.put(a, pathsA);
			
			pathsA.put(a, makePath(new Object[]{a}));
			pathsA.put(b, makePath(new Object[]{a, 2, c, 1, b}));
			pathsA.put(c, makePath(new Object[]{a, 2, c}));
			pathsA.put(d, makePath(new Object[]{a, 2, c, 1, b, 1, d}));
		}
		
		@Override
		public Graph<DijkstraVertex, DijkstraEdge> getGraph( )
		{
			return graph;
		}
		
		@Override
		public HashMap<DijkstraVertex, HashMap<DijkstraVertex, CostPath<DijkstraVertex, DijkstraEdge>>> getPaths( )
		{
			return paths;
		}
	}
	
	@Test
	public void testComputeSingleDestination( )
	{
		LOGGER.debug("Running testComputeSingleDestination.");
		
		GraphSolution1 solution = new GraphSolution1();
		
		try
		{
			CostPath<DijkstraVertex, DijkstraEdge> path = Dijkstra.computeSingleDestination(solution.getGraph(), solution.a, solution.e);
			
			assertEquals(path, solution.paths.get(solution.a).get(solution.e));
		}
		catch( NoPathException e )
		{
			Assert.fail("NoPathException: " + e);
		}
	}
	
	@Test
	public void testComputeSingleDestinationNoPath( )
	{
		LOGGER.debug("Running testComputeSingleDestinationNoPath.");
		
		GraphSolution1 solution = new GraphSolution1();
		try
		{
			Dijkstra.computeSingleDestination(solution.getGraph(), solution.a, solution.unconnected);
			Assert.fail("Expected NoPathException");
		}
		catch( NoPathException e1 )
		{
			// pass
		}
	}
	
	@Test
	public void testComputeGraph1( )
	{
		LOGGER.debug("Running testComputeGraph1");
		
		testComputeGraph(new GraphSolution1());
	}
	
	@Test
	public void testComputeGraph2( )
	{
		LOGGER.debug("Running testComputeGraph2");
		
		testComputeGraph(new GraphSolution2());
	}
	
	public void testComputeGraph( GraphSolution solution )
	{
		for( DijkstraVertex from : solution.getPaths().keySet() )
		{
			HashMap<DijkstraVertex, CostPath<DijkstraVertex, DijkstraEdge>> expectedPaths = solution.getPaths().get(from);
						
			// compute
			HashMap<DijkstraVertex, CostPath<DijkstraVertex, DijkstraEdge>> actualPaths = Dijkstra.compute(solution.getGraph(), from);
						
			for( Vertex to : expectedPaths.keySet() )
			{				
				CostPath<DijkstraVertex, DijkstraEdge> expectedPath = expectedPaths.get(to);
				CostPath<DijkstraVertex, DijkstraEdge> actualPath = actualPaths.get(to);
				
				assertEquals("Path from " + from + " to " + to + " does not match", actualPath, expectedPath);
			}
			
			assertEquals(actualPaths.size(), expectedPaths.size());
		}
	}
	
		
	/**
	 * Utility method to create a path from an object array in the format of [Vertex1, EdgeCost1To2, Vertex2,
	 * EdgeCost2To3, Vertex3, ...]. The final vertex in the array is used as the destination vertex.
	 * @param solution object array of vertices and integers with a length greater than 0.
	 * @return path created from vertices and weights in the object array.
	 */
	private static CostPath<DijkstraVertex, DijkstraEdge> makePath( Object[] solution )
	{
		checkArgument(solution.length > 0, "Solution array must have a length (%d) greater than 0.", solution.length);
		
		CostPath<DijkstraVertex, DijkstraEdge> path = new CostPath<>((DijkstraVertex) solution[0]);
		if( solution.length > 1 )
		{
			for( int i=0; i<solution.length - 2; i+= 2 )
			{
				DijkstraVertex from = (DijkstraVertex) solution[i];
				int cost = (Integer) solution[i + 1];
				DijkstraVertex to = (DijkstraVertex) solution[i + 2];
				
				path.addEdge(new DijkstraEdge(from, to, cost));
			}
		}
		
		return path;
	}
}
