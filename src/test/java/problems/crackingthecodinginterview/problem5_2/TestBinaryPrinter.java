package problems.crackingthecodinginterview.problem5_2;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestBinaryPrinter
{
	@Test
	public void TestOverMaxBits()
	{
		float dec = (float) (1/Math.pow(2, BinaryPrinter.BITS_MAX + 1));
		assertEquals(BinaryPrinter.ERROR_STR, BinaryPrinter.makeDecimalString(dec));
	}
	
	@Test
	public void TestAtMaxBits()
	{
		float dec = (float) (1/Math.pow(2, BinaryPrinter.BITS_MAX));
		assertEquals("0.00000000000000000000000000000001", BinaryPrinter.makeDecimalString(dec));
	}
	
	@Test
	public void Test()
	{
		assertEquals("0.1", BinaryPrinter.makeDecimalString(1/2f));
		assertEquals("0.11", BinaryPrinter.makeDecimalString(1/2f + 1/4f));
		assertEquals("0.101", BinaryPrinter.makeDecimalString(1/2f + 1/8f));
	}
}
