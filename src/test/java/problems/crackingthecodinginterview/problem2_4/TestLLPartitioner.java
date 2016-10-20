package problems.crackingthecodinginterview.problem2_4;

import org.junit.Assert;
import org.junit.Test;
import problems.crackingthecodinginterview.common.LLNode;
import problems.crackingthecodinginterview.common.LLUtils;

public class TestLLPartitioner
{
	public static <T extends Comparable<T>> boolean isPartitioned( LLNode<T> head, T pivot )
	{
		boolean passedPartition = false;
		LLNode<T> node = head;
		
		while( node != null )
		{
			T nodeData = node.getData();
			
			if( !passedPartition && nodeData.compareTo(pivot) > 0 )
			{
				passedPartition = true;
			}
			
			if( passedPartition && nodeData.compareTo(pivot) < 0 )
			{
				return false;
			}
			
			node = node.getNext();
		}
		
		return true;
	}
	
	@Test
	public void testLargePivot( )
	{
		LLNode<Integer> head = LLUtils.makeList(new Integer[]{0, 1, 2, 3, 4, 5});
		Integer pivot = 6;
		
		LLPartitioner.partition(head, pivot);
		
		Assert.assertTrue(isPartitioned(head, pivot));
	}
	
	@Test
	public void testSmallPivot( )
	{
		LLNode<Integer> head = LLUtils.makeList(new Integer[]{0, 1, 2, 3, 4, 5});
		Integer pivot = -1;
		
		LLPartitioner.partition(head, pivot);
		
		Assert.assertTrue(isPartitioned(head, pivot));
	}
	
	@Test
	public void test( )
	{
		LLNode<Integer> head = LLUtils.makeList(new Integer[]{8, 4, 7, 3, 2, 0});
		Integer pivot = 5;
		
		LLPartitioner.partition(head, pivot);
		
		Assert.assertTrue(isPartitioned(head, pivot));
	}
	
	@Test
	public void testEmptyList( )
	{
		LLPartitioner.partition(null, 3);
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullPivot( )
	{
		LLPartitioner.partition(LLUtils.makeList(new Integer[]{0}), null);
	}
}
