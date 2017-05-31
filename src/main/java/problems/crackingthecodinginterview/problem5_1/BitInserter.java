package problems.crackingthecodinginterview.problem5_1;

import static com.google.common.base.Preconditions.checkArgument;

public class BitInserter
{
	/**
	 * Approach: Clears bits i to j in N and sets them to M.
	 * 
	 * @param N number to insert into.
	 * @param M number to insert. Width must be <= (j - i) + 1.
	 * @param i start position in N (inclusive).
	 * @param j end position in N (inclusive).
	 * @return M inserted into N.
	 */
	public static int insert( int N, int M, int i, int j )
	{
		checkArgument(j >= i, "j must be > i.");
		checkArgument(M >> (j - i) + 1 == 0, "M's width must be <= (j - i).");
		// note: assuming inserting off int width is ok
		
		int width = j - i;
		int clearMask = ~(((1 << width + 1) - 1) << i);
		int setMask = M << i;
		
		return (N & clearMask) | setMask;
	}
}
