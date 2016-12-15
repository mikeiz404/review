package problems.interviews.company2.position0.problem3;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class TestSolution1
{
	@Test(expected=NullPointerException.class)
	public void testNull( )
	{
		Solution1.randomSubset(null, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIllegalSize( )
	{
		Solution1.randomSubset(new ArrayList<>(), 1);
	}
	
	@Test
	public void testEmpty( )
	{
		ArrayList<Integer> subset = Solution1.randomSubset(new ArrayList<>(), 0);
		
		Assert.assertEquals(0, subset.size());
	}
	
	@Test
	public void test( )
	{
		int size = 100;
		ArrayList<Integer> list = new ArrayList<>(size);
		for( int i=0; i<size; i++ )
		{
			list.add(i);
		}
		
		ArrayList<Integer> subset = Solution1.randomSubset(list, size);
		
		Assert.assertEquals(size, subset.size());
		for( int i=0; i<size; i++ )
		{
			int item = subset.get(i);
			
			Assert.assertTrue(list.contains(item));
		}
	}
}
