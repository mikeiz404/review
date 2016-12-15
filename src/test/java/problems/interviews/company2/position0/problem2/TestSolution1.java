package problems.interviews.company2.position0.problem2;

import java.util.ArrayList;
import java.util.HashSet;
import org.junit.Assert;
import org.junit.Test;

public class TestSolution1
{
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeSize( )
	{
		Solution1.random(-1, 10);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTooSmallUpperBound( )
	{
		Solution1.random(10, 9);
	}
	
	@Test
	public void testZeroSize( )
	{
		ArrayList<Integer> list = Solution1.random(0, 10);
		
		Assert.assertEquals(0, list.size());
	}
	
	@Test
	public void test( )
	{
		int size = 100;
		int upperBound = 100;
		ArrayList<Integer> list = Solution1.random(size, upperBound);

		Assert.assertEquals(size, list.size());
		
		HashSet<Integer> set = new HashSet<>();
		set.addAll(list);
		Assert.assertEquals(set.size(), list.size());

		for( int item : list )
		{
			Assert.assertTrue(item < upperBound);
		}
	}
}
