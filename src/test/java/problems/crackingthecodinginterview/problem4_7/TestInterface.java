package problems.crackingthecodinginterview.problem4_7;

import javax.validation.constraints.NotNull;
import datastructures.trees.TreeNode;

public interface TestInterface
{
	public <N extends TreeNode<N, V>, V> N locate( N root, @NotNull N a, @NotNull N b );
}
