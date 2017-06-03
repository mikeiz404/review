package problems.crackingthecodinginterview.problem5_3;

public class TestBitSolution extends TestSolution
{
	@Override
	public TestInterface getInstance( )
	{
		return new TestInterface( )
		{
			@Override
			public Integer findLarger( int num )
			{
				return BitSolution.findLarger(num);
			}

			@Override
			public Integer findSmaller( int num )
			{
				return BitSolution.findSmaller(num);
			}
		};
	}
	
}
