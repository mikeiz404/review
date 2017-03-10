package problems.internet.shiftedByNSortedArray;

import java.util.Comparator;
import java.util.List;
import javax.validation.constraints.NotNull;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

/**
 * https://careercup.com/question?id=5709893685215232
 * """
 * Given a sorted array which has been rotated n number of times. Find the value of n.
 * """
 */
public class ShiftedByNSortedArray
{
	public static <T extends Comparable<T>> int calcMinN( @NotNull List<T> a )
	{
		return calcMinN(a, Comparator.naturalOrder());
	}
	
	/**
	 * Finds the minimum amount the sorted list has been shifted by.
	 * 
	 * Time: O(log(n)) where n is the size of the list.
	 * Space: O(log(n)) for the stack. Note: Could implement iteratively to avoid the stack cost by implementing
	 * tail-call optimization.
	 * 
	 * @param a sorted list of data.
	 * @param c comparator that the list was sorted with.
	 * @return the position the start of the list begins at.
	 */
	public static <T extends Comparable<T>> int calcMinN( @NotNull List<T> a, @NotNull Comparator<T> c )
	{
		return calcMinN(a, c, 0);
	}
	
	private static <T extends Comparable<T>> int calcMinN( @NotNull List<T> a, @NotNull Comparator<T> c, int offset )
	{
		checkNotNull(a);
		checkNotNull(c);
		checkArgument(offset >= 0, "Offset ({}) must be >= 0.", offset);
		
		if( a.size() == 0 || a.size() == 1 )
		{ // base: trivial
			return offset;
		}
		else if( a.size() == 2 )
		{
			T left = a.get(0);
			T middle = a.get(1);
			
			int n;
			if( left.compareTo(middle) > 0 )
			{ // not in order
				// shifted by 1
				n = offset + 1;
			}
			else // left.compareTo(middle) <= 0
			{ // in order
				// not shifted
				n = offset;
			}
			
			return n;
		}
		else // a.length > 2
		{
			// recursive: divide
			final int leftIndex = 0;
			final int middleIndex = a.size() / 2;
			final int rightIndex = a.size() - 1;
			T left = a.get(leftIndex);
			T middle = a.get(middleIndex);
			T right = a.get(rightIndex);
			
			if( left.compareTo(middle) > 0 )
			{ // not in order: starting item is between (left, middle]
				int newOffset = offset + (leftIndex + 1);
				List<T> subA = a.subList((leftIndex + 1), (middleIndex + 1));
				
				return calcMinN(subA, c, newOffset);
			}
			else if( middle.compareTo(right) > 0 )
			{ // not in order: starting item is between (middle, right]
				int newOffset = offset + (middleIndex + 1);
				List<T> subA = a.subList((middleIndex + 1), (rightIndex + 1));
				
				return calcMinN(subA, c, newOffset);
			}
			else // left.compareTo(middle) <= 0 && middle.compareTo(right) <= 0
			{ // in order
				return offset;
			}
		}
	}
}
