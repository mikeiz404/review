package problems.crackingthecodinginterview.problem9_6;

import static com.google.common.base.Preconditions.checkArgument;
import java.util.Collection;
import java.util.HashSet;


public class Solution1
{
	public static Collection<String> makeAllParenPairs( int n )
	{
		checkArgument(n > 0);
		
		HashSet<String> result = new HashSet<>();
		if( n == 1 )
		{
			result.add("()");
		}
		else // n > 1
		{
			for( String partial : makeAllParenPairs(n - 1) )
			{
				// insert parenthese pair into all possible locations
				
				// after all open parentheses
				// note: adding "()" only before and after the partial is not good enough as strings such as "(())(())" cannot form
				int from = 0;
				while( true )
				{
					from = partial.indexOf('(', from);
					
					if( from != -1 )
					{
						result.add(partial.substring(0, (from + 1)) + "()" + partial.substring(from + 1));
						from += 1;
					}
					else
					{
						break;
					}
				}
				
				// before and after the partial
				// note: however it is only necessary to add before or after since adding both would result in a duplicate
				// string. This because all possible parenthese positions for n-1 will have been generated, one of them being
				// strings with a single "()" on the left and strings with a single "()" on the right.
				result.add("()" + partial);
			}
		}
		
		return result;
	}
}
