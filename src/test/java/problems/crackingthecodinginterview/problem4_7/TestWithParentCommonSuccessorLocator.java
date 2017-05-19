package problems.crackingthecodinginterview.problem4_7;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import datastructures.trees.TreeNode;
import datastructures.trees.binarytree.Node;

public class TestWithParentCommonSuccessorLocator extends TestCommonSuccessorLocator
{
	@Override
	public TestInterface getInstance( )
	{
		return new TestInterface( )
		{
			@Override
			public <N extends TreeNode<N, V>, V> N locate( N root, N a, N b )
			{
				return WithParentCommonSuccessorLocator.locate(a, b);
			}
		};
	}
	
	@Test
	public void testDifferentTrees( )
	{
		Node<String> a0 = new Node<>("A0");
		Node<String> a1 = a0.setLeft("A1");
		Node<String> a2 = a0.setRight("A2");
		Node<String> a3 = a2.setRight("A3");
		
		Node<String> b0 = new Node<>("B0");
		Node<String> b1 = new Node<>("B1");
		
		assertEquals(null, WithParentCommonSuccessorLocator.locate(a3, b1));
	}
}
