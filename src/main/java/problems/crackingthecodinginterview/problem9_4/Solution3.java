package problems.crackingthecodinginterview.problem9_4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.google.common.collect.ImmutableSet;

public class Solution3
{
	public static <T> Set<Set<T>> makeAllSubsets( Collection<T> items )
	{
		Set<Set<T>> subsets = makeAllSubsetsRec(new ArrayList<>(items));
		subsets.remove(ImmutableSet.copyOf(items));
		
		return subsets;
	}
	
	/**
	 * Approach: L is our list of items. Build off of smaller case of l[1:] by adding additional subsets created by
	 * adding l[0] to each of the smaller subsets.
	 * 
	 * Time: O( 2^n-2 ) => O(2^n) where n is the number of items in the set.
	 * Space: O( 2^n-2 ) => O(2^n) for subsets.
	 * 
	 * @param items the set of items to create subsets from.
	 * @return all possible subsets of the items.
	 */
	protected static <T> Set<Set<T>> makeAllSubsetsRec( List<T> items )
	{
		if( items.size() == 0 )
		{ // base
			return new HashSet<>();
		}
		else if( items.size() == 1 )
		{ // base
			Set<Set<T>> subsets = new HashSet<>();
			Set<T> subset = new HashSet<>();
			subsets.add(subset);
			
			subset.add(items.get(0));
			
			return subsets;
		}
		else
		{ // recursive
			T heldOut = items.get(0);
			
			Set<Set<T>> subsets = makeAllSubsetsRec(items.subList(1, items.size()));
			Set<Set<T>> newSubsets = new HashSet<>(subsets);
			
			for( Set<T> subset : subsets )
			{
				HashSet<T> newSubset = new HashSet<>(subset);
				newSubset.add(heldOut);
				
				newSubsets.add(newSubset);
			}
			
			// note: adding single item since empty sets are not being included
			HashSet<T> newSubset = new HashSet<>();
			newSubset.add(heldOut);
			newSubsets.add(newSubset);
			
			return newSubsets;
		}
	}
}
