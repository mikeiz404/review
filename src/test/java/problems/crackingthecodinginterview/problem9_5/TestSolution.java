package problems.crackingthecodinginterview.problem9_5;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import org.junit.Test;


public class TestSolution
{
	@Test
	public void testEmpty( )
	{
		assertTrue(Solution.makePermutations("").size() == 0);
	}
	
	@Test
	public void testOne( )
	{
		Collection<String> actual = Solution.makePermutations("a");
		Collection<String> expected = new HashSet<String>(Arrays.asList("a"));
		
		assertEquals(expected.size(), actual.size());
		assertTrue(expected.containsAll(actual));
	}
	
	@Test
	public void testMany( )
	{
		Collection<String> actual = Solution.makePermutations("abc");
		Collection<String> expected = new HashSet<String>(Arrays.asList("cab", "acb", "abc", "cba", "bca", "bac"));
		
		assertEquals(expected.size(), actual.size());
		assertTrue(expected.containsAll(actual));
	}
}
