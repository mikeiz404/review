package problems.crackingthecodinginterview.problem2_1;

import org.junit.Assert;
import org.junit.Test;
import problems.crackingthecodinginterview.common.LLNode;

public abstract class TestBase
{
	public abstract <T> LLDuplicateRemoverInterface<T> getTestInstance( );
	
	public static LLNode<Character> makeList( String string )
	{
		LLNode<Character> head = null;
		LLNode<Character> node = null;
		
		for( Character c : string.toCharArray() )
		{
			LLNode<Character> newNode = new LLNode<Character>(c);
			
			if( head == null )
			{
				head = newNode;
				node = head;
			}
			else // head != null
			{
				node.setNext(newNode);
			}
		}
		
		return head;
	}
	
	@Test
	public void test( )
	{
		LLDuplicateRemoverInterface<Character> remover = getTestInstance();
		LLNode<Character> list = makeList("abbcababz");
		
		remover.remove(list);
		
		Assert.assertEquals(makeList("abcz"), list);
	}
	
	@Test
	public void testWithoutDuplicates( )
	{
		LLDuplicateRemoverInterface<Character> remover = getTestInstance();
		LLNode<Character> list = makeList("abcdefg");
		
		remover.remove(list);
		
		Assert.assertEquals(makeList("abcdefg"), list);
	}
	
	@Test
	public void testEmpty( )
	{
		LLDuplicateRemoverInterface<Character> remover = getTestInstance();
		LLNode<Character> list = makeList("");
		
		remover.remove(list);
		
		Assert.assertEquals(makeList(""), list);
	}
	
	@Test
	public void testNullItems( )
	{
		LLDuplicateRemoverInterface<Character> remover = getTestInstance();
		LLNode<Character> list = makeList("\u0000\u0000\u0000");
		
		remover.remove(list);
		
		Assert.assertEquals(makeList("\u0000"), list);
	}
}
