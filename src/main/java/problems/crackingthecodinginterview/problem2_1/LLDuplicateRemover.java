package problems.crackingthecodinginterview.problem2_1;

import java.util.HashSet;
import javax.validation.constraints.NotNull;
import problems.crackingthecodinginterview.common.LLNode;

public class LLDuplicateRemover<T> implements LLDuplicateRemoverInterface<T>
{
	/**
	 * @see LLDuplicateRemover#remove(LinkedList).
	 * 
	 * Approach: Iterator over the list and put items into a set. If an item is already in the set, remove it as it has
	 * already been seen.
	 * 
	 * Time: O(n) where n is the number of items in the list.
	 * Space: O(n).
	 */
	@Override
	public void remove( @NotNull LLNode<T> head )
	{
		HashSet<T> items = new HashSet<>();
		
		LLNode<T> node = head;
		
		while( node != null )
		{
			T item = node.getData();
			
			if( items.contains(item) )
			{ // seen 2+ times
				node.remove();
			}
			else // !items.contains(item)
			{
				items.add(item);
			}
			
			node = node.getNext();
		}
	}
}
