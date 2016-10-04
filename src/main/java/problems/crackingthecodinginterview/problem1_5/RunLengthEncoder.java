package problems.crackingthecodinginterview.problem1_5;

import static com.google.common.base.Preconditions.checkNotNull;
import javax.validation.constraints.NotNull;

public class RunLengthEncoder
{
	/**
	 * Returns a run-length encoded string if that is more more space efficient, otherwise the original string is returned.
	 * 
	 * Approach: Store a run count for the current character as the string is being scanned. When the run ends, write
	 * out the current run length and character, reset, and repeat.
	 * 
	 * Time: O(n) where n is the number of characters in string.
	 * Space: O(n).
	 * 
	 * @param string String to encode.
	 * @return an encoded String or the original String string.
	 */
	public static @NotNull String encode( @NotNull String string )
	{
		checkNotNull(string);
		
		if( string.length() == 0 )
		{
			return string;
		}
		else // string.length() > 0
		{
			StringBuilder encoded = new StringBuilder();
			char prevChar = string.charAt(0);
			int charCount = 1;
			
			for( int i=1; i<string.length(); i++ )
			{
				char c = string.charAt(i);
				
				if( prevChar == c )
				{
					charCount++;
				}
				else // prevChar != c
				{
					// encode char
					encoded.append(charCount);
					encoded.append(prevChar);
					
					// init next char
					prevChar = c;
					charCount = 1;
				}
			}
			
			// encode last character count
			encoded.append(charCount);
			encoded.append(prevChar);
			
			// return most space efficient
			if( encoded.length() > string.length() )
			{
				return string;
			}
			else // encoded.length() <= string.length()
			{
				return encoded.toString();
			}
		}
	}
}
