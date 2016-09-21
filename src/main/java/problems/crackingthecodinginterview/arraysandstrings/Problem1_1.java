package problems.crackingthecodinginterview.arraysandstrings;

import static com.google.common.base.Preconditions.checkNotNull;
import java.util.HashSet;
import javax.validation.constraints.NotNull;

/**
 * Chapter: 1
 * Problem: 1.1
 * Page: 73
 */
public class Problem1_1
{
	/**
	 * Checks if a string contains all unique characters.
	 * 
	 * Approach: Put characters from string into a set until a duplicate is found.
	 * Computation: O(n) to iterate over string
	 * Memory: O(n) to store up to all characters from string in set
	 * 
	 * @param s string to check for unique characters
	 * @return true if s contains all unique characters, false otherwise.
	 */
	public static boolean isAllUnique( @NotNull String s )
	{
		checkNotNull(s);
		
		HashSet<Character> prevChars = new HashSet<>(s.length());
		
		for( int i=0; i<s.length(); i++ )
		{
			Character c = s.charAt(i);
			
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
	
	/**
	 * Checks if a string contains all unique characters without using data structures.
	 * 
	 * Approach: For each character (target) in the string, iterate from one past target character to end of string. If
	 * a match is found, return false. If reach end of string, return true.
	 * Computation: O(n^2) to scan over subset of string for each character.
	 *   O(n-1 + n-2 + ... + 2 + 1) => O(n(n+1)/2 - 2) => O(n^2)
	 * Memory: O(1) for variables.
	 * 
	 * @param s string to check for unique characters
	 * @return true if s contains all unique characters, false otherwise.
	 */
	public static boolean isAllUniqueNoDs( @NotNull String s )
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
