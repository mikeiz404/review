package problems.crackingthecodinginterview.probem1_2;

public class TestCStringReverse extends TestBase
{
	@Override
	public Reverser makeReverser( )
	{
		return CStringReverser::reverse;
	}
}
