package problems.crackingthecodinginterview.problem1_2;

import problems.crackingthecodinginterview.problem1_2.CStringReverser;

public class TestCStringReverseInPlace extends TestBase
{
	@Override
	public Reverser makeReverser( )
	{
		return new Reverser( )
		{
			public char[] reverse( char[] cString )
			{
				char[] revCString = cString.clone();
				
				CStringReverser.reverseInPlace(revCString);
				
				return revCString;
			}
		};
	}
}
