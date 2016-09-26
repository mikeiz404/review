package problems.crackingthecodinginterview.problem1_1;

import javax.validation.constraints.NotNull;

public interface UniqueCheckerInterface
{
	/**
	 * Checks if a string contains all unique characters.
	 * @param string string to check for unique characters
	 * @return true if string contains all unique characters, false otherwise.
	 **/
	public boolean isAllUnique( @NotNull String string );
}
