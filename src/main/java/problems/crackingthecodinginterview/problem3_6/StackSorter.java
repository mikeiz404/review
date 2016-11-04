package problems.crackingthecodinginterview.problem3_6;

import java.util.ArrayDeque;
import java.util.Deque;
import static com.google.common.base.Preconditions.checkNotNull;

public class StackSorter
{
	/**
	 * Sorts a stack in ascending order.
	 * 
	 * Approach: Two stacks will be used to hold half of the list sorted ascending, and the other half sorted descending.
	 * To do this start by finding the min and max by popping the original stack onto a new stack. Place the min on the
	 * original stack. Drop the min and max items from the new stack and transfer the remaining items onto the original
	 * stack. Push the max onto the new stack. Repeat this process on the remaining unsorted items. Once the unsorted
	 * items count is <= 1, the original stack will be sorted ascending starting from the stack min with ~n/2 items and
	 * the new stack will be sorted descending from the list max with n/2 items. Transfer the remaining items from the
	 * new stack onto the original stack to complete the sort.
	 * 
	 * Note: Since you have at most a 2 index wide window into the list which needs to be sorted, it does not seem like
	 * one can do better than O(n^2) since partial sorts/partitioning will require moving an item some ratio of n moves.
	 * 
	 * Time: O(2 * (n + n-1 + n-2 + ... + 2) + n/2) => O(2 * (n*(n-1)/2) + n/2) => O(2 * n^2 + n/2) => O(n^2) where n is
	 * the number of items in the stack.
	 * Space: O(1).
	 * 
	 * @param stack the stack to sort.
	 */
	public static <T extends Comparable<T>> void sort( Deque<T> stack )
	{
		checkNotNull(stack);
		
		Deque<T> minStack = stack;
		Deque<T> maxStack = new ArrayDeque<>(stack.size());
		
		if( stack.size() <= 1 )
		{ // already sorted
			return;
		}
		else // stack.size() > 1
		{ // sort
			int unsortedLen = stack.size();
			
			while( unsortedLen > 1 )
			{
				T min = stack.getLast();
				T max = stack.getLast();
				int minIndex = 0;
				int maxIndex = 0;
				
				// find min and max
				for( int i=0; i<unsortedLen; i++ )
				{
					T val = minStack.removeLast();
					maxStack.addLast(val);
					
					// note: min is < and max is >= to guarantee that min and max indexes will never be equal when the
					// unsorted length is greater than 1 and duplicates exist in the list.
					
					if( val.compareTo(min) < 0 )
					{
						min = val;
						minIndex = i;
					}
					
					if( val.compareTo(max) >= 0 )
					{
						max = val;
						maxIndex = i;
					}
				}
				
				// place min
				minStack.addLast(min);
				
				// reset stacks, and drop min and max
				// note: indexing is done in reverse to maintain consistent item indexes
				for( int i=(unsortedLen - 1); i>=0; i-- )
				{
					T val = maxStack.removeLast();
					if( i != minIndex && i != maxIndex )
					{ // transfer
						minStack.addLast(val);
					}
				}
				
				// place max
				maxStack.addLast(max);
				
				// update length
				unsortedLen -= 2;
			}
			
			// transfer sorted max stack onto the original min stack
			while( !maxStack.isEmpty() )
			{
				minStack.addLast(maxStack.removeLast());
			}
		}
	}
}
