package problems.crackingthecodinginterview.problem3_7;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestABQue<T>
{
	@Test
	public void test( )
	{
		ABQue<Integer> q = new ABQue<>();
		
		q.pushA(1);
		q.pushA(3);
		q.pushA(5);
		
		q.pushB(2);
		
		q.pushA(7);
		
		q.pushB(4);
		q.pushB(6);
		
		assertEquals((Integer) 1, q.popA());
		assertEquals((Integer) 2, q.popB());
		assertEquals((Integer) 3, q.popAB());
		assertEquals((Integer) 5, q.popAB());
		assertEquals((Integer) 7, q.popAB());
		assertEquals((Integer) 4, q.popAB());
		assertEquals((Integer) 6, q.popAB());
	}
}
