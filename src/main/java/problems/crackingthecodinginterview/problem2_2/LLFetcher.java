package problems.crackingthecodinginterview.problem2_2;

import problems.crackingthecodinginterview.common.LLNode;
import static com.google.common.base.Preconditions.checkArgument;

public class LLFetcher<T>
{
	LLNode<T> head;
	int size;
	
	protected static int calcSize( LLNode<?> head )
	{
		LLNode<?> node = head;
		int size = 0;
		while( node != null )
		{
			node = node.getNext();
			size++;
		}
		
		return size;
	}
	
	/**
	 * Creates an instance to access items from the linked list head.
	 * @param head linked list to fetch items from.
	 */
	public LLFetcher( LLNode<T> head )
	{
		this.head = head;
		this.size = calcSize(head);
	}
	
	/**
	 * Gets the kth item from the end of the linked list.
	 * 
	 * Approach: First pass to calculate linked list size. Second pass to return the item at `size - k` index.
	 * 
	 * Time: O(2n) => O(n) where n is the number of items in the linked list.
	 * Space: O(1).
	 * 
	 * @param k index from end of the item to return.
	 * @return kth item from the end of the linked list.
	 */
	public T getFromEnd( int k )
	{
		checkArgument(k < this.size, "k (%s) < list size (%s)", k, this.size);
		
		LLNode<T> node = this.head;
		int index = size - 1 - k;
		for( int i=0; i<index; i++ )
		{
			node = node.getNext();
		}
		
		return node.getData();
	}
}
