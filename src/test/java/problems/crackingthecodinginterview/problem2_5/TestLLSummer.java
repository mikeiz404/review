package problems.crackingthecodinginterview.problem2_5;

import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class TestLLSummer extends TestBase
{
	@Override
	protected LinkedList<Integer> makeList( Integer... integers )
	{
		LinkedList<Integer> list = new LinkedList<>();
		for( Integer integer : integers )
		{
			list.add(integer);
		}
		
		return list;
	}

	@Override
	protected LLSummerInterface makeSummer( )
	{
		return new LLSummer();
	}
}
