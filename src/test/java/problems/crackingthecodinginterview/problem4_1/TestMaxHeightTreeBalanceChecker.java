package problems.crackingthecodinginterview.problem4_1;

public class TestMaxHeightTreeBalanceChecker extends TestTreeBalanceChecker
{
	@Override
	public TreeBalanceChecker getInstance( )
	{
		return MaxHeightTreeBalanceChecker.INSTANCE;
	}
	
}
