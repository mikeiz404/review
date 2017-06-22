package problems.internet.mergeranges;

import static org.junit.Assert.*;
import java.util.HashSet;
import org.junit.Test;

public abstract class TestSolution
{
	public abstract Solution getInstance( );
	
	@Test
	public void testManyOverlap( )
	{
		HashSet<Range> ranges = new HashSet<>();
		ranges.add(new Range(-5, 15));
		ranges.add(new Range(-3, 5));
		ranges.add(new Range(2, 9));
		ranges.add(new Range(15, 1));
		
		HashSet<Range> expected = new HashSet<>();
		expected.add(new Range(-5, 16));
		expected.add(new Range(15, 1));
		
		assertEquals(expected, new HashSet<Range>(getInstance().merge(ranges)));
	}
	
	@Test
	public void testManyNoOverlap( )
	{
		HashSet<Range> ranges = new HashSet<>();
		ranges.add(new Range(-2, 10));
		ranges.add(new Range(10, 2));
		ranges.add(new Range(12, 1));
		
		HashSet<Range> expected = new HashSet<>();
		expected.add(new Range(-2, 10));
		expected.add(new Range(10, 2));
		expected.add(new Range(12, 1));
		
		assertEquals(expected, new HashSet<Range>(getInstance().merge(ranges)));
	}
	
	@Test
	public void testEmpty( )
	{
		HashSet<Range> ranges = new HashSet<>();
		HashSet<Range> expected = new HashSet<>();
		
		assertEquals(expected, new HashSet<Range>(getInstance().merge(ranges)));
	}
	
	@Test
	public void testOne( )
	{
		HashSet<Range> ranges = new HashSet<>();
		ranges.add(new Range(5, 10000));
		
		HashSet<Range> expected = new HashSet<>();
		expected.add(new Range(5, 10000));
		
		assertEquals(expected, new HashSet<Range>(getInstance().merge(ranges)));
	}
}
