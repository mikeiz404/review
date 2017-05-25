package problems.crackingthecodinginterview.problem4_9;

import java.util.ArrayList;
import javax.validation.constraints.NotNull;
import datastructures.trees.TreeNode;
import static com.google.common.base.Preconditions.checkNotNull;

public class RevPathSumPrinter extends PathSumPrinter
{
	public RevPathSumPrinter( )
	{
		super(new Printer());
	}
	
	protected RevPathSumPrinter( Printer printer )
	{
		super(printer);
	}
	
	public <N extends TreeNode<N, Integer>> void print( N root, int target )
	{
		print(target, new ArrayList<Integer>(), 1, root);
	}
	
    /**
     * Approach: 
     * 
     * Time: O(nh) to visit each node and check the reverse path sums. n is the number of nodes and h is the height.
     *   Balanced Binary Tree: O(nlog(n)).
     * 
     * Space: O( h + h ) => O(h) for stack and path list.
     *   Balanced Binary Tree: O(log(n)).
     **/
	private <N extends TreeNode<N, Integer>> void print( int target, @NotNull ArrayList<Integer> path, int level, @NotNull N node )
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
		
		// check reverse paths
		int sum = 0;
		for( int i=level; i>0; i-- )
		{
			sum += path.get(i - 1);
			if( sum == target )
			{
				printPath(path, i, level);
			}
		}
		
		// recurse
		for( N child : node.getChildren() )
		{
			// continue path
			print(target, path, (level + 1), child);
		}
	}
}
