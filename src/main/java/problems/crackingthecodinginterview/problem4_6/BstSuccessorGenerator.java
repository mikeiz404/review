package problems.crackingthecodinginterview.problem4_6;

import javax.validation.constraints.NotNull;
import datastructures.trees.binarytree.Node;
import static com.google.common.base.Preconditions.checkNotNull;
import java.util.NoSuchElementException;

public class BstSuccessorGenerator
{
	/**
	 * Returns the node's in-order successor from a binary search tree.
	 * 
	 * Approach: Back out edge-cases from a recursive in-order traversal to an iterative one.
	 * 
	 * Time: where n is the number of nodes in the tree.
	 *   balanced tree: O(log(n)).
	 *   unbalanced tree: O(n).
	 * Space: O(1).
	 * 
	 * @param node the node to find the in-order successor of.
	 * @return the in-order successor of the node.
	 */
	public static @NotNull <T> Node<T> next( @NotNull Node<T> node )
	{
		checkNotNull(node);
		
		Node<T> next;
		if( node.hasRight() )
		{
			// next is left most right
			next = node.getRight();
			while( next.hasLeft() )
			{
				next = next.getLeft();
			}
		}
		else // !node.hasRight()
		{
			// next is parent which does not have node as its right most descendant
			next = node;
			Node<T> child = null;
			do
			{
				if( !next.hasParent() )
				{ // at end: cannot find a parent where node is not the right most descendant
					throw new NoSuchElementException();
				}
				
				child = next;
				next = next.getParent();
			}
			while( next.hasRight() && next.getRight() == child );
		}
		
		return checkNotNull(next);
	}
	
	public static <T> boolean hasNext( @NotNull Node<T> node )
	{
		try
		{
			next(node);
			
			return true;
		}
		catch( NoSuchElementException e )
		{
			return false;
		}
	}
}
