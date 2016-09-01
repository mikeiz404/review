package problems.company0.position0;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import static com.google.common.base.Preconditions.checkArgument;

/*
 * Problem: Given an array of integers and an integer k, find the closest pair of integers which sum to k.
 *  - Positive and negative integers are allowed
 *  
 *  Example:
 *  int[] data = {1, 5, 2, 4}, k = 8
 *  output : {5, 2}
 */
public class Problem1
{
	public static interface Solution
	{
		public Pair findClosest( int k, int[] data );
	}
	
	public static class Pair
	{
		private int a;
		private int b;
		
		public Pair( int a, int b )
		{
			this.a = a;
			this.b = b;
		}
		
		public int getA( )
		{
			return this.a;
		}
		
		public int getB( )
		{
			return this.b;
		}
		
		public int calcDistance( int k )
		{
			return Math.abs(k - (this.getA() + this.getB()));
		}
		
		@Override
		public int hashCode( )
		{
			return this.getA() + this.getB() * 13;
		}
		
		@Override
		public boolean equals( Object o )
		{
			if( o instanceof Pair )
			{
				Pair p = (Pair) o;
				
				return (p.getA() == this.getA() && p.getB() == this.getB()) ||
				       (p.getB() == this.getA() && p.getA() == this.getB());
			}
			else // ! o instanceof Pair
			{
				return false;
			}
		}
		
		@Override
		public String toString( )
		{
			return String.format("<Pair a:%d b:%d>", this.getA(), this.getB());
		}
	}
	
	/*
	 * Computes all possible combinations.
	 */
	public static class Solution1 implements Solution
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
	
	/*
	 * Sort array and use binary search to locate the second number of the pair.
	 */
	public static class Solution2 implements Solution
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
}
