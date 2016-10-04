package problems.crackingthecodinginterview.problem1_2;

import problems.crackingthecodinginterview.problem1_2.CStringReverser;

public class TestCStringReverse extends TestBase
{
	@Override
	public Reverser makeReverser( )
	{
		return CStringReverser::reverse;
	}
}
