package problems.crackingthecodinginterview.problem4_4;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;
import datastructures.trees.binarytree.Node;

public abstract class TestTreeToListMaker
{
	public abstract TreeToListMaker getInstance( );
	
	@Test
	public void testEmpty( )
	{
		TreeToListMaker maker = this.getInstance();
		
		ArrayList<LinkedList<Node<String>>> levels = maker.makeList(null);
		
		assertEquals(0, levels.size());
	}
	
	private static <T> List<T> nodesToValues( List<Node<T>> nodes )
	{
		return nodes.stream().map(n -> n.getValue()).collect(Collectors.toList());
	}
	
	@Test
	public void test( )
	{
		Node<String> a = new Node<>("0:A");
		Node<String> b = a.setLeft("1:B");
		Node<String> c = a.setRight("1:C");
		Node<String> d = b.setLeft("2:D");
		Node<String> e = b.setRight("2:E");
		Node<String> f = c.setRight("2:F");
		Node<String> g = e.setRight("3:G");
		Node<String> h = f.setRight("3:H");
		Node<String> i = g.setRight("4:I");
		Node<String> j = i.setLeft("5:J");
		
		TreeToListMaker maker = this.getInstance();
		
		ArrayList<LinkedList<Node<String>>> levels = maker.makeList(a);
		
		assertEquals(6, levels.size());
		// note: binary tree node has ordered child iteration (enum map) so ordering will be consistently left node, right node
		assertEquals(Arrays.asList("0:A"), nodesToValues(levels.get(0)));
		assertEquals(Arrays.asList("1:B", "1:C"), nodesToValues(levels.get(1)));
		assertEquals(Arrays.asList("2:D", "2:E", "2:F"), nodesToValues(levels.get(2)));
		assertEquals(Arrays.asList("3:G", "3:H"), nodesToValues(levels.get(3)));
		assertEquals(Arrays.asList("4:I"), nodesToValues(levels.get(4)));
		assertEquals(Arrays.asList("5:J"), nodesToValues(levels.get(5)));
	}
}
