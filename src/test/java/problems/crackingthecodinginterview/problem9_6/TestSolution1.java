package problems.crackingthecodinginterview.problem9_6;


public class TestSolution1 extends TestSolution
{
	@Override
	public TestInterface getInstance( )
	{
		return Solution1::makeAllParenPairs;
	}
}
