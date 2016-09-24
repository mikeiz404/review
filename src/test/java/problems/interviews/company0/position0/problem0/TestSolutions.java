package problems.interviews.company0.position0.problem0;

import org.junit.Assert;
import org.junit.Test;
import problems.interviews.company0.position0.problem0.Pair;
import problems.interviews.company0.position0.problem0.SolutionInterface;
import problems.interviews.company0.position0.problem0.Solution1;
import problems.interviews.company0.position0.problem0.Solution2;

public class TestSolutions
{
	public void testSolution( SolutionInterface solution )
	{
		Assert.assertEquals(new Pair(5, 2), solution.findClosest(8, new int[]{1, 5, 2, 4}));
		Assert.assertEquals(new Pair(-7, 9), solution.findClosest(5, new int[]{-7, 4, 9}));
		Assert.assertEquals(new Pair(5, 1), solution.findClosest(7, new int[]{1, 4, 5, 5, 9}));
		Assert.assertEquals(new Pair(-1, 4), solution.findClosest(-3, new int[]{-1, 4, 5, 5, 9, -22}));
	}
	
	@Test
	public void testSolution1( )
	{
		testSolution(new Solution1());
	}
	
	@Test
	public void testSolution2( )
	{
		testSolution(new Solution2());
	}
}
