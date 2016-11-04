package problems.crackingthecodinginterview.problem3_6;

import java.util.ArrayDeque;
import org.junit.Assert;
import org.junit.Test;

public class TestStackSorter
{
	protected static <T extends Comparable<T>> boolean isSortedAscending( ArrayDeque<T> stack )
	{
		if( stack.size() <= 1 )
		{
			return true;
		}
		else
		{
			T previous = stack.removeLast();
			
			while( !stack.isEmpty() )
			{
				T current = stack.removeLast();
				
				if( current.compareTo(previous) > 0 )
				{
					return false;
				}
				
				previous = current;
			}
			
			return true;
		}
	}
	
	protected static ArrayDeque<Integer> makeStack( Integer[] list )
	{
		ArrayDeque<Integer> stack = new ArrayDeque<>(list.length);
		for( Integer num : list )
		{
			stack.addLast(num);
		}
		
		return stack;
	}
	
	@Test(expected=NullPointerException.class)
	public void testNull( )
	{
		StackSorter.sort(null);
	}
	
	@Test
	public void testEmpty( )
	{
		ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
		
		StackSorter.sort(stack);
		
		Assert.assertTrue(isSortedAscending(stack));
		
	}
	
	@Test
	public void testOne( )
	{
		ArrayDeque<Integer> stack = makeStack(new Integer[]{1});
		
		StackSorter.sort(stack);
		
		Assert.assertTrue(isSortedAscending(stack));
		
	}
	
	
	@Test
	public void testSorted( )
	{
		ArrayDeque<Integer> stack = makeStack(new Integer[]{1, 2, 3, 4, 5});
		
		StackSorter.sort(stack);
		
		Assert.assertTrue(isSortedAscending(stack));
	}
	
	@Test
	public void testEven( )
	{
		ArrayDeque<Integer> stack = makeStack(new Integer[]{4, 3, 2, 1});
		
		StackSorter.sort(stack);
		
		Assert.assertTrue(isSortedAscending(stack));
	}
	
	@Test
	public void testOdd( )
	{
		ArrayDeque<Integer> stack = makeStack(new Integer[]{4, 3, 2, 1, 0});
		
		StackSorter.sort(stack);
		
		Assert.assertTrue(isSortedAscending(stack));
	}
	
	@Test
	public void testDuplicates( )
	{
		ArrayDeque<Integer> stack = makeStack(new Integer[]{-4, 0, 2, 1, 0});
		
		StackSorter.sort(stack);
		
		Assert.assertTrue(isSortedAscending(stack));
	}
}
