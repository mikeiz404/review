package problems.internet.shiftedByNSortedArray;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestShiftedByNSortedArray
{
	@Test
	public void test( )
	{
		ArrayList<Integer> data = new ArrayList<>();
		for( int i=0; i<1000; i++ )
		{
			int number = (int) (100 - (Math.random() * 200));
			data.add(number);
		}
		
		Collections.sort(data);
		
		int n = 0;
		for( int i=0; i< 1000; i++ )
		{
    		int shift = (int) (Math.random() * (data.size() - 1));
    		Collections.rotate(data, shift);
    		
    		n = (n + shift) % data.size();
    		assertEquals(n, ShiftedByNSortedArray.calcMinN(data));
		}
	}
}
