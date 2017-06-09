package problems.crackingthecodinginterview.problem5_7;

import java.util.ArrayList;
import java.util.List;

public class Solution2 extends AbstractSolution
{
	/**
	 * Approach: If we can determine what a list of `n` numbers will look like after a number `v` is removed when `v`
	 * has a 1 or a 0 at bit position `p`, and there are a unique set of states, then we can map the states of the
	 * numbers without `v` back to whether `v` is a 0 or 1 at `p`. (See book for table of states). We can reduce the numbers to
	 * contain only the numbers which match the current suffix of `v`. We can reapply the reverse mapping from above as
	 * long as 1) the new list has the same preconditions as the original list (count(0s @ `p`) == count(1s @ `p`) ||
	 * count(0s @ `p`) == count(1s @ `p`) + 1) and 2) `v` will have been removed from the list. Since the list of numbers
	 * is continuous, removing numbers based on a consistent bit value at `p - 1` will halve the count of zeros and ones
	 * equally, so 1 holds. If we chose to keep only the numbers where `number[0:p] == v[0:p]` then `v` would have been
	 * apart of this list if it were not removed, so 2 holds.
	 * 
	 * Time: O( n + n/2 + n/4 + ... + 1 ) => Geometric series: O( n/(1 - 1/2) ) => O( 2n ) => O(n) where n is the number
	 * of items in numbers.
	 * Space: O(n).
	 * 
	 * @param a array of numbers.
	 * @return the missing number.
	 */
	public int findMissingNumber( List<Integer> numbers )
	{
		int bitsMax = (int) (Math.log(numbers.size()) / Math.log(2));
		
		int v = 0;
		for( int p=0; p<=bitsMax; p++ )
		{
			ArrayList<Integer> zeros = new ArrayList<>();
			ArrayList<Integer> ones = new ArrayList<>();
			
    		for( int number : numbers )
    		{
    			if( getBit(number, p) == 0 )
    			{
    				zeros.add(number);
    			}
    			else
    			{
    				ones.add(number);
    			}
    		}
    		
    		// determine v[p]
    		if( zeros.size() <= ones.size() )
    		{ // zeros.size() == ones.size() - 1 || zeros.size() == ones.size()
    			// v[p] = 0
    			// note: no need to set v as it will already be 0
    			numbers = zeros;
    		}
    		else
    		{ // zeros.size() == ones.size() + 1 || zeros.size() == ones.size() + 2
    			// v[p] == 1 
    			v |= 1 << p;
    			numbers = ones;
    		}
		}
		
		return v;
	}
}
