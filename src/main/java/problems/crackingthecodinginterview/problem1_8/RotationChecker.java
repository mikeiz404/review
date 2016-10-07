package problems.crackingthecodinginterview.problem1_8;

import static com.google.common.base.Preconditions.checkNotNull;
import javax.validation.constraints.NotNull; 

public class RotationChecker
{
	/**
	 * Return whether s2 is a rotation of s1.
	 * 
	 * Approach: Concat s2 to itself and see if s1 exists. This works since s2=xy where x is one portion of s1 and y is
	 * the remaining portion. s2s2 => xyxy. Since s1 = yx, a single call check if s1 is a substring is all that is needed.
	 * 
	 * Time: O(2n) => O(n) where n is the string length of s2 or s1.
	 * Space: O(2n) => O(n).
	 * 
	 * @param s1
	 * @param s2
	 * @return true if s2 is a rotation of s1, false otherwise.
	 */
	public static boolean isRotation( @NotNull String s1, @NotNull String s2 )
	{
		checkNotNull(s1);
		checkNotNull(s2);
		
		if( s1.length() != s2.length() )
		{
			return false;
		}
		else // s1.length() == s2.length()
		{
			return (s2 + s2).contains(s1);
		}
	}
}
