package problems.crackingthecodinginterview.problem4_5;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import datastructures.trees.binarytree.Node;

public class TestBstValidator
{
	@Test
	public void testValid( )
	{
		Node<Integer> a = new Node<>(10);
		Node<Integer> b = a.setLeft(10);
		Node<Integer> c = a.setRight(23);
		Node<Integer> d = b.setLeft(-1);
		
		assertTrue(BstValidator.isValid(a));
	}
	
	@Test
	public void testInvalid( )
	{
		Node<Integer> a = new Node<>(20);
		Node<Integer> b = a.setLeft(10);
		Node<Integer> c = a.setRight(30);
		Node<Integer> d = b.setLeft(5);
		Node<Integer> e = b.setRight(15);
		Node<Integer> f = d.setLeft(3);
		Node<Integer> g = d.setRight(7);
		Node<Integer> h = e.setLeft(17);
		
		assertFalse(BstValidator.isValid(a));
	}
}
