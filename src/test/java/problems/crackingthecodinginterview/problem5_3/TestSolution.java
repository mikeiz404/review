package problems.crackingthecodinginterview.problem5_3;

import static org.junit.Assert.*;
import org.junit.Test;

public abstract class TestSolution
{
	public abstract TestInterface getInstance( );
	
	@Test
	public void testLarger( )
	{
		assertEquals((Integer) 2, getInstance().findLarger(1));
		assertEquals((Integer) 6, getInstance().findLarger(5));
		assertEquals((Integer) 13967, getInstance().findLarger(13948));
	}
	
	@Test
	public void testSmaller( )
	{
		assertEquals((Integer) 1, getInstance().findSmaller(2));
		assertEquals((Integer) 3, getInstance().findSmaller(5));
		assertEquals(null, getInstance().findSmaller(7));
		assertEquals((Integer) 13946, getInstance().findSmaller(13948));
	}
}
