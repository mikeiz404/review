package problems.crackingthecodinginterview.problem4_9;

import java.util.ArrayList;
import datastructures.trees.TreeNode;

public abstract class PathSumPrinter
{
	private Printer printer;

	protected PathSumPrinter( Printer printer )
	{
		this.printer = printer;
	}
	
	protected Printer getPrinter()
	{
		return this.printer;
	}
	
	public abstract <N extends TreeNode<N, Integer>> void print( N root, int target );
	
	protected <N extends TreeNode<N, Integer>> void printPath( ArrayList<Integer> path, int start, int end )
	{
		StringBuilder builder = new StringBuilder();
		for( int i=(start - 1); i<end; i++ )
		{
			Integer val = path.get(i);
			builder.append(val + " ");
		}
		builder.append("\n");
		
		getPrinter().print(builder.toString());
		
	}
}
