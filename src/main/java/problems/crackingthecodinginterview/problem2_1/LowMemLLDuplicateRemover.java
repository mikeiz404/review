package problems.crackingthecodinginterview.problem2_1;

import javax.validation.constraints.NotNull;
import problems.crackingthecodinginterview.common.LLNode;

public class LowMemLLDuplicateRemover<T> implements LLDuplicateRemoverInterface<T>
{
	/**
	 * @see LLDuplicateRemover#remove(LinkedList).
	 * 
	 * Approach: Use two iterators to scan the list. The target iterator selects the item to check duplicates for.
	 * The candidate iterator scans the list for potential duplicates for each target item and removes duplicates
	 * starting from one past the target iterator position.
	 * 
	 * Time: O(n + (n + (n - 1) + (n - 2) + ... + 1)) => O(n + (n * (n - 1) / 2)) => O(n^2).
	 * Space: O(1).
	 */
	@Override
	public void remove( @NotNull LLNode<T> head )
	{
		LLNode<T> targetNode = head;
		while( targetNode != null )
		{
			T target = targetNode.getData();
			
			LLNode<T> candidateNode = targetNode;
			
			// advance candidate iterator one past target
			candidateNode = candidateNode.getNext();
			
			while( candidateNode != null )
			{
				T candidate = candidateNode.getData();
				LLNode<T> nextCandidateNode = candidateNode.getNext();
				
				if( (target == null && candidate == null) ||
				    (target != null && target.equals(candidate)))
				{ // duplicate
					candidateNode.remove();
				}
				
				candidateNode = nextCandidateNode;
			}
			
			targetNode = targetNode.getNext();
		}
	}
}
