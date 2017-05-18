package problems.crackingthecodinginterview.problem4_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import datastructures.trees.TreeNode;

public class BfTreeToListMaker implements TreeToListMaker
{
	public static BfTreeToListMaker INSTANCE = new BfTreeToListMaker();
	
	/**
	 * @see {@link TreeToListMaker}.
	 * 
	 * Approach: Explore tree in level order, using the linked lists, which are being created at each level, for the queue to save on space.
	 * 
	 * Time: O(n) where n represents the tree size.
	 * Space: O(n) for lists.
	 */
	@Override
	public <N extends TreeNode<N, V>, V> ArrayList<LinkedList<N>> makeList( N root )
	{
		ArrayList<LinkedList<N>> levels = new ArrayList<>();
		
		if( root != null )
		{
    		LinkedList<N> currentLevel = new LinkedList<>(Arrays.asList(root));
    		
    		while( currentLevel.size() > 0 )
    		{
    			LinkedList<N> nextLevel = new LinkedList<>();
    			
    			for( N node : currentLevel )
    			{
    				nextLevel.addAll(node.getChildren());
    			}
    			
    			levels.add(currentLevel);
    			currentLevel = nextLevel;
    		}
		}
		
		return levels;
	}
}
