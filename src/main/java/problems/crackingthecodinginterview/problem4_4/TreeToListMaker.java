package problems.crackingthecodinginterview.problem4_4;

import java.util.ArrayList;
import java.util.LinkedList;
import datastructures.trees.TreeNode;

public interface TreeToListMaker
{
	/**
	 * Returns a list of linked lists where each list contains the values of the nodes in a given level.
	 * @param root node to start from.
	 * @return  a list of linked lists for each level.
	 */
	public <N extends TreeNode<N, V>, V> ArrayList<LinkedList<N>> makeList( N root );
}
