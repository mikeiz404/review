package problems.crackingthecodinginterview.problem4_2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import datastructures.graphs.AdjacencyListGraph;
import datastructures.graphs.Edge;
import datastructures.graphs.Vertex;

public class TestGraphPathChecker
{
	@Test
	public void testPathSimple( )
	{
		AdjacencyListGraph<Vertex, Edge<Vertex>> g = new AdjacencyListGraph<>();
		
		Vertex a = new Vertex("A");
		Vertex b = new Vertex("B");
		
		g.addVertex(a);
		g.addVertex(b);
		
		g.addEdge(new Edge<Vertex>(a, b));
		
		assertTrue(GraphPathChecker.hasPath(g, a, b));
	}
	
	@Test
	public void testNoPathSimple( )
	{
		AdjacencyListGraph<Vertex, Edge<Vertex>> g = new AdjacencyListGraph<>();
		
		Vertex a = new Vertex("A");
		Vertex b = new Vertex("B");
		
		g.addVertex(a);
		g.addVertex(b);
		
		assertFalse(GraphPathChecker.hasPath(g, a, b));
	}
	
	@Test
	public void testPathComlex( )
	{
		AdjacencyListGraph<Vertex, Edge<Vertex>> g = new AdjacencyListGraph<>();
		
		Vertex a = new Vertex("A");
		Vertex b = new Vertex("B");
		Vertex c = new Vertex("C");
		Vertex d = new Vertex("D");
		Vertex e = new Vertex("E");
		Vertex f = new Vertex("F");
		
		g.addVertex(a);
		g.addVertex(b);
		g.addVertex(c);
		g.addVertex(d);
		g.addVertex(e);
		g.addVertex(f);
		
		g.addEdge(new Edge<Vertex>(a, c));
		g.addEdge(new Edge<Vertex>(c, c));
		g.addEdge(new Edge<Vertex>(c, a));
		g.addEdge(new Edge<Vertex>(c, d));
		g.addEdge(new Edge<Vertex>(d, e));
		g.addEdge(new Edge<Vertex>(d, a));
		g.addEdge(new Edge<Vertex>(f, a));
		g.addEdge(new Edge<Vertex>(b, f));
		g.addEdge(new Edge<Vertex>(e, b));
		
		assertTrue(GraphPathChecker.hasPath(g, a, b));
	}
	
	@Test
	public void testNoPathComplex( )
	{
		AdjacencyListGraph<Vertex, Edge<Vertex>> g = new AdjacencyListGraph<>();
		
		Vertex a = new Vertex("A");
		Vertex b = new Vertex("B");
		Vertex c = new Vertex("C");
		Vertex d = new Vertex("D");
		Vertex e = new Vertex("E");
		Vertex f = new Vertex("F");
		
		g.addVertex(a);
		g.addVertex(b);
		g.addVertex(c);
		g.addVertex(d);
		g.addVertex(e);
		g.addVertex(f);
		
		g.addEdge(new Edge<Vertex>(a, c));
		g.addEdge(new Edge<Vertex>(c, c));
		g.addEdge(new Edge<Vertex>(c, a));
		g.addEdge(new Edge<Vertex>(d, e));
		g.addEdge(new Edge<Vertex>(d, a));
		g.addEdge(new Edge<Vertex>(f, a));
		g.addEdge(new Edge<Vertex>(b, f));
		g.addEdge(new Edge<Vertex>(e, b));
		
		assertFalse(GraphPathChecker.hasPath(g, a, b));
	}
}
