package problems.crackingthecodinginterview.problem2_3;

import org.junit.Assert;
import org.junit.Test;
import problems.crackingthecodinginterview.common.LLNode;
import problems.crackingthecodinginterview.common.LLUtils;

public class TestLLRemover
{
	@Test
	public void test( )
	{
		LLNode<Integer> head = LLUtils.makeList(new Integer[]{0, 1, 2, 3});
		
		LLRemover.remove(head.getNext());
		
		Assert.assertEquals(LLUtils.makeList(new Integer[]{0, 2, 3}), head);
	}
	
	@Test(expected=NullPointerException.class)
	public void testRemoveLastNode( )
	{
		LLRemover.remove(LLUtils.makeList(new Integer[]{0}));
	}
	
	@Test(expected=NullPointerException.class)
	public void testRemoveNullNode( )
	{
		LLRemover.remove(null);
	}
}
