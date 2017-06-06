package problems.crackingthecodinginterview.problem5_5;

public class BitUtils
{
	/**
	 * Approach: Count the number of 1's in a XOR b since transforming a into b requires flipping the mismatched bits.
	 * @param a integer to transform from.
	 * @param b integer to transform to.
	 * @return number of bits necessary to flip to turn a into b.
	 */
	public static int countAIntoB( int a, int b )
	{
		return countOnesFast(a^b);
	}
	
	/**
	 * Approach: Shift b to the right while counting the set bit in the 1's position until b is 0.
	 * 
	 * Time: O(m) where m is the bit size of b.
	 * 
	 * @param b bits to count.
	 * @return number of set bits in b.
	 */
	public static int countOnes( int b )
	{
		int count = 0;
		while( b != 0 )
		{
			if( (b & 0b1) == 1 ) count++;
			
			b = b >> 1;
		}
		
		return count;
	}
	
	/**
	 * Approach: Clear the least significant set bit in b until b is 0.
	 * 
	 * Time: O(n) where n is the number of set bits in b.
	 * 
	 * @param b bits to count.
	 * @return number of set bits in b.
	 */
	public static int countOnesFast( int b )
	{
		int count = 0;
		while( b != 0 )
		{
			// note: clears the least significant bit in b, aka the lowest power of 2.
			b = b & (b - 1);
			
			count++;
		}
		
		return count;
	}
}
