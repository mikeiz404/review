package problems.crackingthecodinginterview.problem2_1;

import org.junit.Assert;
import org.junit.Test;
import problems.crackingthecodinginterview.common.LLNode;
import problems.crackingthecodinginterview.common.LLUtils;

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
				node = node.getNext();
			}
		}
		
		return head;
	}
	
	@Test
	public void test( )
	{
		LLDuplicateRemoverInterface<Character> remover = getTestInstance();
		LLNode<Character> list = makeList("abbcababz");
		LLNode<Character> expected = makeList("abcz");
		
		remover.remove(list);
		
		Assert.assertTrue(LLUtils.areListsEqual(expected, list));
	}
	
	@Test
	public void test2( )
	{
		LLDuplicateRemoverInterface<Character> remover = getTestInstance();
		LLNode<Character> list = makeList("aaa");
		LLNode<Character> expected = makeList("a");
		
		remover.remove(list);
		
		Assert.assertTrue(LLUtils.areListsEqual(expected, list));
	}
	
	@Test
	public void testWithoutDuplicates( )
	{
		LLDuplicateRemoverInterface<Character> remover = getTestInstance();
		LLNode<Character> list = makeList("abcdefg");
		LLNode<Character> expected = makeList("abcdefg");
		
		remover.remove(list);
		
		Assert.assertTrue(LLUtils.areListsEqual(expected, list));
	}
	
	@Test
	public void testEmpty( )
	{
		LLDuplicateRemoverInterface<Character> remover = getTestInstance();
		LLNode<Character> list = null;
		
		remover.remove(list);
		
		Assert.assertEquals(null, list);
	}
}
