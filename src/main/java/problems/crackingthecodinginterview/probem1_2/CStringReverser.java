package problems.crackingthecodinginterview.probem1_2;

import javax.validation.constraints.NotNull;
import static com.google.common.base.Preconditions.checkNotNull;

public class CStringReverser
{
	/**
	 * Returns the length of a C style null terminated string.
	 * @param cString character array to calculate length of.
	 * @return number of characters before a "null" character is found, starting from the beginning.
	 */
	private static int calcStringLength( char[] cString )
	{
		int i;
		for( i=0; cString[i] != 0; i++);
		
		return i;
	}
	
	/**
	 * Swaps the values at positions a and b in array array.
	 * @param array the array to swap values in.
	 * @param a index to swap value from.
	 * @param b index to swap value from.
	 */
	private static void swap( char[] array, int a, int b )
	{
		char aVal = array[a];
		
		array[a] = array[b];
		array[b] = aVal;
	}
	
	/**
	 * Reverses the ordering of a provided character array.
	 * @param cString array of characters to reverse.
	 * @return reversed orders of cString.
	 * 
	 * Approach: Copy characters from end to beginning into a new array, null terminate, and return.
	 * 
	 * Time: O(n) where n is number of characters in the char array.
	 * Space: O(n).
	 */
	public static char[] reverse( @NotNull char[] cString )
	{
		checkNotNull(cString);
		
		int length = calcStringLength(cString);
		
		if( length == 0 || length == 1 )
		{ // reverse is identity
			return cString;
		}
		else // length > 1
		{
			char[] revCString = new char[length + 1];
			
			for( int i=0; i<length; i++ )
			{
				revCString[i] = cString[(length - 1 - i)];
			}
			
			// "null" terminate
			revCString[length] = 0;
			
			return revCString;
		}
	}
	
	
	/**
	 * Reverses the ordering of a provided character array in place.
	 * @param cString array of characters to reverse.
	 * 
	 * Approach: Swap characters starting from start and end, and moving towards the middle until all characters have
	 * been swapped. If the array length is odd then one character in the middle will not be swapped since it is already
	 * in the correct position.
	 * 
	 * Time: O(n/2) => O(n) where n is number of characters in the char array.
	 * Space: O(1).
	 */
	public static void reverseInPlace( @NotNull char[] cString )
	{
		checkNotNull(cString);
		
		int length = calcStringLength(cString);
		if( length > 1 )
		{
			int i = 0;
			int j = length - 1;
			
			while( i < j )
			{
				swap(cString, i, j);
				
				i++;
				j--;
			}
		}
	}
}
