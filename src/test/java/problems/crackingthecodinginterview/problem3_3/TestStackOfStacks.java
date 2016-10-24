package problems.crackingthecodinginterview.problem3_3;

import org.junit.Assert;
import org.junit.Test;

public class TestStackOfStacks
{
	@Test(expected=IllegalArgumentException.class)
	public void testTooSmallMaxSize( )
	{
		new StackOfStacks<>(0);
	}
	
	@Test
	public void test( )
	{
		StackOfStacks<Integer> sos = new StackOfStacks<>(2);
		
		// isEmpty
		Assert.assertTrue(sos.isEmpty());
		
		// size
		Assert.assertEquals(0, sos.size());
		
		// push, size, peek
		sos.push(1);
		sos.push(2);
		sos.push(3);
		
		Assert.assertEquals(2, sos.size());
		Assert.assertEquals((Integer) 3, sos.peek());
		
		// pop, size, peek
		sos.pop();
		
		Assert.assertEquals(1, sos.size());
		Assert.assertEquals((Integer) 2, sos.peek());
		
		sos.pop();
		
		Assert.assertEquals(1, sos.size());
		Assert.assertEquals((Integer) 1, sos.peek());
		
		sos.pop();
		
		Assert.assertEquals(0, sos.size());
		
		// popAt, size, pop
		sos.push(1);
		sos.push(2);
		sos.push(3);
		
		sos.popAt(0);
		
		Assert.assertEquals(1, sos.size());
		
		Assert.assertEquals((Integer) 3, sos.pop());
		Assert.assertEquals((Integer) 1, sos.pop());
	}
	
	@Test(expected=IllegalStateException.class)
	public void testPopEmpty( )
	{
		new StackOfStacks<>(2).pop();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testPeekEmpty( )
	{
		new StackOfStacks<>(2).peek();
	}
}
