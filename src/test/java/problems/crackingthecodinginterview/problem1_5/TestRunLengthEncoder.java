package problems.crackingthecodinginterview.problem1_5;

import org.junit.Assert;
import org.junit.Test;

public class TestRunLengthEncoder
{
	@Test
	public void testEncode( )
	{
		Assert.assertEquals("2a", RunLengthEncoder.encode("aa"));
		Assert.assertEquals("5a1b2c", RunLengthEncoder.encode("aaaaabcc"));
	}
	
	@Test
	public void testEfficiency( )
	{
		Assert.assertEquals("a", RunLengthEncoder.encode("a"));
		Assert.assertEquals("abcdefg", RunLengthEncoder.encode("abcdefg"));
	}
	
	@Test
	public void testEmpty( )
	{
		Assert.assertEquals("", RunLengthEncoder.encode(""));
	}
	
	@Test(expected=NullPointerException.class)
	public void testNull( )
	{
		RunLengthEncoder.encode(null);
	}
}
