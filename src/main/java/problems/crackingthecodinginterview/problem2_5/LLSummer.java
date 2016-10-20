package problems.crackingthecodinginterview.problem2_5;

import java.util.Iterator;
import java.util.LinkedList;
import javax.validation.constraints.NotNull;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

public class LLSummer implements LLSummerInterface
{
	/**
	 * Sums two linked lists which represent a single number each. Each list stores the number as a sequence of digits.
	 * A similarly formatted linked list is returned, representing the sum.
	 * 
	 * Approach: Iterate over each linked list in parallel in reverse order, summing the two digits plus the carry and
	 * appending to the start the result % 10 to the result list. If the end of one list is reached, assume 0 for the
	 * remaining iterations in the other list. When the ends of both lists are reached, check for a final carry and
	 * append 1 to the start of the result if necessary.
	 * 
	 * Time: O(2n) => O(n) where n represents the size of list a + the size of list b.
	 * Space: O(n/2 + 1) => O(n).
	 * 
	 * @param a linked list of digits to use in sum.
	 * @param b linked list of digits to use in sum.
	 * @return linked list of digits representing the sum of a and b.
	 */
	public LinkedList<Integer> sum( @NotNull LinkedList<Integer> a, @NotNull LinkedList<Integer> b )
	{
		checkNotNull(a);
		checkNotNull(b);
		checkArgument(!a.isEmpty());
		checkArgument(!b.isEmpty());
		
		Iterator<Integer> aIt = a.descendingIterator();
		Iterator<Integer> bIt = b.descendingIterator();
		LinkedList<Integer> result = new LinkedList<>();
		int carry = 0;
		
		while( aIt.hasNext() || bIt.hasNext() )
		{
			Integer aVal = aIt.hasNext() ? aIt.next() : 0;
			Integer bVal = bIt.hasNext() ? bIt.next() : 0;
			
			Integer sum = aVal + bVal + carry;
			Integer digit = sum % 10;
			carry = (sum >= 10) ? 1 : 0;
			
			result.addFirst(digit);
		}
		
		if( carry != 0 )
		{
			result.addFirst(1);
		}
		
		return result;
	}
}
