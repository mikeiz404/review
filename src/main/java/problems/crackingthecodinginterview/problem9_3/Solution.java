package problems.crackingthecodinginterview.problem9_3;

public class Solution
{
	public static int findMagicIndex( int[] a )
	{
		return findMagicIndex(a, 0, a.length);
	}
	
	/**
	 * Approach: Similar to binary search: compare middle and recurse on left and right valid subranges when not found.
	 * Note: In the worst case linear search will perform just as well and only use O(1) space. However this recursive
	 * solution will do better on time on average.
	 * 
	 * Time: O(n - log(n)) => O(n) where n is the number of items in the array.
	 * Space: O(log(n)) for stack.
	 * 
	 * @param a array to search.
	 * @param from index to search from.
	 * @param to index to search to.
	 * @return the magic index, or -1 if not found.
	 */
	public static int findMagicIndex( int[] a, int from, int to )
	{
		if( to > from && from >= 0 )
		{
			int i = from + (to - from)/2;
			if( a[i] == i )
			{ // base: found
				return i;
			}
			else
			{ // recursive
				int leftTo = (a[i] < i) ? a[i] : (i - 1);
				int rightFrom = (a[i] > i) ? a[i] : (i + 1);
				
				// check left
				int leftIndex = findMagicIndex(a, from, leftTo);
				if( leftIndex != -1 ) return leftIndex;
				
				// check right
				int rightIndex = findMagicIndex(a, rightFrom, to);
				if( rightIndex != -1 ) return rightIndex;
				
				// not found
				return -1;
			}
		}
		else
		{ // base: out of bounds
			return -1;
		}
	}
}
