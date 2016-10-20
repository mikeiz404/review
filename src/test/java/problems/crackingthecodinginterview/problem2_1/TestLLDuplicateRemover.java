package problems.crackingthecodinginterview.problem2_1;

public class TestLLDuplicateRemover extends TestBase
{
	@Override
	public <T> LLDuplicateRemoverInterface<T> getTestInstance( )
	{
		return new LLDuplicateRemover<>();
	}
	
}
