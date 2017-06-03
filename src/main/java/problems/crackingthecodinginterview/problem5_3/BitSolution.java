package problems.crackingthecodinginterview.problem5_3;

import static com.google.common.base.Preconditions.checkArgument;

public class BitSolution
{
	public static int countOnes( int num, int to )
	{
		int count = 0;
		int pos = 0;
		while( num !=0 && pos <= to )
		{
			if( (num & 0b1) == 1 )
			{
				count++;
			}
			
			num = num >> 1;
			pos ++;
		}
		
		return count;
	}
	
	public static int findOneZero( int num )
	{
		int pos = 1;
		while( num !=0 )
		{
			if( (num & 0b11) == 0b10 )
			{
				return pos;
			}
			
			num = num >> 1;
			pos++;
		}
		
		return -1;
	}
	
	public static int findZeroOne( int num )
	{
		int pos = 1;
		while( num !=0 )
		{
			if( (num & 0b11) == 0b01 )
			{
				return pos;
			}
			
			num = num >> 1;
			pos++;
		}
		
		return -1;
	}
	
	public static Integer findLarger( int num )
	{
		checkArgument(num > 0, "num (%s) must be > 0.");
		
		// find first "01"
		int p = findZeroOne(num);
		
		// flip to "10"
		int flipped = num + (1 << (p - 1));
		
		// shift ones on the right of p all the way to the beginning by zeroing right and adding in correct number of 1s
		int count = countOnes(num, (p - 2));
		int zeroed = ((flipped >> (p - 1)) << (p - 1));
		return zeroed + ((1 << count) - 1);
	}
	
	public static Integer findSmaller( int num )
	{
		checkArgument(num > 1, "num (%s) must be > 0.");
		
		// find first occurrence of "10"
		int p = findOneZero(num);
		if( p == -1 )
		{ // string of 1's
			return null;
		}
		else
		{
    		// flip to "01"
    		return num - (1 << (p - 1));
		}
	}
}
