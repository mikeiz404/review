package problems.interviews.company0.position0.problem0;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import javax.validation.constraints.NotNull;

/*
 * Computes all possible combinations.
 */
public class Solution1 implements SolutionInterface
{
	/*
	 * Complexity: O(n^2)
	 */
	@Override
	public Pair findClosest( int k, @NotNull int[] data )
	{
		checkNotNull(data);
		checkArgument(data.length >= 2);
		
		Pair closestPair = null;
		int closestDistance = -1;
		boolean closestPairSet = false;
		
		// iterate over all possible combinations
		for( int i=0; i<data.length; i++ )
		{
			int a = data[i];
			for( int j=(i + 1); j<data.length; j++ )
			{
				int b = data[j];
				Pair pair = new Pair(a , b);
				int distance = pair.calcDistance(k);
				
				// update closest distance
				if(  !closestPairSet || distance < closestDistance )
				{
					closestDistance = distance;
					closestPair = pair;
					
					closestPairSet = true;
					
					// found closest possible distance
					if( closestDistance == 0 )
					{
						return closestPair;
					}
				}
			}
		}

		return closestPair;
	}
}
