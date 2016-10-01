package problems.crackingthecodinginterview.problem1_3;

import org.junit.Assert;
import org.junit.Test;

public abstract class TestPermChecker
{
	abstract protected PermChecker makePermChecker( );
	
	@Test
	public void testTrue( )
	{
		PermChecker checker = this.makePermChecker();
		
		Assert.assertTrue(checker.isPermutation("ab", "ba"));
		Assert.assertTrue(checker.isPermutation("abc", "acb"));
	}
	
	@Test
	public void testFalse( )
	{
		PermChecker checker = this.makePermChecker();
		
		Assert.assertFalse(checker.isPermutation("ab", "b"));
		Assert.assertFalse(checker.isPermutation("abc", "abcd"));
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullParam1( )
	{
		PermChecker checker = this.makePermChecker();
		
		checker.isPermutation(null, "");
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullParam2( )
	{
		PermChecker checker = this.makePermChecker();
		
		checker.isPermutation("", null);
	}
}
