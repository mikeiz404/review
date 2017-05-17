package datastructures.trees;

import java.util.List;
import javax.validation.constraints.NotNull;

public interface OrderedChildren <N extends TreeNode<N, V>, V>
{
	public @NotNull List<N> getOrderedChildren( );
}