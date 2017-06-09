package problems.crackingthecodinginterview.problem5_8;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestScreen
{
	@Test
	public void testDrawHLineOnePixel( )
	{
		Screen s = new Screen(16, 16);
		
		s.drawHLine(15, 3, 3);
		byte[] expected = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0};
		assertArrayEquals(expected, s.getData());
	}
	
	@Test
	public void testDrawHLineFull( )
	{
		Screen s = new Screen(16, 16);
		
		s.drawHLine(5, 0, 15);
		byte[] expected = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		assertArrayEquals(expected, s.getData());
	}
	
	@Test
	public void testDrawHLineMiddle( )
	{
		Screen s = new Screen(16, 16);
		
		s.drawHLine(0, 3, 13);
		byte[] expected = new byte[]{-8, 63, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		assertArrayEquals(expected, s.getData());
	}
	
	@Test
	public void testDrawHLineSub( )
	{
		Screen s = new Screen(16, 16);
		
		s.drawHLine(7, 2, 4);
		byte[] expected = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 28, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		assertArrayEquals(expected, s.getData());
	}
}
