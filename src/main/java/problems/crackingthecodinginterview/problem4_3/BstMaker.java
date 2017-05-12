package problems.crackingthecodinginterview.problem4_3;

import java.util.List;
import javax.validation.constraints.NotNull;
import datastructures.trees.binarytree.Node;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

public class BstMaker
{
	/**
	 * Creates a binary search tree with minimum height from a list sorted in increasing order.
	 * 
	 * Approach: Divide the array in half by removing the middle item, inserting it into the tree, and repeating on the
	 * left and right sublists if non-empty. If we descend the tree as we build it, the nodes can be inserted as children
	 * and no extra traversal is necessary to achieve the correct positioning.
	 * 
	 * @param from array to create the tree from.
	 * @return a minimum height binary search tree.
	 * 
	 * Time: O(n) where n is the number of items in the array list.
	 * Space: O(log(n)) for stack.
	 */
	public static @NotNull <T extends Comparable<T>> Node<T> fromArray( @NotNull List<T> array )
	{
		checkNotNull(array);
		checkArgument(array.size() > 0, "Array cannot be empty.");
		checkArgument(isArrayIncreasing(array), "Array must be sorted in increasing order.");
		
		int index = array.size() / 2;
		T val = array.get(index);
		
		Node<T> node = new Node<>(val);
		
		List<T> left = array.subList(0, index);
		List<T> right = array.subList((index + 1), array.size());
		
		fromArrayHelper(node, left, right);
		
		return checkNotNull(node);
	}
	
	private static @NotNull <T extends Comparable<T>> Node<T> fromArrayHelper( Node<T> root, @NotNull List<T> left, @NotNull List<T> right )
	{
		checkNotNull(left);
		checkNotNull(right);
		
		if( left.size() > 0 )
		{
			root.setLeft(fromArray(left));
		}
		
		if( right.size() > 0 )
		{
			root.setRight(fromArray(right));
		}
		
		return checkNotNull(root);
	}
	
	private static <T extends Comparable<T>> boolean isArrayIncreasing( @NotNull List<T> array )
	{
		checkNotNull(array);
		
		if( array.size() > 0 )
		{
			T prev = array.get(0);
			
			for( T item : array.subList(1, array.size()))
			{
				if( item.compareTo(prev) < 0 )
				{
					return false;
				}
			}
		}
		
		return true;
	}
}
