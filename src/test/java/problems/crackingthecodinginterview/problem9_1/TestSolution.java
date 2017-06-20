package problems.crackingthecodinginterview.problem9_1;

import static org.junit.Assert.*;
import org.junit.Test;


public class TestSolution
{
	@Test
	public void testBase( )
	{
		Solution s = new Solution();
		
		assertEquals(0, s.computeWays(0));
	}
	
	@Test
	public void testRecursive( )
	{
		Solution s = new Solution();
		
		assertEquals(1, s.computeWays(1));
		assertEquals(2, s.computeWays(2));
		assertEquals(4, s.computeWays(3));
	}
	
	@Test
	public void test( )
	{
		Solution s = new Solution();
		
		assertEquals(7, s.computeWays(4));
	}
}
