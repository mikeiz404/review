package datastructures.utils;

import org.junit.Test;
import datastructures.utils.StringBuilder;
import org.junit.Assert;

public class TestStringBuilder
{
	@Test
	public void Test( )
	{
		String a = "This";
		String b = " is a ";
		String c = "test";
		
		StringBuilder builder = new StringBuilder();
		
		builder.append(a);
		builder.append(b);
		builder.append(c);
		
		Assert.assertEquals(a + b + c, builder.toString());
	}
}
