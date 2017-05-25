package problems.crackingthecodinginterview.problem4_9;

import org.junit.Test;
import static org.mockito.Mockito.*;
import datastructures.trees.binarytree.Node;

public abstract class TestPathSumPrinter
{
	public abstract PathSumPrinter getMakeInstance( );
	
	public abstract Printer getPrinter( );
	
	@Test
	public void testOneNode( )
	{
		Node<Integer> a = new Node<>(10);
		
		getMakeInstance().print(a, 10);
		verify(getPrinter()).print("10 \n");
		
		getMakeInstance().print(a, 1);
		verify(getPrinter(), never()).print(any());
	}
	
	@Test
	public void testManyNodes( )
	{
		Node<Integer> a = new Node<>(10);
		Node<Integer> b = a.setLeft(4);
		Node<Integer> c = a.setRight(8);
		Node<Integer> d = b.setLeft(-20);
		Node<Integer> e = c.setLeft(1);
		Node<Integer> f = c.setRight(1);
		Node<Integer> g = d.setRight(-3);
		Node<Integer> h = g.setLeft(28);
		Node<Integer> i = g.setRight(18);
		
		getMakeInstance().print(a, 9);
		verify(getPrinter()).print("10 4 -20 -3 18 \n");
		verify(getPrinter()).print("4 -20 -3 28 \n");
		verify(getPrinter(), times(2)).print("8 1 \n");
		
		getMakeInstance().print(a, 1);
		verify(getPrinter(), times(2)).print("1 \n");
		
		getMakeInstance().print(a, -3);
		verify(getPrinter()).print("-3 \n");
		
		getMakeInstance().print(a, 100);
		verify(getPrinter(), never()).print(any());
	}
}
