package problems.crackingthecodinginterview.problem5_7;

import java.util.List;

public class Solution1 extends AbstractSolution
{
	/**
	 * Approach: Calculate the full sum without the missing number, sum the items in a, return the difference.
	 * 
	 * Time: O(n * log(n)) where n is the number of items in a. `log(n)` since the number of set bits is dependent on `n`.
	 * Space: O(1).
	 * 
	 * @param a array of numbers.
	 * @return the missing number.
	 */
	public int findMissingNumber( List<Integer> numbers )
	{
		// note: sum from n=0 to n of n => n(n+1)/2
		int completeSum = (numbers.size() * (numbers.size() + 1)) / 2;
		int msbPosMax = (int) (Math.log(numbers.size()) / Math.log(2));
		
		int incompleteSum = 0;
		for( int i=0; i<numbers.size(); i++ )
		{
			int number = numbers.get(i);
			
			// add number
			for( int j=0; j<=msbPosMax; j++ )
			{
				incompleteSum += getBit(number, j) << j;
			}
		}
		
		return completeSum - incompleteSum;
	}
}
