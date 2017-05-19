package problems.crackingthecodinginterview.problem4_7;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import datastructures.trees.binarytree.Node;

public abstract class TestCommonSuccessorLocator
{
	public abstract TestInterface getInstance( );
	
	@Test
	public void testBothRootNode( )
	{
		
		Node<String> a = new Node<>("A");
		
		assertEquals(null, getInstance().locate(a, a, a));
	}
	
	@Test
	public void testSameDistance( )
	{
		Node<String> a = new Node<>("A");
		Node<String> b = a.setLeft("B");
		Node<String> c = a.setRight("C");
		Node<String> d = b.setLeft("D");
		Node<String> e = c.setRight("E");
		Node<String> f = d.setLeft("F");
		Node<String> g = f.setLeft("G");
		Node<String> h = f.setRight("H");
		Node<String> i = h.setRight("I");
		
		assertEquals(a, getInstance().locate(a, d, e));
	}
	
	@Test
	public void testDifferentDistance( )
	{
		
		Node<String> a = new Node<>("A");
		Node<String> b = a.setLeft("B");
		Node<String> c = a.setRight("C");
		Node<String> d = b.setLeft("D");
		Node<String> e = c.setRight("E");
		Node<String> f = d.setLeft("F");
		Node<String> g = f.setLeft("G");
		Node<String> h = f.setRight("H");
		Node<String> i = h.setRight("I");
		
		assertEquals(f, getInstance().locate(a, g, i));
	}
}
