package problems.crackingthecodinginterview.problem4_5;

import javax.validation.constraints.NotNull;
import datastructures.trees.binarytree.Node;
import static com.google.common.base.Preconditions.checkNotNull;

public class BstValidator
{
	/**
	 * Checks if the given tree starting from node is a valid binary search tree.
	 * 
	 * Approach: Check that BST property is true at each node in the tree. Checking locally that left <= node < right
	 * is not good enough. We must check globally: leftTreeNodes <= node < rightTreeNodes. This can be done by keeping
	 * track of the valid ranges of each subtree as we recurse down.
	 * 
	 * Time: O(n) where n is the number of nodes in the tree.
	 * Space: O(n) for the stack, O(log(n)) if the tree is balanced.
	 * 
	 * 
	 * @param node the node to start checking from.
	 * @return true if the tree starting at node is a valid BST, false otherwise.
	 */
	public static <T extends Comparable<T>> boolean isValid( @NotNull Node<T> node )
	{
		checkNotNull(node);
		
		return isValid(null, null, node);
	}
	
	private static <T extends Comparable<T>> boolean isValid( T min, T max, @NotNull Node<T> node )
	{
		checkNotNull(node);
		
		// check node
		// note: max is set when going left so node <= max
		if( max != null && node.getValue().compareTo(max) > 0 )
		{
			return false;
		}
		
		// note: min is set when going right so node > min
		if( min != null && node.getValue().compareTo(min) <= 0 )
		{
			return false;
		}
		
		// recurse left
		if( node.hasLeft() )
		{
			boolean valid = isValid(min, node.getValue(), node.getLeft());
			if( !valid )
			{
				return false;
			}
		}
		
		// recurse right
		if( node.hasRight() )
		{
			boolean valid = isValid(node.getValue(), max, node.getRight());
			if( !valid )
			{
				return false;
			}
		}
		
		// valid
		return true;
	}
}
