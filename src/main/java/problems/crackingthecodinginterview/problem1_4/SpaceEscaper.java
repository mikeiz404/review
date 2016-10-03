package problems.crackingthecodinginterview.problem1_4;

import static com.google.common.base.Preconditions.checkNotNull;
import javax.validation.constraints.NotNull;

public class SpaceEscaper
{
	public static final String ESCAPE_SEQ = "%20";
	public static final String SPACE = " ";
	
	/**
	 * Returns the length of a c-style string.
	 * @param cString c-style string to calculate length of.
	 * @return length of c-style string.
	 */
	private static int calcCStringLen( @NotNull char[] cString )
	{
		checkNotNull(cString);
		
		int len = 0;
		while( cString[len] != 0 )
		{
			len++;
			
			if( len > cString.length )
			{
				throw new Error("string must be null terminated.");
			}
		}
		
		return len;
	}
	
	/**
	 * Returns the number of spaces in a 'null' terminated c-string.
	 * @param cString c-style to count spaces in.
	 * @return number of spaces in c-style string.
	 */
	private static int countCStringSpaces( @NotNull char[] cString )
	{
		checkNotNull(cString);
		
		int stringLen = calcCStringLen(cString);
		int spacesCount = 0;
		for( int i=0; i<stringLen; i++ )
		{
			char c = cString[i];
			
			if( c == ' ' )
			{
				spacesCount++;
			}
		}
		
		return spacesCount;
	}
	
	/**
	 * Replace spaces in the c-style string with the '%20' escape sequence.
	 * 
	 * Time: O(3n) => O(n) where n is the length of cString.
	 * Space: O(1).
	 * 
	 * Note: Time could have been made a little more efficient if c-string length and spaces count were done in one pass
	 * with a tradeoff in simplicity and readability.
	 * 
	 * @param cString string to replaces in.
	 */
	public static void escape( @NotNull char[] cString )
	{
		checkNotNull(cString);
		
		int stringLen = calcCStringLen(cString);
		int spacesCount = countCStringSpaces(cString);
		int headIndex = stringLen - 1;
		int tailIndex = headIndex + (spacesCount * (ESCAPE_SEQ.length() - SPACE.length()));
		
		// "null" terminate
		cString[tailIndex + 1] = 0;
		
		// swap and replace
		int spacesRemaining = spacesCount;
		while( headIndex >= 0 && spacesRemaining > 0 )
		{
			char c = cString[headIndex];
			if( c == ' ' )
			{ // escape
				// write escape sequence backwards
				for( int i=0; i<ESCAPE_SEQ.length(); i++ )
				{
					char escapeChar = ESCAPE_SEQ.charAt(ESCAPE_SEQ.length() - 1 - i);
					cString[tailIndex] = escapeChar;
					
					tailIndex--;
				}
				
				spacesRemaining--;
			}
			else // c != ''
			{ // move
				cString[tailIndex] = cString[headIndex];
				
				tailIndex--;
			}
			
			headIndex--;
		}
	}
}
