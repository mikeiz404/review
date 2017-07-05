package problems.crackingthecodinginterview.problem9_4;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public abstract class TestSolution
{
	public abstract TestInterface makeInstance( );
	
	@Test
	public void testBase( )
	{
		TestInterface s = makeInstance();
		
		assertEquals(0, s.makeAllSubsets(new HashSet<Integer>()).size());
	}
	
	@Test
	public void testRecursive( )
	{
		TestInterface s = makeInstance();
		
		HashSet<Integer> items = new HashSet<>();
		items.add(1);
		items.add(2);
		items.add(3);
		
		Set<Set<Integer>> expected = new HashSet<>();
		expected.add(new HashSet<>(Arrays.asList(1, 2)));
		expected.add(new HashSet<>(Arrays.asList(2, 3)));
		expected.add(new HashSet<>(Arrays.asList(1, 3)));
		expected.add(new HashSet<>(Arrays.asList(1)));
		expected.add(new HashSet<>(Arrays.asList(2)));
		expected.add(new HashSet<>(Arrays.asList(3)));
		
		assertEquals(expected, s.makeAllSubsets(items));
	}
}
