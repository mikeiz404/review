package problems.interviews.company0.position0.problem0;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * Sort array and use binary search to locate the second number of the pair.
 */
public class Solution2 implements SolutionInterface
{
	/*
	 * Complexity: O((n * log(n)) + (n * log(n))) => O(nlog(n))
	 */
	@Override
	public Pair findClosest( int k, int[] data )
	{
		checkNotNull(data);
		checkArgument(data.length > 2);
		
		int closestDistance = -1;
		Pair closestPair = null;
		boolean closestPairSet = false;
		
		// sort data
		Arrays.sort(data);
		
		// note: stop before the last item in data since a pair cannot be made from items at the same index.
		for( int indexA = 0; indexA < data.length - 1; indexA++ )
		{
			int a = data[indexA];
			
			// find the index where b is at the ideal distance of 0
			// distance = |k - (a + b)| => 0 = |k - (a + b)| => b = k - a
			int idealB = k - a;
			
			int expectedIndex = Arrays.binarySearch(data, indexA + 1, data.length, idealB);
			
			if( expectedIndex >= 0 )
			{ // ideal b exists
				int b = data[expectedIndex];
				
				return new Pair(a, b);
			}
			else // expectedIndex < 0
			{ // ideal b does not exist
				// note: a missing key returns the negative of the expected index minus 1
				expectedIndex = -expectedIndex - 1;
				
				Set<Pair> pairs = new HashSet<>();
				
				// create pairs from a +1 window around the expected index, including the item at the expected index
				if( expectedIndex < data.length )
				{
					pairs.add(new Pair(a, data[expectedIndex]));
				}
				
				if( expectedIndex > (indexA + 1) )
				{
					pairs.add(new Pair(a, data[expectedIndex - 1]));
				}
				
				if( expectedIndex < (data.length - 1) )
				{
					pairs.add(new Pair(a, data[expectedIndex + 1]));
				}
				
				// check if any pairs are better than the current
				for( Pair pair : pairs )
				{
					int distance = pair.calcDistance(k);
					
					if( !closestPairSet || distance < closestDistance )
					{
						closestDistance = distance;
						closestPair = pair;
						
						closestPairSet = true;
					}
				}
			}
		}

		return closestPair;
	}
}
