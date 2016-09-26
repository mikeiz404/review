package problems.crackingthecodinginterview.problem1_1;

import static com.google.common.base.Preconditions.checkNotNull;
import java.util.HashSet;

public class UniqueChecker implements UniqueCheckerInterface
{
	/**
	 * @See {@link #isAllUnique(String)}.
	 * 
	 * Approach: Put characters from string into a set until a duplicate is found.
	 * 
	 * Time: O(n) to iterate over string
	 * Space: O(n) to store up to all characters from string in set
	 */
	@Override
	public boolean isAllUnique( String string )
	{
		checkNotNull(string);
		
		HashSet<Character> prevChars = new HashSet<>(string.length());
		
		for( int i=0; i<string.length(); i++ )
		{
			Character c = string.charAt(i);
			
			if( prevChars.contains(c) )
			{ // char already seen
				return false;
			}
			else // !prevChars.contains(c)
			{ // char unseen
				prevChars.add(c);
			}
		}
		
		// all chars checked
		return true;
	}
}
