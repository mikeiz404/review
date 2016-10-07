package problems.crackingthecodinginterview.problem1_8;

import org.junit.Assert;
import org.junit.Test;

public class TestRotationChecker
{
	@Test(expected=NullPointerException.class)
	public void testNullArg1( )
	{
		RotationChecker.isRotation(null, "");
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullArg2( )
	{
		RotationChecker.isRotation(null, "");
	}
	
	@Test
	public void testFalse( )
	{
		Assert.assertFalse(RotationChecker.isRotation("a", ""));
		Assert.assertFalse(RotationChecker.isRotation("ab", "ac"));
	}
	
	@Test
	public void testTrue( )
	{
		Assert.assertTrue(RotationChecker.isRotation("", ""));
		Assert.assertTrue(RotationChecker.isRotation("abcde", "bcdea"));
		Assert.assertTrue(RotationChecker.isRotation("aaaaa3", "aaa3aa"));
	}
}
