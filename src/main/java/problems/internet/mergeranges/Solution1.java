package problems.internet.mergeranges;

import java.util.Collection;
import java.util.HashSet;

public class Solution1 implements Solution
{
	/**
	 * Approach: Iterate over each range and mark in a new array which areas have been covered. Recover the merged ranges
	 * from this array. To avoid merging immediate neighbors start and continuation indicators are used to mark boundaries.
	 * 
	 * Time: O(s) where s is the sum of each range width. 
	 * Space: O(w) where w is the width of the min range from to the max range to.
	 * 
	 */
	public Collection<Range> merge( Collection<Range> ranges )
	{
		HashSet<Range> merged = new HashSet<>();
		if( ranges.size() == 0 ) return merged;
		
		// find min max range bounds
		int min = 0;
		int max = 0;
		boolean first = true;
		for( Range range : ranges )
		{
			if( first )
			{
				min = range.getFrom();
				max = range.getTo();
				first = false;
			}
			else // !first
			{
				min = Math.min(range.getFrom(), min);
				max = Math.max(range.getTo(), max);
			}
		}
		
		// mark as covered
		int width = max - min + 1;
		char[] covered = new char[width];
		
		for( Range range : ranges )
		{
			if( covered[range.getFrom() - min] != 'c' ) covered[range.getFrom() - min] = 's';
			for( int i = range.getFrom() + 1; i<range.getTo(); i++ )
			{
				covered[i - min] = 'c';
			}
		}
		
		// extract merged ranges
		int i=0;
		while( i < width )
		{
			if( covered[i] == 's' )
			{ // start
				int start = i + min;
				int len = 1;
				
				// find end
				i++;
				while( i < width && covered[i] == 'c' )
				{
					i++;
					len++;
				}
				
				merged.add(new Range(start, len));
			}
			else
			{
				i++;
			}
		}
		
		return merged;
	}
}
