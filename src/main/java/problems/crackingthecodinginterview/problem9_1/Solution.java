package problems.crackingthecodinginterview.problem9_1;

import static com.google.common.base.Preconditions.checkArgument;


public class Solution
{
	public int computeWays( int n )
	{
		checkArgument(n >= 0, "n must be >= 0.");
		
		if( n == 0 )
		{
			return 0;
		}
		else
		{
			return computeWaysRec(new int[n + 1], n);
		}
	}
	
	/**
	 * Time: O(n) since n new subproblems will need to be computed, where n is the number of steps. Without memoization
	 * the runtime would be O(3^n) since each call branches by a factor of 3 with a total stack height of n.
	 * Space: O(n) for the stack and cache.
	 * @param n number of steps.
	 * @return number of ways.
	 */
	private int computeWaysRec( int[] cache, int n )
	{
		if( n < 0 )
		{ // base: not a way
			return 0;
		}
		else if( n == 0 )
		{ // base: end of a way
			return 1;
		}
		else
		{ // recursive
			if( cache[n] !=0 )
			{ // cache hit
				return cache[n];
			}
			else
			{ // compute
				return computeWaysRec(cache, (n - 1)) + computeWaysRec(cache, (n - 2)) + computeWaysRec(cache, (n - 3));
			}
		}
	}
}
