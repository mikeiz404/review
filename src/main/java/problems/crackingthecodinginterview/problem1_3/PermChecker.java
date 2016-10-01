package problems.crackingthecodinginterview.problem1_3;

public interface PermChecker
{
	/**
	 * Checks if candidate can be generated from possible permutation of the characters in root.
	 * @param root string to check if candidate can be generated from.
	 * @param candidate string to check if root can be permuted into.
	 * @return true if the characters in root can be permuted into candidate, false otherwise.
	 */
	public boolean isPermutation( String root, String candidate );
}
