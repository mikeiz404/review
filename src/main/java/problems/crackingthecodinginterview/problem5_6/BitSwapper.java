package problems.crackingthecodinginterview.problem5_6;

public class BitSwapper
{
	// 0x...5 = 0b...0101
	protected static final int EVEN_MASK = 0x55555555;
	protected static final int ODD_MASK = ~EVEN_MASK;
	
	/**
	 * Approach: Mask in the even bits and shift left. Mask in the odd bits and shift right. Merge.
	 * 
	 * Time: O(5) => O(1).
	 * 
	 * @param n bits to swap.
	 * @return swapped bits.
	 */
	public static int swapEvenOdd( int n )
	{
		// note: using >>> to NOT preserve sign during a right shift
		return ((n & EVEN_MASK) << 1) | ((n & ODD_MASK) >>> 1);
	}
}
