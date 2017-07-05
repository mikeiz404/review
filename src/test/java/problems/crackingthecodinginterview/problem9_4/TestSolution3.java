package problems.crackingthecodinginterview.problem9_4;


public class TestSolution3 extends TestSolution
{
	@Override
	public TestInterface makeInstance( )
	{
		return Solution3::makeAllSubsets;
	}
}
