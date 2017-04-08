package problems.crackingthecodinginterview.problem4_1;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import datastructures.trees.binarytree.Node;

public abstract class TestTreeBalanceChecker
{
	public abstract TreeBalanceChecker getInstance( );
	
	@Test
	public void testBase( )
	{
		/**
		 *  A
		 */
		Node<String> a = new Node<>("A");
		
		TreeBalanceChecker checker = this.getInstance();
		
		assertTrue(checker.isBalanced(a, 0));
		assertTrue(checker.isBalanced(a, 1));
		assertTrue(checker.isBalanced(a, 100));
	}
	
	@Test
	public void testRecursiveBalanced( )
	{
		/**
		 *   .A.
		 *  B   C
		 */
		Node<String> a = new Node<>("A");
		a.setLeft("B");
		Node<String> c = a.setRight("C");
		
		TreeBalanceChecker checker = this.getInstance();
		
		assertTrue(checker.isBalanced(a, 0));
		assertTrue(checker.isBalanced(a, 1));
		assertTrue(checker.isBalanced(a, 100));
		
		/**
		 *   .A
		 *  B
		 */
		c.remove();
		
		assertTrue(checker.isBalanced(a, 0));
		assertTrue(checker.isBalanced(a, 1));
		assertTrue(checker.isBalanced(a, 100));
	}
	
	@Test
	public void testRecursiveUnbalanced( )
	{
		/**
		 *     .A.
		 *    B  .C
		 *      D
		 */
		Node<String> a = new Node<>("A");
		a.setLeft("B");
		Node<String> c = a.setRight("C");
		c.setLeft("D");
		
		TreeBalanceChecker checker = this.getInstance();
		
		assertFalse(checker.isBalanced(a, 0));
		assertTrue(checker.isBalanced(a, 1));
		assertTrue(checker.isBalanced(a, 100));
	}
	
	/**
	 *       .A.
	 *      B  .C
	 *       .D.
	 *      E   F.
	 *            G
	 */
	
	@Test
	public void test1( )
	{
		Node<String> a = new Node<>("A");
		Node<String> b = a.setLeft("B");
		Node<String> c = a.setRight("C");
		Node<String> d = c.setLeft("D");
		d.setLeft("E");
		Node<String> f = d.setRight("F");
		f.setRight("G");
		
		TreeBalanceChecker checker = this.getInstance();
		
		assertFalse(checker.isBalanced(a, 0));
		assertFalse(checker.isBalanced(a, 1));
		assertFalse(checker.isBalanced(a, 2));
		assertTrue(checker.isBalanced(a, 3));
		assertTrue(checker.isBalanced(a, 100));
		
		assertTrue(checker.isBalanced(b, 0));
		
		assertFalse(checker.isBalanced(c, 0));
		assertTrue(checker.isBalanced(c, 2));
	}
	
	/**
	 *         .A.
	 *       .B. C.
	 *     .D  E   F.
	 *    G          H
	 **/
	@Test
	public void test2( )
	{
		Node<String> a = new Node<>("A");
		Node<String> b = a.setLeft("B");
		Node<String> c = a.setRight("C");
		Node<String> d = b.setLeft("D");
		Node<String> e = b.setRight("E");
		Node<String> f = c.setRight("F");
		Node<String> g = d.setRight("G");
		Node<String> h = f.setRight("H");
		
		TreeBalanceChecker checker = this.getInstance();
		
		assertFalse(checker.isBalanced(a, 0));
		assertTrue(checker.isBalanced(a, 1));
	}
}
