package problems.crackingthecodinginterview.problem1_4;

import org.junit.Test;
import org.junit.Assert;
import static com.google.common.base.Preconditions.checkNotNull;
import java.util.Arrays;
import javax.validation.constraints.NotNull;
import static com.google.common.base.Preconditions.checkArgument;

public class TestSpaceEscaper
{
	public static @NotNull char[] makeCString( @NotNull String string )
	{
		return makePaddedCString(string, 0);
	}
	
	public static @NotNull char[] makePaddedCString( @NotNull String string, int padSize )
	{
		checkNotNull(string);
		checkArgument(padSize >= 0, "padSize >= 0");
		
		// note: + 1 for null termination
		// note: Arrays.copyOf inserts null characters as the padded characters
		return Arrays.copyOf(string.toCharArray(), (string.length() + 1 + padSize));
	}
	
	public static void test( String inputString, String expectedString )
	{
		char[] expected = makeCString(expectedString);
		char[] cString = makePaddedCString(inputString, (expectedString.length() - inputString.length()));
		
		SpaceEscaper.escape(cString);
		
		Assert.assertArrayEquals(cString, expected);
	}
	
	@Test
	public void testNoSpaces( )
	{
		test("", "");
		test("a", "a");
		test("test", "test");
	}
	
	@Test
	public void testSpaces( )
	{
		test("this is", "this%20is");
		test("this is a test", "this%20is%20a%20test");
		test("start      end", "start%20%20%20%20%20%20end");
		test("start      ", "start%20%20%20%20%20%20");
		test("     end", "%20%20%20%20%20end");
		test("  ", "%20%20");
	}
}
