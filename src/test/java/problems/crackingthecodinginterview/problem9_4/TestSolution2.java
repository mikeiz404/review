package problems.crackingthecodinginterview.problem9_4;


public class TestSolution2 extends TestSolution
{
	@Override
	public TestInterface makeInstance( )
	{
		return Solution2::makeAllSubsets;
	}
}
