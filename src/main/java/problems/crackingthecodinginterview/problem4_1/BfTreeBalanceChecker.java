package problems.crackingthecodinginterview.problem4_1;

import java.util.ArrayDeque;
import java.util.Deque;
import javax.validation.constraints.NotNull;
import datastructures.trees.TreeNode;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class BfTreeBalanceChecker implements TreeBalanceChecker
{
	public static final BfTreeBalanceChecker INSTANCE = new BfTreeBalanceChecker();
	
	/**
	 * Determines if a tree is balanced by checking that the distance between any leaf is no greater than the max
	 * distance in a breadth first fashion.
	 * @param root node to start checking from. Must not be null.
	 * @param maxDistance maximum distance two leafs are allowed to be apart and still be considered balanced. Must be non-negative.
	 * 
	 * Time: O(n) where n is the number of nodes in the tree. All nodes must be checked for leaf status and level.
	 * Space: O(n) for the queue.
	 * 
	 * note: will perform the best on unbalanced trees which have leaves higher up.
	 * note: will perform the worst on balanced trees and unbalanced trees which have all their leaves towards the bottom.
	 */
	@Override
	public boolean isBalanced( @NotNull TreeNode<?, ?> root, int maxDistance )
	{
		checkNotNull(root);
		checkArgument(maxDistance >= 0, "maxDistance ({}) must be >= 0.");
		
		int level = 0;
		int levelCount = 1;
		int nextLevelCount = 0;
		
		Integer min = null;
		Integer max = null;
		
		Deque<TreeNode<?, ?>> que = new ArrayDeque<>();
		que.addLast(root);
		
		while( !que.isEmpty() )
		{
			TreeNode<?, ?> node = que.removeFirst();
			levelCount--;
			
			// update leaf distances
			if( node.isLeaf() )
			{
				if( min == null )
				{ // note: the min will only ever be set once since we are exploring in a BFS fashion.
					min = level;
				}
				
				// note: the max will only ever get bigger since we are exploring in a BFS fashion.
				max = level;
				
				// terminate early if possible
				if( (max - min) > maxDistance )
				{
					return false;
				}
			}
			
			// push children
			for( TreeNode<?, ?> child : node.getChildren() )
			{
				que.addLast(child);
				
				nextLevelCount++;
			}
			
			// handle level change
			if( levelCount == 0 )
			{
				level++;
				
				levelCount = nextLevelCount;
				nextLevelCount = 0;
			}
		}
		
		return (max - min) <= maxDistance;
	}
}
