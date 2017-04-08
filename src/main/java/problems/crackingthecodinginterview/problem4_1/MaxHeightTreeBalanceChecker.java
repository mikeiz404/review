package problems.crackingthecodinginterview.problem4_1;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import javax.validation.constraints.NotNull;
import datastructures.trees.TreeNode;

public class MaxHeightTreeBalanceChecker implements TreeBalanceChecker
{
	public static final MaxHeightTreeBalanceChecker INSTANCE = new MaxHeightTreeBalanceChecker();
	
	/**
	 * Calculates the height of the tree when subtree distance <= maxDistance, otherwise -1 is returned.
	 * @param node the node to calculate the height of.
	 * @param maxDistance the maximum distance apart any two subtrees are allowed to be.
	 * @return the height of the tree when subtree distance <= maxDistance, otherwise -1 is returned.
	 */
	private int calcHeight( TreeNode<?, ?> node, int maxDistance )
	{
		checkArgument(maxDistance >= 0, "maxDistance ({}) must be >= 0.");
		
		if( node == null )
		{ // base
			return 0;
		}
		else
		{ // recursive
			Integer minHeight = null;
			int maxHeight = 0;
			
			if( node.getChildren().size() != 0 )
			{
    			for( TreeNode<?, ?> child : node.getChildren() )
    			{
    				int childHeight = calcHeight(child, maxDistance);
    				
    				if( childHeight == -1 )
    				{ // maxDistance exceeded, bail
    					return -1;
    				}
    				
    				maxHeight = Math.max(maxHeight, childHeight);
    				minHeight = (minHeight == null) ? childHeight : Math.min(minHeight, childHeight);
    			}
    			
    			int distance = maxHeight - minHeight;
    			if( distance > maxDistance )
    			{ // maxDistance exceeded, bail
    				return -1;
    			}
			}
			
			return 1 + maxHeight;
		}
	}
	
	/**
	 * Checks whether a tree is balanced by determining if any two subtree heights differ by more than a maximum distance.
	 * @param root the root of the tree
	 * @param maxDistance the maximumDistance any two subtree heights are allowed to differ by.
	 * 
	 * Time: O(n) where n is the number of nodes in the tree.
	 * Space: O(h) where h is the maximum height of the tree, and the maximum stack size. If the tree is roughly balanced
	 * then h will be around log(n).
	 * 
	 * note: does poorly on balanced trees and trees which have balanced lower subtrees but unbalanced higher subtrees.
	 */
	@Override
	public boolean isBalanced( @NotNull TreeNode<?, ?> root, int maxDistance )
	{
		checkNotNull(root);
		
		return calcHeight(root, maxDistance) != -1;
	}
}
