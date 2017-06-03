package problems.crackingthecodinginterview.problem5_3;

import static com.google.common.base.Preconditions.checkArgument;

public class BruteSolution
{
	public static int countOnes( int num )
	{
		int count = 0;
		while( num !=0 )
		{
			if( (num & 0b1) == 1 )
			{
				count++;
			}
			
			num = num >> 1;
		}
		
		return count;
	}
	
	public static Integer findLarger( int num )
	{
		checkArgument(num > 0, "num (%s) must be > 0.");
		
		int count = countOnes(num);
		
		int nextNum = num + 1;
		while( countOnes(nextNum) != count )
		{
			nextNum++;
		}
		
		return nextNum;
	}
	
	public static Integer findSmaller( int num )
	{
		checkArgument(num > 1, "num (%s) must be > 0.");
		
		int count = countOnes(num);
		
		int nextNum = num - 1;
		while( countOnes(nextNum) != count )
		{
			nextNum--;
			
			if( nextNum == 0 ) return null;
		}
		
		return nextNum;
	}
}
