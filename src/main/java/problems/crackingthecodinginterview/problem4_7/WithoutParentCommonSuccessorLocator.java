package problems.crackingthecodinginterview.problem4_7;

import javax.validation.constraints.NotNull;
import datastructures.trees.TreeNode;
import static com.google.common.base.Preconditions.checkNotNull;
import java.util.Collection;
import java.util.Iterator;

public class WithoutParentCommonSuccessorLocator
{
	/**
	 * Return the common ancestor of nodes a and b without making use of getParent.
	 * 
	 * Approach: For a binary tree determine which side of the tree a and b are on starting from the root. Work your way
	 * down until a and b are on opposite sides of tree; This is where the path diverges and so must be the first common
	 * successor.
	 * 
	 * Time:
	 *   O( 2n*((k-1)/k) + 2n*((k-1)/k)^2 + ... ) => Geometric Series: O( 2n/(1-(k-1)/k) ) => O( 2n/(1/k) ) => O(2n*k) =>
	 *   Since k will be constant for a tree with branching factor k: O(n) where n is the number of nodes in the tree
	 *   and k is the number of children a node can have.
	 * Space: O(h) for the stack where h is the height of the tree.
	 * 
	 * @param root root node of the tree a and b are in.
	 * @param a a node to find the common ancestor of.
	 * @param b a node to find the common ancestor of.
	 * @return first common ancestor.
	 */
	public static <N extends TreeNode<N, V>, V> N locate( @NotNull N root, @NotNull N a, @NotNull N b )
	{
		checkNotNull(root);
		checkNotNull(a);
		checkNotNull(b);
		
		// edge: a and b are both the root
		if( a == root && b == root )
		{
			return null;
		}
		
		Collection<N> children = root.getChildren();
		Iterator<N> childIt = children.iterator();
		
		boolean aFound = false;
		N aRoot = null;
		boolean bFound = false;
		N bRoot = null;
		
		// note: skipping one of the children
		for( int i=0; i<children.size() - 1; i++ )
		{
			N child = childIt.next();
			
			if( !aFound )
			{
				aFound = isPresent(child, a);
				aRoot = child;
			}
			
			if( !bFound )
			{
				bFound = isPresent(child, b);
				bRoot = child;
			}
		}
		
		// note: if a or b have not been found, then they must be in the last child if we assume a and b are in the same tree
		N uncheckedChild = childIt.next();
		
		if( !aFound )
		{
			aRoot = uncheckedChild;
		}
		
		if( !bFound )
		{
			bRoot = uncheckedChild;
		}
		
		// note: tail recursion elimination is possible here to save on stack
		if( aRoot == bRoot )
		{ // node in each path is still common
			return locate(aRoot, a, b);
		}
		else // aRoot != bRoot
		{ // node in each path has diverged
			return root;
		}
	}
	
	/*
	 * Time: O(n).
	 * Space: O(h) for stack.
	 */
	private static <N extends TreeNode<N, V>, V> boolean isPresent( @NotNull N root, @NotNull N target )
	{
		checkNotNull(root);
		checkNotNull(target);
		
		if( root == target )
		{
			return true;
		}
		else // root != target
		{
			// recurse on children
			for( N child : root.getChildren() )
			{
				if( isPresent(child, target) )
				{
					return true;
				}
			}
			
			// not found
			return false;
		}
	}
}
