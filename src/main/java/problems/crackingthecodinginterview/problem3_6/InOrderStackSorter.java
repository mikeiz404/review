package problems.crackingthecodinginterview.problem3_6;

import java.util.ArrayDeque;
import java.util.Deque;

public class InOrderStackSorter implements StackSorter
{
	public static final MinMaxStackSorter INSTANCE = new MinMaxStackSorter();

	/**
	 * Sorts a stack in ascending order using at most one other stack.
	 * 
	 * Approach: Place items from the unsorted stack into the correct position in the sorted stack. This is done by
	 * popping an item from the unsorted stack, setting it aside, transferring all items in the sorted stack into the
	 * unsorted stack which are greater than the item, and push the item onto the sorted stack. This process is repeated
	 * until the unsorted stack is empty.
	 * 
	 * Null items are not allowed.
	 * 
	 * Space: O((0 + 1 + 2 + ... + n-1) + n + n) => O(n*(n-1)/2 + 2n) => O(n^2 + 2n) => O(n^2) where n is the number of
	 * items to be sorted.
	 * Time: O(n).
	 */
	@Override
	public <T extends Comparable<T>> void sort( Deque<T> stack )
	{
		Deque<T> sorted = new ArrayDeque<>(stack.size());
		
		while( !stack.isEmpty() )
		{
			T item = stack.removeLast();
			
			// transfer other items until correct position is found
			// note: sorting descending first so can be push onto stack in ascending order
			while( !sorted.isEmpty() && sorted.peekLast().compareTo(item) < 0 )
			{
				stack.addLast(sorted.removeLast());
			}
			
			// place item
			sorted.addLast(item);
		}
		
		// transfer back
		while( !sorted.isEmpty() )
		{
			stack.addLast(sorted.removeLast());
		}
	}
}
