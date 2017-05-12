package problems.crackingthecodinginterview.problem4_4;

public class TestDfsTreeToListMaker extends TestTreeToListMaker
{
	@Override
	public TreeToListMaker getInstance( )
	{
		return DfsTreeToListMaker.INSTANCE;
	}
	
}
