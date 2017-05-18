package problems.crackingthecodinginterview.problem4_4;

import java.util.ArrayList;
import java.util.LinkedList;
import datastructures.trees.TreeNode;

public class DfTreeToListMaker implements TreeToListMaker
{
	public static DfTreeToListMaker INSTANCE = new DfTreeToListMaker();
	
	/**
	 * @see {@link TreeToListMaker}.
	 * 
	 * Approach: Explore tree in depth-first fashion and append nodes to level list as they are visited.
	 * 
	 * Time: O(n) where n represents the tree size.
	 * Space:
	 *   Balanced Tree: O(log(n) + n) => O(n) for stack and lists.
	 *   Unbalanced Tree: O(n + n) => O(n) for stack and lists.
	 */
	@Override
	public <N extends TreeNode<N, V>, V> ArrayList<LinkedList<N>> makeList( N root )
	{
		ArrayList<LinkedList<N>> levels = new ArrayList<>();
		
		if( root != null )
		{
			makeList(levels, root, 0);
		}
		
		return levels;
	}
	
	private <N extends TreeNode<N, V>, V> void makeList( ArrayList<LinkedList<N>> levels, N node, int levelIndex )
	{
		// note: size will only every be off by one
		if( levels.size() == levelIndex )
		{ // no ll for level yet
			levels.add(new LinkedList<N>());
		}
		
		LinkedList<N> level = levels.get(levelIndex);
		level.add(node);
		
		for( N child : node.getChildren() )
		{
			makeList(levels, child, (levelIndex + 1));
		}
	}
}
