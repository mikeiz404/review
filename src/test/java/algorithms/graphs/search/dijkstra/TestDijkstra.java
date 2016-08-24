package algorithms.graphs.search.dijkstra;

import org.junit.Assert;
import org.junit.Test;

import algorithms.graphs.search.dijkstra.Dijkstra;
import algorithms.graphs.search.dijkstra.NoPathException;
import algorithms.graphs.search.dijkstra.Node;
import algorithms.graphs.search.dijkstra.Path;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestDijkstra
{	
	private static interface Graph
	{
		public Map<Node, ? extends Map<Node, Path>> getPaths( );
	}
	
	private static class Graph1 implements Graph
	{
		Node a = new Node("a");
		Node b = new Node("b");
		Node c = new Node("c");
		Node d = new Node("d");
		Node e = new Node("e");
		Node unconnected = new Node("unconnected");
		
		HashMap<Node, HashMap<Node, Path>> paths = new HashMap<>();
		{
			// connect
			a.addEdge(b, 8);
			a.addEdge(c, 2);
			a.addEdge(e, 12);
			
			b.addEdge(d, 1);
			b.addEdge(e, 1);
			
			c.addEdge(d, 2);
			
			d.addEdge(e, 1);
			
			// solutions
			HashMap<Node, Path> pathsA = new HashMap<>();
			paths.put(a, pathsA);
			
			pathsA.put(a, makePath(a, 0, makeNodes(new String[]{})));
			pathsA.put(b, makePath(b, 8, makeNodes(new String[]{"a"})));
			pathsA.put(c, makePath(c, 2, makeNodes(new String[]{"a"})));
			pathsA.put(d, makePath(d, 4, makeNodes(new String[]{"a", "c"})));
			pathsA.put(e, makePath(e, 5, makeNodes(new String[]{"a", "c", "d"})));	
		}

		@Override
		public Map<Node, ? extends Map<Node, Path>> getPaths( )
		{
			return paths;
		}
	}
	
	private static class Graph2 implements Graph
	{
		Node a = new Node("a");
		Node b = new Node("b");
		Node c = new Node("c");
		Node d = new Node("d");
		
		HashMap<Node, HashMap<Node, Path>> paths = new HashMap<>();
		{
			// connect
			a.addEdge(b, 10);
			a.addEdge(c, 2);
			
			b.addEdge(a, 10);
			b.addEdge(c, 1);
			b.addEdge(d, 1);
			
			c.addEdge(a, 2);
			c.addEdge(b, 1);
			c.addEdge(d, 10);
			
			d.addEdge(b, 1);
			d.addEdge(c, 10);
			
			// solutions
			HashMap<Node, Path> pathsA = new HashMap<>();
			paths.put(a, pathsA);
			
			pathsA.put(a, makePath(a, 0, makeNodes(new String[]{})));
			pathsA.put(b, makePath(b, 3, makeNodes(new String[]{"a", "c"})));
			pathsA.put(c, makePath(c, 2, makeNodes(new String[]{"a"})));
			pathsA.put(d, makePath(d, 4, makeNodes(new String[]{"a", "c", "b"})));
		}

		@Override
		public Map<Node, ? extends Map<Node, Path>> getPaths( )
		{
			return paths;
		}
	}
	
	@Test
	public void testSssdsp( )
	{
		Graph1 graph = new Graph1();
		
		try
		{
			Path path = Dijkstra.computeSssdsp(graph.a, graph.e);
			
			assertEquals(path, graph.paths.get(graph.a).get(graph.e));
		}
		catch( NoPathException e1 )
		{
			Assert.fail("NoPathException: " + e1);
		}
	}
	
	@Test
	public void testSssdspNoPath( )
	{
		Graph1 graph = new Graph1();
		try
		{
			Dijkstra.computeSssdsp(graph.a, graph.unconnected);
			Assert.fail("Expected NoPathException");
		}
		catch( NoPathException e1 )
		{
			
		}
	}
	
	@Test
	public void testSsspGraph1( )
	{
		testSssp(new Graph1());
	}
	
	@Test
	public void testSsspGraph2( )
	{
		testSssp(new Graph2());
	}
	
	public void testSssp( Graph graph )
	{
		for( Node from : graph.getPaths().keySet() )
		{
			Map<Node, Path> expectedPaths = graph.getPaths().get(from);
						
			// compute
			HashMap<Node, Path> actualPaths = Dijkstra.computeSssp(from);
						
			for( Node to : expectedPaths.keySet() )
			{				
				Path expectedPath = expectedPaths.get(to);
				Path actualPath = actualPaths.get(to);
				
				assertEquals("Path from " + from + " to " + to + " does not match", actualPath, expectedPath);
			}
			
			assertEquals(actualPaths.size(), expectedPaths.size());
		}
	}
	
	
	/* utility methods */
	
	private static Path makePath( Node end, int cost, Node[] nodes )
	{
		MockPath path = new MockPath();
		path.setCost(cost);
		path.setNodes(Arrays.asList(nodes));
		path.setDestination(end);
		
		return path;
	}
	
	private static Node[] makeNodes( String[] names )
	{
		Node[] nodes = new Node[names.length];
		
		for( int i=0; i<names.length; i++ )
		{
			nodes[i] = new Node(names[i]);
		}
		
		return nodes;
	}

}
