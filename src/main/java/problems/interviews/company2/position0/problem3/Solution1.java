package problems.interviews.company2.position0.problem3;

import java.util.ArrayList;

public class Solution1
{
	public static <T> ArrayList<T> randomSubset( ArrayList<T> elements, int subsetSize )
	{
		if( elements == null )
		{
			throw new NullPointerException();
		}
		
		if( subsetSize < 0 )
		{
			throw new IllegalArgumentException(String.format("subsetSize (%d) must be >= 0", subsetSize));
		}
		
		ArrayList<Integer> indexes = problems.interviews.company2.position0.problem2.Solution1.random(subsetSize, elements.size());
		
		ArrayList<T> subset = new ArrayList<>(subsetSize);
		for( int index : indexes )
		{
			T element = elements.get(index);
			subset.add(element);
		}
		
		return subset;
	}
}
