package problems.crackingthecodinginterview.problem2_5;

import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public abstract class TestBase
{
	protected abstract LinkedList<Integer> makeList( Integer... integers );
	protected abstract LLSummerInterface makeSummer( );
	
	@Test
	public void testSameLength( )
	{
		LLSummerInterface summer = makeSummer();
		
		LinkedList<Integer> sum = summer.sum(makeList(1, 2, 3, 4), makeList(1, 3, 3, 7));
		
		Assert.assertEquals(makeList(2, 5, 7, 1), sum);
	}
	
	@Test
	public void testEndCarry( )
	{
		LLSummerInterface summer = makeSummer();
		
		LinkedList<Integer> sum = summer.sum(makeList(9), makeList(9));
		
		Assert.assertEquals(makeList(1, 8), sum);
	}
	
	@Test
	public void testDifferentLength( )
	{
		LLSummerInterface summer = makeSummer();
		
		LinkedList<Integer> sum = summer.sum(makeList(9, 9, 9), makeList(9, 9, 9, 9, 9));
		
		Assert.assertEquals(makeList(1, 0, 0, 9, 9, 8), sum);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyA( )
	{
		LLSummerInterface summer = makeSummer();
		
		summer.sum(makeList(), makeList(0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyB( )
	{
		LLSummerInterface summer = makeSummer();
		
		summer.sum(makeList(0), makeList());
	}
	
	@Test(expected = NullPointerException.class)
	public void testNullA( )
	{
		LLSummerInterface summer = makeSummer();
		
		summer.sum(null, makeList(0));
	}
	
	@Test(expected = NullPointerException.class)
	public void testNullB( )
	{
		LLSummerInterface summer = makeSummer();
		
		summer.sum(makeList(0), null);
	}
}
