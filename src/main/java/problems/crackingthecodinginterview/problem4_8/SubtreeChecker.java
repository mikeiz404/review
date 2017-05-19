package problems.crackingthecodinginterview.problem4_8;

import datastructures.trees.TreeNode;
import datastructures.trees.mappedchildrennode.MappedChildrenNode;
import static com.google.common.base.Preconditions.checkNotNull;
import java.util.Collection;
import javax.validation.constraints.NotNull;
import static com.google.common.base.Preconditions.checkArgument;

public class SubtreeChecker
{
	private static class Result
	{
		public final int height;
		public final boolean found;
		
		public Result( int height, boolean found )
		{
			this.height = height;
			this.found = found;
		}
	}
	
	/**
	 * Determine if a particular subtree exists.
	 * 
	 * Approach: Determine the level at which the subtree's root node might be at and then check for subtree equality.
	 * 
	 * Time: O( m + n + n*m ) => O(n*m) => n >> m: O(n) where n is the number of nodes in the tree and m is the
	 * number of nodes in the subtree. 
	 * Space: O( j + h + j ) => O(h+j) => h >> j: O(h) where h is the height of the tree and j is the height of the
	 * subtree.
	 * 
	 * Note: Could improve average time cost by looking for subtree base shape instead of just max height from leaf
	 * but this would increase space cost by m and add to complexity.
	 * 
	 * @param tree root of tree.
	 * @param subtree root of subtree.
	 * @return true if subtree is a subtree of tree.
	 */
	public static <N extends MappedChildrenNode<N, K, V>, K, V> boolean check( @NotNull N tree, @NotNull N subtree )
	{
		checkNotNull(tree);
		checkNotNull(subtree);
		
		int subHeightMax = calcMaxHeight(subtree);
		
		Result result = helper(subtree, subHeightMax, tree);
		
		return result.found;
	}
	
	private static <N extends MappedChildrenNode<N, K, V>, K, V> Result helper( @NotNull N subtree, int targetHeight, @NotNull N tree )
	{
		checkNotNull(tree);
		checkNotNull(subtree);
		checkArgument(targetHeight >= 0, "targetHeight must be >= 0.");
		
		// calc max height from leaf
		// edge: leaf node is handled
		int childHeightMax = 0;
		for( N child : tree.getChildren() )
		{
			Result result = helper(subtree, targetHeight, child);
			
			if( result.found )
			{
				// note: result.height is not needed any more
				return result;
			}
			
			childHeightMax = Math.max(result.height, childHeightMax);
		}
		
		int nodeHeight = childHeightMax + 1;
		
		// check or return
		if( nodeHeight == targetHeight )
		{
			// check for subtree
			boolean found = isSameTree(tree, subtree);
			return new Result(nodeHeight, found);
		}
		else // heightMax != targetHeight
		{
			// note: OK to always return false here since when found is true, it is always returned earlier
			return new Result(nodeHeight, false);
		}
	}
	
	/*
	 * Time: O(n).
	 * Space: O(h).
	 */
	private static <N extends TreeNode<N, V>, V> int calcMaxHeight( N node )
	{
		int height = 0;
		for( N child : node.getChildren() )
		{
			height = Math.max(calcMaxHeight(child), height);
		}
		
		return height + 1;
	}
	
	/*
	 * Time: O(min(n, m)) => O(n + m) where n is the number of nodes in tree a and m is the number of nodes in the tree m.
	 * Space: O(min(h, j)) => O(h + j) where h is the height of tree a and j is the height of tree b.
	 */
	private static <N extends MappedChildrenNode<N, K, V>, K, V> boolean isSameTree( @NotNull N a, @NotNull N b )
	{
		checkNotNull(a);
		checkNotNull(b);
		
		if( !a.equals(b) )
		{
			return false;
		}
		
		// check children
		Collection<K> aChildKeys = a.getChildrenKeys();
		Collection<K> bChildKeys = b.getChildrenKeys();
		
		if( !aChildKeys.equals(bChildKeys) )
		{
			return false;
		}
		
		for( K key : aChildKeys )
		{
			N aChild = a.getChild(key);
			N bChild = b.getChild(key);
			
			if( !isSameTree(aChild, bChild) )
			{
				return false;
			}
		}
		
		return true;
	}
}
