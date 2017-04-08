package problems.crackingthecodinginterview.problem4_1;

public class TestDfTreeBalanceChecker extends TestTreeBalanceChecker
{
	@Override
	public TreeBalanceChecker getInstance( )
	{
		return DfTreeBalanceChecker.INSTANCE;
	}
}
