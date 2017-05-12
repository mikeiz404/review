package problems.crackingthecodinginterview.problem4_4;

public class TestBfsTreeToListMaker extends TestTreeToListMaker
{
	@Override
	public TreeToListMaker getInstance( )
	{
		return BfsTreeToListMaker.INSTANCE;
	}
	
}
