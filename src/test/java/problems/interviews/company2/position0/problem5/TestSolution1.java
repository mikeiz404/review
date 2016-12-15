package problems.interviews.company2.position0.problem5;

import org.junit.Test;


public class TestSolution1
{
	@Test(expected=IllegalArgumentException.class)
	public void testDirectCycle( )
	{
		Solution1 graph = new Solution1();
		graph.addNode(1);
		graph.addEdge(1, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIndirectCycle( )
	{
		Solution1 graph = new Solution1();
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		graph.addEdge(1, 5);
		graph.addEdge(1, 2);
		graph.addEdge(5, 4);
		graph.addEdge(4, 3);
		graph.addEdge(3, 1);
	}
	
	@Test
	public void test( )
	{
		Solution1 graph = new Solution1();
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		graph.addEdge(1, 5);
		graph.addEdge(1, 2);
		graph.addEdge(5, 4);
		graph.addEdge(4, 3);
	}
}
