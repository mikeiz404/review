package problems.crackingthecodinginterview.problem9_6;

import static com.google.common.base.Preconditions.checkArgument;
import java.util.ArrayList;
import java.util.Collection;


public class Solution2
{
	public static Collection<String> makeAllParenPairs( int n )
	{
		checkArgument(n > 0);
		
		Collection<String> result = new ArrayList<>();
		makeAllParenPairsRec(result, "", n, 0, 0);
		
		return result;
	}
	
	/**
	 * Insight: Parentheses can be put in in any order so long as they are balanced: same number of open as closed, and
	 * number of open parentheses at any point must be <= the number of open.
	 */
	public static void makeAllParenPairsRec( Collection<String> result, String partial, int max, int openCount, int closeCount )
	{
		if( openCount == max && closeCount == max )
		{ // done
			result.add(partial);
		}
		else
		{
			if( openCount < max )
			{ // add opened
				makeAllParenPairsRec(result, (partial + "("), max, (openCount + 1), closeCount);
			}
			
			if( closeCount < max && (openCount > closeCount) )
			{ // add closed
				makeAllParenPairsRec(result, (partial + ")"), max, openCount, (closeCount + 1));
			}
		}
	}
}
