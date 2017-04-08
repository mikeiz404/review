package problems.crackingthecodinginterview.problem4_1;

public class TestBfTreeBalanceChecker extends TestTreeBalanceChecker
{
	@Override
	public TreeBalanceChecker getInstance( )
	{
		return BfTreeBalanceChecker.INSTANCE;
	}
	
}
