package problems.interviews.company2.position0.problem0;

public class Solution1
{
	private static void swap( Object[] data, int aIndex, int bIndex )
	{
		Object a = data[aIndex];
		data[aIndex] = data[bIndex];
		data[bIndex] = a;
	}
	
	/**
	 * Reverses an array in place.
	 */
	public static void reverse( Object[] data )
	{
		if( data == null )
		{
			throw new NullPointerException();
		}
		
		// swap start and end elements, working our way in
		// note: if the array size is odd the middle element will not be accessed but this is OK as it is already in the
		// correct position
		for( int i=0; i<data.length/2; i++ )
		{
			swap(data, i , (data.length - 1 - i));
		}
	}
}
