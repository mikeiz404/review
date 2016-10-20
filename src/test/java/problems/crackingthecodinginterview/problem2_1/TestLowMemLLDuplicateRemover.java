package problems.crackingthecodinginterview.problem2_1;

public class TestLowMemLLDuplicateRemover extends TestBase
{
	@Override
	public <T> LLDuplicateRemoverInterface<T> getTestInstance( )
	{
		return new LowMemLLDuplicateRemover<>();
	}
}
