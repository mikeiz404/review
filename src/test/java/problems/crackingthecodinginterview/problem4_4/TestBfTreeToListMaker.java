package problems.crackingthecodinginterview.problem4_4;

public class TestBfTreeToListMaker extends TestTreeToListMaker
{
	@Override
	public TreeToListMaker getInstance( )
	{
		return BfTreeToListMaker.INSTANCE;
	}
	
}
