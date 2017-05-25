package problems.crackingthecodinginterview.problem4_9;

import java.util.ArrayList;
import javax.validation.constraints.NotNull;
import datastructures.trees.TreeNode;
import static com.google.common.base.Preconditions.checkNotNull;

public class RecPathSumPrinter extends PathSumPrinter
{
	public RecPathSumPrinter( )
	{
		super(new Printer());
	}
	
	protected RecPathSumPrinter( Printer printer )
	{
		super(printer);
	}
	
	public <N extends TreeNode<N, Integer>> void print( N root, int target )
	{
		print(1, target, new ArrayList<Integer>(), 0, 1, root, true);
	}
	
    /**
     * Approach: Start from current node and continue summing the path to the children. Also start a new sub-path
     * starting from each newly visited child. If the current sum is the target, print path. Since we are exploring
     * depth-first, a list of the current path can be shared between nodes.
     * 
     * Time: O( n + sum from 1 to h of (n - k^h - 1)) => O( n + hn - h - sum from 1 to h of (k^h) ) => O( n + hn - h - (k^(h+1)) )
     * => for a balanced binary tree: O( n + nlog(n) - log(n) - (2^(log2(n)+1)) ) => O( n + nlog(n) - log(n) - log(n) ) => O(nlog(n))
     * where n is the number of nodes in the tree a, k is the number of children per node, and h is the height of the tree.
     * A simpler way to understand the runtime would be to see that this approach is equivalent to RevPathSumPrinter.
     * Space: O( h + h ) => O(h).
     **/
	private <N extends TreeNode<N, Integer>> void print( int startLevel, int target, @NotNull ArrayList<Integer> path, int sum, int level, @NotNull N node, boolean checkSubpath )
	{
		checkNotNull(node);
		checkNotNull(path);
		
		// update path
		if( path.size() < level )
		{
			path.add(node.getValue());
		}
		else // path.size() >= level
		{
			path.set((level - 1), node.getValue());
		}
		
		// update sum
		int newSum = sum + node.getValue();
		if( newSum == target )
		{
			printPath(path, startLevel, level);
		}
		
		// recurse
		for( N child : node.getChildren() )
		{
			// continue path
			print(startLevel, target, path, newSum, (level + 1), child, checkSubpath);
			
			// start sub-path
			if( checkSubpath )
			{
				print((level + 1), target, path, 0, (level + 1), child, false);
			}
		}
	}
}
