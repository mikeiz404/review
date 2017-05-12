package problems.crackingthecodinginterview.problem4_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.validation.constraints.NotNull;
import org.junit.Test;
import datastructures.trees.binarytree.Node;
import static org.junit.Assert.assertEquals;
import static com.google.common.base.Preconditions.checkNotNull;

public class TestBstMaker
{
	private static int calcExpectedHeight( Collection<?> items )
	{
		return (int) Math.ceil(Math.log(items.size() + 1) / Math.log(2));
	}
	
	private static int calcMaxHeight( Node<?> root )
	{
		if( root == null )
		{
			return 0;
		}
		else // root != null
		{
			return calcMaxHeight(root, 1);
		}
	}
	
	private static int calcMaxHeight( @NotNull Node<?> node, int height )
	{
		checkNotNull(node);
		
		int left = height;
		int right = height;
		
		if( node.hasLeft() )
		{
			left = calcMaxHeight(node.getLeft(), (height + 1));
		}
		
		if( node.hasRight() )
		{
			right = calcMaxHeight(node.getRight(), (height + 1));
		}
		
		return Math.max(left, right);
		
	}
	
	private static @NotNull <T> ArrayList<T> makeInOrderList( @NotNull Node<T> root )
	{
		checkNotNull(root);
		
		ArrayList<T> array = new ArrayList<T>();
		
		if( root.hasLeft() )
		{
			array.addAll(makeInOrderList(root.getLeft()));
		}
		
		array.add(root.getValue());
		
		if( root.hasRight() )
		{
			array.addAll(makeInOrderList(root.getRight()));
		}
		
		return checkNotNull(array);
	}
	
	public <T extends Comparable<T>> void test( ArrayList<T> array )
	{
		Node<T> root = BstMaker.fromArray(array);
		
		assertEquals(array, makeInOrderList(root));
		assertEquals(calcExpectedHeight(array), calcMaxHeight(root));
	}
	
	@Test
	public void testSmall( )
	{
		ArrayList<Integer> array = new ArrayList<>(Arrays.asList(1, 2));
		
		test(array);
	}
	
	@Test
	public void testLarge( )
	{
		ArrayList<Integer> array = new ArrayList<>();
		
		int prev = -100000;
		for( int i=0; i<100000; i++ )
		{
			int num = (int) (prev + (Math.random() * 100));
			
			array.add(num);
			
			prev = num;
		}
		
		test(array);
	}
}
