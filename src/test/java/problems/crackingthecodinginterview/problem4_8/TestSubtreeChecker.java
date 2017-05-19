package problems.crackingthecodinginterview.problem4_8;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import datastructures.trees.binarytree.Node;

public class TestSubtreeChecker
{
	@Test
	public void testSubtreeHigherThanTree( )
	{
		Node<String> a1 = new Node<>("A");
		Node<String> b1 = a1.setLeft("B");
		Node<String> c1 = a1.setRight("C");
		Node<String> d1 = c1.setRight("D");
		
		Node<String> c2 = new Node<>("C");
		Node<String> d2 = c2.setRight("D");
		
		assertFalse(SubtreeChecker.check(c2, a1));
	}
	
	@Test
	public void testTreeEqualsSubtree( )
	{
		Node<String> a1 = new Node<>("A");
		Node<String> b1 = a1.setLeft("B");
		Node<String> c1 = a1.setRight("C");
		Node<String> d1 = c1.setRight("D");
		
		assertTrue(SubtreeChecker.check(a1, a1));
	}
	
	@Test
	public void testTreeHigherThanSubtree( )
	{
		Node<String> a1 = new Node<>("A");
		Node<String> b1 = a1.setLeft("B");
		Node<String> c1 = a1.setRight("C");
		Node<String> d1 = c1.setRight("D");
		
		Node<String> c2 = new Node<>("C");
		Node<String> d2 = c2.setRight("D");
		
		assertTrue(SubtreeChecker.check(a1, c2));
	}
	
	@Test
	public void testSubtreeNotInTree( )
	{
		Node<String> a1 = new Node<>("A");
		Node<String> b1 = a1.setLeft("B");
		Node<String> c1 = a1.setRight("C");
		Node<String> d1 = c1.setRight("D");
		
		Node<String> z2 = new Node<>("Z");
		
		assertFalse(SubtreeChecker.check(a1, z2));
	}
}
