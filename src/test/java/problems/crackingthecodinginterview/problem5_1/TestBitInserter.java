package problems.crackingthecodinginterview.problem5_1;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestBitInserter
{
	@Test
	public void test( )
	{
		assertEquals(0b11111010, BitInserter.insert(0b11111111, 0b1010, 0, 3));
		assertEquals(0b11001010, BitInserter.insert(0b11111111, 0b1010, 0, 5));
		assertEquals(0b11010111, BitInserter.insert(0b11111111, 0b1010, 3, 6));
	}
}
