package problems.crackingthecodinginterview.problem4_4;

public class TestDfTreeToListMaker extends TestTreeToListMaker
{
	@Override
	public TreeToListMaker getInstance( )
	{
		return DfTreeToListMaker.INSTANCE;
	}
	
}
