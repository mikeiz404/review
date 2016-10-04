package problems.crackingthecodinginterview.problem1_2;

import org.junit.Assert;
import org.junit.Test;

public abstract class TestBase
{
	public static char[] makeCString( String string )
	{
		char[] cString = new char[string.length() + 1];
		
		// copy characters
		for( int i=0; i<string.length(); i++ )
		{
			cString[i] = string.charAt(i);
		}
		
		// "null" terminate
		cString[string.length()] = 0;
		
		return cString;
	}
	
	public abstract Reverser makeReverser( );
	
	@Test
	public void TestBaseCase0Chars( )
	{
		Reverser reverser = this.makeReverser();
		
		Assert.assertArrayEquals(makeCString(""), reverser.reverse(makeCString("")));
	}
	
	@Test
	public void TestBaseCase1Char( )
	{
		Reverser reverser = this.makeReverser();
		
		Assert.assertArrayEquals(makeCString("a"), reverser.reverse(makeCString("a")));
	}
	
	@Test
	public void Test( )
	{
		Reverser reverser = this.makeReverser();
		
		Assert.assertArrayEquals(makeCString("9876543210"), reverser.reverse(makeCString("0123456789")));
	}
	
	@Test(expected=NullPointerException.class)
	public void TestNull( )
	{
		Reverser reverser = this.makeReverser();
		
		reverser.reverse(null);
	}
}
