package problems.crackingthecodinginterview.problem2_4;

import problems.crackingthecodinginterview.common.LLNode;
import static com.google.common.base.Preconditions.checkNotNull;

public class LLPartitioner
{
	protected static <T> void swap( LLNode<T> a, LLNode<T> b )
	{
		T aData = a.getData();
		a.setData(b.getData());
		b.setData(aData);
	}
	
	/**
	 * Approach: Use the partition algorithm of quick sort. Since the pivot value is know before hand, only sequential
	 * access is required.
	 * 
	 * Time: O(2n) => O(n).
	 * Space: O(1).
	 * 
	 * @param head the head node of the linked list.
	 * @param pivot the value to partition the list by.
	 */
	public static <T extends Comparable<T>> void partition( LLNode<T> head, T pivot )
	{
		checkNotNull(pivot);
		
		LLNode<T> smallTail = null;
		LLNode<T> candidateHead = head;
		
		while( candidateHead != null )
		{ // candidate list is not empty
			T candidate = candidateHead.getData();
			
			if( candidate.compareTo(pivot) < 0 )
			{
				// append candidate to small list
				if( smallTail == null )
				{
					swap(head, candidateHead);
					
					// advance smallTail
					smallTail = head;
				}
				else // smallTail != null
				{
					// note: will never be null since largeHead always trails or is candidateHead
					LLNode<T> largeHead = smallTail.getNext();
					swap(largeHead, candidateHead);
					
					// advance smallTail
					smallTail = largeHead;
				}
			}
			// else: largeTail is effectively advanced when candidateHead is advanced
			
			// advance candidateHead
			candidateHead = candidateHead.getNext();
		}
	}
}
