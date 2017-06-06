package problems.crackingthecodinginterview.problem5_5;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestBitUtils
{
	@Test
	public void test( )
	{
		assertEquals(0, BitUtils.countAIntoB(0b0, 0b0));
		assertEquals(0, BitUtils.countAIntoB(0b1, 0b1));
		assertEquals(0, BitUtils.countAIntoB(0b11111111, 0b11111111));
		
		assertEquals(1, BitUtils.countAIntoB(0b0, 0b1));
		assertEquals(1, BitUtils.countAIntoB(0b1, 0b0));
		
		assertEquals(4, BitUtils.countAIntoB(0b110011001, 0b101011100));
	}
}
