package problems.crackingthecodinginterview.problem5_2;

import static com.google.common.base.Preconditions.checkArgument;

public class BinaryPrinter
{
	public static final int BITS_MAX = 32;
	public static final String ERROR_STR = "ERROR";
	
	/**
	 * Approach: Shift decimal left, print 1's position, clear 1's position, repeat until max is reached or decimal becomes 0.
	 * @param dec decimal to print. Must be > 0 and < 1.
	 */
	public static String makeDecimalString( float dec )
	{
		checkArgument(dec > 0 && dec < 1, "dec (%s) must be > 0 and < 1.", dec);
		
		StringBuilder bits = new StringBuilder(BITS_MAX);
		while( dec != 0 )
		{
			// shift decimal over: 0b0.11 to 0b1.1
			dec *= 2;
			
			int bit;
			if( dec >= 1 )
			{
				bit = 1;
				
				// reset 1's position
				dec -= 1;
			}
			else // dec < 1
			{
				bit = 0;
			}

			bits.append(bit);
			
			if( bits.length() > BITS_MAX )
			{
				return ERROR_STR;
			}
		}
		
		return "0." + bits;
	}
	
	public void printDecimal( float dec )
	{
		System.out.println(makeDecimalString(dec));
	}
}
