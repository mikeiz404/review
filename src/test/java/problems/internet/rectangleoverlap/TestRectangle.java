package problems.internet.rectangleoverlap;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestRectangle
{
	@Test
	public void testOverlapNone( )
	{
		Rectangle a = new Rectangle(0, 0, 5, 5);
		Rectangle b = new Rectangle(5, 5, 1, 1);
		
		assertFalse(a.overlapsX(b));
		assertFalse(b.overlapsX(a));
		
		assertFalse(a.overlapsY(b));
		assertFalse(b.overlapsY(a));
		
		assertFalse(a.overlaps(b));
		assertFalse(b.overlaps(a));
	}
	
	@Test
	public void testOverlapInX( )
	{
		Rectangle a = new Rectangle(0, 0, 5, 5);
		Rectangle b = new Rectangle(0, 10, 5, 5);
		
		assertTrue(a.overlapsX(b));
		assertTrue(b.overlapsX(a));
		
		assertFalse(a.overlapsY(b));
		assertFalse(b.overlapsY(a));
		
		assertFalse(a.overlaps(b));
		assertFalse(b.overlaps(a));
	}
	
	@Test
	public void testOverlapInY( )
	{
		Rectangle a = new Rectangle(0, 0, 5, 5);
		Rectangle b = new Rectangle(10, 0, 5, 5);
		
		assertTrue(a.overlapsY(b));
		assertTrue(b.overlapsY(a));
		
		assertFalse(a.overlapsX(b));
		assertFalse(b.overlapsX(a));
		
		assertFalse(a.overlaps(b));
		assertFalse(b.overlaps(a));
	}
	
	@Test
	public void testOverlapInXY( )
	{
		Rectangle a = new Rectangle(-10, 10, 26, 26);
		Rectangle b = new Rectangle(15, 15, 5, 5);
		
		assertTrue(a.overlapsY(b));
		assertTrue(b.overlapsY(a));
		
		assertTrue(a.overlapsX(b));
		assertTrue(b.overlapsX(a));
		
		assertTrue(a.overlaps(b));
		assertTrue(b.overlaps(a));
	}
}
