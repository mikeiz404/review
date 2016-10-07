package problems.crackingthecodinginterview.problem1_6;

import static com.google.common.base.Preconditions.checkNotNull;
import javax.validation.constraints.NotNull;
import static com.google.common.base.Preconditions.checkArgument;

public class MatrixRotater
{
	/**
	 * Rotates cells, starting with the "key cell" on the top edge of a square with side length sideLength at offset
	 * cells right from the top left corner clock wise. The other cell which would have been overwritten by the rotation
	 * is also rotated. In this way four rotations are made without any overwriting of cells.
	 * 
	 * Example rotation with side length 5 and offset 1:
	 *  x  A  x  x  x      x  D  x  x  x 
	 *  x  x  x  x  B      x  x  x  x  A 
	 *  x  x  x  x  x  =>  x  x  x  x  x 
	 *  D  x  x  x  x      C  x  x  x  x 
	 *  x  x  x  C  x      x  x  x  B  x 
	 *  
	 * @param matrix the matrix with cells to rotate.
	 * @param x top left row index of square.
	 * @param y top left y column of square.
	 * @param sideLength length of square side.
	 * @param offset offset of key cell from the right of the top left corner.
	 */
	private static <T> void rotateCellsCw( @NotNull T[][] matrix, @NotNull Point topLeft, int sideLength, int offset )
	{
		checkNotNull(matrix);
		checkNotNull(topLeft);
		checkArgument(offset < sideLength, "offset (%s) < sideLength (%s)", offset, sideLength);
		checkArgument( topLeft.getX() >= 0 && topLeft.getX() < matrix.length, "x (%s) >= 0 && x (%s) < matrix.length", topLeft.getX());
		checkArgument( topLeft.getY() >= 0 && topLeft.getY() < matrix.length, "y (%s) >= 0 && y (%s) < matrix.length", topLeft.getY());
		
		Point top = new Point((topLeft.getX() + offset), topLeft.getY());
		Point left = new Point(topLeft.getX(), (topLeft.getY() + sideLength - 1 - offset));
		Point bottom = new Point((topLeft.getX() + sideLength - 1 - offset), (topLeft.getY() + sideLength - 1));
		Point right = new Point((topLeft.getX() + sideLength - 1), (topLeft.getY() + offset));
		
		T temp = matrix[top.getY()][top.getX()];
		matrix[top.getY()][top.getX()] = matrix[left.getY()][left.getX()];
		matrix[left.getY()][left.getX()] = matrix[bottom.getY()][bottom.getX()];
		matrix[bottom.getY()][bottom.getX()] = matrix[right.getY()][right.getX()];
		matrix[right.getY()][right.getX()] = temp;
	}
	
	/**
	 * Rotates a NxN matrix clock wise by 90 degrees.
	 * 
	 * Approach: Rotate each cell clock wise on a square border of the NxN matrix working from the outside in. By
	 * rotating four cells at a time, only a constant amount of space is needed for the rotation.
	 * 
	 * Time: O(n) where n is the size in either dimension.
	 * Space: O(1).
	 * 
	 * @param matrix the matrix to rotate.
	 */
	public static <T> void rotateCw( @NotNull T[][] matrix )
	{
		checkNotNull(matrix);
		// check NxN
		for( T[] row : matrix )
		{
			checkArgument(row.length == matrix.length, "matrix must be NxN");
		}
		
		for( int xy=0; xy<(matrix.length / 2); xy++ )
		{
			Point topLeft = new Point(xy, xy);
			int sideLength = matrix.length - (2 * xy);
			
			for( int offset = 0; offset<(sideLength - 1); offset++ )
			{
				rotateCellsCw(matrix, topLeft, sideLength, offset);
			}
		}
	}
}
