package problems.crackingthecodinginterview.problem5_6;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestBitSwapper
{
	@Test
	public void test( )
	{
		assertEquals(0, BitSwapper.swapEvenOdd(0));
		assertEquals(0xFFFFFFFF, BitSwapper.swapEvenOdd(0xFFFFFFFF));
		assertEquals(0xAAAAAAAA, BitSwapper.swapEvenOdd(0x55555555));
		assertEquals(0x55555555, BitSwapper.swapEvenOdd(0xAAAAAAAA));
	}
}
