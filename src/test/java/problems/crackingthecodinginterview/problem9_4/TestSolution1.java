package problems.crackingthecodinginterview.problem9_4;


public class TestSolution1 extends TestSolution
{
	@Override
	public TestInterface makeInstance( )
	{
		return Solution1::makeAllSubsets;
	}
}
