package problems.crackingthecodinginterview.problem5_7;

import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;

public abstract class TestSolution
{
	public abstract AbstractSolution getInstance( );
	
	@Test
	public void test( )
	{
		assertEquals(0, getInstance().findMissingNumber(Arrays.asList()));
		
		assertEquals(1, getInstance().findMissingNumber(Arrays.asList(0)));
		assertEquals(0, getInstance().findMissingNumber(Arrays.asList(1)));
		
		assertEquals(4, getInstance().findMissingNumber(Arrays.asList(1, 6, 2, 0, 3, 5)));
	}
}
