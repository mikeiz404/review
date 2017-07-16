package problems.crackingthecodinginterview.problem9_6;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;


public abstract class TestSolution
{
	public abstract TestInterface getInstance( );
	
	protected static void assertCollectionEquals( Collection<String> expected, Collection<String> actual )
	{
		assertEquals(expected.size(), actual.size());
		assertTrue(expected.containsAll(actual));
	}
	
	protected void test( int n, Collection<String> expected )
	{
		assertCollectionEquals(expected, getInstance().makeAllParenPairs(n));
	}
	
	@Test
	public void testBase( )
	{
		test(1, Arrays.asList("()"));
	}
	
	@Test
	public void testRec2( )
	{
		test(2, Arrays.asList("()()","(())"));
	}
	
	@Test
	public void testRec4( )
	{
		test(4, Arrays.asList("()()()()", "(()())()", "(()(()))", "()()(())", "(((())))", "(())()()", "()((()))", "()(())()", "()(()())", "(()()())", "((()()))", "((()))()", "((())())", "(())(())"));
	}
}
