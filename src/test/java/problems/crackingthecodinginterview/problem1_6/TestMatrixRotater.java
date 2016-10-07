package problems.crackingthecodinginterview.problem1_6;

import org.junit.Assert;
import org.junit.Test;

public class TestMatrixRotater
{
	@Test(expected=NullPointerException.class)
	public void testNull( )
	{
		MatrixRotater.rotateCw(null);
	}
	
	@Test
	public void test( )
	{
		Integer[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		Integer[][] expected = {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
		
		MatrixRotater.rotateCw(matrix);
		
		Assert.assertArrayEquals(expected, matrix);
	}
}
