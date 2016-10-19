package problems.crackingthecodinginterview.common;

public class LLUtils
{
	public static <T> LLNode<T> makeList( T[] items )
	{
		LLNode<T> head;
		if( items.length == 0 )
		{
			head = null;
		}
		else // items.length > 0
		{
			head = new LLNode<>(items[0]);
		}
		
		LLNode<T> node = head;
		for( int i=1; i<items.length; i++ )
		{
			// append
			node.setNext(new LLNode<>(items[i]));
			node = node.getNext();
		}
		
		return head;
	}
}
