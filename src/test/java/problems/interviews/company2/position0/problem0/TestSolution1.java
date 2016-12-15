package problems.interviews.company2.position0.problem0;

import org.junit.Assert;
import org.junit.Test;

public class TestSolution1
{
	private static final int SIZE = 10;
	
	@Test(expected=NullPointerException.class)
	public void testNull( )
	{
		Solution1.reverse(null);
	}
	
	@Test
	public void testEmpty( )
	{
		Object[] list = new Object[]{};
		
		Solution1.reverse(list);
		
		Assert.assertEquals(list.length, 0);
	}
	
	@Test
	public void test( )
	{
		
    	Integer[] list = new Integer[SIZE];
    	for( int i=0; i<SIZE; i++ )
    	{
    		list[i] = i;
    	}
		
		Solution1.reverse(list);
		
		for( int i=0; i<SIZE; i++ )
    	{
			int index = SIZE - 1 - i;
			
    		Assert.assertEquals(((Integer) i), list[index]);
    	}
	}
}
