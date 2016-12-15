package problems.interviews.company2.position0.problem2;

import java.util.ArrayList;

public class Solution1
{
	private static class Range
	{
		// exclusive
		int upper;
		// inclusive
		int lower;
		
		public Range( int lower, int upper )
		{
			this.upper = upper;
			this.lower = lower;
		}
		
		public int getRandom( )
		{
			if( this.isEmpty() )
			{
				throw new IllegalStateException(String.format("[%d, %d)", this.lower, this.upper));
			}
			
			int range = (this.upper - this.lower);
			return this.lower + (int) (Math.random() * range);
		}
		
		public Range split( int at )
		{
			if( this.isEmpty() )
			{
				throw new IllegalStateException();
			}
			
			if( at >= this.upper || at < this.lower )
			{
				throw new IllegalArgumentException("Out of bounds.");
			}
			
			Range newRange = new Range(this.lower, at);
			this.lower = at + 1;
			
			return newRange;
		}
		
		boolean isEmpty( )
		{
			return this.upper - this.lower <= 0;
		}
	}
	
	/**
	 * Generates a list of unique non-negative random numbers with an upper bound.
	 * Time: O(1 + (n-1) + ... n) => O(n(n-1)/2) => O(n^2) where n is the size.
	 * Space: O(n + (n + 1)) => O(n).
	 * @param size the number of random unique numbers to generate.
	 * @param upperBound the exclusive upper bound on the numbers to return.
	 * @return a list of unique non-negative random numbers.
	 */
	public static ArrayList<Integer> random( int size, int upperBound )
	{
		if( size < 0 )
		{
			throw new IllegalArgumentException(String.format("size (%d) must be >= 0", size));
		}
		
		if( size > upperBound )
		{
			throw new IllegalArgumentException(String.format("size (%d) must be <= upperBound (%d)", size, upperBound));
		}
		
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		ArrayList<Range> ranges = new ArrayList<>();
		ranges.add(new Range(0, upperBound));
		
		while( numbers.size() != size )
		{
			int rangeIndex = (int)(Math.random() * ranges.size());
			Range range = ranges.get(rangeIndex);
			
			int number = range.getRandom();
			
			numbers.add(number);

			Range newRange = range.split(number);
			
			if( range.isEmpty() && !newRange.isEmpty() )
			{
				ranges.set(rangeIndex, newRange);
			}
			else // !range.isEmpty() || newRange.isEmpty()
			{
				if( !newRange.isEmpty() )
				{
					ranges.add(newRange);
				}
				
				if( range.isEmpty() )
				{
					ranges.remove(rangeIndex);
				}
			}
		}
		
		return numbers;
	}
}
