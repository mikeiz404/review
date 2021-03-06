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
	
	public static <T> boolean areListsEqual( LLNode<T> aHead, LLNode<T> bHead )
	{
		LLNode<T> aNode = aHead;
		LLNode<T> bNode = bHead;
		
		while( aNode != null && bNode != null )
		{
			if( !aNode.equals(bNode) )
			{
				return false;
			}
			
			aNode = aNode.getNext();
			bNode = bNode.getNext();
		}
		
		return aNode == null && bNode == null;
	}
}
