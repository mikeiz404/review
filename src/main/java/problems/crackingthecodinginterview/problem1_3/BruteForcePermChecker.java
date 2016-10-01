package problems.crackingthecodinginterview.problem1_3;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import static com.google.common.base.Preconditions.checkNotNull;

public class BruteForcePermChecker implements PermChecker
{
	/**
	 * Creates a set of all possible permutations of the characters in the string root.
	 * @param root string to generate permutations of.
	 * @return set of permutations of root.
	 * 
	 * Time: O(((n-1)! * n) + ((n-2)! * (n-1)) + ... + (2 * 1) + 1 ) => O(n * ((n-1)! * n)) => O((n^2) * n!) => O(n!)
	 * where n is the number of characters in root.
	 * Space: T(n) = n * T(n-1) => O(n * (n - 1) * ... * 1) => O(n!).
	 */
	private static @NotNull Set<String> makePermutations( @NotNull String root )
	{
		checkNotNull(root);
		
		HashSet<String> permutations = new HashSet<>();
		
		if( root.length() == 0 || root.length() == 1 )
		{ // base: identity
			permutations.add(root);
		}
		else // root.length() > 1
		{ // recursive
			char letter = root.charAt(0);
			String subRoot = root.substring(1, root.length());
			Set<String> subPermutations = makePermutations(subRoot);
			
			for( String subPermutation : subPermutations )
			{
				for( int i=0; i<(subPermutation.length() + 1); i++ )
				{
					String permutation = subPermutation.substring(0, i) +
					                     letter +
					                     subPermutation.substring(i, subPermutation.length());
					permutations.add(permutation);
				}
			}
		}
		
		return checkNotNull(permutations);
	}

	/**
	 * @see #check(String, String).
	 * 
	 * Approach: Create all possible permutations of root and check if candidate is one of them.
	 * 
	 * Time: O(n!) where n is the number of characters in root.
	 * Space: O(n!).
	 */
	@Override
	public boolean isPermutation( @NotNull String root, @NotNull String candidate )
	{
		checkNotNull(root);
		checkNotNull(candidate);
		
		if( candidate.length() != root.length() )
		{ // size mismatch
			return false;
		}
		else // candidate.length() == root.length()
		{ // possible
			Set<String> permutations = makePermutations(root);
			return permutations.contains(candidate);
		}
	}
}
