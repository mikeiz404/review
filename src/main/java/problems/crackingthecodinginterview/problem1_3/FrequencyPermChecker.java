package problems.crackingthecodinginterview.problem1_3;

import java.util.Map;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import static com.google.common.base.Preconditions.checkNotNull;

public class FrequencyPermChecker implements PermChecker
{
	private static @NotNull Map<Character, Integer> makeCharFrequencyMap( @NotNull String string )
	{
		checkNotNull(string);
		
		HashMap<Character, Integer> charFrequencies = new HashMap<>();
		
		for( char c : string.toCharArray() )
		{
			Integer frequency = charFrequencies.getOrDefault(c, 0);
			
			charFrequencies.put(c, (frequency + 1));
		}
		
		return checkNotNull(charFrequencies);
	}
	
	/**
	 * @see #isPermutation(String, String).
	 * 
	 * Approach: Create character frequency maps for root and candidate, and check if they are equal.
	 * Note: Could be slightly more efficient by creating one frequency map for root and decrementing the frequency
	 * counts as candidate is being checked. This allows for one map to be stored and the ability to potentially end the
	 * check early. However there is a cost to simplicity, readability, and code reuse.
	 * 
	 * Time: O(2n) => O(n) where n is the number of characters in root.
	 * Space: O(2n) => O(n).
	 */
	@Override
	public boolean isPermutation( @NotNull String root, @NotNull String candidate )
	{
		checkNotNull(root);
		checkNotNull(candidate);
		
		if( root.length() == candidate.length() )
		{
			Map<Character, Integer> rootFreqMap = makeCharFrequencyMap(root);
			Map<Character, Integer> candidateFreqMap = makeCharFrequencyMap(candidate);
			
			return rootFreqMap.equals(candidateFreqMap);
		}
		else // root.length() != candidate.length()
		{
			return false;
		}
	}
}
