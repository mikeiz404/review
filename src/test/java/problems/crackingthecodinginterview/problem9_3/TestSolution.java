package problems.crackingthecodinginterview.problem9_3;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestSolution
{
	@Test
	public void testEmpty( )
	{
		assertEquals(-1, Solution.findMagicIndex(new int[]{}));
	}
	
	@Test
	public void testWithoutMagic( )
	{
		assertEquals(-1, Solution.findMagicIndex(new int[]{1, 2, 3, 4, 6, 6}));
	}
	
	@Test
	public void testWithMagic( )
	{
		assertEquals(5, Solution.findMagicIndex(new int[]{1, 2, 3, 4, 5, 5}));
	}
}
