package problems.crackingthecodinginterview.problem2_5;

import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class TestRevLLSummer extends TestBase
{
	@Override
	protected LinkedList<Integer> makeList( Integer... integers )
	{
		LinkedList<Integer> list = new LinkedList<>();
		for( int i=(integers.length - 1); i>=0; i-- )
		{
			Integer integer = integers[i];
			
			list.add(integer);
		}
		
		return list;
	}

	@Override
	protected LLSummerInterface makeSummer( )
	{
		return new LLRevSummer();
	}
}
