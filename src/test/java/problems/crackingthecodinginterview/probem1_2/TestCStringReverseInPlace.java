package problems.crackingthecodinginterview.probem1_2;

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
