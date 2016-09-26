package problems.crackingthecodinginterview.problem1_1;

import org.junit.Test;
import org.junit.Assert;

public abstract class TestBase
{
	protected abstract UniqueCheckerInterface getCheckerInstance( );
	
	@Test
	public void TestIsAllUniqueTrue( )
	{
		TestIsAllUniqueTrue(this.getCheckerInstance());
	}
	
	@Test
	public void TestIsAllUniqueFalse( )
	{
		TestIsAllUniqueFalse(this.getCheckerInstance());
	}
	
	@Test
	public void TestIsAllUniqueNoDsTrue( )
	{
		TestIsAllUniqueTrue(this.getCheckerInstance());
	}
	
	@Test
	public void TestIsAllUniqueNoDsFalse( )
	{
		TestIsAllUniqueFalse(this.getCheckerInstance());
	}
	
	public void TestIsAllUniqueTrue( UniqueCheckerInterface checker )
	{
		Assert.assertTrue(checker.isAllUnique(""));
		Assert.assertTrue(checker.isAllUnique("a"));
		Assert.assertTrue(checker.isAllUnique("abcdefg"));
	}
	
	public void TestIsAllUniqueFalse( UniqueCheckerInterface checker )
	{
		Assert.assertFalse(checker.isAllUnique("aa"));
		Assert.assertFalse(checker.isAllUnique("abcdefga"));
	}
}
