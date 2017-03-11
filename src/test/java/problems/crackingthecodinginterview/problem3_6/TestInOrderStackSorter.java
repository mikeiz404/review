package problems.crackingthecodinginterview.problem3_6;

public class TestInOrderStackSorter extends TestStackSorter
{
	@Override
	public StackSorter getTestInstance( )
	{
		return InOrderStackSorter.INSTANCE;
	}
}
