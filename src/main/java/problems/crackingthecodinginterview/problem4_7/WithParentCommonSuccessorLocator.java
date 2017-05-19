package problems.crackingthecodinginterview.problem4_7;

import javax.validation.constraints.NotNull;
import datastructures.trees.TreeNode;
import static com.google.common.base.Preconditions.checkNotNull;

public class WithParentCommonSuccessorLocator
{
	/**
	 * Return the common ancestor of nodes a and b by making use of getParent.
	 * 
	 * Approach: Find distance difference between each node to the root. This will allow us to synchronize the paths
	 * from each node so that only up to the height of the tree comparisons are needed to determine when path intersection
	 * first occurs. Since a common path can only exist between the root and s concurrent descendants, where s is the
	 * number of nodes in the shortest path, this approach of skipping the longer path's nodes from the starting node
	 * will not effect correctness.
	 * 
	 * Time:
	 * 	Balanced Tree: O(2log(n) + log(n)) => O(log(n)) where n represents the number of nodes in the tree.
	 * 	Unbalanced Tree: O(2n + n) => O(n).
	 * Space: O(1).
	 * 
	 * @param a a node to find the common ancestor of.
	 * @param b a node to find the common ancestor of.
	 * @return first common ancestor.
	 */
	public static <N extends TreeNode<N, V>, V> N locate( @NotNull N a, @NotNull N b )
	{
		checkNotNull(a);
		checkNotNull(b);
		
		// edge: nodes are the same and is the root
		if( a == b && a.isRoot() && b.isRoot() )
		{
			return null;
		}
		
		// calc distance
		int aDistance = calcRootDistance(a);
		int bDistance = calcRootDistance(b);
		
		// synchronize
		if( aDistance > bDistance )
		{
			int difference = aDistance - bDistance;
			for( int i=0; i<difference; i++ )
			{
				a = a.getParent();
			}
		}
		else // aDistance <= bDistance
		{
			int difference = bDistance - aDistance;
			for( int i=0; i<difference; i++ )
			{
				b = b.getParent();
			}
		}
		
		// check
		while( a.hasParent() && b.hasParent() )
		{
			if( a == b )
			{ // note: assuming nodes are in the same tree so a general equality check is not needed
				return a;
			}
			
			a = a.getParent();
			b = b.getParent();
		}
		
		if( a == b )
		{ // note: assuming nodes are in the same tree so a general equality check is not needed
			return a;
		}
		
		// edge: nodes are not in the same tree
		return null;
	}
	
	private static <N extends TreeNode<N, V>, V> int calcRootDistance( @NotNull N node )
	{
		checkNotNull(node);
		
		int i=0;
		while( node.hasParent() )
		{
			node = node.getParent();
			i++;
		}
		
		return i;
	}
}
