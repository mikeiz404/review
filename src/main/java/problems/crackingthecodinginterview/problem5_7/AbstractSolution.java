package problems.crackingthecodinginterview.problem5_7;

import java.util.List;

public abstract class AbstractSolution
{
	public static int getBit( int number, int position )
	{
		int mask = 1 << position;
		return ((number & mask) == 0) ? 0 : 1;
	}
	
	public abstract int findMissingNumber( List<Integer> numbers );
}
