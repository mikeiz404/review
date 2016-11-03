package problems.crackingthecodinginterview.problem3_5;

import org.junit.Assert;
import org.junit.Test;

public class TestMyQueue
{
	@Test(expected=IllegalStateException.class)
	public void testPopEmpty( )
	{
		MyQueue<?> queue = new MyQueue<>();
		queue.pop();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testPeekEmpty( )
	{
		MyQueue<?> queue = new MyQueue<>();
		queue.peek();
	}
	
	@Test
	public void test( )
	{
		MyQueue<Integer> queue = new MyQueue<>();
		
		queue.push(1);
		queue.push(2);
		queue.push(3);
		
		Assert.assertEquals(3, queue.size());
		Assert.assertEquals((Integer) 1, queue.peek());
		
		queue.pop();
		
		Assert.assertEquals(2, queue.size());
		Assert.assertEquals((Integer) 2, queue.peek());
		
		queue.push(4);
		
		Assert.assertEquals(3, queue.size());
		Assert.assertEquals((Integer) 2, queue.peek());
		
		queue.pop();
		queue.pop();
		
		Assert.assertEquals(1, queue.size());
		Assert.assertEquals((Integer) 4, queue.peek());
		
		queue.pop();
		
		Assert.assertEquals(0, queue.size());
		Assert.assertTrue(queue.isEmpty());
		
		queue.push(5);
		
		Assert.assertEquals((Integer) 5, queue.peek());
	}
}
