package problems.crackingthecodinginterview.problem9_2;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.Test;

public class TestSolution
{
	@Test
	public void testComputePathCountOrigin( )
	{
		assertEquals(1, Solution.computePathCount(0, 0));
	}
	
	@Test
	public void testComputePathCountOne( )
	{
		assertEquals(1, Solution.computePathCount(1, 0));
		assertEquals(1, Solution.computePathCount(0, 1));
		assertEquals(1, Solution.computePathCount(0, 5));
		assertEquals(1, Solution.computePathCount(5, 0));
	}
	
	@Test
	public void testComputePathCountMany( )
	{
		assertEquals(2, Solution.computePathCount(1, 1));
		assertEquals(6, Solution.computePathCount(2, 2));
	}
	
	@Test
	public void testFindAPathNoPath( )
	{
		HashSet<Point> blocked;
		
		blocked = new HashSet<>();
		blocked.add(new Point(0,0));
		
		assertEquals(null, Solution.findAPath(blocked, new Point(0, 0)));
		
		
		blocked = new HashSet<>();
		blocked.add(new Point(1,0));
		blocked.add(new Point(2,0));
		blocked.add(new Point(3,0));
		
		assertEquals(null, Solution.findAPath(blocked, new Point(3, 0)));
	}
	
	@Test
	public void testFindAPathOrigin( )
	{
		List<Point> path = new ArrayList<>();
		path.add(new Point(0, 0));
		
		assertEquals(path, Solution.findAPath(new HashSet<>(), new Point(0, 0)));
	}
	
	@Test
	public void testFindAPath( )
	{
		HashSet<Point> blocked = new HashSet<>();
		blocked.add(new Point(1,0));
		blocked.add(new Point(3,0));
		
		Point dst = new Point(10, 2);
		List<Point> path = Solution.findAPath(blocked, dst);
		
		// follow path to dst
		Point prev = path.get(0);
		for( Point point : path.subList(1, path.size()) )
		{
			// valid move
			Point diff = point.subtract(prev);
			assertTrue(diff.getX() == 1 || diff.getX() == 0);
			assertTrue(diff.getY() == 1 || diff.getY() == 0);
			assertTrue(diff.getX() != diff.getY());
			
			// not blocked 
			assertFalse(blocked.contains(point));
			
			prev = point;
		}
		
		assertEquals(dst, prev);
	}
}
