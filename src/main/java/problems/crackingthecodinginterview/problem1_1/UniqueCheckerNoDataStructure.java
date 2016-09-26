package problems.crackingthecodinginterview.problem1_1;

import static com.google.common.base.Preconditions.checkNotNull;
import javax.validation.constraints.NotNull;

public class UniqueCheckerNoDataStructure implements UniqueCheckerInterface
{
	/**
	 * @See {@link #isAllUnique(String)}.
	 * 
	 * Approach: For each character (target) in the string, iterate from one past target character to end of string. If
	 * a match is found, return false. If reach end of string, return true.
	 * 
	 * Time: O(n^2) to scan over subset of string for each character.
	 *   O(n-1 + n-2 + ... + 2 + 1) => O(n(n+1)/2 - 2) => O(n^2)
	 * Space: O(1) for variables.
	 */
	public boolean isAllUnique( @NotNull String s )
	{
		checkNotNull(s);
		
		for( int i=0; i<s.length(); i++ )
		{
			char target = s.charAt(i);
			
			for( int j=(i + 1); j<s.length(); j++ )
			{
				char candidate = s.charAt(j);
				
				if( target == candidate )
				{ // found another instance
					return false;
				}
			}
		}
		
		// all chars checked
		return true;
	}
}
