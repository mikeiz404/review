package problems.crackingthecodinginterview.problem2_3;

import problems.crackingthecodinginterview.common.LLNode;
import static com.google.common.base.Preconditions.checkNotNull;
import javax.validation.constraints.NotNull;

public class LLRemover
{
	
	/**
	 * Removes a linked list node from a singly linked list, given a reference to the node.
	 * 
	 * Approach: Copy the next node values into the current node.
	 * 
	 * Time: O(1).
	 * Space: O(1).
	 * 
	 * @param node the node to remove. Cannot be last item in list.
	 */
	public static <T> void remove( @NotNull LLNode<T> node )
	{
		checkNotNull(node);
		checkNotNull(node.getNext(), "node cannot be the last node in the list.");
		
		// copy next values into node
		LLNode<T> next = node.getNext();
		node.setData(next.getData());
		node.setNext(next.getNext());
	}
}
