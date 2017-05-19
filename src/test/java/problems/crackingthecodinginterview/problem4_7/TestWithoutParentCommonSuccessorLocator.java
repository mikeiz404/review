package problems.crackingthecodinginterview.problem4_7;

import datastructures.trees.TreeNode;

public class TestWithoutParentCommonSuccessorLocator extends TestCommonSuccessorLocator
{
	public TestInterface getInstance()
	{
		return new TestInterface( )
		{
			@Override
			public <N extends TreeNode<N, V>, V> N locate( N root, N a, N b )
			{
				return WithoutParentCommonSuccessorLocator.locate(root, a, b);
			}
		};
	}
}
