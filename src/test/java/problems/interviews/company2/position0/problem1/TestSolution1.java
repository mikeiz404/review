package problems.interviews.company2.position0.problem1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class TestSolution1
{

	private static InputStream makeInputStream( String content )
	{
		return new ByteArrayInputStream(content.getBytes());
	}
	
	@Test(expected=NullPointerException.class)
	public void testNull( )
	{
		Solution1.calcWordFrequencies(null);
	}
	
	@Test
	public void testEmpty( )
	{
		Map<String, Integer> frequenies = Solution1.calcWordFrequencies(makeInputStream(""));
		
		Assert.assertEquals(0, frequenies.size());
	}
	
	@Test
	public void test( )
	{
		Map<String, Integer> frequenies = Solution1.calcWordFrequencies(makeInputStream("this is a test. test. testing this."));
		
		Assert.assertEquals(6, frequenies.size());
		Assert.assertEquals(((Integer) 1), frequenies.get("this"));
		Assert.assertEquals(((Integer) 1), frequenies.get("is"));
		Assert.assertEquals(((Integer) 1), frequenies.get("a"));
		Assert.assertEquals(((Integer) 2), frequenies.get("test."));
		Assert.assertEquals(((Integer) 1), frequenies.get("testing"));
		Assert.assertEquals(((Integer) 1), frequenies.get("this."));
	}
}
