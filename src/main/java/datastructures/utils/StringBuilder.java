package datastructures.utils;

import java.util.ArrayList;
import javax.validation.constraints.NotNull;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A class for efficiently appending strings.
 * 
 * Note: String concatenation is inefficient because Strings are immutable. This means that every time a string is
 * appended each character from the base string and each character from the appended string need to be copied into a new
 * string. To append n strings, each with a string length of s, the work done is O(1s + 2s + ... + ns) =>
 * O(s * (1 + 2 + ... + n)) => O(s * (n(n+1)/2)) => O(sn^2).
 * By using a string builder the complexity can be reduced to O(sn).
 * 
 * Note: String literal concatenation is done at compile time and results in a single string during runtime.
 */
public class StringBuilder
{
	private final ArrayList<Character> buffer;

	/**
	 * Create an StringBuilder with {@link #toString()} initially equal to "".
	 */
	public StringBuilder( )
	{
		this.buffer = new ArrayList<>();
	}
	
	/**
	 * Adds string to end of previously appended strings.
	 * Complexity: O(s) where s is string.length().
	 * @param string
	 */
	public void append( @NotNull String string )
	{
		checkNotNull(string);
		
		// append string characters to buffer
		for( int i=0; i<string.length(); i++ )
		{
			Character c = string.charAt(i);
			
			this.buffer.add(c);
		}
	}
	
	/**
	 * Creates a single String from the previously appended strings.
	 * Complexity: O(b) where b is buffer.size() which is equivalent to the sum of the length of all the strings appended.
	 */
	@Override
	public @NotNull String toString( )
	{
		// convert array list to char array
		char[] array = new char[this.buffer.size()];
		for( int i=0; i<this.buffer.size(); i++ )
		{
			array[i] = this.buffer.get(i);
		}
		
		return new String(array);
	}
}
