package problems.interviews.company1.position0.problem0;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;
import com.google.common.collect.Iterators;
import problems.interviews.company1.position0.problem0.MultiListIterator;

public class TestMultiListIterator
{
	@Test
	public void testExample( )
	{
		List<List<Integer>> list = new LinkedList<>();
		list.add(Arrays.asList(1, 2, 3));
		list.add(Arrays.asList(4, 5));
		list.add(Arrays.asList(6, 7, 8));
		
		Iterator<Integer> iterator = new MultiListIterator<>(list);
		
		Integer[] actuals = Iterators.toArray(iterator, Integer.class);
		Integer[] expecteds = new Integer[]{1, 4, 6, 2, 5, 7, 3, 8};
		Assert.assertArrayEquals(expecteds, actuals);
	}
	
	@Test
	public void testEmptyList( )
	{
		List<List<Integer>> list = new LinkedList<>();
		list.add(Arrays.asList(1, 1));
		list.add(Arrays.asList());
		list.add(Arrays.asList(2, 2));
		
		Iterator<Integer> iterator = new MultiListIterator<>(list);
		
		Integer[] actuals = Iterators.toArray(iterator, Integer.class);
		Integer[] expecteds = new Integer[]{1, 2, 1, 2};
		Assert.assertArrayEquals(expecteds, actuals);
	}
	
	@Test
	public void testEmpty( )
	{
		List<List<Integer>> list = new LinkedList<>();
		
		Iterator<Integer> iterator = new MultiListIterator<>(list);
		
		Integer[] actuals = Iterators.toArray(iterator, Integer.class);
		Integer[] expecteds = new Integer[]{};
		Assert.assertArrayEquals(expecteds, actuals);
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testEmptyNext( )
	{
		List<List<Integer>> list = new LinkedList<>();
		
		Iterator<Integer> iterator = new MultiListIterator<>(list);
		
		iterator.next();
	}
	
	@Test(expected=NullPointerException.class)
	public void testNull( )
	{
		new MultiListIterator<>(null);
	}
}
