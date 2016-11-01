package problems.crackingthecodinginterview.problem3_2;

import org.junit.Test;
import org.junit.Assert;

public class TestMinStack
{
	@Test(expected=IllegalStateException.class)
	public void testPopEmpty( )
	{
		MinStack<?> stack = new MinStack<>();
		stack.pop();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testPeekEmpty( )
	{
		MinStack<?> stack = new MinStack<>();
		stack.peek();
	}
	
	@Test
	public void testMin( )
	{
		MinStack<Integer> stack = new MinStack<>();
		
		stack.push(10);
		stack.push(4);
		stack.push(10);
		stack.push(2);
		
		Assert.assertEquals((Integer) 2, stack.min());
		
		stack.pop();
		
		Assert.assertEquals((Integer) 4, stack.min());
		
		stack.pop();
		
		Assert.assertEquals((Integer) 4, stack.min());
		
		stack.pop();
		
		Assert.assertEquals((Integer) 10, stack.min());
		
		stack.push(11);
		
		Assert.assertEquals((Integer) 10, stack.min());
	}
	
	@Test
	public void testPushPeekPop( )
	{
		MinStack<Integer> stack = new MinStack<>();
		
		stack.push(1);
		stack.push(2);
		stack.push(3);

		Assert.assertEquals((Integer) 3, stack.peek());
		
		stack.pop();
		
		Assert.assertEquals((Integer) 2, stack.peek());
		
		stack.pop();
		stack.pop();
		
		Assert.assertTrue(stack.isEmpty());
		
		stack.push(1);
		
		Assert.assertEquals((Integer) 1, stack.peek());
	}
}
