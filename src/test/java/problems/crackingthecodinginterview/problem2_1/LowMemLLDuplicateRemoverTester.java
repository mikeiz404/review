package problems.crackingthecodinginterview.problem2_1;

public class LowMemLLDuplicateRemoverTester extends TestBase
{
	@Override
	public <T> LLDuplicateRemoverInterface<T> getTestInstance( )
	{
		return new LowMemLLDuplicateRemover<>();
	}
}
