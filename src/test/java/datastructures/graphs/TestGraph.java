package datastructures.graphs;

import org.junit.Test;

import datastructures.graphs.Edge;
import datastructures.graphs.Graph;
import datastructures.graphs.Vertex;

import org.junit.Assert;
import org.junit.Ignore;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import javax.validation.constraints.NotNull;

/**
 * Base class for testing graphs.
 */
@Ignore
public abstract class TestGraph<G extends Graph<Vertex, Edge<Vertex>>>
{
	public static interface GraphFactory<G>
	{
		public G make( );
	}
	
	private GraphFactory<G> factory;
	
	public TestGraph( @NotNull GraphFactory<G> factory )
	{
		this.factory = checkNotNull(factory);
	}
	
	public @NotNull G makeGraph( )
	{
		return this.factory.make();
	}
	
	@Test
	public void TestAddVertex( )
	{
		Graph<Vertex, Edge<Vertex>> graph = this.makeGraph();
		Vertex vertex = new Vertex("a");
		
		graph.addVertex(vertex);
		
		Set<Vertex> vertices = graph.getVertices();
		Assert.assertTrue(vertices.contains(vertex));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void TestAddVertexAlreadyExists( )
	{
		Graph<Vertex, Edge<Vertex>> graph = this.makeGraph();
		Vertex vertex = new Vertex("a");
		
		graph.addVertex(vertex);
		graph.addVertex(vertex);
	}
	
	@Test
	public void TestRemoveVertex( )
	{
		Graph<Vertex, Edge<Vertex>> graph = this.makeGraph();
		Vertex vertex = new Vertex("a");
		
		graph.addVertex(vertex);
		
		graph.removeVertex(vertex);
		
		Set<Vertex> vertices = graph.getVertices();
		Assert.assertEquals(0, vertices.size());
	}
	
	@Test
	public void TestRemoveDestinationVertexWithEdge( )
	{
		Graph<Vertex, Edge<Vertex>> graph = this.makeGraph();
		Vertex from = new Vertex("from");
		Vertex to = new Vertex("to");
		Edge<Vertex> edge = new Edge<>(from, to);
		
		graph.addVertex(from);
		graph.addVertex(to);
		graph.addEdge(edge);
		
		graph.removeVertex(to);
		
		Set<Edge<Vertex>> edges = graph.getEdges(from);
		Assert.assertEquals(0, edges.size());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void TestRemoveVertexDoesNotExist( )
	{
		Graph<Vertex, Edge<Vertex>> graph = this.makeGraph();
		Vertex vertex = new Vertex("a");
		
		graph.removeVertex(vertex);
	}
	
	@Test
	public void TestAddEdge( )
	{
		Graph<Vertex, Edge<Vertex>> graph = this.makeGraph();
		Vertex from = new Vertex("from");
		Vertex to = new Vertex("to");
		Edge<Vertex> edge = new Edge<>(from, to);
		
		graph.addVertex(from);
		graph.addVertex(to);
		
		graph.addEdge(edge);
		
		Set<Edge<Vertex>> edges = graph.getEdges(from);
		Assert.assertTrue(edges.contains(edge));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void TestAddEdgeSourceDoesNotExist( )
	{
		Graph<Vertex, Edge<Vertex>> graph = this.makeGraph();
		Vertex from = new Vertex("from");
		Vertex to = new Vertex("to");
		Edge<Vertex> edge = new Edge<>(from, to);
		
		graph.addVertex(to);
		
		graph.addEdge(edge);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void TestAddEdgeDestinationDoesNotExist( )
	{
		Graph<Vertex, Edge<Vertex>> graph = this.makeGraph();
		Vertex from = new Vertex("from");
		Vertex to = new Vertex("to");
		Edge<Vertex> edge = new Edge<>(from, to);
		
		graph.addVertex(from);
		
		graph.addEdge(edge);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void TestAddEdgeAlreadyExists( )
	{
		Graph<Vertex, Edge<Vertex>> graph = this.makeGraph();
		Vertex from = new Vertex("from");
		Vertex to = new Vertex("to");
		Edge<Vertex> edge = new Edge<>(from, to);
		
		graph.addVertex(from);
		graph.addVertex(to);
		
		graph.addEdge(edge);
		graph.addEdge(edge);
	}
	
	@Test
	public void TestRemoveEdge( )
	{
		Graph<Vertex, Edge<Vertex>> graph = this.makeGraph();
		Vertex from = new Vertex("from");
		Vertex to = new Vertex("to");
		Edge<Vertex> edge = new Edge<>(from, to);
		
		graph.addVertex(from);
		graph.addVertex(to);
		graph.addEdge(edge);
		
		graph.removeEdge(edge);
		
		Set<Edge<Vertex>> edges = graph.getEdges(from);
		Assert.assertEquals(0, edges.size());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void TestRemoveEdgeDoesNotExist( )
	{
		Graph<Vertex, Edge<Vertex>> graph = this.makeGraph();
		Vertex from = new Vertex("from");
		Vertex to = new Vertex("to");
		Edge<Vertex> edge = new Edge<>(from, to);
		
		graph.addVertex(from);
		graph.addVertex(to);
		graph.addEdge(edge);
		
		graph.removeEdge(edge);
		graph.removeEdge(edge);
	}
	
	@Test
	public void TestGetVertices( )
	{
		Graph<Vertex, Edge<Vertex>> graph = this.makeGraph();
		Vertex a = new Vertex("a");
		Vertex b = new Vertex("b");
		Vertex c = new Vertex("c");
		
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addVertex(c);
		graph.removeVertex(b);
		
		Set<Vertex> vertices = graph.getVertices();
		Assert.assertTrue(vertices.contains(a));
		Assert.assertTrue(vertices.contains(c));
		Assert.assertFalse(vertices.contains(b));
	}
	
	@Test
	public void TestGetEdges( )
	{
		Graph<Vertex, Edge<Vertex>> graph = this.makeGraph();
		Vertex a = new Vertex("a");
		Vertex b = new Vertex("b");
		Vertex c = new Vertex("c");
		
		Edge<Vertex> a2b = new Edge<>(a, b);
		Edge<Vertex> a2c = new Edge<>(a, c);
		Edge<Vertex> b2c = new Edge<>(b, c);
		Edge<Vertex> c2a = new Edge<>(c, a);
		
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addVertex(c);
		graph.addEdge(a2b);
		graph.addEdge(a2c);
		graph.addEdge(b2c);
		graph.addEdge(c2a);
		graph.removeEdge(b2c);
		
		Set<Edge<Vertex>> aEdges = graph.getEdges(a);
		Assert.assertEquals(2, aEdges.size());
		Assert.assertTrue(aEdges.contains(a2b));
		Assert.assertTrue(aEdges.contains(a2c));
		
		Set<Edge<Vertex>> bEdges = graph.getEdges(b);
		Assert.assertEquals(0, bEdges.size());
		
		Set<Edge<Vertex>> cEdges = graph.getEdges(c);
		Assert.assertEquals(1, cEdges.size());
		Assert.assertTrue(cEdges.contains(c2a));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void TestGetEdgesVertexDoesNotExist( )
	{
		Graph<Vertex, Edge<Vertex>> graph = this.makeGraph();
		Vertex vertex = new Vertex("a");
		
		graph.getEdges(vertex);
	}
	
	@Test
	public void TestGetEdge( )
	{
		Graph<Vertex, Edge<Vertex>> graph = this.makeGraph();
		Vertex from = new Vertex("from");
		Vertex to = new Vertex("to");
		Edge<Vertex> edge = new Edge<>(from, to);
		
		graph.addVertex(from);
		graph.addVertex(to);
		graph.addEdge(edge);
		
		Assert.assertEquals(edge, graph.getEdge(from, to));
	}
	
	@Test
	public void TestGetEdgeMissing( )
	{
		Graph<Vertex, Edge<Vertex>> graph = this.makeGraph();
		Vertex from = new Vertex("from");
		Vertex to = new Vertex("to");
		
		graph.addVertex(from);
		graph.addVertex(to);
		
		Assert.assertEquals(null, graph.getEdge(from, to));
	}
	
	@Test
	public void TestHasEdge( )
	{
		Graph<Vertex, Edge<Vertex>> graph = this.makeGraph();
		Vertex from = new Vertex("from");
		Vertex to = new Vertex("to");
		Edge<Vertex> edge = new Edge<>(from, to);
		
		graph.addVertex(from);
		graph.addVertex(to);
		graph.addEdge(edge);
		
		Assert.assertTrue(graph.hasEdge(from, to));
	}
	
	@Test
	public void TestHasEdgeMissing( )
	{
		Graph<Vertex, Edge<Vertex>> graph = this.makeGraph();
		Vertex from = new Vertex("from");
		Vertex to = new Vertex("to");
		
		graph.addVertex(from);
		graph.addVertex(to);
		
		Assert.assertFalse(graph.hasEdge(from, to));
	}
	
}
