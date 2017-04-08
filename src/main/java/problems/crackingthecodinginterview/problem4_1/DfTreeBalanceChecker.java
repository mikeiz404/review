package problems.crackingthecodinginterview.problem4_1;

import datastructures.trees.TreeNode;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import javax.validation.constraints.NotNull;

public class DfTreeBalanceChecker implements TreeBalanceChecker
{
	public static DfTreeBalanceChecker INSTANCE = new DfTreeBalanceChecker();
	
	public static class MinMax
	{
		private final int min;
		private final int max;
		
		public MinMax( int min, int max )
		{
			this.min = min;
			this.max = max;
		}
		
		public int getMin( )
		{
			return this.min;
		}
		
		public int getMax( )
		{
			return this.max;
		}
	}
	
	/**
	 * Determines if a tree is balanced by checking that the distance between any leaf is no greater than the max
	 * distance in a depth first fashion.
	 * @param root node to start checking from. Must not be null.
	 * @param maxDistance maximum distance two leafs are allowed to be apart and still be considered balanced.Must be
	 * non-negative.
	 * 
	 * Time: O(n) where n is the number of nodes in the tree. All nodes must be checked for leaf status and level.
	 * Space: O(h) for the call stack where h is the height of the tree.
	 */
	@Override
	public boolean isBalanced( TreeNode<?, ?> root, int maxDistance )
	{
		checkNotNull(root);
		checkArgument(maxDistance >= 0, "maxDistance ({}) must be >= 0.");
		
		MinMax minMax = calcMinMaxLeafDistance(root);
		int distance = minMax.getMax() - minMax.getMin();
		
		return (distance <= maxDistance);
	}
	
	public MinMax calcMinMaxLeafDistance( TreeNode<?, ?> root )
	{
		return calcLeafMinMaxDistance(root, 0);
	}
	
	/*
	 * note: could terminate early by passing the current min down but this would complicate logic.
	 * 
	 * note: this implementation with early termination will do better on large full unbalanced trees which have all leaves
	 * towards the bottom and most of the bottom subtrees are unbalanced when compared to the breadth first implementation.
	 * The early termination implementation will also favor trees with unbalanced subtrees towards the start of the child
	 * iteration order (ex: left side when a left to right iteration order is used).
	 * 
	 * note: the early termination implementation does poorly on balanced trees and unbalanced trees with mostly balanced
	 * subtrees towards the start of the child iteration order.
	 */
	private MinMax calcLeafMinMaxDistance( @NotNull TreeNode<?, ?> node, int level )
	{
		checkNotNull(node);
		checkArgument(level >= 0, "Level ({}) must be >= 0.");
		
		// max and child min
		int max = level;
		Integer childMin = null;
		
		for( TreeNode<?, ?> child : node.getChildren() )
		{
			MinMax childMinMax = calcLeafMinMaxDistance(child, (level + 1));
			max = Math.max(max, childMinMax.getMax());
			
			childMin = (childMin == null) ? childMinMax.getMin() : Math.min(childMin, childMinMax.getMin());
		}
		
		// min
		int min;
		
		if( node.isLeaf() )
		{
			min = level;
		}
		else // !node.isLeaf()
		{
			min = childMin;
		}
		
		return new MinMax(min, max);
	}
	
}
