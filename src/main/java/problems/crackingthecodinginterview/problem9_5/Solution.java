package problems.crackingthecodinginterview.problem9_5;

import java.util.ArrayList;
import java.util.Collection;
import static com.google.common.base.Preconditions.checkNotNull;


public class Solution
{
	public static Collection<String> makePermutations( String str )
	{
		checkNotNull(str);
		
		Collection<String> results = new ArrayList<>();
		if( str.length() == 1 )
		{
			results = new ArrayList<>();
			results.add(str);
		}
		else if( str.length() > 1 )
		{
			char letter = str.charAt(0);
			for(String substr : makePermutations(str.substring(1)))
			{
				// insert letter into all possible positions
				for( int i=0; i<= substr.length(); i++ )
				{
					results.add(substr.substring(0, i) + letter + substr.substring(i));
				}
			}
		}
		
		return results;
	}
}
