package problems.crackingthecodinginterview.problem5_3;

public class TestBruteSolution extends TestSolution
{
	@Override
	public TestInterface getInstance( )
	{
		return new TestInterface( )
		{
			@Override
			public Integer findLarger( int num )
			{
				return BruteSolution.findLarger(num);
			}

			@Override
			public Integer findSmaller( int num )
			{
				return BruteSolution.findSmaller(num);
			}
		};
	}
	
}
