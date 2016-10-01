package problems.crackingthecodinginterview.problem1_3;

public class TestFrequencyPermChecker extends TestPermChecker
{
	
	@Override
	protected PermChecker makePermChecker( )
	{
		return new FrequencyPermChecker();
	}
	
}
