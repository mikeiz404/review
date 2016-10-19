package problems.crackingthecodinginterview.problem2_2;

import problems.crackingthecodinginterview.common.LLNode;
import problems.crackingthecodinginterview.common.LLUtils;
import org.junit.Assert;
import org.junit.Test;

public class TestLLFetcher
{
	@Test
	public void test( )
	{
		LLNode<Integer> head = LLUtils.makeList(new Integer[]{0, 1, 2, 3, 4, 5});
		LLFetcher<Integer> fetcher = new LLFetcher<>(head);
		
		Assert.assertEquals((Integer) 5, fetcher.getFromEnd(0));
		Assert.assertEquals((Integer) 4, fetcher.getFromEnd(1));
		Assert.assertEquals((Integer) 3, fetcher.getFromEnd(2));
		Assert.assertEquals((Integer) 2, fetcher.getFromEnd(3));
		Assert.assertEquals((Integer) 1, fetcher.getFromEnd(4));
		Assert.assertEquals((Integer) 0, fetcher.getFromEnd(5));
	}
}
