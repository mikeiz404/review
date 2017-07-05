package problems.crackingthecodinginterview.problem9_4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution2
{
	/**
	 * Approach: Use a bitset to keep track of which items are being held out. Iterate from 1 to 2^n-2 to generate all subsets.
	 * 
	 * Time: O( 2^n-3 * n ) => O(2^n) where n is the number of items in the set.
	 * Space: O( 2^n-3 ) => O(2^n) for subsets.
	 * 
	 * @param items the set of items to create subsets from.
	 * @return all possible subsets of the items.
	 */
	public static <T> Set<Set<T>> makeAllSubsets( Collection<T> items )
	{
		Set<Set<T>> subsets = new HashSet<>();
		List<T> list = new ArrayList<>(items);
		
		// note: max is 2^n-2 since we do not want to include all items
		int max = (int) (Math.pow(2, items.size())) - 2;
		// note: starting from 1 since we do not want to include the empty set
		for( int i=1; i<=max; i++ )
		{
			// iterate over mask and add if set
			int bits = i;
			Set<T> subset = new HashSet<>();
			for( int j=0; j<items.size(); j++ )
			{
				if( (bits & 0b1) == 1 )
				{
					subset.add(list.get(j));
				}
				
				bits = bits >> 1;
			}
			subsets.add(subset);
		}
		
		return subsets;
	}
}
