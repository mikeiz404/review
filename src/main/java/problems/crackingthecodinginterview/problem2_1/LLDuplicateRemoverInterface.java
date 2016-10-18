package problems.crackingthecodinginterview.problem2_1;

import javax.validation.constraints.NotNull;
import problems.crackingthecodinginterview.common.LLNode;

public interface LLDuplicateRemoverInterface<T>
{
	/**
	 * Removes all duplicate items in the linked list list.
	 * @param list
	 */
	public void remove( @NotNull LLNode<T> head );
}
