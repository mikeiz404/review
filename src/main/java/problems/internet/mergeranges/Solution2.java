package problems.internet.mergeranges;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;

public class Solution2 implements Solution
{
	public static final Comparator<Range> RANGE_WIDTH_COMPARATOR = (Range a, Range b)->-(a.getWidth() - b.getWidth());
	public static final Comparator<Range> RANGE_FROM_COMPARATOR = (Range a, Range b)->-(a.getFrom() - b.getFrom());

	/**
	 * Approach: If we can find merged ranges near a given point, we can look at those near it to determine if an overlap exists.
	 * This does not help our runtime very much since we will have to look at up to n ranges. However if we can guarantee that the merged ranges will
	 * always be >= in width to the new ranges, then we only need to look at the left and right merged ranges near a new range and check if they overlap.
	 * By sorting the ranges from high to low based on width, we can satisfy the merged ranges width requirement. To look up the left and right neighbors
	 * of a given point quickly, we can store the ranges in a balanced binary search tree.
	 * 
	 * Time: O( nlog(n) + nlog(n) ) => O(nlog(n)) for sorting and adding of each range for merging where n is the number of ranges.
	 * Space: O(n) for the tree and sorted list.
	 */
	public Collection<Range> merge( Collection<Range> ranges )
	{
		HashSet<Range> merged = new HashSet<>();
		if( ranges.size() == 0 ) return merged;
		
		// sort from high to low widths
		// note: by sorting we can guarantee that the merged ranges will always be greater than or equal to an added range
		ArrayList<Range> sortedRanges = new ArrayList<>(ranges);
		sortedRanges.sort(RANGE_WIDTH_COMPARATOR);
		
		// insert merged ranges into tree
		BsTree<Range> tree = new BsTree<>(RANGE_FROM_COMPARATOR);
		tree.insert(sortedRanges.get(0));
		
		for( int i=1; i<sortedRanges.size(); i++ )
		{
			Range range = sortedRanges.get(i);
			
			// note: since merged range widths >= added range we only need to check up to two merged ranges for overlap per added range
			// find closest ranges on left and right of added range start position
			BsTree<Range>.Node left = tree.findNodeOrNextSmaller(range);
			BsTree<Range>.Node right = tree.findNodeOrNextLarger(range);
			
			// check for overlap
			// note: impossible for left and right to be null
			boolean overlapsLeft = left != null && range.overlaps(left.getValue());
			boolean overlapsRight = right != null && range.overlaps(right.getValue());
			
			// partially covered: left, right
			if( overlapsLeft && overlapsRight )
			{ // range overlaps two merge ranges
				int from = left.getValue().getFrom();
				int width = right.getValue().getTo() - from;
				
				left.setValue(new Range(from, width));
				right.remove();
			}
			else if( overlapsLeft ) // && !overlapsRight
			{
				Range leftRange = left.getValue();
				if( range.getTo() > leftRange.getTo() )
				{ // new range expands old range
    				int from = leftRange.getFrom();
    				int width = range.getTo() - leftRange.getFrom();
    				left.setValue(new Range(from, width));
				}
			}
			else if( overlapsRight ) // && !overlapsLeft
			{ // must expand old range since new range width must be <= old range width
				Range rightRange = right.getValue();
				int from = range.getFrom();
				int width = rightRange.getTo() - from;
				
				left.remove();
				tree.insert(new Range(from, width));
			}
			else
			{ // no overlap
				tree.insert(range);
			}
		}
		
		return tree.inOrder();
	}
}
