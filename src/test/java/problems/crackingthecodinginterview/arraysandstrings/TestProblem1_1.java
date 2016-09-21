package problems.crackingthecodinginterview.arraysandstrings;

import org.junit.Test;
import org.junit.Assert;

public class TestProblem1_1
{
	private interface ProblemClass
	{
		public boolean isAllUnique( String s );
	}
	
	@Test
	public void TestIsAllUniqueTrue( )
	{
		TestIsAllUniqueTrue(Problem1_1::isAllUnique);
	}
	
	@Test
	public void TestIsAllUniqueFalse( )
	{
		TestIsAllUniqueFalse(Problem1_1::isAllUnique);
	}
	
	@Test
	public void TestIsAllUniqueNoDsTrue( )
	{
		TestIsAllUniqueTrue(Problem1_1::isAllUniqueNoDs);
	}
	
	@Test
	public void TestIsAllUniqueNoDsFalse( )
	{
		TestIsAllUniqueFalse(Problem1_1::isAllUniqueNoDs);
	}
	
	public void TestIsAllUniqueTrue( ProblemClass problem )
	{
		Assert.assertTrue(problem.isAllUnique(""));
		Assert.assertTrue(problem.isAllUnique("a"));
		Assert.assertTrue(problem.isAllUnique("abcdefg"));
	}
	
	public void TestIsAllUniqueFalse( ProblemClass problem )
	{
		Assert.assertFalse(problem.isAllUnique("aa"));
		Assert.assertFalse(problem.isAllUnique("abcdefga"));
	}
}
