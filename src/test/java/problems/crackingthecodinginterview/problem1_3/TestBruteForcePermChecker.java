package problems.crackingthecodinginterview.problem1_3;

import org.junit.Assert;
import org.junit.Test;

public class TestBruteForcePermChecker extends TestPermChecker
{
	@Override
	protected PermChecker makePermChecker( )
	{
		return new BruteForcePermChecker();
	}
	
	@Test
	public void testBaseTrue( )
	{
		PermChecker checker = this.makePermChecker();
		
		Assert.assertTrue(checker.isPermutation("", ""));
		Assert.assertTrue(checker.isPermutation("a", "a"));
	}
	
	@Test
	public void testBaseFalse( )
	{
		PermChecker checker = this.makePermChecker();
		
		Assert.assertFalse(checker.isPermutation("a", ""));
	}
}
