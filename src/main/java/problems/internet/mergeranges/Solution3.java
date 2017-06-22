package problems.internet.mergeranges;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;

public class Solution3 implements Solution
{
	public static final Comparator<Range> RANGE_FROM_COMPARATOR = (Range a, Range b)->(a.getFrom() - b.getFrom());

	/**
	 * Approach: Sort ranges from low to high based on from. This means that all ranges which overlap will be grouped together.
	 * As we traverse the list of ranges, merge if necessary.
	 * The only way for a gap to occur in the group is if the range which does not merge were to come before a range which
	 * does merge (has an earlier start position), but since the list is sorted based on start position, this is impossible.
	 * 
	 * Time: O(nlogn + n) => O(nlogn) to sort and iterate where n is the number of ranges.
	 * Space: O(n) to store the merged ranges and sorted list.
	 */
	public Collection<Range> merge( Collection<Range> ranges )
	{
		HashSet<Range> merged = new HashSet<>();
		if( ranges.size() == 0 ) return merged;
		
		ArrayList<Range> rangesSorted = new ArrayList<>(ranges);
		rangesSorted.sort(RANGE_FROM_COMPARATOR);
		
		Range current = rangesSorted.get(0);
		System.out.println("starting: " + current);
		
		for( Range range : rangesSorted.subList(1, rangesSorted.size()) )
		{
			if( range.getFrom() < current.getTo() )
			{
				if( range.getTo() > current.getTo() )
				{
					// merge
					current = new Range(current.getFrom(), (range.getTo() - current.getFrom()));
				}
			}
			else // non-overlapping
			{
				merged.add(current);
				current = range;
			}
		}
		merged.add(current);
		
		return merged;
	}
}
