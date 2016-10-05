package problems.crackingthecodinginterview.problem1_7;

import static com.google.common.base.Preconditions.checkNotNull;
import javax.validation.constraints.NotNull;
import java.util.HashSet;

public class RowColZeroer
{
	/**
	 * Sets the rows and columns to zero of any cell which is 0 in a two-dimensional array.
	 * 
	 * Approach: Two passes. First pass records all row and column indexes which contain cells with zeros in two sets.
	 * Second pass over the matrix zeros the cell if the row or column is in one of the respective zero index sets.
	 * 
	 * Time: O(2mn) => O(mn) where m is the row count and n is the column count.
	 * Space: O(m+n).
	 * 
	 * @param matrix the matrix to zero rows and columns in.
	 */
	public static void zero( @NotNull int[][] matrix )
	{
		checkNotNull(matrix);
		
		HashSet<Integer> zeroRows = new HashSet<>();
		HashSet<Integer> zeroCols = new HashSet<>();
		
		// check
		for( int rowIndex=0; rowIndex<matrix.length; rowIndex++ )
		{
			int[] row = matrix[rowIndex];
			
			for( int colIndex=0; colIndex<row.length; colIndex++ )
			{
				int cell = row[colIndex];
				
				if( cell == 0 )
				{
					zeroRows.add(rowIndex);
					zeroCols.add(colIndex);
				}
			}
		}
		
		// zero
		for( int rowIndex=0; rowIndex<matrix.length; rowIndex++ )
		{
			int[] row = matrix[rowIndex];
			
			for( int colIndex=0; colIndex<row.length; colIndex++ )
			{
				if( zeroRows.contains(rowIndex) || zeroCols.contains(colIndex) )
				{
					row[colIndex] = 0;
				}
			}
		}
	}
}
