package problems.crackingthecodinginterview.problem9_4;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Solution1
{
	public static <T> Set<Set<T>> makeAllSubsets( Collection<T> items )
	{
		Set<Set<T>> subsets = new HashSet<>();
		makeAllSubsets(subsets, items);
		
		return subsets;
	}
	
	/**
	 * Approach: For each item take one item out of the set, add it to the subsets, and recurse on it. If the length of
	 * the set is 0, return an empty set. Use sets to ignore duplicates.
	 * 
	 * Time: O( n! ) where n is the number of items in the set.
	 * Space: O( (2^n-1) + (n - 1 + n - 2 + ... + 1) ) => O(2^n) for storing the sublists.
	 * 
	 * @param items the set of items to create subsets from.
	 * @return all possible subsets of the items.
	 */
	protected static <T> void makeAllSubsets( Set<Set<T>> subsets, Collection<T> items )
	{
		if( items.size() == 1 )
		{ // base
			return;
		}
		else
		{ // recursive
			for( T item : items )
			{
				Set<T> subset = new HashSet<>(items);
				subset.remove(item);
				
				// add
				subsets.add(subset);
				
				// recurse
				subsets.addAll(makeAllSubsets(subset));
			}
		}
	}
}
