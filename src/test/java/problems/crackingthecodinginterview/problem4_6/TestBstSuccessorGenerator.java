package problems.crackingthecodinginterview.problem4_6;

import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;
import datastructures.trees.binarytree.Node;

public class TestBstSuccessorGenerator
{
	@Test
	public void test( )
	{
		Node<Integer> a = new Node<>(10);
		Node<Integer> b = a.setLeft(9);
		Node<Integer> c = a.setRight(20);
		Node<Integer> d = b.setLeft(7);
		Node<Integer> e = c.setLeft(15);
		Node<Integer> f = c.setRight(30);
		Node<Integer> g = d.setRight(8);
		
		Integer[] actual = new Integer[7];
		
		Node<Integer> current = d;
		int i=0;
		while( BstSuccessorGenerator.hasNext(current) )
		{
			actual[i] = current.getValue();
			
			current = BstSuccessorGenerator.next(current);
			i++;
		}
		actual[i] = current.getValue();
		
		assertArrayEquals(new Integer[]{7, 8, 9, 10, 15, 20, 30}, actual);
	}
}
