package problems.crackingthecodinginterview.problem1_7;

import org.junit.Assert;
import org.junit.Test;
import problems.crackingthecodinginterview.problem1_7.RowColZeroer;

public class TestRowColZeroer
{
	@Test
	public void testNoZeros( )
	{
		int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		int[][] expected = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		
		RowColZeroer.zero(matrix);
		
		Assert.assertArrayEquals(expected, matrix);
	}
	
	@Test
	public void test1( )
	{
		int[][] matrix = {{1, 0, 3}};
		int[][] expected = {{0, 0, 0}};
		
		RowColZeroer.zero(matrix);
		
		Assert.assertArrayEquals(expected, matrix);
	}
	
	@Test
	public void test2( )
	{
		int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
		int[][] expected = {{1, 2, 0}, {4, 5, 0}, {0, 0, 0}};
		
		RowColZeroer.zero(matrix);
		
		Assert.assertArrayEquals(expected, matrix);
	}
	
	@Test
	public void test3( )
	{
		int[][] matrix = {{1, 0, 3}, {0, 5, 6}, {7, 8, 0}};
		int[][] expected = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
		
		RowColZeroer.zero(matrix);
		
		Assert.assertArrayEquals(expected, matrix);
	}
	
	@Test
	public void testNoCols( )
	{
		int[][] matrix = {{}};
		
		RowColZeroer.zero(matrix);
	}
	
	@Test
	public void testNoRows( )
	{
		int[][] matrix = {};
		
		RowColZeroer.zero(matrix);
	}
	
	@Test(expected=NullPointerException.class)
	public void testNull( )
	{
		RowColZeroer.zero(null);
	}
}
